package main;

public class ResilPopCard implements EventCard {

	@Override
	public void trigger() {
		GameBoard.showResilPop();

	}
	@Override
	public String toString(){
		return "Resilient Population";
	}

}
