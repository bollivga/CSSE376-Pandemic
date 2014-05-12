package main;

import java.awt.Color;
import java.awt.Font;
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
		this.cityNode = city;
		this.setFont(new Font("Default", 1, 10));
		this.setText("" + (this.cityNode.infectionStatus[this.cityNode.color]));

		// Blue Cities
		if (this.cityNode.color == 0) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.getHSBColor((float) (225.0 / 360.0),
					(float) 0.8, (float) 0.62));
		}

		// Black Cities
		else if (this.cityNode.color == 1) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.DARK_GRAY);
		}

		// Red Cities
		else if (this.cityNode.color == 2) {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.getHSBColor(0, 40, 158));
		}

		// Yellow Cities
		else {
			this.setForeground(Color.WHITE);
			this.setBackground(Color.getHSBColor((float) (60.0 / 360.0),
					(float) .78, (float) 0.46));
		}
	}

	/**
	 * Show infection rates for each city
	 */
	public void refreshInfection() {
		this.setText("" + (this.cityNode.infectionStatus[this.cityNode.color]));
		System.out.println(this.cityNode.getName() + " "
				+ (this.cityNode.infectionStatus[this.cityNode.color]));
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
		// Refreshes the infection rate on the city clicked on.
		this.setText("" + (this.cityNode.infectionStatus[this.cityNode.color]));
		if (AirliftCard.isFlying) {
			PandemicGame.controlledPlayer.currentCity = this.cityNode;
			AirliftCard.isFlying = false;
		}
		if (PandemicGame.p1.isFlying) {
			if (PandemicGame.controlledPlayer.tryFlyToCity(cityNode)) {
				PandemicGame.p1.isFlying = false;
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.controlledPlayer.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
				}
			} else {
				System.out.println("You are already at this city!");
				sameCity();
			}
		} else {
			if (!PandemicGame.controlledPlayer.tryMoveToCity(cityNode)) {
				System.out.printf(
						"Move failed to %s, too far away or same city\n",
						this.cityNode.toString());
				if (PandemicGame.controlledPlayer.currentCity == this.cityNode
						&& PandemicGame.p1 == PandemicGame.controlledPlayer) {
					if (PandemicGame.p1.getRole() == 2) {
						this.removeInfections(true);
					} else {
						// if has cured true
						this.removeInfections(false);
					}
				} else if (PandemicGame.controlledPlayer.currentCity == this.cityNode) {
					System.out.println("Cannot cause another player to cure");
				} else if (PandemicGame.controlledPlayer.currentCity != this.cityNode
						&& PandemicGame.p1.getRole() == 1) {
					boolean canGoToAlly = false;
					for (Player x : PandemicGame.playerStorage) {
						if (x.currentCity == this.cityNode) {
							canGoToAlly = true;
						}
						if (canGoToAlly) {
							if (PandemicGame.controlledPlayer
									.tryFlyToCity(cityNode)) {
								++PandemicGame.currentMoves;
								System.out
										.println(PandemicGame.controlledPlayer
												.toString()
												+ " has moved to "
												+ cityNode.getName()
												+ ". "
												+ (4 - PandemicGame.currentMoves)
												+ " moves left.");
								if (PandemicGame.currentMoves == 4) {
									Board.changePlayer();
								}
							}
						}
					}

				}
			} else {
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.controlledPlayer.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
					try {
						GameBoard.movePlayer();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		try {
			GameBoard.movePlayer();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void removeInfections(boolean curedOrMedic) {
		System.out.println(this.cityNode.toString() + " cured.");
		if (!curedOrMedic) {
			if (this.cityNode.cureOnce()) {
				this.setText(""
						+ (this.cityNode.infectionStatus[this.cityNode.color]));
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.p1.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
					try {
						GameBoard.movePlayer();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else {
			if (this.cityNode.cureAll()) {
				this.setText(""
						+ (this.cityNode.infectionStatus[this.cityNode.color]));
				++PandemicGame.currentMoves;
				System.out.println(PandemicGame.p1.toString()
						+ " has moved to " + cityNode.getName() + ". "
						+ (4 - PandemicGame.currentMoves) + " moves left.");
				if (PandemicGame.currentMoves == 4) {
					Board.changePlayer();
					try {
						GameBoard.movePlayer();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

	}

	private String sameCity() {
		// TODO Auto-generated method stub
		System.out.println("Same City");
		return "Same City";
	}
}
