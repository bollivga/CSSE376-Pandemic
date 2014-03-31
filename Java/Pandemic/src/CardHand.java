import java.util.ArrayList;


public class CardHand extends CardStorage {
	private ArrayList<Card> stored;
    public CardHand()
    {
        this.stored = new ArrayList<Card>();

    }
    
	@Override
	public void add(Card x) {
		stored.add(x);
		
	}
}
