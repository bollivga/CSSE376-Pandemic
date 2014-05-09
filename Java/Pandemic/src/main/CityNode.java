package main;

import java.util.ArrayList;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The CityNode class for Pandemic. Each City has a node, which contains
 *         it's bounds on the board (x and y coordinates) as well as the city's
 *         name and disease color and number of diseases. Each city also has an
 *         ArrayList of connected cities.
 * 
 */
public class CityNode {
	String name = "";
	/**
	 * The color of the city infection disease.
	 */
	public int color;
	public boolean hasResearchStation = false;
	int[] bounds;
	/**
	 * The infection statuses of each disease color on each city.
	 */
	public int[] infectionStatus = { 0, 0, 0, 0 };// blue,black,red,yellow
	/**
	 * The array of connected cities. It's a graph.
	 */
	public ArrayList<CityNode> connectedCities = new ArrayList<CityNode>();
	/**
	 * Checks if any of the diseases have outbroken.
	 */
	public boolean[] hasOutbroken = { false, false, false, false };

	/**
	 * Initializes a city based on passed in variables.
	 * 
	 * @param cityName
	 * @param diseaseColor
	 * @param x
	 * @param y
	 */
	public CityNode(String cityName, int diseaseColor, int x, int y) {
		int[] bounds = { x, y };
		this.bounds = bounds;
		name = cityName;
		color = diseaseColor;
	}

	/**
	 * Adds in connections to other cities.
	 * 
	 * @param cityNode
	 */
	public void addConnection(CityNode cityNode) {
		this.connectedCities.add(cityNode);
		cityNode.connectedCities.add(this);
	}

	/**
	 * @return the name of the city.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return true if there is an outbreak. Used every time there is an
	 *         infection.
	 */
	public boolean infectOnce() {
		// Returns true if there is an outbreak
		// Used every time there's an infection
		if (!PandemicGame.isEradicated[this.color]) {
			if (!this.hasOutbroken[color]) {
				if (this.infectionStatus[this.color] >= 3) {
					this.hasOutbroken[this.color] = true;
					for (CityNode city : connectedCities) {
						city.infectOnce(this.color);
					}
					++PandemicGame.outbreakCount;
					return true;
				} else {
					++this.infectionStatus[this.color];
				}
			}
		}
		return false;
	}

	private boolean infectOnce(int color) {
		if (!this.hasOutbroken[color]) {
			if (this.infectionStatus[color] == 3) {
				this.hasOutbroken[color] = true;
				for (CityNode city : this.connectedCities) {
					city.infectOnce(color);
				}
				++PandemicGame.outbreakCount;
				return true;
			} else {
				++this.infectionStatus[color];
			}
		}
		return false;

	}

	/**
	 * @return Infects a city twice. Used during startup when cities are set up
	 *         with infections.
	 */
	public boolean infectTwice() {
		// Double infect
		// only used on startup infections, but referred to by infectThrice
		boolean x = this.infectOnce();
		boolean y = this.infectOnce();
		return (x || y);
	}

	/**
	 * @return Infects the city three times at the start.
	 */
	public boolean infectThrice() {
		// Triple infect
		// Used for startup infections and epidemic infections.
		boolean x = this.infectTwice();
		boolean y = this.infectOnce();
		return (x || y);
	}

	/**
	 * @param x
	 * @return If this city is connected to another city.
	 */
	public boolean isConnectedTo(CityNode x) {
		// Returns whether x is directly connected
		return this.connectedCities.contains(x);
	}

	/**
	 * Resets all booleans that state outbreaks. Used at the end of every
	 * infection phase.
	 */
	public void resetOutbreaks() {
		for (int i = 0; i < this.hasOutbroken.length; ++i) {
			this.hasOutbroken[i] = false;
		}
	}

	@Override
	public String toString() {
		return this.name;
	}

	public boolean cureOnce() {
		// TODO Auto-generated method stub
		if (this.infectionStatus[this.color] == 0) {
				System.out.println("This city is already safe");
				return false;
			} else {
				System.out.println("remove");
				--this.infectionStatus[color];
			}
		return true;
	}

	public boolean cureAll() {
		if (this.infectionStatus[this.color] == 0) {
			System.out.println("This city is already safe");
			return false;
		} else {
			System.out.println("remove all");
			this.infectionStatus[color] = 0;
		}
	return true;
	}
}
