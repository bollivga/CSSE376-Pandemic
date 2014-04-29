package tests;

import static org.junit.Assert.*;
import main.Board;
import main.CityButton;
import main.GameBoard;
import main.PandemicGame;

import org.junit.Test;

public class GuiTests {

	@Test
	public void testBoardShowsUp(){
		Board.init();
		assertFalse(PandemicGame.world.equals(null));
	}
	
	@Test
	public void testClickCurrentCity(){
		new PandemicGame();
		Board newBoard = new Board();
		newBoard.init();
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("No More Players");
		PandemicGame.handOutCards();
		newBoard.runGame();
		newBoard.cityList.get(0).doClick();
		Board.changePlayer();
	}
	
	@Test
	public void charterFlight() {
		new PandemicGame();
		Board.charterFlight(Board.cityList.get(0).cityNode);
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
