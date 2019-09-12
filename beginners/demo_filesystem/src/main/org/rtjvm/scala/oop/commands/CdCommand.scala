package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.{DirEntry, Directory}
import org.rtjvm.scala.oop.filesystem.State

import scala.annotation.tailrec

class CdCommand(dir: String) extends Command {

  override def apply(state: State): State = {

    // 1. find root
    val root = state.root
    val wd = state.wd

    // 2. find absolute path of target directory
    val absolutePath = {
      if(dir.startsWith(Directory.SEPARATOR)) dir
      else if(wd.isRoot) wd.path + dir
      else wd.path + Directory.SEPARATOR + dir
    }

    // 3. use the path find the target directory
    val target = doFindEntry(root, absolutePath)

    // 4. change the state given the new working directory
    if(target == null) state.setMessage(s"$absolutePath not found")
    else if(!target.isDirectory) state.setMessage(s"$absolutePath is not a directory")
    else State(state.root, target.asDirectory, target.asDirectory.name)
  }

  def doFindEntry(directory: Directory, path: String): DirEntry = {
    @tailrec
    def findEntryHelper(current: Directory, path: List[String]): DirEntry = {
      if(path.isEmpty || path.head.isEmpty) current
      else if (path.tail.isEmpty) {
        val entry = current.findEntry(path.head)
        if(entry.isEmpty) null
        else entry.get
      }
      else {
        val nextDir = current.findEntry(path.head)
        if(nextDir.isEmpty || !nextDir.get.isDirectory) null
        else findEntryHelper(nextDir.get.asDirectory, path.tail)
      }
    }

    @tailrec
    def collapseRelativeTokens(path: List[String], result: List[String]): List[String] = {
      if(path.isEmpty) result
      else if (".".equals(path.head)) collapseRelativeTokens(path.tail, result)
      else if ("..".equals(path.head)){
        if(result.isEmpty) null
        else collapseRelativeTokens(path.tail, result.init)
      } else collapseRelativeTokens(path.tail, result :+ path.head)
    }

    val tokens: List[String] = path.substring(1).split(Directory.SEPARATOR).toList
    val noDots = collapseRelativeTokens(tokens, List())
    if(noDots == null) null
    else findEntryHelper(directory, noDots)
  }
}
