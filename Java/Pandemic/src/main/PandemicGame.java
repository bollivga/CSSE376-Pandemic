package main;

import java.util.ArrayList;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The main game class that initializes all aspects of the game,
 *         including the player hand, infections, and out breaks. Keeps track of
 *         all data.
 * 
 */
public class PandemicGame {

	ArrayList<CardHand> playerHands = new ArrayList<CardHand>();
	/**
	 * The list infection cards that have been discarded.
	 */
	public static CardDiscard infectionDiscard;
	/**
	 * The list of player cards that have been discarded.
	 */
	public CardDiscard playerDiscard;
	/**
	 * The deck of player cards available to draw.
	 */
	public static CardDeck playerDeck;
	/**
	 * The deck of city infection cards available to draw.
	 */
	public static CardDeck infectDeck;

	/**
	 * The first player.
	 */
	public static Player p1;
	/**
	 * The number of outbreaks that have occurred.
	 */
	public static int outbreakCount;
	/**
	 * The world as a city graph initialized.
	 */
	public static CityGraph world = new CityGraph();
	/**
	 * Tells you if a disease has been eradicated. Used to hold all diseases.
	 */
	public static boolean isEradicated[] = { false, false, false, false };
	/**
	 * Tells you if a disease has been cured, similarly.
	 */
	public static boolean isCured[] = { false, false, false, false };
	/**
	 * Stores the players
	 */
	public static ArrayList<Player> playerStorage;
	/**
	 * Keeps track of how many moves a player has taken. Turns change at 4.
	 */
	public static int currentMoves;
	/**
	 * The current player's id.
	 */
	public static int currentPlayer;
	/**
	 * The list of card buttons for the current player. Changes with each
	 * player.
	 */
	public static ArrayList<CardButton> handList = new ArrayList<CardButton>();
	
	/**
	 * The current epidemic count
	 */
	private static int epidemicCount;

	/**
	 * The main game class initializes the game.
	 */
	public PandemicGame() {
		PandemicGame.playerStorage = new ArrayList<Player>();
		PandemicGame.currentMoves = 0;
		PandemicGame.infectionDiscard = new CardDiscard();
		PandemicGame.playerDeck = new CardDeck();
		this.playerDiscard = new CardDiscard();
		PandemicGame.infectDeck = new CardDeck();
		PandemicGame.currentPlayer = 0;
		for (CityNode x : CityGraph.cities) {
			PandemicGame.playerDeck.add(new PlayerCityCard(x));
			PandemicGame.infectDeck.add(new InfectCityCard(x));
		}
		PandemicGame.outbreakCount = 0;
		PandemicGame.playerDeck.shuffle();
		PandemicGame.infectDeck.shuffle();
		PandemicGame.epidemicCount = 0;

	}

	/**
	 * Switches control to the next player.
	 */
	public static void nextPlayer() {
		for (int i = 0; i < 2; ++i) {
			Card draw = PandemicGame.playerDeck.draw();
			if (draw.getClass().equals(EpidemicCard.class)) {
				PandemicGame.epidemicTriggered();
				--i;
			} else {
				PandemicGame.p1.addToHand(draw);
			}
		}
		++PandemicGame.currentPlayer;
		if (PandemicGame.currentPlayer == PandemicGame.playerStorage.size()) {
			PandemicGame.currentPlayer = 0;
		}
		PandemicGame.p1 = PandemicGame.playerStorage
				.get(PandemicGame.currentPlayer);
		PandemicGame.currentMoves = 0;
		PandemicGame.infectCitiesBasedOnEpidemics();
	}

	/**
	 * Infects the cities based on the number of epidemics you have had.
	 */
	public static void infectCitiesBasedOnEpidemics() {
		InfectCityCard infected;
		int count;
		if(PandemicGame.epidemicCount < 3){
			count = 2;
			
		}else if(PandemicGame.epidemicCount < 5){
			count = 3;
		}else{
			count = 4;
		}
		for(int i = 0; i < count; ++i){
			infected = (InfectCityCard) PandemicGame.infectDeck.draw();
			infected.infect();
			PandemicGame.infectionDiscard.add(infected);
			System.out.println(infected.toString()+ " infected.");
		}
		
	}

	/**
	 * The epidemics are triggered.
	 */
	public static void epidemicTriggered() {
		InfectCityCard bottom =((InfectCityCard) PandemicGame.infectDeck.getBottom());
		bottom.infectThrice();
		PandemicGame.infectionDiscard.add(bottom);
		PandemicGame.infectionDiscard.shuffle();
		PandemicGame.infectDeck.addAll(PandemicGame.infectionDiscard);
		++PandemicGame.epidemicCount;
	}

	/**
	 * 
	 */
	public static void handOutCards() {
		if (PandemicGame.playerStorage.size() == 4) {
			for (Player i : PandemicGame.playerStorage) {
				i.hand = playerDeck.getHand(2);
			}
		} else if (PandemicGame.playerStorage.size() == 3) {
			for (Player i : PandemicGame.playerStorage) {
				i.hand = PandemicGame.playerDeck.getHand(3);
			}
		} else {
			for (Player i : PandemicGame.playerStorage) {
				i.hand = PandemicGame.playerDeck.getHand(4);
			}
		}

		PandemicGame.p1 = PandemicGame.playerStorage.get(0);
		for (int i = 0; i < 4; ++i) {
			PandemicGame.playerDeck.add(new EpidemicCard());
		}
		PandemicGame.playerDeck.shuffle();
	}

	/**
	 * Adds the player passed in by game creation.
	 * 
	 * @param x
	 * @throws Exception
	 */
	public static void addPlayer(String x) {
		int y = 0;
		if (x == "Contingency Planner") {
			y = 0;
		} else if (x == "Dispatcher") {
			y = 1;
		} else if (x == "Medic") {
			y = 2;
		} else if (x == "Operations Expert") {
			y = 3;
		} else if (x == "Quarantine Specialist") {
			y = 4;
		} else if (x == "Researcher") {
			y = 5;
		} else if (x == "Scientist") {
			y = 6;
		} else {
			// do nothing; illegal pass, but ignoring is easier than throwing an
			// exception for now
			return;
		}
		PandemicGame.playerStorage.add(new Player(y));
	}
}
