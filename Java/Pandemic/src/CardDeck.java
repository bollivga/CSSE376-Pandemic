import java.util.ArrayList;


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

}
