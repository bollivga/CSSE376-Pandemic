
public class Player {
	private int role;
	private CityNode currentCity;
    public Player(int roleNumber)
    {
        this.role = roleNumber;
        this.currentCity = PandemicGame.world.citiesSet.get("Atlanta");
    }
    public boolean tryMoveToCity(CityNode x){
    	if(this.currentCity.isConnectedTo(x)){
    		this.currentCity = x;
    		return true;
    	}
    	return false;
    }
}
