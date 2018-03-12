import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;

public class Exam1 extends JFrame implements ActionListener, ComponentListener, ChangeListener {
	JFrame frm;
	Vector registeredListeners = new Vector();
	JLabel l1,l2,l3;
	JButton b1,b2;
	JPanel topPanel,titlePanel;
	JTabbedPane theTab= new JTabbedPane();
	JLabel statusLabel = new JLabel();
	JLabel statusLabel1 = new JLabel("fastride!Ashish");
	
	 JPanel bottomPanel = new JPanel(new BorderLayout());
	    
	static TotalCar totalCar = new TotalCar();
	Ccomponents cm = new Ccomponents();
	private boolean carsUpdated = false;
	String currentPanel1;
	
	
	public Exam1(){
		super("fastride!");
		theTab.addChangeListener(this);
		Container c = getContentPane();
		c.setBackground(Color.GRAY);

		setSize(780, 560);
		topPanel =new JPanel(new BorderLayout());
		titlePanel = new JPanel(new GridLayout(2,1));
		l1 = new JLabel("CAR SALES SYSTEM ",JLabel.CENTER);
		l2 = new JLabel("Car Details",JLabel.CENTER);
		l3 = new JLabel();
		
		String currentFont = l1.getFont().getName();
		l1.setFont(new Font(currentFont, Font.BOLD, 26));
		l1.setForeground(Color.WHITE);
		l2.setFont(new Font(currentFont, Font.PLAIN, 18));
		l2.setForeground(Color.WHITE);
		
		statusLabel.setBorder(new javax.swing.border.EtchedBorder());
		
		l3.setIcon(new ImageIcon("car22.jpg"));
		titlePanel.add(l1);
		titlePanel.add(l2);
		titlePanel.setBackground(Color.BLUE);
		titlePanel.setOpaque(false);
		topPanel.setBackground(Color.BLUE);
		topPanel.setOpaque(false);
		topPanel.add(l3,"West");
		topPanel.add(titlePanel,"Center");
		
		theTab.setBackground(Color.white);
		WelcomePanel welcomePanel = new WelcomePanel(this);
		AddCarPanel1 addCarPanel = new AddCarPanel1(this);
	    AllCarPanel allCar = new AllCarPanel(this);
		AgePanel agePanel = new AgePanel(this);
		PricePanel pPanel = new PricePanel(this);
		DeletePanel delete = new DeletePanel(this);
		UpdatePanel update = new UpdatePanel(this);
		
		theTab.add("Welcome", welcomePanel);
		theTab.add("Add a Car", addCarPanel);
		theTab.add("Show All Car", allCar);
		theTab.add("SearchBy Age",agePanel);
		theTab.addTab("Search on Price & tavelled Distance", pPanel);
		theTab.addTab("Delete a Car", delete);
		theTab.addTab("Update Car", update);
		
		theTab.addChangeListener(update);
		theTab.addChangeListener(allCar);
		theTab.addChangeListener(welcomePanel);
		theTab.addChangeListener(delete);
		
		theTab.setSelectedIndex(0);
		c.setLayout(new BorderLayout());
		c.add(topPanel, "North");
		c.add(theTab, "Center");
		c.add(statusLabel,"South");
	//	c.add(statusLabel1,"South");
		 bottomPanel.add(statusLabel1, BorderLayout.LINE_END);
	}
	
	public static void main(String []args){
		Exam1 e = new Exam1();
		e.setVisible(true);
	}	
	
	public ArrayList<String> getAllCars()
	{
		return totalCar.getCarModel();
	}
	
	
	public static double[] convertToRange(String s)
	{
		String[] parts = s.trim().split("-");
		double[] bounds = new double[2];

		try
		{
			if (parts.length == 1)
			{
				String c = s.substring(s.length() - 1);

				if (c.equals("+"))
				{
					bounds[0] = Double.parseDouble(s.substring(0, s.length() - 1));
					bounds[1] = -1;
				}

				else
				{
					bounds[0] = Double.parseDouble(s);
					bounds[1] = bounds[0];
				}
			}
			else if(parts.length == 2)
			{
				bounds[0] = Double.parseDouble(parts[0]);
				bounds[1] = Double.parseDouble(parts[1]);
				if (bounds[0] > bounds[1])
				{
					bounds[0] = -1;
					bounds[1] = -1;
				}
			}
		}
		catch (NumberFormatException exp)
		{
			bounds[0] = -1;
			bounds[1] = -1;
		}

		return bounds;
	}
	public ArrayList<String> Search(int minAge, int maxAge) {
		return totalCar.search(minAge,  maxAge);
	}
	public ArrayList<String> SearchPrice(int minPrice, int maxPrice, double minDistance, double maxDistance) {

		return totalCar.search1(minPrice, maxPrice,  minDistance, maxDistance);
	}
	
	public void addCarUpdateListener(Object listener)
	{
		if (!(listener == null))
			registeredListeners.add(listener);
	}

	@Override
	public void stateChanged(ChangeEvent ev) {
		if (ev.getSource() == theTab)
			currentPanel1 = theTab.getTitleAt(theTab.getSelectedIndex());
			//statusLabel.setText("<html><font color=\"white\">+currentPanel1</font></html>");
			statusLabel.setText("Current panel: " +currentPanel1 );
			String currentFont1 = statusLabel.getFont().getName();
			statusLabel.setFont(new Font(currentFont1, Font.BOLD, 20));
			statusLabel.setForeground(Color.WHITE);
	}

	@Override
	public void componentResized(ComponentEvent ev) {
		if (this == ev.getComponent())
		{
			Dimension size = getSize();

			if (size.height < 530)
				size.height = 530;
			if (size.width < 675)
				size.width = 675;

			setSize(size);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
