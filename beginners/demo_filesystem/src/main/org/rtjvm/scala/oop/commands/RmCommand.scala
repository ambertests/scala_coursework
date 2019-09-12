package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State

class RmCommand(name: String) extends Command {
  override def apply(state: State): State = {

    val absolutePath = {
      if(name.startsWith(Directory.SEPARATOR)) name
      else if(state.wd.isRoot) state.wd.path + name
      else state.wd.path + Directory.SEPARATOR + name
    }

    if(absolutePath.equals(Directory.ROOT_PATH)) state.setMessage("removing root not supported")
    else doRm(state, absolutePath)
  }
  def doRm(state: State, path: String): State = {

    def rmHelper(current: Directory, path: List[String]): Directory = {
      if(path.isEmpty) current
      else if (path.tail.isEmpty) current.removeEntry(path.head)
      else{
        val next = current.findEntry(path.head)
        if(next.isEmpty || !next.get.isDirectory) current
        else {
          val newNext = rmHelper(next.get.asDirectory, path.tail)
          if(newNext == next.get) current
          else current.replaceEntry(path.head, newNext)
        }
      }
    }
    val tokens = path.substring(1).split(Directory.SEPARATOR).toList

    val newRoot: Directory = rmHelper(state.root, tokens)
    if(newRoot == state.root) state.setMessage(path + ": no such file or directory")
    else State(newRoot, newRoot.findDescendant(state.wd.path.substring(1)))


  }
}
