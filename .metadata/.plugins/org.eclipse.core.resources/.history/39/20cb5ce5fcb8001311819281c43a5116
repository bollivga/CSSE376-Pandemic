import java.awt.*;

import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.JTextArea;



public class SimpleBorder {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JLayeredPane lpane = new JLayeredPane();
        frame.setSize(1400,849);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.add(lpane, BorderLayout.CENTER);
        Border etched = (Border) BorderFactory.createEtchedBorder();


        String[] items = {"Medic", "Dispatcher", "Quarantine Specialist", "Research"};
        JList list = new JList(items);

        JTextArea text = new JTextArea(10, 40);

        JScrollPane scrol = new JScrollPane(text);
        JScrollPane scrol2 = new JScrollPane(list);

        JPanel panel= new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3 = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(scrol2,BorderLayout.WEST);
//        panel.add(scrol, BorderLayout.EAST);    
//        panel.setBorder(etched);
        ImageIcon image = new ImageIcon("src/board.jpg");
        JLabel label = new JLabel("", image, JLabel.CENTER);
        panel3.add(label, BorderLayout.EAST);
        RoundButton atlanta = new RoundButton("Atlanta");
//        atlanta.setSize(200, 200);
//        atlanta.setPreferredSize(new Dimension(200, 200));
//        atlanta.setBounds(0, 0, 200, 200);
        panel2.add(atlanta);
        panel3.setBounds(150, 0, 1200, 849);
        panel2.setBounds(360, 310, 40, 40);
        panel.setBounds(0, 0, 150, 300);
        
        lpane.add(panel, new Integer(0), 0);
        lpane.add(panel3, new Integer(0), 0);
        lpane.add(panel2, new Integer(1), 0);

        frame.setVisible(true);
    }

}