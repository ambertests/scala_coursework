package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.{DirEntry, Directory}
import org.rtjvm.scala.oop.filesystem.State

abstract class CreateEntryCommand(entryName: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hasEntry(entryName)){
      state.setMessage(s"Entry $entryName already exists")
    } else if(entryName.contains(Directory.SEPARATOR)){
      state.setMessage(s"$entryName must not contain separators")
    } else if (checkIllegal(entryName)){
      state.setMessage(s"$entryName: illegal entry name")
    } else {
      create(state)
    }
  }
  def checkIllegal(name: String): Boolean = {
    name.contains(".") || name.trim().isEmpty
  }

  def create(state: State): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if(path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).get.asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val wd = state.wd

    // 1. get all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in working directory
    val newEntry: DirEntry = createSpecificEntry(state)
//    val newDir = Directory.empty(wd.path, name)

    // 3. update the whole directory structure starting from the root
    // NOTE: the directory structure is IMMUTABLE, so cannot be changed
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // 4. get the new working directory instance from the full path in the new directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry

}
