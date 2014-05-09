package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
public class AirliftCard extends JButton implements ActionListener {

	/**
	 * Serial ID for CityButton Class
	 */
	private static final long serialVersionUID = 1L;
	public static boolean isFlying;
	/**
	 * This is the node for the city that can be accessed by the board.
	 */
	public Card card;
	public Player player;

	/**
	 * Sets the city based on the node passed in on initialization.
	 * 
	 * @param card
	 */
	public AirliftCard(Card card, Player player) {
		// // Set the city name to the city passed in
		// this.player = player;
		// this.setFont(new Font("Default", 1, 10));
		// this.setText(this.player.toString());
		// this.setForeground(Color.WHITE);
		// this.setBackground(this.player.color);
		// this.card = card;
		// this.setText(card.toString());
		// //PandemicGame.handList.add(this);
	}

	/**
	 * Main function for citybutton, currently useless.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// if (PandemicGame.p1.getRole() == 1) {
		// PandemicGame.controlledPlayer = this.player;
		// System.out.println("Current player = " + this.player.toString());
		// }
		AirliftCard.isFlying = true;
	}
}