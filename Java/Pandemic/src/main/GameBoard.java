package main;

import java.awt.BorderLayout;
import java.awt.Component;
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

	private static ContinPlannerButton plannerButton = new ContinPlannerButton();

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
		if ((PandemicGame.p1.checkCure() < 4)
				&& (PandemicGame.p1.currentCity.hasResearchStation)) {
			Board.cureButton = new CureButton();
			Board.background.add(Board.cureButton);
			Board.cureButton.addActionListener(Board.cureButton);
			// background.add(Board.cureButton);
			Board.cureButton.setBounds(500, 500, 100, 50);

		} else {
			Board.background.remove(Board.cureButton);
		}
		if (!PandemicGame.isSetup) {
			if (PandemicGame.p1.getRole() == 3
					&& !PandemicGame.p1.currentCity.hasResearchStation) {
				Board.background.add(stationButton);
				try {
					Board.background.remove(plannerButton);
				} finally {

				}
			} else if (PandemicGame.p1.getRole() == 0
					&& (PandemicGame.discardedEventCount > 0 || ContinPlannerButton.hasEventCard == true)) {
				Board.background.add(plannerButton);
				plannerButton.refreshCard();
				try {

					Board.background.remove(stationButton);
				} finally {

				}
			} else {
				try {
					Board.background.remove(plannerButton);
					Board.background.remove(stationButton);
				} finally {
				}
			}
		}else{
			
		}
	}

	/**
	 * Research Button
	 */
	public static JButton showResearch;

	public static JFrame resilFrame;

	public static JPanel resil;

	public static ArrayList<InfectCardButton> resilButtons;

	public static JFrame eventFrame;

	private static JPanel eventP;

	private static ArrayList<PlannerButton> eventButtons;

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
		if (!PandemicGame.isGerman) {
			handFrame = new JFrame("(Player "
					+ (PandemicGame.currentPlayer + 1) + ") "
					+ PandemicGame.p1.toString() + "'s Hand");
		} else {
			handFrame = new JFrame("(Spieler-"
					+ (PandemicGame.currentPlayer + 1) + ") "
					+ PandemicGame.p1.toString() + " Hand");
		}
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
	}

	public static void showResilPop() {
		if (!PandemicGame.isGerman) {
			resilFrame = new JFrame("Resilient Population");
		} else {
			resilFrame = new JFrame("Elastische Bevölkerung");
		}
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
			resilFrame.setSize(180 * 7 + 20, (1 + (k / 7)) * 300);
		}
		resilFrame.setVisible(true);
		resil.setVisible(true);
	}

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

	public static void plannerGet() {
		if(!(eventFrame == null)){
			eventFrame.dispose();
		}
		if (!PandemicGame.isGerman) {
			eventFrame = new JFrame("Event Cards");
		} else {
			eventFrame = new JFrame("Ereigniskarten");
		}
		eventP = new JPanel();
		eventP.setSize(180 * PandemicGame.discardedEventCount, 300);
		eventP.setLayout(new BorderLayout(180, 300));
		eventFrame.setLayout(new BorderLayout(180, 300));
		eventButtons = new ArrayList<PlannerButton>();
		
		int k = 0;
		for (Card j : PandemicGame.playerDiscard.stored) {
			if ((j instanceof GovernmentGrantCard) || (j instanceof AirliftCard) || (j instanceof SilentNightCard) || (j instanceof ResilPopCard)) {
				PlannerButton card = new PlannerButton((EventCard) j);
				card.addActionListener(card);
				eventButtons.add(card);
				eventFrame.add(card);
				System.out.println("Should add "+j.toString());
				card.setBounds(0 + 180 * k, 0, 180, 300);
				k++;
			}
		}
		eventFrame.add(eventP);
		eventFrame.setSize(180 * k + 20, 300);

		eventFrame.setVisible(true);
		eventP.setVisible(true);
		eventFrame.repaint();

	}

}
