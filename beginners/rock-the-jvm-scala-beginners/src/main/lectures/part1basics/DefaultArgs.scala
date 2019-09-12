package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def factorial(x: Int, accumulator: BigInt=1): BigInt = {
    if(x <= 1) accumulator
    else factorial(x-1, x*accumulator)
  }

  println(factorial(10, 1)) // 2nd param will almost always be 1 to start
  println(factorial(10)) //2nd param has default which is used for cleaner signature

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit =
    println(width + "x" + height + " " + format )

  //default parameters handled similar to python
  savePicture()
  savePicture("bmp")
  savePicture(width=800)
  savePicture(height = 20, width = 40, format = "png")

}