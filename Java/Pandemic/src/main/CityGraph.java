package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The graph of all cities on the map. Each city is stored as a city
 *         node, with its infection color and button location. This will be used
 *         for creating buttons and moving player images.
 * 
 */
public class CityGraph {
	static ArrayList<CityNode> cities = new ArrayList<CityNode>();
	HashMap<String, CityNode> citiesSet = new HashMap<String, CityNode>();

	/**
	 * Initializes the graph of cities.
	 */
	public CityGraph() {
		// Initialize all blues
		CityNode atlanta = new CityNode("Atlanta", 0, 219, 290);
		CityNode chicago = new CityNode("Chicago", 0, 188, 244);
		CityNode montreal = new CityNode("Montreal", 0, 271, 242);
		CityNode washington = new CityNode("Washington", 0, 300, 297);
		CityNode new_york = new CityNode("New York", 0, 335, 252);
		CityNode san_francisco = new CityNode("San Francisco", 0, 83, 270);
		CityNode london = new CityNode("London", 0, 488, 192);
		CityNode madrid = new CityNode("Madrid", 0, 482, 275);
		CityNode paris = new CityNode("Paris", 0, 555, 235);
		CityNode essen = new CityNode("Essen", 0, 570, 185);
		CityNode st_petersburg = new CityNode("St. Petersburg", 0, 662, 166);
		CityNode milan = new CityNode("Milan", 0, 610, 228);

		// Initialize all blacks
		CityNode algiers = new CityNode("Algiers", 1, 570, 313);
		CityNode istanbul = new CityNode("Istanbul", 1, 648, 268);
		CityNode moscow = new CityNode("Moscow", 1, 708, 226);
		CityNode cairo = new CityNode("Cairo", 1, 632, 327);
		CityNode baghdad = new CityNode("Baghdad", 1, 700, 309);
		CityNode tehran = new CityNode("Tehran", 1, 766, 264);
		CityNode riyadh = new CityNode("Riyadh", 1, 712, 380);
		CityNode karachi = new CityNode("Karachi", 1, 780, 338);
		CityNode delhi = new CityNode("Delhi", 1, 841, 315);
		CityNode mumbai = new CityNode("Mumbai", 1, 785, 395);
		CityNode kolkata = new CityNode("Kolkata", 1, 897, 338);
		CityNode chennai = new CityNode("Chennai", 1, 849, 439);

		// Initialize all reds
		CityNode bangkok = new CityNode("Bangkok", 2, 906, 405);
		CityNode jakarta = new CityNode("Jakarta", 2, 913, 507);
		CityNode sydney = new CityNode("Sydney", 2, 1094, 620);
		CityNode ho_chi_minh = new CityNode("Ho Chi Minh City", 2, 964, 457);
		CityNode manila = new CityNode("Manila", 2, 1045, 450);
		CityNode hong_kong = new CityNode("Hong Kong", 2, 957, 372);
		CityNode taipei = new CityNode("Taipei", 2, 1024, 347);
		CityNode shanghai = new CityNode("Shanghai", 2, 957, 303);
		CityNode beijing = new CityNode("Beijing", 2, 948, 238);
		CityNode seoul = new CityNode("Seoul", 2, 1011, 235);
		CityNode tokyo = new CityNode("Tokyo", 2, 1072, 267);
		CityNode osaka = new CityNode("Osaka", 2, 1082, 325);

		// Initialize all yellows CityNode los_angeles = new
		CityNode los_angeles = new CityNode("Los Angeles", 3, 100, 345);
		CityNode mexico_city = new CityNode("Mexico City", 3, 176, 375);
		CityNode miami = new CityNode("Miami", 3, 267, 360);
		CityNode bogota = new CityNode("Bogota", 3, 261, 446);
		CityNode lima = new CityNode("Lima", 3, 232, 530);
		CityNode santiago = new CityNode("Santiago", 3, 244, 625);
		CityNode buenos_aires = new CityNode("Buenos Aires", 3, 331, 609);
		CityNode sao_paulo = new CityNode("Sao Paulo", 3, 379, 548);
		CityNode lagos = new CityNode("Lagos", 3, 536, 440);
		CityNode kinshasa = new CityNode("Kinshasa", 3, 592, 485);
		CityNode johannesburg = new CityNode("Johannesburg", 3, 638, 573);
		CityNode khartoum = new CityNode("Khartoum", 3, 642, 413);
		// Add all blues to storage
		CityGraph.cities.add(atlanta);
		CityGraph.cities.add(chicago);
		CityGraph.cities.add(montreal);
		CityGraph.cities.add(washington);
		CityGraph.cities.add(new_york);
		CityGraph.cities.add(san_francisco);
		CityGraph.cities.add(london);
		CityGraph.cities.add(madrid);
		CityGraph.cities.add(paris);
		CityGraph.cities.add(essen);
		CityGraph.cities.add(st_petersburg);
		CityGraph.cities.add(milan);

		// Add all blacks to storage
		CityGraph.cities.add(algiers);
		CityGraph.cities.add(istanbul);
		CityGraph.cities.add(moscow);
		CityGraph.cities.add(cairo);
		CityGraph.cities.add(baghdad);
		CityGraph.cities.add(tehran);
		CityGraph.cities.add(riyadh);
		CityGraph.cities.add(karachi);
		CityGraph.cities.add(delhi);
		CityGraph.cities.add(mumbai);
		CityGraph.cities.add(kolkata);
		CityGraph.cities.add(chennai);

		// Add all reds to storage
		CityGraph.cities.add(jakarta);
		CityGraph.cities.add(sydney);
		CityGraph.cities.add(ho_chi_minh);
		CityGraph.cities.add(manila);
		CityGraph.cities.add(hong_kong);
		CityGraph.cities.add(taipei);
		CityGraph.cities.add(shanghai);
		CityGraph.cities.add(beijing);
		CityGraph.cities.add(seoul);
		CityGraph.cities.add(tokyo);
		CityGraph.cities.add(osaka);
		CityGraph.cities.add(bangkok);

		// Add all yellows to storage
		CityGraph.cities.add(los_angeles);
		CityGraph.cities.add(mexico_city);
		CityGraph.cities.add(miami);
		CityGraph.cities.add(bogota);
		CityGraph.cities.add(lima);
		CityGraph.cities.add(santiago);
		CityGraph.cities.add(buenos_aires);
		CityGraph.cities.add(sao_paulo);
		CityGraph.cities.add(lagos);
		CityGraph.cities.add(kinshasa);
		CityGraph.cities.add(johannesburg);
		CityGraph.cities.add(khartoum);

		// Connect all blues
		san_francisco.addConnection(chicago);
		chicago.addConnection(atlanta);
		chicago.addConnection(montreal);
		atlanta.addConnection(washington);
		montreal.addConnection(washington);
		montreal.addConnection(new_york);
		washington.addConnection(new_york);
		new_york.addConnection(madrid);
		new_york.addConnection(london);
		madrid.addConnection(london);
		madrid.addConnection(paris);
		london.addConnection(paris);
		london.addConnection(essen);
		paris.addConnection(essen);
		paris.addConnection(milan);
		essen.addConnection(milan);
		essen.addConnection(st_petersburg);

		// Bridge between black and blue

		// Connect all blacks algiers.addConnection(madrid);
		algiers.addConnection(paris);
		madrid.addConnection(algiers);
		algiers.addConnection(madrid);
		algiers.addConnection(istanbul);
		algiers.addConnection(cairo);
		istanbul.addConnection(milan);
		istanbul.addConnection(cairo);
		istanbul.addConnection(baghdad);
		istanbul.addConnection(moscow);
		istanbul.addConnection(st_petersburg);
		cairo.addConnection(baghdad);
		cairo.addConnection(riyadh);
		moscow.addConnection(st_petersburg);
		baghdad.addConnection(riyadh);
		baghdad.addConnection(karachi);
		baghdad.addConnection(tehran);
		tehran.addConnection(moscow);
		riyadh.addConnection(karachi);
		tehran.addConnection(karachi);
		tehran.addConnection(delhi);
		karachi.addConnection(delhi);
		karachi.addConnection(mumbai);
		delhi.addConnection(kolkata);
		delhi.addConnection(mumbai);
		mumbai.addConnection(chennai);
		kolkata.addConnection(chennai);
		// Connect all reds jakarta.addConnection(bangkok);
		jakarta.addConnection(ho_chi_minh);
		jakarta.addConnection(chennai);
		jakarta.addConnection(sydney);
		sydney.addConnection(manila);
		bangkok.addConnection(chennai);
		bangkok.addConnection(kolkata);
		bangkok.addConnection(ho_chi_minh);
		bangkok.addConnection(hong_kong);
		ho_chi_minh.addConnection(manila);
		ho_chi_minh.addConnection(hong_kong);
		manila.addConnection(san_francisco);
		manila.addConnection(hong_kong);
		manila.addConnection(taipei);
		hong_kong.addConnection(taipei);
		hong_kong.addConnection(shanghai);
		hong_kong.addConnection(kolkata);
		taipei.addConnection(osaka);
		taipei.addConnection(shanghai);
		osaka.addConnection(tokyo);
		shanghai.addConnection(beijing);
		shanghai.addConnection(seoul);
		shanghai.addConnection(tokyo);
		beijing.addConnection(seoul);
		seoul.addConnection(tokyo);
		tokyo.addConnection(san_francisco);

		// Connect all yellows
		los_angeles.addConnection(sydney);
		los_angeles.addConnection(mexico_city);
		los_angeles.addConnection(san_francisco);
		los_angeles.addConnection(chicago);
		mexico_city.addConnection(miami);
		mexico_city.addConnection(chicago);
		mexico_city.addConnection(bogota);
		mexico_city.addConnection(lima);
		lima.addConnection(bogota);
		santiago.addConnection(lima);
		bogota.addConnection(miami);
		bogota.addConnection(sao_paulo);
		bogota.addConnection(buenos_aires);
		miami.addConnection(atlanta);
		miami.addConnection(washington);
		buenos_aires.addConnection(sao_paulo);
		sao_paulo.addConnection(madrid);
		sao_paulo.addConnection(lagos);
		lagos.addConnection(khartoum);
		lagos.addConnection(kinshasa);
		kinshasa.addConnection(khartoum);
		kinshasa.addConnection(johannesburg);
		johannesburg.addConnection(khartoum);
		khartoum.addConnection(cairo);

		// Test
		// Initialize the HashMap for better retrieval
		for (CityNode city : cities) {
			citiesSet.put(city.getName(), city);
		}
	}

	/**
	 * @param string
	 * @return the city with the given string as its name.
	 */
	public CityNode getCity(String string) {
		// Returns the city with the given string as its name.
		return this.citiesSet.get(string);
	}
}
