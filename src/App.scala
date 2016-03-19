import Cards._

object App {
  def main(args: Array[String]): Unit = {

    println("WarGame!")

    val card1 = new Card(CardValue.Six, CardSuit.Hearts)
    val card2 = new Card(CardValue.Jack, CardSuit.Diamonds)

    println(card1)
    println(card2)

    val deck1 = new Deck()
    val deck2 = Deck.fresh()
    println(deck1.size())
    println(deck2.size())

  }
}
