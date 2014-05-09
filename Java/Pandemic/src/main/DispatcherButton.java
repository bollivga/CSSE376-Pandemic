package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author Jonathan Jungck and Greg Bollivar
 * 
 */
public class DispatcherButton extends JButton implements ActionListener {

	/**
	 * serialUID or whatever the errors were yelling at me for
	 */
	private static final long serialVersionUID = 1L;
	private Player player;

	/**
	 * @param player
	 */
	public DispatcherButton(Player player) {
		this.player = player;
		this.setFont(new Font("Default", 1, 10));
		this.setText(this.player.toString());
		this.setForeground(Color.WHITE);
		this.setBackground(this.player.color);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (PandemicGame.p1.getRole() == 1) {
			PandemicGame.controlledPlayer = this.player;
			System.out.println("Current player = " + this.player.toString());
		}
	}

}
