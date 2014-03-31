import java.util.ArrayList;


public class CityNode {
	 String name = "";
     int color;
     int[] infectionStatus = {0,0,0,0};//blue,black,red,yellow
     ArrayList<CityNode> connectedCities = new ArrayList<CityNode>();
     public CityNode(String cityName, int diseaseColor)
     {
         name = cityName;
         color = diseaseColor;
     }

     public void addConnection(CityNode cityNode)
     {
         this.connectedCities.add(cityNode);
         cityNode.connectedCities.add(this);
     }
     
     public String getName(){
    	 return this.name;
     }
}
