package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class ContinPlannerButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	public static boolean hasEventCard = false;
	/**
	 * 
	 */
	public static EventCard currentCard;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4980630928773970384L;

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (hasEventCard) {
			currentCard.trigger();
			hasEventCard = false;
			++PandemicGame.currentMoves;
			--PandemicGame.discardedEventCount;
			if (PandemicGame.currentMoves == 4) {
				Board.changePlayer();
			}
		} else {
			GameBoard.plannerGet();
		}
		this.refreshCard();
	}

	/**
	 * Main class for creating the button
	 */
	public ContinPlannerButton() {
		this.setBounds(950, 750, 200, 50);

		this.refreshCard();

		this.addActionListener(this);

	}

	private void refreshCard() {
		if (PandemicGame.discardedEventCount > 0) {
			if (!PandemicGame.isGerman) {
				if (hasEventCard) {
					this.setText("Use Event card");
				} else {
					this.setText("Take Event Card");
				}
			} else {
				if (hasEventCard) {
					this.setText("Use event card");
				} else {
					this.setText("Nehmen Ereigniskarte");
				}
			}
			Board.background.add(this);
		} else {
			if (Board.background != null) {
				try {
					Board.background.remove(this);
				} finally {

				}
			}
		}
	}
}
