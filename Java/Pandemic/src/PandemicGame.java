import java.util.ArrayList;


public class PandemicGame{
    
    ArrayList<CardHand> playerHands = new ArrayList<CardHand>();
    private CardStorage infectionDiscard = new CardDiscard();
    private CardStorage playerDiscard = new CardDiscard();
    private CardStorage playerDeck = new CardDeck();
    Player p1 = new Player(0);
	private CardStorage infectDeck;
    public static CityGraph world = new CityGraph();
    
    
    
    
    public PandemicGame(){
    	
    	
    	for(CityNode x : world.cities){
    		playerDeck.add(new PlayerCityCard(x));
    		infectDeck.add(new InfectCityCard(x));
    	}
    }
}
