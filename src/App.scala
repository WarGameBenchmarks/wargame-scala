import Card._
import Deck._

object App {
  def main(args: Array[String]): Unit = {

    val game = new WarGame()

    game.play()

    val benchmark = new Benchmark()

    benchmark.benchmark

  }
}
