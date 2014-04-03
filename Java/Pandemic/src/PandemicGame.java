import java.util.ArrayList;


public class PandemicGame{
    
    ArrayList<CardHand> playerHands = new ArrayList<CardHand>();
    private CardStorage infectionDiscard;
    private CardStorage playerDiscard;
    private CardStorage playerDeck;
    public static Player p1;
	private CardStorage infectDeck;
    public static CityGraph world = new CityGraph();
    public static boolean isEradicated[] = {false, false ,false, false};
    public static boolean isCured[] = {false, false ,false, false};
    
    
    
    public PandemicGame(){
    	this.infectionDiscard  = new CardDiscard();
    	this.playerDeck = new CardDeck();
    	this.playerDiscard = new CardDiscard();
    	this.infectDeck = new CardDeck();
    	p1 = new Player(0);
    	for(CityNode x : world.cities){
    		this.playerDeck.add(new PlayerCityCard(x));
    		infectDeck.add(new InfectCityCard(x));
    	}
    }
}
