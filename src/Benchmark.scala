import Card._
import Deck._


class Benchmark {

  val MS = 1000000
  val NS = 1000000000

  def benchmark() {

    for (i <- 1 to 100) {
      val thread = new Thread {
        override def run {
          val game = new WarGame()
          game.play()
          print("-")
        }
      }
      thread.start

    }

  }


}
