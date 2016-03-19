object Cards {

  object CardValue extends Enumeration {
    type CardValue = Value
    val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
        Jack, Queen, King, Ace = Value
  }
  import CardValue._

  object CardSuit extends Enumeration {
    type CardSuit = Value
    val Hearts, Diamonds, Spades, Clubs = Value
  }
  import CardSuit._

  def valueToString(v: CardValue): String = v match {
    case CardValue.Two => "Two"
    case CardValue.Three => "Three"
    case CardValue.Four => "Four"
    case CardValue.Five => "Five"
    case CardValue.Six => "Six"
    case CardValue.Seven => "Seven"
    case CardValue.Eight => "Eight"
    case CardValue.Nine => "Nine"
    case CardValue.Ten => "Ten"
    case CardValue.Jack => "Jack"
    case CardValue.Queen => "Queen"
    case CardValue.King => "King"
    case CardValue.Ace => "Ace"
    case _ => "?"
  }

  def valueToInt(v: CardValue): Int = v match {
    case CardValue.Two => 2
    case CardValue.Three => 3
    case CardValue.Four => 4
    case CardValue.Five => 5
    case CardValue.Six => 6
    case CardValue.Seven => 7
    case CardValue.Eight => 8
    case CardValue.Nine => 9
    case CardValue.Ten => 10
    case CardValue.Jack => 11
    case CardValue.Queen => 12
    case CardValue.King => 13
    case CardValue.Ace => 14
    case _ => 0
  }

  def suitToString(s: CardSuit): String = s match {
    case CardSuit.Hearts => "Hearts"
    case CardSuit.Spades => "Spades"
    case CardSuit.Diamonds => "Diamonds"
    case CardSuit.Clubs => "Clubs"
    case _ => "?"
  }

  class Card(v: CardValue, s: CardSuit) extends Ordered[Card] {
    var value = v
    var suit = s

    override def toString = valueToString(value) + " of " + suitToString(suit)

    def compare(that: Card) = valueToInt(value) - valueToInt(that.value)
  }

  import scala.collection.mutable.ArrayBuffer
  class Deck(c: ArrayBuffer[Card]) {
    var cards: ArrayBuffer[Card] = c

    def this() {
      this(ArrayBuffer())
    }

    def addCard(c: Card) {
      cards += c
    }

    def size():Int = {
      return cards.size
    }

  }

  object Deck {
    def fresh(): Deck = {
      val deck = new Deck()
      val suits = List(Hearts, Diamonds, Clubs, Spades)
      val values = List(Two, Three, Four, Five,
                    Six, Seven, Eight, Nine, Ten,
                    Jack, Queen, King, Ace)
      for (s <- suits) {
        for (v <- values) {
          deck.addCard(new Card(v, s))
        }
      }
      return deck
    }
  }

}
