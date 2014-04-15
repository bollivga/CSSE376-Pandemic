package main;
import java.awt.Component;
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
public class CardButton extends JButton implements ActionListener {

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
	public CardButton(Card card) {
		// Set the city name to the city passed in
		this.card = card;
		this.setText(card.toString());
		PandemicGame.handList.add(this);
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
		PandemicGame.p1.useCard(this.card);
		Board.background.remove((Component) e.getSource());
        Board.background.validate();
        Board.background.repaint();    
	}
}
