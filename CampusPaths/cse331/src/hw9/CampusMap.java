package hw9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CampusMap extends JPanel {
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private BufferedImage image;
	private static final String FILE_NAME = "src/hw8/data/campus_map.jpg";
	
	private ImageIcon icon;
	private JLabel label;
	
    public CampusMap() {

     }

    public static void main(String[] args) {
        try {
           /*
           image = ImageIO.read(new File(FILE_NAME));
           icon = new ImageIcon(image);
           label = new JLabel(icon);
           add(label);
           JOptionPane.showMessageDialog(null, label);
           */
           JFrame frame = new JFrame("Something");

           BufferedImage myPicture = ImageIO.read(new File(FILE_NAME));
           Image scaledImage = myPicture.getScaledInstance(WIDTH, -1, Image.SCALE_SMOOTH);
           JPanel panel = new JPanel(new BorderLayout());
           JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
           picLabel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
           panel.add(picLabel, BorderLayout.CENTER);
           panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
           frame.add(panel,BorderLayout.CENTER);
           //frame.add(picLabel,BorderLayout.CENTER);
           
           frame.pack();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           //panel.add(picLabel);//, BorderLayout.CENTER);
           //add(picLabel);
           //picLabel.setBounds(w,x,y,z


        } catch (IOException e) {
           e.printStackTrace();
        }
     }
    
}
