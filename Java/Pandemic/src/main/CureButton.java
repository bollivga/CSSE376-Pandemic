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
	public static boolean isSelecting;
	/**
	 * The number of cards needed to cure the disease. Differs based on class.
	 */
	public static int cardsLeft;
	/**
	 * The color that is currently having a cure developed
	 */
	public static int colorCuring;
	/**
	 * This specific cure button
	 */
	public static CureButton me;

	/**
	 * Creates the button for the cure.
	 */
	public CureButton() {
		this.setFont(new Font("Default", 1, 10));
		if(!PandemicGame.isGerman){
		this.setText("Cure Disease");
		}else{
			this.setText("Krankheit heilen");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CureButton.isSelecting = true;
		if (PandemicGame.p1.getRole() == 6) {
			CureButton.cardsLeft = 4;
		} else {
			CureButton.cardsLeft = 5;
		}
		CureButton.colorCuring = PandemicGame.p1.checkCure();
		me = this;
		
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