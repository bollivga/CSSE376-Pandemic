package tests;

import static org.junit.Assert.*;
import main.AirliftCard;
import main.Board;
import main.CardButton;
import main.CityButton;
import main.CureButton;
import main.GameBoard;
import main.GovernmentGrantCard;
import main.InfectCityCard;
import main.PandemicGame;
import main.PlayerCityCard;
import main.ResilPopCard;

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
	public void testBoardShowsUp() {
		Board.init();
		assertFalse(PandemicGame.world.equals(null));
	}

	/**
	 * Tests
	 */
	@Test
	public void testButtonShowsCorrectInfections() {
		for (CityButton j : Board.cityList) {
			assertEquals("" + j.cityNode.infectionStatus[j.cityNode.color],
					j.getText());
		}
	}

	/**
	 * Checks what happens when you click on the current city.
	 */
	@Test
	public void testClickCurrentCity() {
		new PandemicGame();
		PandemicGame.isGerman = false;
		Board.init();
		PandemicGame.isGerman = false;
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
		new Board();
		Board.charterFlight(Board.cityList.get(0).cityNode);
	}

	/**
	 * Test choosing players
	 */
	@Test
	public void testChoosing() {
		new PandemicGame();
		Board.chooseRoles();
		assertFalse(PandemicGame.playerStorage.isEmpty());
	}

	/**
	 * Tests whether everything runs
	 */
	@Test
	public void testRunning() {
		new PandemicGame();
		PandemicGame.isGerman = false;
		Board.init();
		PandemicGame.addPlayer("Medic");
		PandemicGame.addPlayer("Dispatcher");
		PandemicGame.handOutCards();
		Board.runGame();
		assertFalse(PandemicGame.playerStorage.isEmpty());
		GameBoard.handButtons.get(0).doClick();
		CureButton.isSelecting = true;
		CureButton.colorCuring = 0;
		CureButton.cardsLeft = 2;
		CardButton x = new CardButton(new PlayerCityCard(PandemicGame.world.getCity("New York")));
		PandemicGame.p1.hand.stored.add(x.card);
		x.doClick();
		PandemicGame.p1.hand.stored.add(x.card);
		x.doClick();
		PandemicGame.p1.hand.stored.add(x.card);
		Board.discarding = true;
		Board.discardAmount = 2;
		x.doClick();
		PandemicGame.p1.hand.stored.add(x.card);
		x.doClick();
		Board.cityList.get(0).refreshResearchStation();
		Board.cityList.get(0).refreshInfection();
		new GovernmentGrantCard().trigger();
		Board.cityList.get(0).doClick();
		assertTrue(Board.cityList.get(0).cityNode.hasResearchStation);
		new AirliftCard().trigger();
		Board.cityList.get(0).doClick();
		PandemicGame.p1.isFlying = true;
		Board.cityList.get(20).doClick();
	}
	
	@Test
	public void testResilPop(){
		new PandemicGame();
		PandemicGame.isGerman = false;
		InfectCityCard x = new InfectCityCard(PandemicGame.world.getCity("New York"));
		PandemicGame.infectionDiscard.add(x);
		ResilPopCard y = new ResilPopCard();
		CardButton z = new CardButton(y);
		z.doClick();
	}
}
