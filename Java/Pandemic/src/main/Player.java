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
	CardHand hand;
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
		this.hand = new CardHand();
		if (this.role == 1) {
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
			return true;
		}
		return false;
	}

	/**
	 * @return the player's hand of cards
	 */
	public CardHand getHand() {
		return this.hand;
	}

	/**
	 * Adds a new card to a hand
	 * 
	 * @param newCard
	 */
	public void addToHand(Card newCard) {
		this.hand.add(newCard);
	}

	/**
	 * Removes the card from the hand, and plays the card's ability.
	 * 
	 * @param cardToUse
	 * @return whether the card may be used
	 */
	public boolean useCard(Card cardToUse) {
		if (cardToUse.getClass() == PlayerCityCard.class) {
			CityNode city = ((PlayerCityCard) cardToUse).city;
			if (this.currentCity == city) {
				this.isFlying = true;
				this.hand.remove(cardToUse);
				return false;
			} else {
				if (this.tryFlyToCity(city)) {
					++PandemicGame.currentMoves;
					if (PandemicGame.currentMoves == 4) {
						Board.changePlayer();
					}
					try {
						this.hand.remove(cardToUse);
					} catch (ArrayIndexOutOfBoundsException e) {
						// should be a test if this happens
					} finally {

					}
					return true;
				}
				return false;
			}
		} else if (cardToUse.getClass() == EventCard.class) {
			Board.useEventCard();
			this.hand.remove(cardToUse);
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
			this.currentCity = x;
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
}
