import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;


public class AgePanel extends JPanel implements ActionListener{

	
	Exam1 ex1;
	int currentIndex = 0;
	TotalCar tc = new TotalCar();
	ArrayList<String> carList = tc.getCarModel();
	final String[] age={"0","1","2","3","4","5","5-10","10-18"};
	JButton b= new JButton("a");
	JPanel buttonPanel = new JPanel();
	JLabel searchAge = new JLabel("Search By Age");
	JLabel cAge = new JLabel("Car Age");
	JComboBox comboAge = new JComboBox(age);
	JButton search = new JButton("Search");
	JButton reset = new JButton("Reset");
	JButton next = new JButton("Next");
	JButton previous = new JButton("Previous");
	JPanel navigatePanel = new JPanel();
	JTextField tf= new JTextField();
	JPanel topPanel = new JPanel();
	JPanel agePanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel navPanel= new JPanel();
	CarComponents cm = new CarComponents();
	private boolean carUpdated = true;
	
	public AgePanel(Exam1 ex){		
		ex1 = ex;
		
		setLayout(new BorderLayout());
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		
		agePanel.add(cAge);
		agePanel.add(comboAge);
		agePanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
		buttonPanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
		buttonPanel.add(search);
		buttonPanel.add(reset);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(searchAge);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(agePanel);
		topPanel.add(buttonPanel);
		topPanel.add(Box.createVerticalStrut(15));
		navigatePanel.add(next);
		navigatePanel.add(previous);
		
		search.addActionListener(this);
		reset.addActionListener(this);
		previous.addActionListener(this);
		next.addActionListener(this);
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		cm.add(navigatePanel,"Center");
		cm.setVisible(false);
		
		searchAge.setFont(new Font("Serif", Font.BOLD, 16));
		searchAge.setForeground(Color.BLACK);
		searchAge.setBorder(new EmptyBorder(new Insets(10,200,15,0)));
		add(searchAge);
		add(topPanel,"North");
		add(cm,"Center");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
		comboAge.setSelectedIndex(0); 
	}
	
	private void searchButtonClicked()
	{

		double[] range = Exam1.convertToRange((String)comboAge.getSelectedItem());
		if(range[0] >= 0)
		{
			carList = ex1.Search((int)range[0], (int)range[1]);
		}
		
		if(carList.size() > 0){
			currentIndex = 0;
			cm.setVisible(true);
			cm.displayDetails(currentIndex);
			
			if(carList.size() == 1){
				next.setEnabled(false);
				previous.setEnabled(false);
			}else{
				next.setEnabled(true);
				previous.setEnabled(true);
			}		
			ex1.repaint();
		
		}else{
			JOptionPane.showMessageDialog(ex1, "You can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
		}
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
