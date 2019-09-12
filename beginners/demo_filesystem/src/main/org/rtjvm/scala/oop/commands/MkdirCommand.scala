package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.{DirEntry, Directory}
import org.rtjvm.scala.oop.filesystem.State

class MkdirCommand(name: String) extends CreateEntryCommand(entryName = name) {

  def createSpecificEntry(state: State): DirEntry = Directory.empty(state.wd.path, name)

}
