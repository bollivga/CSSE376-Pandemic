package main;

public class GovernmentGrantCard implements EventCard {

	@Override
	public void trigger() {
		PandemicGame.govtGrant = true;

		PandemicGame.discardedEventCount++;
		PandemicGame.playerDiscard.add(this);
	}

	@Override
	public String toString() {
		if (!PandemicGame.isGerman) {
			return "Government Grant";
		} else {
			return "Zuwendung";
		}
	}
}
