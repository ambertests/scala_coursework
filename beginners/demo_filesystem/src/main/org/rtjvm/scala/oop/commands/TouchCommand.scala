package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.{DirEntry, File}
import org.rtjvm.scala.oop.filesystem.State

class TouchCommand(name: String) extends CreateEntryCommand(entryName = name) {

  def createSpecificEntry(state: State): DirEntry = File.empty(state.wd.path, name)

}
