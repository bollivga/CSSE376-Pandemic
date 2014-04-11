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
    /**
     * @param roleNumber contains the role number of the player.
     */
    public Player(int roleNumber)
    {
        this.role = roleNumber;
        this.currentCity = PandemicGame.world.citiesSet.get("Atlanta");
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
