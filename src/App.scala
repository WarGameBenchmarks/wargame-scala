import Card._
import Deck._

object App {
  def main(args: Array[String]): Unit = {

    val winner = new Deck()
    val original = Deck.fresh()
    original.shuffle()
    println("----")
    println(original)
    println("----")
    val (deck1, deck2) = original.split()
    println("----")
    println("----")
    println("----")

  }
}
