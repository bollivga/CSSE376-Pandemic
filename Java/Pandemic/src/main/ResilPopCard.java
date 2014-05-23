package main;

public class ResilPopCard implements EventCard {

	@Override
	public void trigger() {
		GameBoard.showResilPop();

		PandemicGame.discardedEventCount++;
		PandemicGame.playerDiscard.add(this);
	}
	@Override
	public String toString(){
		if(!PandemicGame.isGerman){
		return "Resilient Population";
		}
		else{
			return "Elastische Bevölkerung";
		}
	}

}
