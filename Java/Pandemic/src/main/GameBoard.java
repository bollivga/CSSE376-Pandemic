package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;


/**
 * @author Jonathan Jungck and Greg Bollivar
 *
 */
public class GameBoard extends JFrame {

	public Graphics g = this.getGraphics();
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Graphics2D g2 = (Graphics2D) Board.background.getGraphics();
			g2.setColor(Color.GREEN);
		 g2.fill(new Ellipse2D.Double(PandemicGame.p1.currentCity.bounds[0], PandemicGame.p1.currentCity.bounds[1], 500, 500));
		 // Board.background.repaint();
	}
	
	/**
	 * Method to move a player to its current city. Called after every move.
	 * 
	 * @param p
	 * @throws InterruptedException
	 */
	public static void movePlayer(Player p) throws InterruptedException {   // Moves the player image to the new city
		    //Graphics g = this.getGraphics();
		    //Board.Dispatcher.setLocation(p.currentCity.bounds[0], p.currentCity.bounds[1]);
		    //g.drawImage(Board.Dispatcher, p.currentCity.bounds[0], p.currentCity.bounds[1], Board.Dispatcher.getHeight(null), Board.Dispatcher.getWidth(null), null);
		    //Graphics2D g2 = (Graphics2D) g;
		    //g2.draw(new Ellipse2D.Double(p.currentCity.bounds[0], p.currentCity.bounds[1], 20, 40));
		Graphics2D g2 = (Graphics2D) Board.background.getGraphics();
		//g2.clearRect(PandemicGame.p1.currentCity.bounds[0], PandemicGame.p1.currentCity.bounds[1] - 30, 20, 40);
	    //g2.draw(new Ellipse2D.Double(PandemicGame.p1.currentCity.bounds[0], PandemicGame.p1.currentCity.bounds[1] - 30, 20, 40));
		g2.setColor(Color.GREEN);
	    Ellipse2D.Double player = new Ellipse2D.Double(PandemicGame.p1.currentCity.bounds[0], PandemicGame.p1.currentCity.bounds[1] - 30, 20, 40);
	    g2.fill(player);
	 
	}
		/**
		 * Overridden paint method
		 * 
		 * @param g
		 */
		public void paint(Graphics g) {
			super.paint(g);
			    //Board.background.repaint();
		}
		/**
		 * Method that draws the player initially
		 * 
		 * @param p
		 */
		public static void spawnPlayer(Player p) {
		    Board.background.getGraphics();
		    //Graphics2D g2 = (Graphics2D) Board.background.getGraphics();
		    //g2.draw(new Ellipse2D.Double(p.currentCity.bounds[0], p.currentCity.bounds[1]-40, 20, 40));
			//g.drawImage(Board.Dispatcher, p.currentCity.bounds[0], p.currentCity.bounds[1], null);
			//Board.frame.add(Board.Dispatcher, p.currentCity.bounds[0], p.currentCity.bounds[1]);
			//Board.background.repaint();
		}
		
		/**
		 * Draws the cards for each player.
		 */
		public static void redrawCards() {
			for (CardButton l : PandemicGame.handList) {
				Board.background.remove(l);
			}
			Board.background.validate();
			Board.background.repaint();
			int k = 0;
			for (Card j : PandemicGame.p1.hand.stored) {
				k++;
				CardButton card = new CardButton(j);
				card.addActionListener(card);
				Board.background.add(card);
				card.setBounds(300 + 140*k, 650, 140, 300);
			}
			GameBoard.spawnPlayer(PandemicGame.p1);
		}

}
