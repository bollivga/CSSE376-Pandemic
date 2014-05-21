package main;

import java.awt.Color;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The player class keeps track of a player's role as well as their
 *         current city location and which player they are. Each player is
 *         controlled by the movement action listener in Board.java, and it
 *         calls the appropriate function from this class.
 * 
 */
public class Player {
	private int role;
	CityNode currentCity;
	/**
	 * The hand of cards for that player
	 */
	public CardHand hand;
	boolean isFlying;
	/**
	 * The color of the player for graphical appearance.
	 */
	public Color color;

	/**
	 * @param roleNumber
	 *            contains the role number of the player.
	 */
	public Player(int roleNumber) {
		this.role = roleNumber;
		this.currentCity = PandemicGame.world.citiesSet.get("Atlanta");
		hand = new CardHand();
		if (this.role == 0) {
			color = Color.black;
		} else if (this.role == 1) {
			color = Color.GREEN;
		} else if (this.role == 2) {
			color = Color.BLUE;
		} else if (this.role == 3) {
			color = Color.PINK;
		} else if (this.role == 4) {
			color = Color.ORANGE;
		} else if (this.role == 5) {
			color = Color.CYAN;
		} else if (this.role == 6) {
			color = Color.MAGENTA;
		} else if (this.role == 7) {
			color = Color.YELLOW;
		} else if (this.role == 8) {
			color = Color.WHITE;
		}
	}

	/**
	 * @return the role of the player
	 */
	public int getRole() {
		return this.role;
	}

	/**
	 * @param x
	 * @return returns whether a player can move to a city.
	 */
	public boolean tryMoveToCity(CityNode x) {
		if (this.currentCity.isConnectedTo(x)) {
			this.currentCity = x;
			if (PandemicGame.controlledPlayer.toString().equals("Medic")
					&& PandemicGame.isCured[this.currentCity.color]) {
				this.currentCity.infectionStatus[this.currentCity.color] = 0;
			}
			return true;
		} else if (PandemicGame.p1.isFlying | PandemicGame.isOperationFlight) {
			this.currentCity = x;
			if (PandemicGame.controlledPlayer.toString().equals("Medic")
					&& PandemicGame.isCured[this.currentCity.color]) {
				this.currentCity.infectionStatus[this.currentCity.color] = 0;
			}
			PandemicGame.p1.isFlying = false;
			return false;
		} else if (PandemicGame.p1.getRole() == 3 && x.hasResearchStation) {
			this.currentCity = x;
			return true;
		} else if (PandemicGame.controlledPlayer.currentCity.hasResearchStation
				&& x.hasResearchStation && !(x.equals(PandemicGame.controlledPlayer.currentCity))) {
			this.currentCity = x;
			if (PandemicGame.controlledPlayer.toString().equals("Medic")
					&& PandemicGame.isCured[this.currentCity.color]) {
				this.currentCity.infectionStatus[this.currentCity.color] = 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the player's hand of cards
	 */
	public CardHand getHand() {
		return hand;
	}

	/**
	 * Adds a new card to a hand
	 * 
	 * @param newCard
	 */
	public void addToHand(Card newCard) {
		hand.add(newCard);
	}

	/**
	 * Removes the card from the hand, and plays the card's ability.
	 * 
	 * @param cardToUse
	 * @return whether the card may be used
	 */
	public boolean useCard(Card cardToUse) {
		if (cardToUse.getClass() == PlayerCityCard.class) {
			return true;
		} else if (cardToUse.getClass() == EventCard.class) {
			Board.useEventCard();
			hand.remove(cardToUse);
			return false;
		}
		return false;
	}

	/**
	 * @param x
	 * @return true; all flights work.
	 */
	public boolean tryFlyToCity(CityNode x) {
		if (!(this.currentCity.equals(x))) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		if (this.role == 0) {
			return "Contingency Planner";
		} else if (this.role == 1) {
			return "Dispatcher";
		} else if (this.role == 2) {
			return "Medic";
		} else if (this.role == 3) {
			return "Operations Expert";
		} else if (this.role == 4) {
			return "Quarantine Specialist";
		} else if (this.role == 5) {
			return "Researcher";
		} else if (this.role == 6) {
			return "Scientist";
		} else {
			return "Player";
		}
	}

	/**
	 * To be created. Removes the cards used to create the cure.
	 */
	public void removeCureCards() {
		// TODO Auto-generated method stub

	}

	/**
	 * Checks if a cure is viable. Currently prints if viable, will show cure
	 * later.
	 * 
	 * @return the cure that is viable, or 4 if none are.
	 */
	public int checkCure() {
		// TODO Auto-generated method stub
		int blues = 0, blacks = 0, reds = 0, yellows = 0;
		if (this.role == 6) {
			blues++;
			blacks++;
			reds++;
			yellows++;
		}

		for (Card city : hand.stored) {
			if ((city.getClass().equals(PlayerCityCard.class))) {

				if (((PlayerCityCard) city).city.color == 0) {
					blues++;
				} else if (((PlayerCityCard) city).city.color == 1) {
					blacks++;
				} else if (((PlayerCityCard) city).city.color == 2) {
					reds++;
				} else {
					yellows++;
				}
			}
		}
		if (blues > 4) {
			System.out.println("Blue Cure Viable");
			return 0;
		} else if (blacks > 4) {
			System.out.println("Black Cure Viable");
			return 1;
		} else if (reds > 4) {
			System.out.println("Red Cure Viable");
			return 2;
		} else if (yellows > 4) {
			System.out.println("Yellow Cure Viable");
			return 3;
		} else
			return 4;
	}
}
