package main;
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
	
	
	/**
	 * Infects this card's city
	 * @return  outbreak?
	 */
	public boolean infect(){
		return this.city.infectOnce();
	}
	/**
	 * Infects this card's city twice; used only for setup
	 * @return outbreak?
	 */
	public boolean infectTwice(){
		return this.city.infectTwice();
	}
	/**
	 * Infects this card's city three times; used for setup and epidemics.
	 * @return outbreak?
	 */
	public boolean infectThrice(){
		return this.city.infectThrice();
	}
	
	@Override
	public String toString()
	{
		return this.city.toString();
	}
}
