import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
public class AllCarPanel extends JPanel implements ActionListener,ChangeListener{
	JLabel showCar = new JLabel("Show All Models");
	JButton previous = new JButton("Previous");
	JButton next = new JButton("Next");
	JPanel buttonPanel = new JPanel();
	private int currentIndex = 0;
	Exam1 carsales;
	TotalCar tc = new TotalCar();
	CarComponents cm = new CarComponents();
	ArrayList<String> carList = tc.getCarModel();	
	private boolean carUpdated = true;
	
	
	public AllCarPanel(Exam1 carsys)
	{
		carsales = carsys;
		
		if(carList.size()>0)
		{
			cm.displayDetails(currentIndex);
		}
		
		previous.addActionListener(this);
		next.addActionListener(this);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		showCar.setFont(new Font("Serif", Font.BOLD, 16));
		showCar.setForeground(Color.BLACK);
		add(showCar);
		add(Box.createVerticalStrut(15));
		cm.add(buttonPanel,"Center");
		add(cm);
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(!(carList.isEmpty()))
		{
			if(e.getSource()==previous)
			{
				previousClicked();
			}else if(e.getSource() == next)
			{
				nextClicked();
			}
		 }
	}
	
	private void nextClicked() 
	{
		int i = cm.displayDetails(currentIndex);
		if(!(carList.isEmpty()))
		{
			if(currentIndex < carList.size()-1)
			{
				currentIndex++;
				cm.displayDetails(currentIndex);
			}else
				JOptionPane.showMessageDialog(carsales, "You can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
		}else
			next.setEnabled(false);
	}
	private void previousClicked() {
		if(!(carList.isEmpty()))
		{
			if(currentIndex > 0){
				currentIndex--;
				cm.displayDetails(currentIndex);
			}else{
				JOptionPane.showMessageDialog(carsales, "You can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
			}
		}else
			previous.setEnabled(false);
	}
	
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == carsales)
		{
			System.out.println("Hello");
			carUpdated = true;
		}
	}
	
	public void stateChanged(ChangeEvent ev) 
	{
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			if (tab.getSelectedIndex() == 2)
			{
				carList = tc.getCarModel();
				if (!(carList.isEmpty()))
				{
					cm.displayDetails(currentIndex);
				}else{
					 ResetLabels();
				 }
			}
		}
	}
	public void ResetLabels()
	{
		cm.mfg.setText("");
		cm.yer.setText("");
		cm.mod.setText("");
		cm.prc.setText("");
		cm.tKm.setText("");
		cm.eInfo.setText("");
		
	}
}
