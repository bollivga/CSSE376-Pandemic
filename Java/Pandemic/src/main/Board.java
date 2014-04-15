package main;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	public static GameBoard frame;

	/**
	 * The main class for the board. Draws the frame and background and
	 * iterates all buttons on cities based on graph information.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//JFrame frame = new JFrame();
		frame = new GameBoard();
//		{	
//			Graphics paint( Graphics g )
//			{
//				draw(g);
//				// draw img
//			}
//			
//		};
		frame.setSize(1200, 849);
		frame.setLayout(new BorderLayout());
		
		// Draw the background board on the frame.
		background = new JLabel(new ImageIcon("src/board.jpg"));
		frame.add(background, BorderLayout.CENTER);
		frame.setVisible(true);
		background.setLayout(null);
		
		// Initialize a new game.
		new PandemicGame();
		
		ArrayList<String> chooseRoles = new ArrayList<String>();
		chooseRoles.add("Contingency Planner");
		chooseRoles.add("Dispatcher");
		chooseRoles.add("Medic");
		chooseRoles.add("Operations Expert");
		chooseRoles.add("Quarantine Specialist");
		chooseRoles.add("Researcher");
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
			String s = (String)JOptionPane.showInputDialog(frame, "Please select  the role for Player " + i + ".\n" + "To finish and choose less than four players, select No More Players.",
			                    "Role Selection", JOptionPane.PLAIN_MESSAGE, null, list,
			                    "Contingency Planner");
			if (s == "No More Players") {
				if (i > 2) {
					JFrame finish = new JFrame();
					String nextPlayer = PandemicGame.playerStorage.get(PandemicGame.currentPlayer).toString();
					JOptionPane.showMessageDialog(finish, "All players have been selected. There are " + (i - 1) + " players in the game.\n" + "It is now Player 1, the " + nextPlayer + "'s turn.");
					i = 5;
				}
				else {
					JFrame finish = new JFrame();
					JOptionPane.showMessageDialog(finish, "You must have at least two players!");
				}
			}
			else {
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
		PandemicGame.handOutCards();
		PandemicGame.p1 = PandemicGame.playerStorage.get(0);
		// Initialize all city buttons on the map from CityGraph
		for (CityNode j : CityGraph.cities) {
			CityButton city = new CityButton(j);
			city.addActionListener(city);
			background.add(city);
			city.setBounds(j.bounds[0], j.bounds[1], 20, 20);
		}
		GameBoard.redrawCards();
//		int k = 0;
//		for (Card j : PandemicGame.p1.hand.stored) {
//			k++;
//			CardButton card = new CardButton(j);
//			card.addActionListener(card);
//			background.add(card);
//			card.setBounds(300 + 140*k, 650, 140, 300);
//		}
		
		System.out.println("Player is at Atlanta. Click a connected city to move.");

		// Test Player Graphic
		JLabel player = new JLabel(new ImageIcon("src/player.jpg"));
		//player.setBounds(280, 297, 20, 50);
		//player.setLayout(BorderLayout.NORTH);
		frame.add(player);
		
		// If you try to close the window, it verifies with a yes/no and then quits if yes.
		frame.addWindowListener(new WindowAdapter() {

			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String ObjButtons[] = {"Yes","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Are you sure you want to exit?", "Quit?", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			      System.exit(0);
			    }
			  }
			});
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
		
	/**
	 * When it changes to the next player, we notify the user.
	 */
	public static void changePlayer() {
		// Gives a notification that it is the next player's turn.
		JFrame frame = new JFrame();
		PandemicGame.nextPlayer();
		String nextPlayer = PandemicGame.playerStorage.get(PandemicGame.currentPlayer).toString();
		String lastPlayer = PandemicGame.playerStorage.get(((PandemicGame.currentPlayer - 1)+PandemicGame.playerStorage.size())% PandemicGame.playerStorage.size()).toString();
		JOptionPane.showMessageDialog(frame, "The " + lastPlayer + "'s turn has ended. It is now the " + nextPlayer + "'s turn.");
		GameBoard.redrawCards();
	}

	/**
	 * Charter flight code for flight from a city.
	 * 
	 * @param city 
	 */
	public static void charterFlight(CityNode city) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You can use this city card to fly from " + city.name + " to any other city. \n Please select another city to which you wish to fly.");
		
	}

	public static void cityFlight(CityNode city) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Flying to " + city.name + ".");
		
	}

	public static void useEventCard() {
		// TODO Auto-generated method stub
		
	}
}