package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class GameBoard extends JFrame implements MouseListener {

	/**
	 * The graphics object for the game board.
	 */
	public Graphics g = this.getGraphics();
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Graphics2D g2 = (Graphics2D) Board.background.getGraphics();
		// g2.setColor(Color.GREEN);
		// g2.fill(new Ellipse2D.Double(PandemicGame.p1.currentCity.bounds[0],
		// PandemicGame.p1.currentCity.bounds[1], 500, 500));
		// Board.background.repaint();

	}

	/**
	 * Method to move a player to its current city. Called after every move.
	 * 
	 * @param p
	 * @throws InterruptedException
	 */
	public static void movePlayer() throws InterruptedException { // Moves the
																	// player
																	// image to
																	// the new
																	// city
		Graphics2D g2 = (Graphics2D) Board.background.getGraphics();
		for (Player x : PandemicGame.playerStorage) {
			g2.setColor(x.color);
			Ellipse2D.Double player = new Ellipse2D.Double(
					x.currentCity.bounds[0], x.currentCity.bounds[1] - 30, 20,
					40);
			g2.fill(player);
		}
	}

	/**
	 * Overridden paint method
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		super.paint(g);
		this.addMouseListener(this);

		// Board.background.repaint();
	}

	/**
	 * Method that draws the player initially
	 * 
	 * @param p
	 */
	public static void spawnPlayer(Player p) {
		Board.background.getGraphics();
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
			card.setBounds(300 + 140 * k, 650, 140, 300);
		}
		Card card = PandemicGame.p1.hand.stored.get(0);
		AirliftCard alc = new AirliftCard(card, PandemicGame.p1);
	}

	// /**
	// * Draws the cards for each player.
	// */
	// public static void redrawPlayers() {
	// for (Ellipse2D.Double l : Board.playerList) {
	// Board.background.remove(9);
	// }
	// Board.background.validate();
	// Board.background.repaint();
	// GameBoard.spawnPlayer(PandemicGame.p1);
	// }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + ", " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
