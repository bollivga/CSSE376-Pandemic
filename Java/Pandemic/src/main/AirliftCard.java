package main;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The button class for each card. Displays a square button for each
 *         card. Currently, when you click on it, it moves you to that city if
 *         it is a valid move - otherwise returns same city or too far away.
 *         Will also include the ability to use cards to move from city to city,
 *         as well as remove infections.
 * 
 */
public class AirliftCard implements EventCard {

	/**
	 * Airlift card is in use if this boolean is true.
	 */
	public static boolean isFlying;
	/**
	 * This is the node for the city that can be accessed by the board.
	 */
	public Card card;
	/**
	 * The player using the airlift card.
	 */
	public Player player;

	/**
	 * Sets the city based on the node passed in on initialization.
	 */
	public AirliftCard() {
	}

	/**
	 * Main function for citybutton, currently useless.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override
	public String toString() {
		if (!PandemicGame.isGerman) {
			return "Airlift";
		} else {
			return "Luftbrücke";
		}
	}

	@Override
	public void trigger() {
		AirliftCard.isFlying = true;
	}
}