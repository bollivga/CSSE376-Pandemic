package tests;

import static org.junit.Assert.*;

import main.Board;
import main.PandemicGame;

import org.junit.Test;

public class GuiTests {

	@Test
	public void testBoardShowsUp(){
		Board.init();
		assertFalse(PandemicGame.world.equals(null));
	}
	@Test
	public void testChoosing(){
		new PandemicGame();
		Board.chooseRoles();
		assertFalse(PandemicGame.playerStorage.isEmpty());
	}
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
