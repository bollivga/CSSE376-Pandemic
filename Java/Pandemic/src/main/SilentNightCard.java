package main;

public class SilentNightCard implements EventCard {

	@Override
	public void trigger() {
		PandemicGame.oneQuietNight = true;
	}
	@Override
	public String toString(){
		return "One Quiet Night";
	}
}
