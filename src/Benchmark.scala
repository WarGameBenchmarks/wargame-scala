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
        /**
         * This causes an infinite loop.
         * Weep with me.
         */
        while (running) {
          game.play()
          sender ! "progress"
        }
      }
      case "stop" => {
        println("game actor: stopping")
        /**
         * `running` = false should stop
         * the loop above from running, but it does not.
         * Plus, it should end itself.
         */
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
        /**
         * Start each GameActor
         */
        for (actor <- actors) {
          actor ! "start"
        }
      }
      case "progress" => {
        /**
         * When a GameActor makes progress,
         * accumulate the progress here
         */
        total += 1
        println("progress: " + total)

        /**
         * This should trigger an end point during testing.
         * It's disgusting.
         *
         * In theory, this should call `stop` on
         * each GameActor but for some reason, it never triggers.
         */
        if (total > 2000) {
          for (actor <- actors) {
            actor ! "stop"
          }
          println("stopping")
        }
      }
      case "stop" => {
        /**
         * Each GameActor should respond that it stopped.
         * And this `Master` should handle
         * its own shutdown when all of the games are done.
         */
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

    // pretend for now to use a 2-thread system
    val count:Int = 2
    val actors = new Array[ActorRef](count)

    // make N actors
    for (i <- 0 until count) {
      val actor = system.actorOf(Props(new GameActor), name = "GameActor" + i)
      actors(i) = actor
    }

    // make the Master actor (or main thread?) and pass it the
    // thread game actors
    val master = system.actorOf(Props(new Master(actors)), name = "Master")

    master ! "start"

    // system.shutdown
  }


}
