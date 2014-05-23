package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The button class for each card. Displays a square button for each
 *         card. Currently, when you click on it, it moves you to that city if
 *         it is a valid move - otherwise returns same city or too far away.
 *         Will also include the ability to use cards to move from city to city,
 *         as well as remove infections.
 * 
 */
public class InfectCardButton extends JButton implements ActionListener {

	/**
	 * Serial ID for CityButton Class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This is the node for the city that can be accessed by the board.
	 */
	public Card card;

	/**
	 * Sets the city based on the node passed in on initialization.
	 * 
	 * @param card
	 */
	public InfectCardButton(Card card) {
		// Set the city name to the city passed in
		this.card = card;
		this.setText(card.toString());
		
			if (((InfectCityCard) this.card).city.color == 0) {
				this.setForeground(Color.WHITE);
				this.setBackground(Color.getHSBColor((float) (225.0 / 360.0),
						(float) 0.8, (float) 0.62));
			}

			// Black Cities
			else if (((InfectCityCard) this.card).city.color == 1) {
				this.setForeground(Color.WHITE);
				this.setBackground(Color.DARK_GRAY);
			}

			// Red Cities
			else if (((InfectCityCard) this.card).city.color == 2) {
				this.setForeground(Color.WHITE);
				this.setBackground(Color.getHSBColor((float) (11.0 / 360.0),
						(float) 1.0, (float) 0.65));
			}

			// Yellow Cities
			else {
				this.setForeground(Color.BLACK);
				this.setBackground(Color.getHSBColor(0, 40, (float) .5));
			}
	}

	/**
	 * Main function for citybutton, currently useless.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// use the card
		PandemicGame.infectionDiscard.removeCardFromGame(this.card);
		GameBoard.resilFrame.dispose();
		GameBoard.resilFrame.removeAll();

	}
}
