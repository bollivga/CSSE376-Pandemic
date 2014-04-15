package main;
import java.awt.Graphics;

import javax.swing.JFrame;


/**
 * @author Jonathan Jungck and Greg Bollivar
 *
 */
public class GameBoard extends JFrame {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Method to move a player to its current city. Called after every move.
	 * 
	 * @param p
	 * @throws InterruptedException
	 */
	public void movePlayer(Player p) throws InterruptedException {   // Moves the player image to the new city
		    Graphics g = this.getGraphics();
		    //Board.Dispatcher.setLocation(p.currentCity.bounds[0], p.currentCity.bounds[1]);
		    g.drawImage(Board.Dispatcher, p.currentCity.bounds[0], p.currentCity.bounds[1], Board.Dispatcher.getHeight(null), Board.Dispatcher.getWidth(null), null);
		}
		/**
		 * Overridden paint method
		 * 
		 * @param g
		 */
		public void paint(Graphics g) {
			super.paint(g);
		}
		/**
		 * Method that draws the player initially
		 * 
		 * @param p
		 */
		public void spawnPlayer(Player p) {
		    Graphics g = this.getGraphics();
			g.drawImage(Board.Dispatcher, p.currentCity.bounds[0], p.currentCity.bounds[1], Board.Dispatcher.getHeight(null), Board.Dispatcher.getWidth(null), null);
			Board.background.repaint();
		}
		
		/**
		 * Draws the cards for each player.
		 */
		public static void redrawCards() {
			for (CardButton l : PandemicGame.handList) {
				Board.background.remove(l);
				Board.background.validate();
				Board.background.repaint();
			}
			int k = 0;
			for (Card j : PandemicGame.p1.hand.stored) {
				k++;
				CardButton card = new CardButton(j);
				card.addActionListener(card);
				Board.background.add(card);
				card.setBounds(300 + 140*k, 650, 140, 300);
			}
		}

}
