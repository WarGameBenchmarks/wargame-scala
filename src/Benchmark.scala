import Card._
import Deck._

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor._


class Benchmark {

  val MS = 1000000
  val NS = 1000000000

  case object PingMessage
  case object PongMessage
  case object StartMessage
  case object StopMessage

  class Ping(pong: ActorRef, c: Int) extends Actor {
    var count = c
    def processAndPrint { count = count - 1; println("ping: " + count) }
    def receive = {
      case StartMessage =>
          processAndPrint
          pong ! PingMessage
      case PongMessage =>
          processAndPrint
          if (count < 5) {
            sender ! StopMessage
            context.stop(self)
            println("ping stopped")
          } else {
            sender ! PingMessage
          }
    }
  }

  class Pong() extends Actor {
  def receive = {
    case PingMessage =>
        println("  pong")
        sender ! PongMessage
    case StopMessage =>
        context.stop(self)
        println("pong stopped")
  }
}


  def benchmark() {
    val system = ActorSystem("PingPongSystem")
    val pong = system.actorOf(Props(new Pong), name = "pong")
    val ping = system.actorOf(Props(new Ping(pong, 10000)), name = "ping")
    // start them going
    ping ! StartMessage
    println("???")
  }


}
