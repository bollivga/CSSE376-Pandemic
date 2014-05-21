package main;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         This is the city card for players.
 * 
 */
public class PlayerCityCard implements Card {
	/**
	 * The node for each city.
	 */
	public CityNode city;

	/**
	 * @param x
	 */
	public PlayerCityCard(CityNode x) {
		this.city = x;
	}

	@Override
	public String toString() {
		return this.city.toString();
	}

}
