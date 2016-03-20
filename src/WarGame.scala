import Card._
import Deck._

import scala.util.control._

class WarGame {

  def play() {

    val deck = Deck.fresh()
    deck.shuffle()

    // Scala supports tuples, which makes this a little
    // cleaner than WarGame Java, specifically,
    // array indexes are not used to specify which
    // deck is being handled.
    val (player1, player2) = deck.split()

    // The winner deck will contain the cards that can
    // be earned during each round
    val winner = new Deck()

    // In Scala, breaking out of control flow is generally
    // frowned upon. Despite that, it is allowed using some
    // special constructs.
    val base = new Breaks;

    // Loops that might break out wrapped in the breakable {}
    // structure.
    base.breakable {
      while (player1.hasCards() && player2.hasCards()) {

        var c1 = player1.getCard()
        var c2 = player2.getCard()

        player1.giveCard(winner)
        player2.giveCard(winner)

        if (c1 == c2) {

          do {

            if (player1.size() < 4 || player2.size() < 4) {
              // When a deck has less than 4 cards,
              // it cannot possibly play war, which means that
              // the other player has won the game.
              base.break
            }

            for (i <- 0 until 3) {
              player1.giveCard(winner)
              player2.giveCard(winner)
            }

            c1 = player1.getCard()
            c2 = player2.getCard()

            player1.giveCard(winner)
            player2.giveCard(winner)

            if (c1 > c2) {
              winner.shuffle()
              winner.giveCards(player1)
            } else if (c1 < c2) {
              winner.shuffle()
              winner.giveCards(player2)
            } else {
              // another war
            }

          } while (c1 == c2)

        } else if (c1 > c2) {
          winner.shuffle()
          winner.giveCards(player1)
        } else if (c1 < c2) {
          winner.shuffle()
          winner.giveCards(player2)
        }

        // println("P1: " + player1)
        // println("P2: " + player2)

      }
    }

  }

}
