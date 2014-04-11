import java.util.ArrayList;

/**
 * @author Jonathan Jungck and Greg Bollivar
 *
 */
public abstract class CardStorage {
	protected ArrayList<Card> stored;
	
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
