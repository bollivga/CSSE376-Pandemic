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
		assertFalse(PandemicGame.world.getCity("New York").infectThrice());
		assertTrue(PandemicGame.world.getCity("New York").infectOnce());
		assertTrue(PandemicGame.world.getCity("New York").infectThrice());
	}
	
	@Test
	public void testPlayerMove(){
		PandemicGame testGame = new PandemicGame();
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("Chicago")));
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("Montreal")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world.getCity("New York")));
		
	}
}
