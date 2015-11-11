package hw9;

import hw8.Coordinate;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * This class is a custom JPanel which has functionality for displaying
 * a map from a given image and drawing a path on that map based on a given
 * starting and ending location.
 */
public class MapBackground extends JPanel {

	// File location for the map (change this for a different map)
    private final String FILE_NAME = "src/hw8/data/campus_map.jpg";
    
    
	private BufferedImage image;
    private ArrayList<Coordinate> path; // holds a list of the current path
    private Coordinate start;
    private Coordinate end;
    private CampusPathList data;
 
    /*
     * Reads in the image from a stored file name. This becomes the image that
     * will be displayed for this panel.
     */
    public MapBackground() {
    	super();
    
    	path = new ArrayList<Coordinate>();
		data = new CampusPathList();
		
    	try {
			image = ImageIO.read(new File(FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /*
     * Draws the image loaded from the given file. Will also draw a path
     * if a path has been selected.
     */
	@Override
	public void paintComponent(Graphics g) {		
		
		// ensure other crap before is painted
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, null);
		
		if(!path.isEmpty()) {
			// No reason to draw a path if the starting location is the destination.
			if(!start.equals(end)) {
				Coordinate first;
				Coordinate second;
				
				// Stroke selected based on 1024x768 size
				g2.setStroke(new BasicStroke(3));
				g2.setColor(Color.RED); // path is red
				for(int i = 0; i < path.size(); i+=2) {
					first = path.get(i);
					second = path.get(i+1);
					g2.drawLine((int)first.getX(), (int)first.getY(), 
							(int)second.getX(), (int)second.getY());
				}
				
				first = path.get(1);
				second = path.get(path.size()-1);
				
				// Draws circles at the starting and ending locations
				g2.setColor(Color.CYAN); // start is cyan!
				g2.fill(new Ellipse2D.Double(first.getX()-20, first.getY()-20, 40, 40));
				g2.setColor(Color.DARK_GRAY); // destination is gray? 
				g2.fill(new Ellipse2D.Double(second.getX()-20,second.getY()-20, 40, 40));
			}
		}
	}
	
	/*
	 * Returns dimension based on the loaded IMAGE not the actual panel
	 * (this allows the image to be scrollable)
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(), image.getHeight());
	}
	
	/*
	 * Clears the list of the current path
	 */
	public void clearList() {
		path.clear();
	}

	/*
	 * Sets the coordinates of the starting and ending location based on the given 
	 * start and end
	 */
	public void setLocations(String start, String end) {
		this.start = data.stringToCoord(start);
		this.end = data.stringToCoord(end);
	}
	
	/*
	 * Gets a new list for a path.
	 */
	public void getList() {
		path = data.pathList(start,end);
	}
	
	/*
	 * Returns the x coordinate of the starting location.
	 */
	public int horizontalPos() {
		Coordinate first = path.get(1);
		return (int)first.getX();
	}
	
	/*
	 * Returns the y coordinate of the ending location.
	 */
	public int verticalPos() {
		Coordinate first = path.get(1);
		return (int)first.getY();
	}
	
	/*
	 * Returns a mapping of all abbreviated building names to their full name.
	 */
	public Map<String,String> buildingNames() {
		return data.allBuildings();
	}
	
}
