package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 *         The button class for each city. Displays a square button over each
 *         city. Currently, when you click on it, it moves you to that city if
 *         it is a valid move - otherwise returns same city or too far away.
 *         Will also include the ability to use cards to move from city to city,
 *         as well as remove infections.
 * 
 */
public class CityButton extends JButton implements ActionListener {

	/**
	 * Serial ID for CityButton Class
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This is the node for the city that can be accessed by the board.
	 */
	public CityNode cityNode;

	/**
	 * Sets the city based on the node passed in on initialization.
	 * 
	 * @param city
	 */
	public CityButton(CityNode city) {
		// Set the city name to the city passed in
		cityNode = city;
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
		// Print the name of the city
		if (PandemicGame.p1.isFlying) {
			if (PandemicGame.p1.tryFlyToCity(cityNode)) {
				PandemicGame.p1.isFlying = false;
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.p1.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
				}
			} else {
				System.out.println("You are already at this city!");
			}
		} else {
			if (!PandemicGame.p1.tryMoveToCity(cityNode)) {
				System.out.println("Move failed, too far away or same city");
			} else {
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.p1.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
				}
			}
		}
		try {
			GameBoard.movePlayer(PandemicGame.p1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
