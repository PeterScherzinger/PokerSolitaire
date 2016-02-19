import java.util.ArrayList;
import java.util.Collections;

/**
 * Keeps track of a Poker Hand.  Hand size could be 1 to 7 cards.
 * Includes a getType() method that finds the type (e.g. two pair,
 * flush, straight) of this hand.  Note: In determining a hand's type
 * you should consider up to the best 5 cards in the hand.
 * 
 * @author Peter Scherzinger
 */

public class PokerHand extends Hand
{
	// Poker Hand types/categories
	// Use these constants in your getType method
	// e.g. return FULL_HOUSE;
	public final static int ROYAL_FLUSH = 9;
	public final static int STRAIGHT_FLUSH = 8;
	public final static int FOUR_OF_A_KIND = 7;
	public final static int FULL_HOUSE = 6;
	public final static int FLUSH = 5;
	public final static int STRAIGHT = 4;
	public final static int THREE_OF_A_KIND = 3;
	public final static int TWO_PAIR = 2;
	public final static int PAIR = 1;
	public final static int NOTHING = 0;

	public final static String[] TYPES = { "Nothing", "Pair", "Two Pair",
			"Three of a Kind ", "Straight", "Flush", "Full House",
			"Four of a Kind", "Straight Flush", "Royal Flush" };

	/** Constructs an empty PokerHand
	  */        
	public PokerHand()
	{
		super();
	}

	/** Returns the type of this hand e.g. Flush, Straight, Two Pair
	  * @return the poker hand type 0 - NOTHING to 9 - ROYAL_FLUSH
	  */
	public int getType()
	{
	      int [] ranks = new int [15];
	      int [] suits = new int [5];
	          
	      
//Count number of cards in each rank and suit
for(Card inHand: hand)
{
	if(inHand.isAce())
		ranks[14]++;
	ranks[inHand.getRank()]++;
	suits[inHand.getSuit()]++;
	
}


int flushSuit=0;
//Check for Flushes and the suit of the flush
for(int suit=1;suit<suits.length;suit++)
{
	if(suits[suit]>=5)
		flushSuit=suit;	
}


int noOfInARow=0;
int straightRank=0;
//Check for Straight
for(int rank=1;rank<ranks.length;rank++)
{
if(ranks[rank]>0)
{
 noOfInARow++;
	if(noOfInARow==5)
		straightRank=rank-4;
}
else
		noOfInARow=0;

}

//Collect all the cards that are the same suit as the flush
ArrayList <Card> straightSuit = new ArrayList <Card> ();
int [] rankFlush= new int [15];

if(straightRank!=0 && flushSuit!=0)
{
	for(Card inHand: hand)
	{
	if (inHand.getSuit()==flushSuit)
	{

		straightSuit.add(inHand);
		rankFlush[inHand.getRank()]++;
	}
	}

}


Collections.sort(straightSuit,Card.RANK_ORDER);


	//Check for royal flush
int royalFlushCount=0;
	for(int rank=rankFlush.length-1;rank>=9;rank--)
	{
		if(rankFlush[rank]>0)
		{
			royalFlushCount++;
			if(royalFlushCount==5)
				return ROYAL_FLUSH;

			
		}

	}
	
	
//Check for Straight Flush
	int straightFlushCount=0;
	for(int rank=straightRank;rank<=straightRank+4;rank++)
	{
		if(rankFlush[rank]>0)
		{
			straightFlushCount++;
			if(straightFlushCount==5)
				return STRAIGHT_FLUSH;

			
		}


	}


int threeCount=0;
int twoCount=0;
//Count number of Four of a kind, and count the number of 
//Three of a kinds and pairs
//Start from two so you don't count aces twice
for(int pos=2;pos<ranks.length;pos++)
{

	if(ranks[pos]==4)
		return FOUR_OF_A_KIND;
	else if(ranks[pos]==3)
		threeCount++;
	else if(ranks[pos]==2)
		twoCount++;
	
}

//Check for Full Houses, Flushes, Straights, Three of a Kinds, Two Pairs
//And Pairs
if(twoCount>0 && threeCount>0 || threeCount>=2)
	 return FULL_HOUSE;
if(flushSuit!=0)
	return FLUSH;
if(straightRank!=0)
	return STRAIGHT;
if(threeCount>0)
	return THREE_OF_A_KIND;
if(twoCount>=2)
	return TWO_PAIR;
if(twoCount==1)
	return PAIR;
	      return NOTHING;
	}
}
