package exercises.part2oop.oobasics

class Writer(val firstName: String, val surname: String, val yearOfBirth: Int){
  def fullName(): String = s"$firstName $surname"

  override def equals(obj: Any): Boolean = {
    if (!obj.isInstanceOf[Writer]) false
    else {
      val a = obj.asInstanceOf[Writer]
      a.yearOfBirth == this.yearOfBirth &&
        a.surname.equalsIgnoreCase(this.surname) &&
        a.firstName.equalsIgnoreCase(this.firstName)
    }
  }
}
