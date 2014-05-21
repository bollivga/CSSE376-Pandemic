package main;

public class GovernmentGrantCard implements EventCard {

	@Override
	public void trigger() {
		PandemicGame.govtGrant = true;

	}
	@Override
	public String toString(){
		return "Government Grant";
	}
}
