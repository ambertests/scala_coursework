package lectures.part3fp

import scala.util.Random

object Options extends App {
  val emptyList = List()
  println(emptyList.headOption)
  val list = List(1,2,3)
  println(emptyList.headOption.getOrElse(-1))
  println(list.headOption.getOrElse(-1))
  /*
  Exercise
   */
  val config: Map[String, String] = Map(
    //can't be certain there are values for host and port
    "host" -> "12.23.34.5",
    "port" -> "80"
  )
  class Connection {
    def connect = "connected"
  }
  object Connection{
    val rand = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if(host == null || port == null) None
      else if(rand.nextBoolean()) Some(new Connection)
      else None
    }
  }

  def getConnection(cfg: Map[String, String]): Option[String] = {
//    cfg.get("host").flatMap(h => cfg.get("port")
//      .flatMap(p => Connection(h, p)))
//      .map(c => c.connect).getOrElse("cannot connect")
    for{
      host <- cfg.get("host")
      port <- cfg.get("port")
      conn <- Connection(host, port)
    } yield conn.connect
  }

  (1 to 10).foreach(x => println(getConnection(config).getOrElse("cannot connect")))
  println(getConnection(Map("host"-> null, "port" -> "8080")))
  println(getConnection(Map("host"-> "a.b.c.d", "port" -> null)))


}
