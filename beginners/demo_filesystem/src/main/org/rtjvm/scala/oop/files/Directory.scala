package org.rtjvm.scala.oop.files

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry] = List())
  extends DirEntry(parentPath, name) {

  def asDirectory: Directory = this

  def asFile: File = throw new RuntimeException("A directory cannot be a file")

  def isDirectory: Boolean = true

  def isFile: Boolean = false

  def isRoot: Boolean = parentPath.isEmpty

  def hasEntry(name: String): Boolean = contents.exists(_.name.equals(name))

  def getAllFoldersInPath: List[String] = {
    // /a/b/c/d => List["a", "b", "c", "d"]
    path.substring(1).split(Directory.SEPARATOR).toList.filter(s => s.nonEmpty)
  }

  def findDescendant(relativePath: String): Directory =
    if(relativePath.isEmpty) this
    else findDescendant(relativePath.split(Directory.SEPARATOR).toList)

  def findDescendant(relativePath: List[String]): Directory = {
    if(relativePath.isEmpty) this
    else findEntry(relativePath.head).get.asInstanceOf[Directory].findDescendant(relativePath.tail)
  }

  def addEntry(newEntry: DirEntry): Directory = new Directory(parentPath, name, contents :+ newEntry)

  def findEntry(entryName: String): Option[DirEntry] =
    contents.find(_.name.equals(entryName))

  def replaceEntry(oldEntryName: String, newEntry: DirEntry): Directory = {
    if(!hasEntry(oldEntryName)) throw new NoSuchElementException(s"entry $oldEntryName cannot be replaced because it doesn't exist")
    else new Directory(parentPath, name, contents.filter(_.name.ne(oldEntryName)) :+ newEntry)
  }

  def removeEntry(entryName: String): Directory =
    if(!hasEntry(entryName)) this
    else {
      val dir = new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)))
      dir
    }
}

object Directory {
  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name)

}