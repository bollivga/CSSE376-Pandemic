package main;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         Discards a card from an ArrayList
 * 
 */
public class CardDiscard extends CardStorage {
	/**
	 * Creates a CardDiscard, functions as a public stack.
	 */
	public CardDiscard() {
		this.stored = new ArrayList<Card>();
	}

	@Override
	public void add(Card x) {
		this.stored.add(x);
	}

	/**
	 * Removes the passed card from play. Permanently.
	 * 
	 * @param x
	 * @param CardToBeRemoved
	 */
	public void removeCardFromGame(Card x) {
		this.stored.remove(this.stored.indexOf(x));
	}

	/**
	 * @return the card on the top of the deck
	 */
	public Card top() {
		return this.stored.get(this.stored.size() - 1);
	}

	/**
	 * Shuffles the deck of cards
	 */
	public void shuffle() {
		Collections.shuffle(this.stored);
	}
}
