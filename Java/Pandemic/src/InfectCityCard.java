/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 * Infects the city based on the city of the card.
 *
 */
public class InfectCityCard extends Card {
	
	@SuppressWarnings("unused")
	private CityNode city;
	
	/**
	 * Infects the city on the card.
	 * 
	 * @param x
	 */
	public InfectCityCard(CityNode x) {
		this.city = x;
	}

}
