import java.io.Serializable;

/**Keeps track of the player and its variables (score & name)
 * 
 * 
 * @author Peter Scherzinger
 *
 */
public class Player implements Comparable<Player>, Serializable {
	private final int score;
	private final String name;

	/*
	 * Constructs a player
	 */
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	/**
	 * Compares two players by score
	 * 
	 * @param first
	 *            the first player being compared
	 * @param second
	 *            the second player being compared
	 * @return the
	 */
	public int compareTo(Player other) {
	
		return other.score-score;
	}

	/**
	 * Gets the score of the player
	 * 
	 * @return the score of the player
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Creates a string with the name and score of the player
	 * 
	 * @return a string with the name and score of the player
	 */
	public String toString() {
		return ("Name: " + this.name + " Score: " + this.score);
	}

}
