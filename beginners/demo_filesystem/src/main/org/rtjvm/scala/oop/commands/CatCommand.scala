package org.rtjvm.scala.oop.commands
import org.rtjvm.scala.oop.filesystem.State

class CatCommand(filename: String) extends Command {
  override def apply(state: State): State = {
    val entry = state.wd.findEntry(filename)
    if(entry.isEmpty) state.setMessage(s"cat: $filename does not exist")
    else if(entry.get.isDirectory) state.setMessage(s"cat: $filename is a directory")
    else state.setMessage(entry.get.asFile.contents)
  }
}
