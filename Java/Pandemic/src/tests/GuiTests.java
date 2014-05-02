package tests;

import static org.junit.Assert.*;
import main.Board;
import main.CityButton;
import main.PandemicGame;

import org.junit.Test;

/**
 * @author Jonathan Jungck and Greg Bollivar
 *
 */
public class GuiTests {

	/**
	 * Tests that the board is created.
	 */
	@Test
	public void testBoardShowsUp(){
		Board.init();
		assertFalse(PandemicGame.world.equals(null));
	}
	
	/**
	 * Tests
	 */
	@Test
	public void testButtonShowsCorrectInfections(){
		for (CityButton j : Board.cityList) {
			assertEquals("" + j.cityNode.infectionStatus[j.cityNode.color], j.getText());
		}
	}
	
	/**
	 * Checks what happens when you click on the current city.
	 */
	@Test
	public void testClickCurrentCity(){
		new PandemicGame();
		Board.init();
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("No More Players");
		PandemicGame.handOutCards();
		Board.runGame();
		Board.cityList.get(0).doClick();
		Board.changePlayer();
	}
	
	/**
	 * Checks a charter flight.
	 */
	@Test
	public void charterFlight() {
		new PandemicGame();
		Board.charterFlight(Board.cityList.get(0).cityNode);
	}
	
	/**
	 * Test choosing players
	 */
	@Test
	public void testChoosing(){
		new PandemicGame();
		Board.chooseRoles();
		assertFalse(PandemicGame.playerStorage.isEmpty());
	}
	/**
	 * Tests whether everything runs
	 */
	@Test
	public void testRunning(){
		new PandemicGame();
		Board.init();
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.handOutCards();
		Board.runGame();
		assertFalse(PandemicGame.playerStorage.isEmpty());
	}

}
