package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The button class for the cure. If the player has a cure for this
 *         city, then this button will be visible on screen. If you click the
 *         button, it sends you into a mode where the next number of cards you
 *         select from your deck become part of the cure. Once you have selected
 *         the cards you wish to use, a cure is made and the cards are taken
 *         from your hand.
 * 
 */
public class CureButton extends JButton implements ActionListener {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -3645875276117826237L;
	/**
	 * The player is selecting cards to cure.
	 */
	public boolean isSelecting;
	/**
	 * The number of cards needed to cure the disease. Differs based on class.
	 */
	public int cardsLeft;

	/**
	 * Creates the button for the cure.
	 */
	public CureButton() {
		this.setFont(new Font("Default", 1, 10));
		this.setText("Cure Disease");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.isSelecting = true;
		if (PandemicGame.p1.getRole() == 5) {
			this.cardsLeft = 4;
		} else {
			this.cardsLeft = 5;
		}
	}

	/**
	 * @return when a cure is made
	 */
	public boolean makeCure() {
		if (PandemicGame.isCured[PandemicGame.p1.currentCity.color]) {
			return false;
		} else {
			PandemicGame.isCured[PandemicGame.p1.currentCity.color] = true;
			PandemicGame.p1.removeCureCards();
			return true;
		}
	}

}