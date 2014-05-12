package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class AirliftMovement extends JButton implements ActionListener {

	/**
	 * serialUID or whatever the errors were yelling at me for
	 */
	// private static final long serialVersionUID = 1L;
	// public Player player;
	// public boolean isFlying;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6914025901189952726L;

	/**
	 * @param player
	 */
	public AirliftMovement(Player player) {
		// this.player = player;
		// this.setFont(new Font("Default", 1, 10));
		// this.setText(this.player.toString());
		// this.setForeground(Color.WHITE);
		// this.setBackground(this.player.color);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// PandemicGame.controlledPlayer = this.player;
		// System.out.println("Current player = " + this.player.toString());
		// this.isFlying = true;
	}

}
