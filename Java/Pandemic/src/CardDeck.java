import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * 
 *
 */
public class CardDeck extends CardStorage {
	private ArrayList<Card> storedDeck = new ArrayList<Card>();
	
	
	
	@Override
	public void add(Card x) {
		storedDeck.add(x);
		
	}
	
	
	/**
	 * Takes the front card off of the deck. Does not handle epidemics, currently
	 * @return Card
	 */
	public Card draw(){
		return this.storedDeck.remove(0);
	}
	
	
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle(){
		Collections.shuffle(this.storedDeck);
	}
	
	
	
}
