import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Methods to read and manipulate the hand of cards 
 * Includes methods to sort the hand by both Rank and Suit,
 * To get the value of a hand( aces default to 11),
 * To add and remove cards,and to draw cards
 * 
 * 
 * @author Peter Scherzinger 
 */
public class Hand {
	protected ArrayList<Card> hand;

	/**
	 * Constructs the hand
	 * 
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}

	/**
	 * Constructs the hand from a given string
	 * 
	 * @param handStr
	 *            the string containing the cards in the hand
	 */
	public Hand(String handStr) {
		hand = new ArrayList<Card>();
		// Add each card to the hand
		for (int pos = 0; pos < handStr.length(); pos += 3) {
			Card newCard = new Card(handStr.substring(pos, pos + 2));
			hand.add(newCard);
		}

	}

	/**
	 * Sort the hand by suit
	 * 
	 */
	public void sortBySuit() {
		Collections.sort(hand);
	}

	/**
	 * Adds a given card to the hand
	 * 
	 * @param cardToAdd
	 *            the card being added to the hand
	 */
	public void addCard(Card cardToAdd) {
		hand.add(cardToAdd);
	}

	/**
	 * Sorts the hand by rank and then by suit
	 * 
	 */
	public void sortByRank() {
		Collections.sort(hand, Card.RANK_ORDER);
	}

	/**
	 * Creates and returns a string with all of the cards in the hand
	 * 
	 * @return the string with all the cards inside of it
	 */
	public String toString() {
		StringBuilder cardStr = new StringBuilder(hand.size() * 3);
		// Add each card to the string
		for (Card inHand : hand)
			cardStr.append(inHand.toString() + " ");
		return cardStr.toString();
	}

	/**
	 * Gets the total value of a hand
	 * 
	 * @return the value of the hand
	 */
	public int getValue() {
		int totalValue = 0;
		int aceCount = 0;

		// Adds up the total value of all cards that are not aces
		// And counts the number of aces
		for (Card card : hand) {
			if (card.getValue() != 11 && card.getValue() < 10)
				totalValue += card.getRank();
			else if (card.getValue() != 11 && card.getValue() >= 10)
				totalValue += 10;
			else
				aceCount++;

		}

		// Checks to see if the ace should be counted as 11 or 1
		if (totalValue <= 10 && aceCount == 1)
			totalValue += 11;
		else if (totalValue + aceCount <= 11)
			totalValue += 11 + aceCount - 1;
		else
			totalValue += aceCount;

		return totalValue;

	}

	/**
	 * Empties out the hand
	 * 
	 */
	public void clear() {
		hand.clear();
	}

	/**
	 * Displays the Cards in this Hand
	 * 
	 * @param g
	 *            Graphics context to display the deck
	 */
	public void draw(Graphics g) {
		for (Card next : hand)
			next.draw(g);
	}

	/**
	 * Removes a card from the hand
	 * 
	 * @param card
	 *            the card being removed
	 */
	public void removeCard(Card card) {
		hand.remove(card);
	}

	/**
	 * Searches hand to find card with the given point
	 * 
	 * @param point
	 *            the point being looked for
	 * @return the card with the given point
	 */
	public Card getCardAt(Point point) {
		for (Card next : hand)
			if (next.contains(point))
				return next;
		return null;
	}

	/**
	 * Comparator to compare two cards by rank And then by suit
	 * 
	 */
	private static class RankOrder implements Comparator<Card> {
		/**
		 * Compares two cards by rank and then by suit
		 * 
		 * @param first
		 *            the first card being compared
		 * @param second
		 *            the second card being compared
		 * @return the difference of the two cards ranks
		 */
		public int compare(Card first, Card second) {
			if (first.getRank() < second.getRank())
				return -1;
			else if (first.getRank() > second.getRank())
				return 1;
			else
				return (first.compareTo(second));
		}
	}

}
