package tests;

import static org.junit.Assert.*;
import main.Board;
import main.CardHand;
import main.CityNode;
import main.GameBoard;
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
public class HandDeckTests {

	/**
	 * Test whether a city is connected to another city.
	 */
	@Test
	public void testDeck() {
		new PandemicGame();
		Board.init();
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.addPlayer("No More Players");
		PandemicGame.handOutCards();
		Board.runGame();
		GameBoard.redrawCards();
	}

}
