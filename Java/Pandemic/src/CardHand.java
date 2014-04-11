import java.util.ArrayList;


/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * The hand of cards that belongs to a player.
 *
 */
public class CardHand extends CardStorage {
    /**
     * Hand of cards as an ArrayList
     */
    public CardHand()
    {
        this.stored = new ArrayList<Card>();

    }
    
	@Override
	public void add(Card x) {
		stored.add(x);
		
	}
	/**
	 * Removes the given card from the hand and returns it.
	 * @param CardToBeRemoved
	 */
	public Card remove(Card removal){
		return this.stored.remove(this.stored.indexOf(removal));
	}
}
