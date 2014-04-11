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
	 * @param city
	 */
	public CardButton(Card card) {
		// Set the city name to the city passed in
		this.card = card;
		this.setText(card.toString());
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
		PandemicGame.p1.useCard(this.card);
//		if (PandemicGame.p1.isFlying) {
//			if (!PandemicGame.p1.tryFlyToCity(cityNode)) {
//				System.out.println("Move succeeded, " + PandemicGame.p1.toString() + " has moved to " + cityNode.name + ".");
//			}
//		}
//		if (!PandemicGame.p1.tryMoveToCity(cityNode)) {
//			System.out.println("Move failed, too far away or same city");
//		} else {
//			++PandemicGame.currentMoves;
//			System.out.println(PandemicGame.p1.toString() + " has moved to "
//					+ cityNode.getName() + ". "
//					+ (4 - PandemicGame.currentMoves) + " moves left.");
//			if (PandemicGame.currentMoves == 4) {
//				Board.changePlayer();
//			}
//		}
	}
}
