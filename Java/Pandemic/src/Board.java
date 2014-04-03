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
		System.out.println(CityGraph.cities);
		
		for (CityNode i : CityGraph.cities) {
			String name = i.getName();
			CityButton city = new CityButton(name);
			city.addActionListener(city);
			System.out.println(city.cityName);
			background.add(city);
			city.setBounds(i.bounds[0], i.bounds[1], 20, 20);
		}
		

		// Atlanta
		CityButton atlanta = new CityButton("Atlanta");
		atlanta.addActionListener(atlanta);
		System.out.println(atlanta.cityName);
		background.add(atlanta);
		atlanta.setBounds(83, 266, 20, 20);

	}
}