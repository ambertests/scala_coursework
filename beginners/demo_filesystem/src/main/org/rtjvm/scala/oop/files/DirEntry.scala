package org.rtjvm.scala.oop.files

abstract class DirEntry(val parentPath: String, val name: String) {

  def path: String = {
    val sepIfNecessary =
      if(Directory.ROOT_PATH.equals(parentPath)) ""
      else Directory.SEPARATOR
    parentPath +sepIfNecessary+ name
  }
  def asDirectory: Directory
  def asFile: File
  def isDirectory: Boolean
  def isFile: Boolean
}
