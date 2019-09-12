package org.rtjvm.scala.oop.commands
import org.rtjvm.scala.oop.files.{Directory, File}
import org.rtjvm.scala.oop.filesystem.State

import scala.annotation.tailrec

class EchoCommand(args: List[String]) extends Command {
  override def apply(state: State): State = {
    if(args.isEmpty) state
    else if(args.length.equals(1)) state.setMessage(args(0) + "\n")
    else {
      val operator = args(args.length - 2)
      val filename = args(args.length - 1)
      val contents = createContent(args, args.length - 2)

      if(">>".equals(operator)) doEcho(state, contents, filename, append = true)
      else if(">".equals(operator)) doEcho(state, contents, filename, append = false)
      else state.setMessage(createContent(args, args.length))
    }
  }

  //topIndex NON-INCLUSIVE
  def createContent(args: List[String], topIndex: Int): String = {

    @tailrec
    def createContentHelper(currentIndex: Int, accumulator: String): String = {
      if(currentIndex >= topIndex) accumulator
      else createContentHelper(currentIndex + 1, accumulator + " " + args(currentIndex))
    }
    createContentHelper(0, "") + "\n"
  }

  def getRootAfterEcho(current: Directory, path: List[String], contents: String, append: Boolean): Directory = {

    if(path.isEmpty) current
    else if (path.tail.isEmpty){
      val dirEntry = current.findEntry(path.head)
      if(dirEntry.isEmpty) current.addEntry(new File(current.path, path.head, contents))
      else if (!dirEntry.get.isFile) current
      else {
        if(append) current.replaceEntry(path.head, dirEntry.get.asFile.appendContents(contents))
        else current.replaceEntry(path.head, dirEntry.get.asFile.setContents(contents))
      }
    } else{
      val nextDir = current.findEntry(path.head).get.asDirectory
      val newNextDir = getRootAfterEcho(nextDir, path.tail, contents, append)
      if(newNextDir == nextDir) current
      else current.replaceEntry(path.head, newNextDir)
    }
  }
  def doEcho(state: State, contents: String, filename: String, append: Boolean): State = {
    if(filename.contains(Directory.SEPARATOR)) state.setMessage("Echo: filename must not contain separators")
    else{
      val newRoot: Directory = getRootAfterEcho(state.root, state.wd.getAllFoldersInPath :+ filename, contents, append)
      if(newRoot == state.root) state.setMessage(s"Echo: File $filename invalid")
      else{
        State(newRoot, newRoot.findDescendant(state.wd.getAllFoldersInPath))
      }
    }
  }
}


