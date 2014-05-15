package main;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
@SuppressWarnings("unused")
public class Board {

	static Image Dispatcher = new ImageIcon("player.jpg").getImage();
	/**
	 * Background is static to allow access from drawing components.
	 */
	public static JLabel background;
	/**
	 * The main frame in which everything is drawn.
	 */
	public static GameBoard frame;
	/**
	 * The stored location of the players
	 */
	public static Player[] playerLoc = new Player[4];
	@SuppressWarnings("javadoc")
	public static int i = 0;
	/**
	 * The list of buttons for each city.
	 */
	public static ArrayList<CityButton> cityList = new ArrayList<CityButton>();
	/**
	 * The list of player ellipses in the game. Unused as of yet.
	 */
	public static ArrayList<Ellipse2D.Double> players;

	/**
	 * The buttons used by the Dispatcher special ability.
	 */
	public static ArrayList<DispatcherButton> dispatcherList = new ArrayList<DispatcherButton>();
	/**
	 * The number of cards you want to discard
	 */
	public static int discardAmount;
	/**
	 * Tells you if the player is currently discarding
	 */
	public static boolean discarding;
	/**
	 * Keeps track of the last card discarded for GUI purposes
	 */
	public static Card lastDiscard;

	/**
	 * The main class for the board. Draws the frame and background and iterates
	 * all buttons on cities based on graph information.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// JFrame frame = new JFrame();
		Board.init();
		Board.chooseRoles();
		PandemicGame.handOutCards();
		Board.runGame();

		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Runs the main game.
	 */
	public static void runGame() {
		Board.frame.repaint();
		playerLoc[i] = PandemicGame.p1;
		// GameBoard.redrawPlayers();

		// Initialize all city buttons on the map from CityGraph
		for (CityNode j : CityGraph.cities) {
			CityButton city = new CityButton(j);
			city.addActionListener(city);
			background.add(city);
			city.setBounds(j.bounds[0] - 10, j.bounds[1] - 10, 40, 40);
			cityList.add(city);
		}
		Board.cityList.get(0).setBackground(
				Board.cityList.get(0).getBackground().darker());
		for (Player p : PandemicGame.playerStorage) {
			DispatcherButton x = new DispatcherButton(p);
			Board.dispatcherList.add(x);
			x.addActionListener(x);
			x.setBounds(50, 10 + (PandemicGame.playerStorage.indexOf(p) * 40),
					150, 40);
			if (PandemicGame.p1.getRole() == 1) {
				background.add(x);
			}
		}
		// int k = 0;
		// for (Card j : PandemicGame.p1.hand.stored) {
		// k++;
		// CardButton card = new CardButton(j);
		// card.addActionListener(card);
		// background.add(card);
		// card.setBounds(300 + 140*k, 650, 140, 300);
		// }

		// Test Player Graphic
		// JLabel player = new JLabel(new ImageIcon("src/player.jpg"));
		// player.setBounds(280, 297, 20, 50);
		// player.setLayout(BorderLayout.NORTH);
		// frame.add(player);

		// If you try to close the window, it verifies with a yes/no and then
		// quits if yes.
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit?", "Quit?",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						null, ObjButtons, ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					// Don't close the program?
				}
			}
		});
		Board.frame.paint(Board.frame.getGraphics());
		PandemicGame.setupInfections();
		cityList.get(25).doClick();
	}

	/**
	 * The graphic dialogue for choosing each role.
	 */
	public static void chooseRoles() {
		ArrayList<String> chooseRoles = new ArrayList<String>();
		// role 0
		chooseRoles.add("Contingency Planner");
		// role 1
		chooseRoles.add("Dispatcher");
		// role 2
		chooseRoles.add("Medic");
		// role 3
		chooseRoles.add("Operations Expert");
		// role 4
		chooseRoles.add("Quarantine Specialist");
		// role 5
		chooseRoles.add("Researcher");
		// role 6
		chooseRoles.add("Scientist");
		chooseRoles.add("No More Players");

		int i = 0;
		Object[] list = new Object[8];

		for (String role : chooseRoles) {
			list[i] = role;
			i++;
		}

		i = 1;
		while (i < 5) {
			String s = (String) JOptionPane
					.showInputDialog(
							frame,
							"Please select  the role for Player "
									+ i
									+ ".\n"
									+ "To finish and choose less than four players, select No More Players.",
							"Role Selection", JOptionPane.PLAIN_MESSAGE, null,
							list, "Contingency Planner");
			if (s == "No More Players") {
				if (i > 2) {
					JFrame finish = new JFrame();
					String nextPlayer = PandemicGame.playerStorage.get(
							PandemicGame.currentPlayer).toString();
					JOptionPane.showMessageDialog(finish,
							"All players have been selected. There are "
									+ (i - 1) + " players in the game.\n"
									+ "It is now Player 1, the " + nextPlayer
									+ "'s turn.");
					i = 5;
				} else {
					JFrame finish = new JFrame();
					JOptionPane.showMessageDialog(finish,
							"You must have at least two players!");
				}
			} else {
				int oldLength = list.length;
				Object[] newList = new Object[oldLength - 1];
				int k = 0;
				for (Object obj : list) {
					if (obj != s) {
						newList[k] = obj;
						k++;
					}
				}
				//
				PandemicGame.addPlayer(s);
				i++;
				list = newList;
			}
		}

	}

	/**
	 * Initializes the gameboard.
	 */
	public static void init() {
		Board.frame = new GameBoard();
		Board.frame.setSize(1200, 849);
		Board.frame.setLayout(new BorderLayout());

		// Draw the background board on the frame.
		Board.background = new JLabel(new ImageIcon("src/board.jpg"));
		Board.frame.add(background, BorderLayout.CENTER);
		Board.frame.setVisible(true);
		Board.background.setLayout(null);

		JButton showHand = new JButton("Show Hand");
		Board.background.add(showHand);
		showHand.setBounds(10, 750, 100, 50);
		showHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PandemicGame.p1.hand.stored.size() > 0) {
					GameBoard.redrawCards();
				} else {
					System.out.println("The " + PandemicGame.p1.toString()
							+ " has no more cards!");
				}
			}
		});

		// Initialize a new game.
		new PandemicGame();
	}

	/**
	 * When it changes to the next player, we notify the user.
	 */
	public static void changePlayer() {
		// Gives a notification that it is the next player's turn.

		discarding = false;
		PandemicGame.p1.checkCure();
		JFrame frame = new JFrame();
		if (PandemicGame.p1.getRole() == 1) {
			for (DispatcherButton x : Board.dispatcherList) {
				background.remove(x);
			}
		}
		try {
			GameBoard.movePlayer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PandemicGame.drawPlayerCards();

		int handSize = PandemicGame.p1.getHand().stored.size();
		if (handSize > 7) {
			discarding = true;
			System.out.println("You may only have 7 cards! Please discard.");
			discardAmount = handSize - 7;
			JOptionPane.showMessageDialog(frame, "You must discard "
					+ discardAmount + " cards.");
			GameBoard.redrawCards();
			// PandemicGame.currentMoves = 0;
		} else {
			PandemicGame.nextPlayer();
			Board.changePlayerPhaseTwo();
		}

	}

	/**
	 * The second phase of player changing.
	 */
	public static void changePlayerPhaseTwo() {
		String nextPlayer = PandemicGame.playerStorage.get(
				PandemicGame.currentPlayer).toString();
		String lastPlayer = PandemicGame.playerStorage.get(
				((PandemicGame.currentPlayer - 1) + PandemicGame.playerStorage
						.size()) % PandemicGame.playerStorage.size())
				.toString();
		if (!discarding) {
			JOptionPane.showMessageDialog(frame, "The " + lastPlayer
					+ "'s turn has ended. It is now the " + nextPlayer
					+ "'s turn.");
		} else {
			JOptionPane.showMessageDialog(frame, lastPlayer + " discarded the "
					+ lastDiscard.toString() + " card.\n" + "The " + lastPlayer
					+ "'s turn has ended. It is now the " + nextPlayer
					+ "'s turn.");
		}
		GameBoard.redrawCards();
		if (PandemicGame.p1.getRole() == 1) {
			for (DispatcherButton x : Board.dispatcherList) {
				background.add(x);
			}
		}
		try {
			GameBoard.movePlayer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (CityButton j : cityList) {
			j.refreshInfection();
		}
		Board.frame.repaint();

	}

	/**
	 * Charter flight code for flight from a city.
	 * 
	 * @param city
	 */
	public static void charterFlight(CityNode city) {
		JFrame frame = new JFrame();
		JOptionPane
				.showMessageDialog(
						frame,
						"You can use this city card to fly from "
								+ city.name
								+ " to any other city. \n Please select another city to which you wish to fly.");
	}

	/**
	 * @param city
	 */
	public static void cityFlight(CityNode city) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Flying to " + city.name + ".");
		PandemicGame.p1.currentCity = city;
	}

	/**
	 * Event card
	 */
	public static void useEventCard() {
		// Auto-generated method stub

	}

	public static void displayInfection(CityNode city) {
		if (!PandemicGame.isSetup) {
			JOptionPane.showMessageDialog(Board.frame, city.toString() + " was infected.");
		}
	}

}