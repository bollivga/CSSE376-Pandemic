import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
 
/**
 * NOTE FROM JONATHAN: This is an example class on player movement that I've found. I've
 * been having issues with moving buffered images in JFrame so I've imported this to test for
 * our class on movement. I found that I actually had an issue with the image I was attempting
 * to display, it won't even show up here. I'm keeping this class imported for now for reference
 * as I build my player movement.
 * 
 * I have made personal edits to this code as well.
 * 
 * @author René Cruz - G-Cross Studio 2011 and Jonathan Jungck - CSSE 376 - Spring 2014
 */
public class MovePlayerImage extends JFrame implements KeyListener {
    //Objects for images
 
    /**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
    private BufferedImage player;
    //First coordinates of player image
    private int cordX = 50;
    private int cordY = 250;
 
    /**
     * Cretaes the JFrame and sets the size and title. Also loads the images in.
     */
    public MovePlayerImage() {
        setTitle("Move Image Sample");
        setSize(512, 512); //set window dimension 480x320px
        loadImages();
        setVisible(true); //make window visible
    }
 
    /**
     * Loads the background and player images onto the board.
     */
    public void loadImages() {
        try {
            //path for image file
            String pathBackground = "src/board.jpg";
            background = ImageIO.read(new File(pathBackground));
            String pathplayer = "src/board.jpg";
            player = ImageIO.read(new File(pathplayer));
 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //Associate the keyboard listener with this JFrame
        addKeyListener(this);
    }
 
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //draw background image (first image)
        g.drawImage(background, 0, 0, this);
        //draw player image (second image, in this order)
        g.drawImage(player, cordX, cordY, this);
    }
 
    /**
     * Main just calls the initialization code.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new MovePlayerImage();
    }
 
 
    //While a key is pressed
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            //if the right arrow in keyboard is pressed...
            case KeyEvent.VK_RIGHT: {
                cordX+=3;
            }
            break;
            //if the left arrow in keyboard is pressed...
            case KeyEvent.VK_LEFT: {
                cordX-=3;
            }
            break;
            //if the down arrow in keyboard is pressed...
            case KeyEvent.VK_DOWN: {
                cordY+=3;
            }
            break;
            //if the up arrow in keyboard is pressed...
            case KeyEvent.VK_UP: {
                cordY-=3;
            }
            break;
        }
        repaint();
    }
 
    //When a key is typed (once)
    public void keyTyped(KeyEvent ke) {}
     
    //When a key is released (typed or pressed)
    public void keyReleased(KeyEvent ke) {}
}