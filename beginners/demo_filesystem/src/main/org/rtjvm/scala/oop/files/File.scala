package org.rtjvm.scala.oop.files


class File(override val parentPath: String, override val name: String, val contents: String) extends DirEntry(parentPath, name) {
  def asDirectory: Directory = throw new RuntimeException("A file cannot be a directory")

  def isDirectory: Boolean = false

  def isFile: Boolean = true

  def asFile: File = this

  def setContents(newContent: String) = new File(parentPath, name, newContent)

  def appendContents(newContent: String) = new File(parentPath, name, contents + "\n" + newContent)
}

object File {
  def empty(parentPath: String, name: String): File = {
    new File(parentPath, name, "")
  }
}
