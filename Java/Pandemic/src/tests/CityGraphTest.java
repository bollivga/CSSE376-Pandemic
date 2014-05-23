package tests;

import static org.junit.Assert.*;

import main.Board;
import main.CardHand;
import main.CityNode;
import main.InfectCityCard;
import main.PandemicGame;
import main.Player;
import main.PlayerCityCard;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         Test class for the city graph to test functionality.
 * 
 */
@SuppressWarnings("unused")
public class CityGraphTest {

	/**
	 * Test whether a city is connected to another city.
	 */
	@Test
	public void testConnection() {
		PandemicGame testGame = new PandemicGame();
		assertFalse(PandemicGame.world.getCity("New York").isConnectedTo(
				PandemicGame.world.getCity("Chicago")));
		assertTrue(PandemicGame.world.getCity("Chicago").isConnectedTo(
				PandemicGame.world.getCity("Atlanta")));

	}

	/**
	 * Test whether a city is infected. Tests whether outbreaks occur when a
	 * city reaches 4 infections.
	 */
	@Test
	public void testInfection() {
		PandemicGame testGame = new PandemicGame();
		PandemicGame.controlledPlayer = new Player(0);
		InfectCityCard ny = new InfectCityCard(
				PandemicGame.world.getCity("New York"));
		assertFalse(ny.infectThrice());
		assertEquals(3,
				PandemicGame.world.getCity("New York").infectionStatus[0]);
		for (CityNode x : ny.city.connectedCities) {
			assertEquals(0, x.infectionStatus[0]);
		}
		assertEquals(0, PandemicGame.outbreakCount);
		assertTrue(ny.infect());
		for (CityNode x : ny.city.connectedCities) {
			assertEquals(1, x.infectionStatus[0]);
		}
		assertEquals(1, PandemicGame.outbreakCount);
		ny.city.resetOutbreaks();
		assertTrue(ny.infectThrice());
		for (CityNode x : ny.city.connectedCities) {
			assertEquals(2, x.infectionStatus[0]);
		}
		assertEquals(2, PandemicGame.outbreakCount);
		ny.city.resetOutbreaks();
		assertTrue(ny.infectThrice());
		for (CityNode x : ny.city.connectedCities) {
			assertEquals(3, x.infectionStatus[0]);
		}
		assertEquals(3, PandemicGame.outbreakCount);
		ny.city.resetOutbreaks();
		assertTrue(ny.infectThrice());
		for (CityNode x : ny.city.connectedCities) {
			assertEquals(3, x.infectionStatus[0]);
			assertTrue(x.hasOutbroken[0]);
		}
		PandemicGame.isEradicated[0] = true;
		ny.city.infectionStatus[0] = 0;
		for (CityNode x : ny.city.connectedCities) {
			x.infectionStatus[0] = 0;
		}
		assertFalse(ny.city.infectOnce());
	}

	/**
	 * Tests whether a player can move from city to city legitimately.
	 */
	@Test
	public void testPlayerMove() {
		PandemicGame testGame = new PandemicGame();
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("Medic");
		PandemicGame.handOutCards();
		testGame = new PandemicGame();
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Researcher");
		PandemicGame.handOutCards();
		testGame = new PandemicGame();
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Researcher");
		PandemicGame.addPlayer("Scientist");
		PandemicGame.handOutCards();
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("New York")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("Chicago")));
		assertFalse(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("New York"))); // I LIKE TRAINS
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("Montreal")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("New York")));
		assertFalse(PandemicGame.p1.tryFlyToCity(PandemicGame.world
				.getCity("New York")));
		assertTrue(PandemicGame.p1.tryFlyToCity(PandemicGame.world
				.getCity("San Francisco")));
		PandemicGame.p1.useCard(new PlayerCityCard(PandemicGame.world
				.getCity("New York")));
		assertTrue(PandemicGame.p1.tryMoveToCity(PandemicGame.world
				.getCity("London")));
		PandemicGame.p1.useCard(new PlayerCityCard(PandemicGame.world
				.getCity("New York")));
		assertTrue(PandemicGame.p1.tryFlyToCity(PandemicGame.world
				.getCity("Chicago")));

	}

	/**
	 * Tests the player toString function.
	 */
	@Test
	public void testPlayerToString() {
		PandemicGame testGame = new PandemicGame();
		assertEquals(new Player(0).toString(), "Contingency Planner");
		assertEquals(new Player(1).toString(), "Dispatcher");
		assertEquals(new Player(2).toString(), "Medic");
		assertEquals(new Player(3).toString(), "Operations Expert");
		assertEquals(new Player(4).toString(), "Quarantine Specialist");
		assertEquals(new Player(5).toString(), "Researcher");
		assertEquals(new Player(6).toString(), "Scientist");
		assertEquals(new Player(7).toString(), "Player");

	}

	/**
	 * Test that doesn't work lol
	 */
	// @Test
	// public void testPandemicGamePlayerAddingAndRotation(){
	// PandemicGame testGame = new PandemicGame();
	// PandemicGame.addPlayer("Contingency Planner");
	// PandemicGame.p1 = PandemicGame.playerStorage.get(0);
	// PandemicGame.addPlayer("Dispatcher");
	// PandemicGame.addPlayer("Medic");
	// PandemicGame.addPlayer("Operations Expert");
	// PandemicGame.addPlayer("Quarantine Specialist");
	// PandemicGame.addPlayer("Researcher");
	// PandemicGame.addPlayer("Scientist");
	// PandemicGame.addPlayer("This does nothing unless we make the function throw something, because it's a useless input.");
	// assertEquals(PandemicGame.p1.toString(),"Contingency Planner");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Dispatcher");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Medic");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Operations Expert");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Quarantine Specialist");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Researcher");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Scientist");
	// PandemicGame.nextPlayer();
	// assertEquals(PandemicGame.p1.toString(),"Contingency Planner");
	// }

	/**
	 * 
	 */
	@Test
	public void testDeck() {
		PandemicGame testGame = new PandemicGame();
		PandemicGame.playerDeck.toString();
		assertEquals(PandemicGame.playerDeck.getBottom().getClass(),
				PlayerCityCard.class);
		CardHand x = PandemicGame.playerDeck.getHand(5);
		assertEquals(x.stored.size(), 5);
		PandemicGame.playerDeck.addAll(x);
	}

}
