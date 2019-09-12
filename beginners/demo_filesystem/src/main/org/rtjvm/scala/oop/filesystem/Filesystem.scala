package org.rtjvm.scala.oop.filesystem

import java.util.Scanner

import org.rtjvm.scala.oop.commands.Command
import org.rtjvm.scala.oop.files.Directory

object Filesystem extends App {


  val root = Directory.ROOT

  // [1,2,3,4] --> how to get a sum?
  // List(1,2,3,4).foldLeft(0)((x,y) => x + y)
  /*
  0 (op) 1 => 1
  1 (op) 2 => 3
  3 (op) 3 => 6
  6 (op) 4 => 10 <-- final sum
   */
  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
    currentState.show()
    Command.from(newLine).apply(currentState)
  })

//  val scanner = new Scanner(System.in)
//  var state = State(root, root) //var because we need to be able to change the state
//
//  while(true) {
//    state.show()
//    val input = scanner.nextLine()
//    state = Command.from(input).apply(state)
//  }
}
