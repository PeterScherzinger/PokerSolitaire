import java.util.Scanner;

/**
 * Methods to create , read, deal, and shuffle a deck of cards
 * 
 * @author Peter Scherzinger
 */
public class Deck {
	protected Card[] deck;
	private int topCard;

	/**
	 * Constructs a deck of cards for a card game
	 * 
	 */
	public Deck() {
		deck = new Card[52];

		int pos = 0;
		for (int suit = 1; suit <= 4; suit++)
			for (int rank = 1; rank <= 13; rank++) {
				deck[pos] = new Card(suit, rank);
				pos++;

			}

	}

	/**
	 * Shuffles up the deck of cards
	 * 
	 */
	public void shuffle() {

		// Swap each card with a card at a random position
		for (int i = 0; i < deck.length; i++) {

			int random = ((int) (Math.random() * deck.length));
			Card temp = deck[random];
			deck[random] = deck[i];
			deck[i] = temp;

		}
		topCard = deck.length;
	}

	/**
	 * Deals out a card to a player
	 * 
	 * @return the card to be dealt
	 */
	public Card deal() {
		// Check if there are any cards left
		if (topCard == 0)
			return null;

		return deck[--topCard];
	}

	/**
	 * Finds the number of cards left in the deck
	 * 
	 * @return number of cards left
	 */
	public int noOfCardsLeft() {
		return topCard;
	}

}
