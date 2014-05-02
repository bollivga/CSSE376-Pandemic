package main;
import java.util.ArrayList;

/**
 * @author Jonathan Jungck and Greg Bollivar
 *
 */
public abstract class CardStorage {
	/**
	 * The array of stored cards in the deck
	 */
	public ArrayList<Card> stored;
	
	/**
	 * Class for card storage.
	 */
	public CardStorage() { 
        
    }

	/**
	 * @param x
	 */
	public abstract void add(Card x);
	
}
