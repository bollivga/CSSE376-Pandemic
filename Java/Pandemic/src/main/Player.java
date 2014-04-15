package main;
import java.util.ArrayList;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * The player class keeps track of a player's role as well as their
 * current city location and which player they are. Each player is controlled
 * by the movement action listener in Board.java, and it calls the appropriate
 * function from this class.
 * 
 */
public class Player {
	private int role;
	CityNode currentCity;
	CardHand hand;
	boolean isFlying;
	
    /**
     * @param roleNumber contains the role number of the player.
     */
    public Player(int roleNumber)
    {
        this.role = roleNumber;
        this.currentCity = PandemicGame.world.citiesSet.get("Atlanta");
        this.hand = new CardHand();
    }
    /**
     * @param x
     * @return returns whether a player can move to a city.
     */
    public boolean tryMoveToCity(CityNode x){
    	if(this.currentCity.isConnectedTo(x)){
    		this.currentCity = x;
    		return true;
    	}
    	return false;
    }
    
    /**
     * @return the player's hand of cards
     */
    public CardHand getHand() {
    	return this.hand;
    }
    
    /**
     * Adds a new card to a hand
     * 
     * @param newCard 
     */
    public void addToHand(Card newCard) {
    	this.hand.add(newCard);
    }
    
    /**
     * Removes the card from the hand, and plays the card's ability.
     * 
     * @param cardToUse
     */
    public void useCard(Card cardToUse) {
    	if (cardToUse.getClass() == PlayerCityCard.class) {
    		CityNode city = ((PlayerCityCard) cardToUse).city;
    		if (this.currentCity == city) {
    			Board.charterFlight(city);
    			this.isFlying = true;
    	    	this.hand.remove(cardToUse);
    		}
    		else {
    			Board.cityFlight(city);
    			if(this.tryFlyToCity(city)) {
    				System.out.println(PandemicGame.p1.toString()
    						+ " has moved to " + city.getName() + ". "
    						+ (4 - PandemicGame.currentMoves) + " moves left.");
    				++PandemicGame.currentMoves;
    				if (PandemicGame.currentMoves == 4) {
    					Board.changePlayer();
    				}
        	    	this.hand.remove(cardToUse);
    			}
    		}
    	}
    	else if (cardToUse.getClass() == EventCard.class) {
    		Board.useEventCard();
        	this.hand.remove(cardToUse);
    	}
    }
    
    
    
    /**
     * @param x
     * @return true; all flights work.
     */
    public boolean tryFlyToCity(CityNode x){
    	if(!(this.currentCity.equals(x))){
    	this.currentCity = x;
    	return true;
    	}
    	return false;
    }
    
    
    
    @Override
    public String toString(){
		if(this.role == 0)
			{
			return "Contingency Planner";
			}
		else if(this.role == 1){
			return "Dispatcher";
		}else if(this.role == 2){
			return "Medic";
		}else if(this.role == 3){
			return "Operations Expert";
		}else if(this.role == 4){
			return "Quarantine Specialist";
		}else if(this.role == 5){
			return "Researcher";
		}else if(this.role == 6){
			return "Scientist";
		}
		else{
			return "Player";
		}
    }
}
