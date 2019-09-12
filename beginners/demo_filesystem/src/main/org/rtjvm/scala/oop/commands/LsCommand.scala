package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.DirEntry
import org.rtjvm.scala.oop.filesystem.State

class LsCommand extends Command {

  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    State(state.root, state.wd, niceOutput)
  }

  def createNiceOutput(contents: List[DirEntry]): String = {
    if(contents.isEmpty) ""
    else{
      val entry = contents.head
      entry.name + " [" + entry.getClass.getSimpleName + "]\n" + createNiceOutput(contents.tail)
    }
  }
}
