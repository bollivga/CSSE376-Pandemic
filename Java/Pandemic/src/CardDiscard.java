import java.util.ArrayList;


/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * Discards a card from an ArrayList
 *
 */
public class CardDiscard extends CardStorage {
     /**
     * Creates a CardDiscard, functions as a public stack.
     */
    public CardDiscard()
     {
         this.stored = new ArrayList<Card>();
     }
	@Override
	public void add(Card x) {
		this.stored.add(x);
	}
	
	/**
	 * Removes the passed card from play. Permanently.
	 * @param CardToBeRemoved
	 */
    public void removeCardFromGame(Card x){
    	this.stored.remove(this.stored.indexOf(x));
    }
}
