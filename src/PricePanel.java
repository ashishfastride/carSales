import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
public class PricePanel extends JPanel implements ActionListener{

	Exam1 ex1;
	int currentIndex = 0;
	TotalCar tc = new TotalCar();
	ArrayList<String> carList = tc.getCarModel();
	final String[] price={"20000-50000","50001-100000","100001-150000","150001-1000000"};
	final String[] travl={"1-1000","1001-5000","5001-10000","10001-20000","20001-50000","50001-100000"};
	JButton b= new JButton("a");
	JLabel searchBy = new JLabel("Search on Price & Distance Travelled");
	JLabel Price = new JLabel("Price");
	JLabel travelled = new JLabel("Travelled Distance");
	JComboBox comboPrice = new JComboBox(price);
	JComboBox comboTravel = new JComboBox(travl);
	JPanel buttonPanel = new JPanel();
	JTextField tf= new JTextField();
	JPanel topPanel = new JPanel();
	JPanel agePanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel searchPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel navPanel= new JPanel();
	JButton search = new JButton("Search");
	JButton reset = new JButton("Reset");
	JButton next = new JButton("Next");
	JButton previous = new JButton("Previous");
	CarComponents cm = new CarComponents();
	private boolean carUpdated = true;
	
	public PricePanel(Exam1 ex){
		ex1 = ex;
		
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		
		searchBy.setAlignmentX(0.5f);
		search.addActionListener(this);
		reset.addActionListener(this);
		next.addActionListener(this);
		previous.addActionListener(this);
		agePanel.add(Price);
		agePanel.add(comboPrice);
		agePanel.add(travelled);
		agePanel.add(comboTravel);
		agePanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
		//agePanel.setBackground(Color.pink);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(agePanel);
		topPanel.add(buttonPanel);
		
		buttonPanel.add(search);
    	buttonPanel.add(reset);
    	navPanel.add(next);
    	navPanel.add(previous);
		
		//searchBy.setAlignmentX(0.5f);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		cm.add(navPanel,"Center");
		cm.setVisible(false);
		
		searchBy.setFont(new Font("Serif", Font.BOLD, 16));
		searchBy.setForeground(Color.BLACK);
		searchBy.setBorder(new EmptyBorder(new Insets(10,200,15,0)));
		add(searchBy);
		add(topPanel,"North");
		add(cm,"Center");
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==search){
			searchButtonClicked();
			}else if(e.getSource() == reset){
				resetButtonClicked();
			}else if(e.getSource() == next){
				nextButtonClicked();
			}else if(e.getSource() == previous){
				previousButtonClicked();
			}
	}

	private void previousButtonClicked() {
		if(currentIndex > 0){
			currentIndex --;
			cm.displayDetails(currentIndex);
		}else{
			JOptionPane.showMessageDialog(ex1, "can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
		}			
	}

	private void nextButtonClicked() {
		if(currentIndex < carList.size()-1)
		{
			currentIndex++;
			cm.displayDetails(currentIndex);
		}else{
			JOptionPane.showMessageDialog(ex1, "can't navigate much More","Alert",JOptionPane.ERROR_MESSAGE);
		}		
	}

	private void resetButtonClicked() {
		currentIndex = 0;
		carList = null;
		cm.setVisible(false);
		comboPrice.setSelectedIndex(0); 
		comboTravel.setSelectedIndex(0);
		
	}

	private void searchButtonClicked() {
		double[] priceRange = Exam1.convertToRange((String)comboPrice.getSelectedItem());
		double[] distanceRange = Exam1.convertToRange((String)comboTravel.getSelectedItem());
		
		if(priceRange[0] >=0 && distanceRange[0] >= 0){
			carList = ex1.SearchPrice((int)priceRange[0], (int)priceRange[1], (double)distanceRange[0], (double)distanceRange[1]);
		}
		if (carList.size() > 0)
		{
			currentIndex = 0;
			cm.setVisible(true);
			cm.displayDetails(currentIndex);

			if (carList.size() == 1)
			{
				next.setEnabled(false);
				previous.setEnabled(false);
			}
			else
			{
				next.setEnabled(true);
				previous.setEnabled(true);
			}

			ex1.repaint();
		}
		else
			JOptionPane.showMessageDialog(ex1, "Sorry, you can't navigate much More", "failed", JOptionPane.WARNING_MESSAGE);
	}
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == ex1)
		{
			System.out.println("Hello");
			carUpdated = true;
		}
	}
	
	public void stateChanged(ChangeEvent ev) {
				if (ev.getSource() instanceof JTabbedPane)
				{
					JTabbedPane tab = (JTabbedPane)ev.getSource();
					if (tab.getSelectedIndex() == 3)
					{
						if (carUpdated)
						{
							carList = tc.getCarModel();
							if(!(carList==null))
								cm.displayDetails(currentIndex);
				//			UpdateCarInfo();
							
							carUpdated = false;
						}
						//UpdateCarInfo();
					}
				}
	}
}
