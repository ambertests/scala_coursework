package exercises.part2oop.inheritence

abstract class MyList[+A]{
  /*
  singly linked list containing objects of type A
  head = first element of list
  tail = remainder of list
  isEmpty: Boolean
  add: Int => returns new list with element added
  toString => string representation of list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def contains[B >: A](element: B): Boolean
  def add[B >: A](element: B): MyList[B]
  ///
  /// These signatures for these methods ensure for comprehensions will work
  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: (A) => Boolean): MyList[A]
  ///
  ///
  def ++[B >: A](list: MyList[B]): MyList[B]
  protected def printList: String
  override def toString: String = "[" + printList + "]"

  //HOFs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A,A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A,B)=>C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B


}
// two different implementations!
case object EmptyList extends MyList[Nothing]{ // <-- Nothing is a subtype of everything, including Null
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def contains[B >: Nothing](element: B): Boolean = false
  def add[B >: Nothing](element: B): MyList[B] = new LinkedList(element, this)
  override protected def printList: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = EmptyList
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = EmptyList
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = EmptyList
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()
  def sort(comparable: (Nothing, Nothing) => Int): EmptyList.type = EmptyList

  override def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else EmptyList
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class LinkedList[+A](h: A, t: MyList[A] = EmptyList) extends MyList[A]{
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new LinkedList(element, this)
  def contains[B >: A](element: B): Boolean = {
    if(element == h) true
    else tail.contains(element)
  }

  @scala.annotation.tailrec
  private def stringify[C](l: MyList[C], str: String): String ={
    if(l.isEmpty) str.trim
    else stringify(l.tail, s"${l.head} $str")
  }

  override protected def printList: String = {
    stringify(this, "")
  }

  override def map[B](transformer: A => B): MyList[B] = {
    LinkedList(transformer(h), t.map(transformer))
  }

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  override def filter(predicate: A => Boolean): MyList[A] = {
    if(predicate(h)) LinkedList(h, t.filter(predicate))
    else t.filter(predicate)
  }
  override def ++[B >: A](list: MyList[B]): MyList[B] = {
    LinkedList(h, t ++ list)
  }

  override def foreach(f: A => Unit): Unit = {
    f(h)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if(sortedList.isEmpty) new LinkedList[A](x, EmptyList)
      else if(compare(sortedList.head, head) <= 0) new LinkedList[A](x, sortedList)
      else new LinkedList[A](sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else new LinkedList[C](zip(h, list.head), tail.zipWith(list.tail, zip))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}


