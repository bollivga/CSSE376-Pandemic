import java.util.ArrayList;


public class CardDeck extends CardStorage {
	private ArrayList<Card> storedDeck = new ArrayList<Card>();
	@Override
	public void add(Card x) {
		storedDeck.add(x);
		
	}

}
