object Card {


  /**
   * Enum for Card Values.
   *
   * Note: in Scala, there are two choices for handling enumerations.
   * An `object` can be extended from Enumeration, in which it functions
   * similarly to Java's `enum`.
   *
   * The other case, incidentially, is a barrage of `case object`s.
   * This is also similar to Java's `enum` extension, which provides methods
   * and properties for enum values, but this results in a boat load
   * of complexity plus many more compiled classes.
   *
   */
  object CardValue extends Enumeration {
    type CardValue = Value
    val Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
        Jack, Queen, King, Ace = Value
  }

  // Allow these to exist freely.
  import CardValue._

  /**
   * Enum for Card Suits.
   */
  object CardSuit extends Enumeration {
    type CardSuit = Value
    val Hearts, Diamonds, Spades, Clubs = Value
  }

  // Allow these to exist freely.
  import CardSuit._

  /**
   * Return a CardValue's string name.
   * @param  {CardValue} v: CardValue     a card value
   * @return {String}    the name of the card value
   */
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

  /**
   * Return a CardValue's integer value.
   * @param  {CardValue} v: CardValue     a card value
   * @return {Int}    the integer representation of the card value
   */
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

  /**
   * Return a CardSuit's name.
   * @param  {CardSuit} s: CardSuit      a card suit
   * @return {String}    a name of the Card Suit
   */
  def suitToString(s: CardSuit): String = s match {
    case CardSuit.Hearts => "Hearts"
    case CardSuit.Spades => "Spades"
    case CardSuit.Diamonds => "Diamonds"
    case CardSuit.Clubs => "Clubs"
    case _ => "?"
  }

  /**
   * Card, typically represented by a value and suit. Allows for comparison.
   */
  class Card(v: CardValue, s: CardSuit) extends Ordered[Card] {
    var value = v
    var suit = s

    override def toString = valueToString(value) + " of " + suitToString(suit)

    /**
     * Compare a card to this card, uses natural value ordering.
     *
     * More specifically, this `compare` method allows Scala
     * to handle < = > operators for cards, unlike Java's compareTo.
     * In this way, it is more like Rust in that one can override the
     * natural types that < = > can process.
     *
     * @param  {Card} that: Card          another cards
     * @return {Int}       < if less, 0 if the same, > if more
     */
    def compare(that: Card) = valueToInt(value) - valueToInt(that.value)
  }

}
