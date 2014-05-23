package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PlannerButton extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EventCard eCard;
	
	public PlannerButton(EventCard card) {
		// Set the city name to the city passed in
		this.eCard = card;
		this.setText(card.toString());
		this.setForeground(Color.black);
		this.setBackground(Color.green);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ContinPlannerButton.hasEventCard = true;
		ContinPlannerButton.currentCard = this.eCard;
		PandemicGame.playerDiscard.removeCardFromGame(this.eCard);
		GameBoard.eventFrame.dispose();
	}

}
