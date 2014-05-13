package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	 * @param card
	 */
	public CardButton(Card card) {
		// Set the city name to the city passed in
		this.card = card;
		this.setText(card.toString());
		PandemicGame.handList.add(this);

		// Blue Cities
		if (((PlayerCityCard) this.card).city.color == 0) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.getHSBColor((float) (225.0 / 360.0),
					(float) 0.8, (float) 0.62));
		}

		// Black Cities
		else if (((PlayerCityCard) this.card).city.color == 1) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.DARK_GRAY);
		}

		// Red Cities
		else if (((PlayerCityCard) this.card).city.color == 2) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.getHSBColor((float) (11.0 / 360.0),
					(float) 1.0, (float) 0.65));
		}

		// Yellow Cities
		else {
			this.setForeground(Color.BLACK);
			this.setBackground(Color.getHSBColor(0, 40, (float) .5));
		}
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
		// use the card
		if (Board.discarding) {
			PandemicGame.prevPlayer.hand.remove(this.card);
			Board.discardAmount--;
			if (Board.discardAmount == 0) {
				Board.changePlayer();
			} else {
				GameBoard.redrawCards();
			}
		}
		else if (PandemicGame.p1.useCard(this.card)) {
			ArrayList<String> list = new ArrayList<String>();
			if (((PlayerCityCard) this.card).city == PandemicGame.p1.currentCity) {
				System.out.println("" + ((PlayerCityCard) this.card).city.toString() + PandemicGame.p1.currentCity.toString());
				list.add("Charter Flight");
				list.add("Research Station");
				if (PandemicGame.p1.getRole() == 5) {
					for (Player p : (PandemicGame.playerStorage)) {
						int k = 0;
						if (p.currentCity == ((PlayerCityCard) this.card).city) {
							k++;
						}
						if (k > 1) {
							list.add("Pass a Card");
						}
					}
				} else {
					int k = 0;
					for (Player p : (PandemicGame.playerStorage)) {

						System.out.println("Player at " + p.currentCity.toString());
						if (p.currentCity == ((PlayerCityCard) this.card).city) {
							k++;
						}
						System.out.println(k);
						if (k > 1) {
							list.add("Give " + ((PlayerCityCard) this.card).city.toString() + " Away");
							k = -4;
						}
					}
				}
			} else {
				list.add("City Flight");
				
				if (PandemicGame.p1.getRole() == 5) {
					int k = 0;
					for (Player p : (PandemicGame.playerStorage)) {
						if (p.currentCity == PandemicGame.p1.currentCity) {
							k++;
						}
						if (k > 1) {
							list.add("Give " + ((PlayerCityCard) this.card).city.toString() + " Away to " + p.toString());
							k--;
						}
					}
				}
			}
			int i = 0;
			Object[] actionList = new Object[list.size()];

			for (String role : list) {
				actionList[i] = role;
				i++;
			}
			
			String s = (String) JOptionPane.showInputDialog(Board.frame,
					"Choose an Action: ",
					"Card Action", JOptionPane.PLAIN_MESSAGE, null,
					actionList, "Flight");
			if (s == "City Flight") {
				//PandemicGame.p1.isFlying = true;
				Board.cityFlight(((PlayerCityCard) this.card).city);
				PandemicGame.p1.hand.remove(this.card);
				if (PandemicGame.p1.getHand().stored.size() > 0) {
					GameBoard.redrawCards();
				} else {
					GameBoard.handFrame.dispose();
				}
				
			}
			else if (s == "Charter Flight") {
				Board.charterFlight(((PlayerCityCard) this.card).city);
				if (PandemicGame.p1.getHand().stored.size() > 0) {
					GameBoard.redrawCards();
				} else {
					GameBoard.handFrame.dispose();
				}
			}
			else if (s == "Research Station") {
				if (!((PlayerCityCard) this.card).city.hasResearchStation){
					((PlayerCityCard) this.card).city.hasResearchStation = true;
					System.out.println("Added a research station to " + ((PlayerCityCard) this.card).city.toString() + ".");
					PandemicGame.p1.hand.remove(this.card);
					++PandemicGame.currentMoves;
					if (PandemicGame.currentMoves == 4) {
						Board.changePlayer();
					}
					for (CityButton city : Board.cityList) {
						if (city.cityNode == ((PlayerCityCard) this.card).city) {
							city.refreshResearchStation();
						}
					}
					if (PandemicGame.p1.getHand().stored.size() > 0) {
						GameBoard.redrawCards();
					} else {
						GameBoard.handFrame.dispose();
					}

					
				} else {
					System.out.println(((PlayerCityCard) this.card).city.toString() + " already contained a research station!");
				}

			}
			else if (s == "Give " + ((PlayerCityCard) this.card).city.toString() + " Away") {
				System.out.println("Test?");
				for (Player p : (PandemicGame.playerStorage)) {
					System.out.println("Player at " + p.currentCity.toString());
					System.out.println(p.currentCity.toString() + p.getRole());
					if (p.currentCity == ((PlayerCityCard) this.card).city && p.getRole() != PandemicGame.p1.getRole()) {
						p.hand.add(this.card);
						System.out.println("Gave " + p.toString() + " " + ((PlayerCityCard) this.card).city.toString());
					}
				}
				PandemicGame.p1.hand.remove(this.card);
				++PandemicGame.currentMoves;
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
				}
			} else {
				System.out.println("fail");
				for (Player p : (PandemicGame.playerStorage)) {
					System.out.println("Player at " + p.currentCity.toString());
					System.out.println(p.currentCity.toString() + p.getRole());
					if (p.currentCity == ((PlayerCityCard) this.card).city && p.getRole() != PandemicGame.p1.getRole()) {
						p.hand.add(this.card);
						System.out.println("Gave " + p.toString() + " " + ((PlayerCityCard) this.card).city.toString());
					}
				}
				PandemicGame.p1.hand.remove(this.card);
				++PandemicGame.currentMoves;
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
				}
				if (PandemicGame.p1.getHand().stored.size() > 0) {
					GameBoard.redrawCards();
				} else {
					GameBoard.handFrame.dispose();
				}
			}
		} else if (PandemicGame.p1.isFlying) {
			Board.charterFlight(((PlayerCityCard) this.card).city);
		}
		try {
			GameBoard.movePlayer();
		} catch (InterruptedException e1) {
			// Auto-generated catch block
			e1.printStackTrace();
		}
		GameBoard.handFrame.remove((Component) e.getSource());
		GameBoard.handFrame.validate();
		GameBoard.handFrame.repaint();
	}
}
