package main;

public class SilentNightCard implements EventCard {

	@Override
	public void trigger() {
		PandemicGame.oneQuietNight = true;
	}
	@Override
	public String toString(){
		if(!PandemicGame.isGerman){
		return "One Quiet Night";
		}else{
			return "Eine ruhige Nacht";
		}
	}
}
