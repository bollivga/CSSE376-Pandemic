import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CityGraphTest {

	

	@Test
	public void testConnection() {
		PandemicGame testGame = new PandemicGame();
		assertFalse(PandemicGame.world.getCity("New York").isConnectedTo(PandemicGame.world.getCity("Chicago")));
		assertTrue(PandemicGame.world.getCity("Chicago").isConnectedTo(PandemicGame.world.getCity("Atlanta")));
		
	}
	
	@Test
	public void testInfection(){
		PandemicGame testGame = new PandemicGame();
		CityNode ny = PandemicGame.world.getCity("New York");
		assertFalse(ny.infectThrice());
		assertTrue(3 == PandemicGame.world.getCity("New York").infectionStatus[0]);
		for(CityNode x : ny.connectedCities){
			assertTrue(0 == x.infectionStatus[0]);
		}
		assertTrue(ny.infectOnce());
		for(CityNode x : ny.connectedCities){
			System.out.println(x.infectionStatus[0]); //I LIKE TRAINS
			assertTrue(1 == x.infectionStatus[0]);
		}
		ny.resetOutbreaks();
		assertTrue(ny.infectThrice());
		for(CityNode x : ny.connectedCities){
			assertTrue(2 == x.infectionStatus[0]);
		}
		ny.resetOutbreaks();
		assertTrue(ny.infectThrice());
		for(CityNode x : ny.connectedCities){
			assertTrue(3 == x.infectionStatus[0]);
		}
	}
	
	@Test
	public void testPlayerMove(){
		PandemicGame testGame = new PandemicGame();
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("Chicago")));
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York"))); //I LIKE TRAINS
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("Montreal")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York")));
		
	}
}
