package exercises.part3fp

//object SocialNetwork extends App {
////  /*
////  1. what would happen in the lowercase map if orig had "Jim" and "JIM"
////  2. simple social network based on maps
////      Person = String
////      Map Person to list of friends (Persons)
////      - add a person to the network
////      - remove a person from the network
////      - add friend
////      - remove friend
////      - friend count per person
////      - person with most friends
////      - people with no friends
////      - are there friends in common
////   */
////
////}


case class SocialNetwork(people:Map[String, Set[String]] = Map()){
  def addPerson(name: String, friends: Set[String] = Set()):SocialNetwork = {
    SocialNetwork(people + (name -> friends))
  }
  def removePerson(name: String): SocialNetwork = {
    @scala.annotation.tailrec
    def removeHelper(friends: Set[String], network: SocialNetwork): SocialNetwork = {
      if(friends.isEmpty) network
      else removeHelper(friends.tail, network.removeFriend(name, friends.head))
    }
    if(!people.contains(name)) this
    else {
      val unfriended = removeHelper(friendsOf(name), this)
      SocialNetwork(unfriended.people - name)
    }
  }
  def addFriend(name: String, friend: String): SocialNetwork = {
    if(!people.contains(friend)) addPerson(friend)
    SocialNetwork(people + (name -> (friendsOf(name) + friend)) + (friend -> (friendsOf(friend) + name)))
  }
  def removeFriend(name: String, friend: String): SocialNetwork = {
    SocialNetwork(people + (name -> (friendsOf(name) - friend)) + (friend -> (friendsOf(friend) - name)))
  }
  def numFriends(name: String): Int = {
    friendsOf(name).size
  }
  def friendCountPerPerson: Map[String, Int] = {
    people.map[String, Int](pair => pair._1 -> pair._2.size)
  }
  def peopleWithNoFriends: Set[String] = {
    people.filter(pair => pair._2.isEmpty).keys.toSet
  }
  def personWithMostFriends: String = {
    people.maxBy(pair => pair._2.size)._1
  }
  def friendsOf(name: String): Set[String] = {
    if(!people.contains(name)) Set()
    else people(name)
  }
  def hasConnection(a: String, b: String): Boolean = {
    @scala.annotation.tailrec
    def bfs(target: String, considered: Set[String], discovered: Set[String]): Boolean ={
      if(discovered.isEmpty) false
      else {
        val person = discovered.head
        if(person == target) true
        else if(considered.contains(person))
          bfs(target, considered, discovered.tail)
        else
          bfs(target, considered + person, discovered.tail ++ friendsOf(person))
      }
    }
    bfs(b, Set(), friendsOf(a) + a)
  }

}



