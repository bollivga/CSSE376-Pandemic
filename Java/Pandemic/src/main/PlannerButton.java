package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlannerButton extends JButton implements ActionListener{
	public EventCard eCard;
	public PlannerButton(EventCard j) {
		this.eCard = j;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ContinPlannerButton.hasEventCard = true;
		ContinPlannerButton.currentCard = this.eCard;
		PandemicGame.playerDiscard.removeCardFromGame(this.eCard);
	}

}
