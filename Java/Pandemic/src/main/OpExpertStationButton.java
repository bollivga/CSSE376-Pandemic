package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class OpExpertStationButton extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4980630928773970384L;

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!PandemicGame.p1.currentCity.hasResearchStation) {
			PandemicGame.p1.currentCity.hasResearchStation = true;
			if (GameBoard.showResearch != null
					&& GameBoard.showResearch.isDisplayable()) {
				Board.background.remove(GameBoard.showResearch);
			}
			for (CityButton city : Board.cityList) {
				if (city.cityNode == PandemicGame.p1.currentCity) {
					city.refreshResearchStation();
				}
			}
			++PandemicGame.currentMoves;
			if (PandemicGame.currentMoves == 4) {
				Board.changePlayer();
			}
		}

	}

	/**
	 * Main class for creating the button
	 */
	public OpExpertStationButton() {
		this.setBounds(950, 750, 200, 50);
		if (!PandemicGame.isGerman) {
			this.setText("Add Research Station");
		} else {
			this.setText("In Forschungsstation");
		}
		this.addActionListener(this);

	}
}
