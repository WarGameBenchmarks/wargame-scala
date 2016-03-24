import Card._
import Deck._

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor._
import akka.event.Logging
import akka.util.Timeout

class Benchmark {

  sealed trait MessageBase

  case object Stop

  case class Progress(amount: Int)

  class GameActor extends Actor {
    val game = new WarGame()
    var running: Boolean = true


    def receive = {
      case "start" => {
        println("game actor: started")
        while (running) {
          game.play()
          sender ! "progress"
        }
      }
      case "stop" => {
        println("game actor: stopping")
        running = false
        context.stop(self)
      }
      case _       => {
        println("game actor: error")
        sender ! "error"
      }
    }
  }

  class Master(actors: Array[ActorRef]) extends Actor {

    var total:Int = 0
    var stopped:Int = 0

    def receive = {
      case "start" => {
        println("started")
        for (actor <- actors) {
          actor ! "start"
        }
      }
      case "progress" => {
          total += 1
          println("progress: " + total)

          if (total > 2000) {
            for (actor <- actors) {
              actor ! "stop"
            }
            println("stopping")
          }
      }
      case "stop" => {
          stopped += 1
          if (stopped >= actors.length) {
            context.stop(self)
          }
      }
      case "error" => {
        println("error")
        for (actor <- actors) {
          actor ! "stop"
        }
      }
      case  _     => println("unknown")
    }
  }

  def benchmark() {
    val system = ActorSystem("BenchmarkSystem")

    val count:Int = 2
    val actors = new Array[ActorRef](count)

    for (i <- 0 until count) {
      val actor = system.actorOf(Props(new GameActor), name = "GameActor" + i)
      actors(i) = actor
    }

    val master = system.actorOf(Props(new Master(actors)), name = "Master")

    master ! "start"

    // system.shutdown
  }


}
