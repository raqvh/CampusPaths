package hw9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * This is the actual GUI part of Campus Maps that is responsible
 * for putting all the pieces of the GUI together in the correct
 * order.
 *
 */
public class CampusPathGUI extends JPanel implements ActionListener {
	
	// Minimum width and height to be supported for this project
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	//private ImageIcon icon;
	private MapBackground mapPanel;
	private JPanel buttons;
	private JPanel panelContainer;
	private JFrame frame;
	private JScrollPane pane;
	private JButton reset;
	private JButton findPath;
	private JComboBox<String> list1 = new JComboBox<String>();
	private JComboBox<String> list2 = new JComboBox<String>();

	/*
	 * Sets up all the different components of the GUI
	 */
    public CampusPathGUI() {
    	/*
    	 * Basic outline:
    	 * One panel is added to the actual frame.
    	 * In this panel are two smaller panels.
    	 * The panel at the top is a scrollable JPanel which displays the maps
    	 * and handles drawing the paths whenever necessary
    	 * 
    	 * The second panel is on the button and contains the boxes
    	 * to select builidngs from as well as a find path and reset button
    	 * for finding paths and reseting the map.
    	 * 
    	 * These panels are first created then added to the main panel
    	 * which is finally added to the frame. Then all of these are displayed.
    	 */
    	
        // Create the mapPanel and give it the image
    	// This will display UW Campus Map in the GUI
    	setMapPanel();
    	
        // Add combo boxes with all the building names
        setList(list1);
        setList(list2);
    	
        // Get the set and reset buttons prepped
        setButtons();
        
        // Create the main panel which holds both smaller panels
        // The smaller panels are the Campus Map and the panel containing all the buttons
        panelContainer = new JPanel();
        panelContainer.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        panelContainer.add(pane, BorderLayout.NORTH);
        panelContainer.add(buttons, BorderLayout.SOUTH);
        
        // Create the frame
    	setFrame();
         
         // Pack and display
         draw();
     }
    
    /*
     * This MUST be done after everything is ready.
     * Packs and displays the GUI.
     */
    public void draw() {
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    /*
     * Sets up a combo box with a list of all the UW buildings
     * The buildings are sorted alphabetically by abbreviated name
     */
    public void setList(JComboBox<String> list) {
    	Map<String,String> map = mapPanel.buildingNames();
    	
    	//list = new JComboBox<String>();
    	list.setPreferredSize(new Dimension(360,30));
    	
    	Set<String> abbreviated = new TreeSet<String>(map.keySet());
    	
    	for(String str : abbreviated) {
    		String name = str + ": " + map.get(str);
    		list.addItem(name);
    	}
    }
    
    /*
     * This resets the map whenever reset is selected.
     */
    public void reset() {
    	mapPanel.clearList();
    	pane.getHorizontalScrollBar().setValue(0);
    	pane.getVerticalScrollBar().setValue(0);
    	list1.setSelectedIndex(0);
    	list2.setSelectedIndex(0);
    	mapPanel.repaint();
    }
    
    /*
     * Preps the buttons and all their labels within
     * a panel to be added to the frame.
     */
    public void setButtons() {
    	// Create the panel which will hold the buttons and combo boxes
    	buttons = new JPanel();
    	buttons.setPreferredSize(new Dimension(WIDTH,100));
    	
    	// First box is for the selection of the starting location
    	// Has a label before the box specifying start.
    	JLabel start = new JLabel("Start:");
    	buttons.add(start);
    	buttons.add(list1);
    	
    	// Second box is for the selection of the destination.
    	// Has a label before the box specifying end.
    	JLabel end = new JLabel("End:");
    	buttons.add(end);
    	buttons.add(list2);
    	
    	// After the boxes comes the find path button.
    	// This allows the user to find a path based on the selected
    	// buildings within the boxes.
    	reset = new JButton("Reset Map");
    	reset.setActionCommand("reset");
    	reset.addActionListener(this);
    	
    	
    	// The final button is the reset button.
    	// When this button is clicked the GUI is reverted to it's original state.
    	findPath = new JButton("Find Path");
    	findPath.setActionCommand("findPath");
    	findPath.addActionListener(this);
    	
    	// Add both of these buttons to the actual panel
    	buttons.add(findPath, BorderLayout.EAST);
    	buttons.add(reset, BorderLayout.WEST);
    }
    
    /*
     * Controls when any buttons are pressed and calls the appropriate
     * methods based on the buttons clicked.
     */
    public void actionPerformed(ActionEvent e) {
        if ("reset".equals(e.getActionCommand())) {
        	reset();
        } else if ("findPath".equals(e.getActionCommand())) {
        	findPath();
        } else {
        	
        }
    } 
    
    /*
     * Actually creates the frame with the correct size and adds the panel
     * containing both smaller panels
     */
    public void setFrame() {
        frame = new JFrame("UW Campus Map");
        frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame.add(panelContainer);
    }
    
    /*
     * Creates the map panel to display the campus map.
     */
    public void setMapPanel() {
        mapPanel = new MapBackground();
        mapPanel.setPreferredSize(new Dimension(WIDTH-15,HEIGHT-100));
        // Create a scroll pane then add in mapPanel
        pane = new JScrollPane(mapPanel);        
        pane.setPreferredSize(new Dimension(WIDTH-15,HEIGHT-100));
    }
    
    /*
     * When the 'find path' button is selected, this handles actually finding and
     * displaying the path for the two given buildings.
     */
    public void findPath() {
    	// Get the starting location and destination from the combo boxes
    	String start = String.valueOf(list1.getSelectedItem());
    	String end = String.valueOf(list2.getSelectedItem());
    	
    	// Before new path is displayed, the old path must be removed
    	mapPanel.clearList();
    	
    	// This handles calling the appropriate methods which will get the right
    	// data and draw the paths on the map
    	mapPanel.setLocations(start,end);
    	mapPanel.getList();
    	setHorizBar(mapPanel.horizontalPos());
    	setVertBar(mapPanel.verticalPos());
    	
    	// Must repaint for the paths to show up immediately!!
    	mapPanel.repaint();    	
    }
    
    /*
     * Sets the horizontal scroll bar to the given position.
     */
    public void setHorizBar(int pos) {
    	pane.getHorizontalScrollBar().setValue(pos-500);    	
    }
    
    /*
     * Sets the vertical scroll bar to the given position.
     */
    public void setVertBar(int pos) {
    	pane.getVerticalScrollBar().setValue(pos-300);    	
    }
}
