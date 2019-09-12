package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("YOU FAILED"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  //the Try function will catch the exception and wrap it in a Failure object
  val potentialFailure = Try(unsafeMethod())

  println(potentialFailure) // Failure(java.lang.RuntimeException: NO STRING FOR YOU)

  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) //false

  // orElse
  def backupMethod(): String = "Valid Result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry) //Success(Valid Result)

  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("Valid Result")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  //map, flatMpa, filter
  println(aSuccess.map(_*2)) //Success(6)
  println(aSuccess.flatMap(x => Success(x*10))) //Success(30)
  println(aSuccess.filter(_>10)) //Failure(java.util.NoSuchElementException: Predicate does not hold for 3)
  // can also do for comprehensions

  /*
  Exercise
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String): Unit = println(page)

  class Connection{
    def get(url: String): String = {
      val r = new Random(System.nanoTime())
      if(r.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }
  object HttpService{
    val r = new Random(System.nanoTime())
    def getConnection(host:String, port: String): Connection = {
      if(r.nextBoolean()) new Connection
      else throw new RuntimeException("Service not available")
    }
    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }
  println("step-by-step")
  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  println("flatMap/foreach")
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  println("for comprehension")
  for{
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)


}
