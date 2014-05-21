package main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class GameBoard extends JFrame implements MouseListener {

	/**
	 * Shows a visual panel with the hand of cards
	 */
	public static JPanel hand;
	
	/**
	 * 
	 */
	public static OpExpertStationButton stationButton = new OpExpertStationButton();
	
	/**
	 * The list of gui elements for the hand.
	 */
	public static ArrayList<CardButton> handButtons;

	/**
	 * Also used for the hand GUI
	 */
	public static JFrame handFrame = new JFrame();

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
	public static void movePlayer() throws InterruptedException {
		// Moves the player image to the new city
		Board.frame.repaint();
		if((PandemicGame.p1.checkCure() < 4) && (PandemicGame.p1.currentCity.hasResearchStation)){
			Board.cureButton = new CureButton();
			Board.background.add(Board.cureButton);
			Board.cureButton.addActionListener(Board.cureButton);
			//background.add(Board.cureButton);
			Board.cureButton.setBounds(500, 500, 100, 50);
			
		}else{
			Board.background.remove(Board.cureButton);
		}
		if (!PandemicGame.isSetup) {
			if (PandemicGame.p1.getRole() == 3
					&& !PandemicGame.p1.currentCity.hasResearchStation) {
				Board.background.add(stationButton);

			} else {
				Board.background.remove(stationButton);
			}
		}
	}

	/**
	 * Research Button
	 */
	public static JButton showResearch;

	public static JFrame resilFrame;

	public static JPanel resil;

	public static ArrayList<InfectCardButton> resilButtons;

	/**
	 * Overridden paint method
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		try {
			super.paint(g);

		} catch (NullPointerException x) {
			System.out.println("Exception.");
		}
		try {
			for (Player x : PandemicGame.playerStorage) {

				g2.setColor(x.color);
				Ellipse2D.Double player = new Ellipse2D.Double(
						x.currentCity.bounds[0] + 2
								+ PandemicGame.playerStorage.indexOf(x) * 3,
						x.currentCity.bounds[1], 20, 40);
				g2.fill(player);
			}
		} catch (NullPointerException x) {
			System.out.println("Exception.");
		}

		// Board.background.repaint();
	}

	/**
	 * Method that draws the player initially
	 * 
	 * @param p
	 */
	public static void spawnPlayer(Player p) {
		Board.frame.paint(Board.frame.getGraphics());
	}

	/**
	 * Draws the cards for each player.
	 */
	public static void redrawCards() {
		handButtons = new ArrayList<CardButton>();
		handFrame.dispose();
		handFrame.removeAll();
		handFrame = new JFrame("(Player " + (PandemicGame.currentPlayer + 1)
				+ ") " + PandemicGame.p1.toString() + "'s Hand");
		hand = new JPanel();
		hand.setSize(180 * PandemicGame.p1.hand.stored.size(), 300);
		hand.setLayout(new BorderLayout(180, 300));
		handFrame.setLayout(new BorderLayout(180, 300));
		int k = 0;
		for (Card j : PandemicGame.p1.hand.stored) {
			CardButton card = new CardButton(j);
			card.addActionListener(card);
			handButtons.add(card);
			handFrame.add(card);
			if (k < 7) {
				card.setBounds(0 + 180 * k, 0, 180, 300);
			} else {
				card.setBounds(0 + 180 * (k - 7), 300, 180, 300);
			}
			k++;
		}
		handFrame.add(hand);
		if (k < 8) {
			handFrame.setSize(180 * PandemicGame.p1.hand.stored.size() + 20,
					300);
		} else {
			handFrame.setSize(180 * 7 + 20, 600);
		}
		handFrame.setVisible(true);
		hand.setVisible(true);
		// Card card = PandemicGame.p1.hand.stored.get(0);
		// @SuppressWarnings("unused")
		// AirliftCard alc = new AirliftCard(card, PandemicGame.p1);
	}
	
	public static void showResilPop(){
		resilFrame = new JFrame("Resilient Population");
		resil = new JPanel();
		resil.setSize(180 * PandemicGame.infectionDiscard.stored.size(), 300);
		resil.setLayout(new BorderLayout(180, 300));
		resilFrame.setLayout(new BorderLayout(180, 300));
		resilButtons = new ArrayList<InfectCardButton>();
		int k = 0;
		for (Card j : PandemicGame.infectionDiscard.stored) {
			InfectCardButton card = new InfectCardButton(j);
			card.addActionListener(card);
			resilButtons.add(card);
			resilFrame.add(card);
			if (k < 7) {
				card.setBounds(0 + 180 * k, 0, 180, 300);
			} else {
				card.setBounds(0 + 180 * (k % 7), 300, 180, 300);
			}
			k++;
		}
		resilFrame.add(resil);
		if (k < 8) {
			resilFrame.setSize(180 * k + 20, 300);
		} else {
			resilFrame.setSize(180 * 7 + 20, (1+(k / 7))*300);
		}
		resilFrame.setVisible(true);
		resil.setVisible(true);
	}
	// public static void redrawCards() {
	// for (CardButton l : PandemicGame.handList) {
	// Board.background.remove(l);
	// }
	// Board.background.validate();
	// Board.background.repaint();
	// hand = new JFrame();
	// hand.setSize(1200, 849);
	// hand.setLayout(new BorderLayout());
	// int k = 0;
	// for (Card j : PandemicGame.p1.hand.stored) {
	// k++;
	// CardButton card = new CardButton(j);
	// card.addActionListener(card);
	// Board.background.add(card);
	// card.setBounds(300 + 140 * k, 650, 140, 300);
	// }
	// Card card = PandemicGame.p1.hand.stored.get(0);
	// @SuppressWarnings("unused")
	// AirliftCard alc = new AirliftCard(card, PandemicGame.p1);
	// }

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
