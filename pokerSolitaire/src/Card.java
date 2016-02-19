import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;

import javax.swing.ImageIcon;

/**
 * Methods to create, compare and read cards for the deck class
 * 
 * @author Peter Scherzinger
 */
public class Card extends Rectangle implements Comparable<Card> {
	private int suit;// D-1,C-2,H-3.S-4
	private int rank;// A-1,2-10,J-11,Q-12.K-13
	private boolean isFaceUp;
	private final static String SUITSTRING = " DCHS";
	private final static String RANKSTRING = " A23456789TJQK";
	public static final Comparator<Card> RANK_ORDER = new RankOrder();
	private final static Image background = new ImageIcon("images\\redback.png")
			.getImage();
	public final static int WIDTH = background.getWidth(null);
	public final static int HEIGHT = background.getHeight(null);
	private Image image;

	/**
	 * Constructs the card for the deck
	 * 
	 * @param rank
	 *            the rank of the card
	 * @param suit
	 *            the suit of the card
	 */
	public Card(int suit, int rank) {
		super(0, 0, 0, 0);
		this.suit = suit;
		this.rank = rank;
		this.isFaceUp = true;

		// Load up the appropriate image file for this card
		String imageFileName = "" + " dchs".charAt(suit) + rank + ".png";
		imageFileName = "images\\" + imageFileName;
		image = new ImageIcon(imageFileName).getImage();
		setSize(image.getWidth(null), image.getHeight(null));
	}

	/**
	 * Creates a new card given a string (e.x 2D is the 2 of diamonds)
	 * 
	 * @param wordStr
	 *            the string the card is being created from
	 */
	public Card(String wordStr) {

		this.suit = SUITSTRING.indexOf(wordStr.charAt(1));
		this.rank = RANKSTRING.indexOf(wordStr.charAt(0));

	}

	/**
	 * Returns a string to represent a card (E.X 2 of diamonds is 2D)
	 * 
	 * @return the String with the card suit and rank inside
	 * 
	 */
	public String toString() {
		return "" + RANKSTRING.charAt(this.rank) + SUITSTRING.charAt(this.suit);
	}

	/**
	 * Compares two cards by suit and then by rank
	 * 
	 * @param other
	 *            the other card being compared to
	 * @return the difference of the two cards
	 */
	public int compareTo(Card other) {

		int suitDifference = this.suit - other.suit;
		// Check if the cards are the same suit. if so, compare them by rank
		if (suitDifference == 0)
			return this.rank - other.rank;

		return suitDifference;
	}

	/**
	 * Finds the rank of the card
	 * 
	 * @return the rank of the card
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Finds the suit of the card
	 * 
	 * @return the suit of the card
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Finds the blackjack value of the card
	 * 
	 * @return the value of the card
	 */
	public int getValue() {
		if (this.rank == 1)
			return 11;

		else if (this.rank < 10)
			return this.rank;

		return 10;
	}

	/**
	 * Flips the card so it is face up
	 * 
	 */
	public void turnFaceUp() {
		isFaceUp = true;
	}

	/**
	 * Flips the card so it is face down
	 * 
	 */
	public void turnFaceDown() {
		isFaceUp = false;
	}

	/**
	 * Checks if a card is an ace
	 * 
	 * @return whether or not the card is an ace
	 */
	public boolean isAce() {
		if (rank == 1)
			return true;

		return false;
	}

	/**
	 * Draws a card in a Graphics context
	 * 
	 * @param g
	 *            Graphics to draw the card in
	 */
	public void draw(Graphics g) {
		if (isFaceUp)
			g.drawImage(image, x, y, null);
		else
			g.drawImage(background, x, y, null);
	}

	/**
	 * Allows the player to drag the card to another spot in poker solitaire
	 * 
	 * @param initialPos
	 *            the initial position of the card
	 * @param finalPos
	 *            the final position of the card
	 */
	public void move(Point initialPos, Point finalPos) {
		translate(finalPos.x - initialPos.x, finalPos.y - initialPos.y);
	}

	/**
	 * Comparator to compare two cards by rank
	 * 
	 */
	private static class RankOrder implements Comparator<Card> {

		/**
		 * Compares two cards by rank
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
