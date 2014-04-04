import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class CityButton extends JButton implements ActionListener {
	
	public CityNode cityNode;
	
	public CityButton(CityNode city) {
		// Set the city name to the city passed in
		cityNode = city;
	}

	public static void main(String[] args) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Print the name of the city
		if (!PandemicGame.p1.tryMoveToCity(cityNode)){
			System.out.println("Move failed, too far away or same city");
		}
		else {
			System.out.println("Player has moved to " + cityNode.getName() + ".");
		}
	}
}