import java.util.ArrayList;

public class CityNode {
	String name = "";
	int color;
	int[] bounds;
	int[] infectionStatus = { 0, 0, 0, 0 };// blue,black,red,yellow
	ArrayList<CityNode> connectedCities = new ArrayList<CityNode>();
	boolean[] hasOutbroken = { false, false, false, false };

	public CityNode(String cityName, int diseaseColor, int x, int y) {
		int[] bounds = { x, y };
		this.bounds = bounds;
		name = cityName;
		color = diseaseColor;
	}

	public void addConnection(CityNode cityNode) {
		this.connectedCities.add(cityNode);
		cityNode.connectedCities.add(this);
	}

	public String getName() {
		return this.name;
	}

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
				return true;
			} else {
				++this.infectionStatus[color];
			}
		}
		return false;

	}

	public boolean infectTwice() {
		// Double infect
		// only used on startup infections, but referred to by infectThrice
		boolean x = this.infectOnce();
		boolean y = this.infectOnce();
		return (x || y);
	}

	public boolean infectThrice() {
		// Triple infect
		// Used for startup infections and epidemic infections.
		boolean x = this.infectTwice();
		boolean y = this.infectOnce();
		return (x || y);
	}

	public boolean isConnectedTo(CityNode x) {
		// Returns whether x is directly connected
		return this.connectedCities.contains(x);
	}

	public void resetOutbreaks() {
		for (int i = 0; i < this.hasOutbroken.length; ++i) {
			this.hasOutbroken[i] = false;
		}
	}
}
