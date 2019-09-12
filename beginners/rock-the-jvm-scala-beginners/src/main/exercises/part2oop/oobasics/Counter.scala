package exercises.part2oop.oobasics

class Counter(val current: Int = 0){
  def increment(amount: Int): Counter = {
    if(amount <= 0) this
    else increment.increment(amount - 1)
  }
  def increment: Counter = {
    println("incrementing")
    new Counter(current + 1)
  }
  def decrement(amount: Int): Counter = {
    if(amount <= 0) this
    else decrement.decrement(amount - 1)
  }
  def decrement: Counter = {
    println("decrementing")
    new Counter(current - 1)
  }
}
