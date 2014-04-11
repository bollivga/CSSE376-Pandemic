import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * 
 *
 */
public class CardDeck extends CardStorage {
	
	public CardDeck(){
		this.stored = new ArrayList<Card>();
	}
	
	@Override
	public void add(Card x) {
		this.stored.add(x);
		
	}
	
	
	/**
	 * Takes the front card off of the deck. Does not handle epidemics, currently.
	 * @return Card
	 */
	public Card draw(){
		if(this.stored.size() > 0){
		return this.stored.remove(0);
		}
		return null;
	}
	
	
	
	/**
	 * Shuffles the deck.
	 */
	public void shuffle(){
		Collections.shuffle(this.stored);
	}
	
	/**
	 * Adds all cards in the given storage to the deck, removing them from the original in the process.
	 * @param deckTop
	 */
	public void addAll(CardStorage deckTop){
		this.stored.addAll(deckTop.stored);
		deckTop.stored.clear();
	}
	
	/**
	 * Returns the bottom card from the deck. Also removes it from the deck.
	 * @return Card bottomCard
	 */
	public Card getBottom(){
		return this.stored.remove(this.stored.size() - 1);
	}

	/**
	 * Gives a CardHand from the top n cards
	 * @param int
	 * @return hand of size int
	 */
	public CardHand getHand(int i) {
		CardHand toBeReturned = new CardHand();
		int j = 0;
		while(j<i){
		toBeReturned.add(this.draw());
		++j;
		}
		return toBeReturned;
		
	}
	
	public String toString(){
		String built = "";
		for(Card x : this.stored){
			built = built+this.stored;
		}
		return built;
	}
}
