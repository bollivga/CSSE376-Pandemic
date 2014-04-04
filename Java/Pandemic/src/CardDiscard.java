import java.util.ArrayList;


/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * Discards a card from an ArrayList
 *
 */
public class CardDiscard extends CardStorage {
	 private ArrayList<Card> stored;
     /**
     * 
     */
    public CardDiscard()
     {
         this.stored = new ArrayList<Card>();
     }
	@Override
	public void add(Card x) {
		stored.add(x);
	}
     
}
