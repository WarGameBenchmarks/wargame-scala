/**
 * In Scala, the ArrayList-like structure is the ArrayBuffer.
 * This could change if it has to.
 */
import scala.collection.mutable.ArrayBuffer
import Card._

class Deck(c: ArrayBuffer[Card]) {
  var cards: ArrayBuffer[Card] = c

  def this() {
    this(ArrayBuffer())
  }

  override def toString():String = {

    return "[Deck: " + size + "]"
  }

  // Add a card
  def addCard(c: Card) {
    println(c)
    cards += c
  }

  // How many cards are in the deck?
  def size():Int = {
    return cards.size
  }

  // Does this deck have cards?
  def hasCards():Boolean = {
    return size() > 0
  }

  // Get a card frop the top of the deck
  // Note: this is wrapped in an Option
  def getCard():Option[Card] = {
    return cards.lift(0)
  }

  // Give a card from this deck to the given deck
  def giveCard(that: Deck) {
    getCard() match {
      case Some(card) => {
        var c = cards.remove(0)
        that.addCard(card)}
      case None => {} // nothing
    }
  }

  // Given all the cards from this deck to the given deck
  def giveCards(that: Deck) {
    val s = size
    for (i <- 1 to s) {
      giveCard(that)
    }
  }

  // Shuffle this array
  def shuffle() {
    cards = util.Random.shuffle(cards)
  }

  // Split the current deck into two decks
  def split():(Deck, Deck) = {
    val s = size()
    val h = s / 2
    val d1 = new Deck()
    val d2 = new Deck()
    for (i <- 0 until s) {
      val card = cards(i)
        if (i < h) {
          d1.addCard(card)
        } else {
          d2.addCard(card)
        }
    }
    return (d1, d2)
  }

}

object Deck {
  /**
   * Generate a fresh deck with the 52 card, 4 suit of 13 cards each setup.
   * @return {Deck} return a deck of 52 cards
   */
  def fresh(): Deck = {
    val deck = new Deck()
    /**
     * The following lists will generate the 52-normal deck.
     *
     * The CardSuit/CardValue prefix here is quite busy.
     */
    val suits = List(
                  CardSuit.Hearts,
                  CardSuit.Diamonds,
                  CardSuit.Clubs,
                  CardSuit.Spades)
    val values = List(
                  CardValue.Two, CardValue.Three, CardValue.Four,
                  CardValue.Five, CardValue.Six, CardValue.Seven,
                  CardValue.Eight, CardValue.Nine, CardValue.Ten,
                  CardValue.Jack, CardValue.Queen, CardValue.King,
                  CardValue.Ace)
    for (s <- suits) {
      for (v <- values) {
        deck.addCard(new Card(v, s))
      }
    }
    return deck
  }
}
