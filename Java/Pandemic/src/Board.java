import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Board {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1200, 849);
		frame.setLayout(new BorderLayout());
		
		JLabel background = new JLabel(new ImageIcon("src/board.jpg"));
		frame.add(background, BorderLayout.CENTER);
		frame.setVisible(true);
		background.setLayout(null);
		PandemicGame mainGame = new PandemicGame();
		
		// Initialize all city buttons on the map from CityGraph
		for (CityNode i : CityGraph.cities) {
			CityButton city = new CityButton(i);
			city.addActionListener(city);
			background.add(city);
			city.setBounds(i.bounds[0], i.bounds[1], 20, 20);
		}
		
		System.out.println("Player is at Atlanta. Click a connected city to move.");

		// TestButton
		JLabel player = new JLabel(new ImageIcon("src/player.jpg"));
		player.setBounds(280, 297, 50, 50);
		background.add(player);

	}
}