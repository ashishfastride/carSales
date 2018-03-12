import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
public class UpdatePanel extends JPanel implements ActionListener,ChangeListener
{
	JLabel showCar = new JLabel("Update a Car");
	JButton previous = new JButton("Previous");
	JButton next = new JButton("Next");
	JButton update = new JButton("Update");
	JPanel buttonPanel = new JPanel();
	private int currentIndex = 0;
	Exam1 carsales;
	TotalCar tc = new TotalCar();
	static Ccomponents cm = new Ccomponents();
	ArrayList<String> carList = tc.getCarModel();	
	private boolean carUpdated = true;
	static Connection con = DB.getConnection();
	
	public static void currentText() 
		{
			String manuf3 = cm.mfg.getText();
			int yer3 = Integer.valueOf((cm.yer.getText()).trim());
			String model3 = cm.mod.getText();
			int prc3 = Integer.valueOf((cm.prc.getText()).trim());
			int travl3 = Integer.valueOf((cm.tKm.getText()).trim());
			String extra3 = cm.eInfo.getText();
	   }
	
	
	public UpdatePanel(Exam1 carsys)
	{
		carsales = carsys;
		
		if(carList.size()>0)
		{
			cm.displayDetails(currentIndex);
		}
		
		update.setActionCommand("update1");
		previous.addActionListener(this);
		next.addActionListener(this);
		update.addActionListener(this);
		buttonPanel.add(previous);
		buttonPanel.add(update);
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
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if (!(carList.isEmpty()))
		{
			if(e.getSource()==previous)
			{
				previousClicked();
			}else if(e.getSource() == next)
			{
				nextClicked();
			}else if(e.getSource() == update)
			{
				//updateClicked();
			}
			
			if ("update1".equals(e.getActionCommand()))
			{
				String manuf3 = cm.mfg.getText();
				int yer3 = Integer.valueOf((cm.yer.getText()).trim());
				String model3 = cm.mod.getText();
				int prc3 = Integer.valueOf((cm.prc.getText()).trim());
				int travl3 = Integer.valueOf((cm.tKm.getText()).trim());
				String extra3 = cm.eInfo.getText();
				int selectedOption = JOptionPane.showConfirmDialog(null, 
		                "are you sure?", 
		                "Confirmation", 
				                JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) 
				{
				 try{
					String model = tc.getCarModel().get(currentIndex);
					String command = "update addcar set manufacturer='"+manuf3+"',MfgYear="+yer3+""
							+ ",CarModel='"+model3+"',CarPrice="+prc3+",TravelledKm="+travl3+",Exttra='"+extra3+"' where CarModel = '"+model+"';";
					PreparedStatement ps=con.prepareStatement(command);
					ps.executeUpdate(command);
						//con.close();
					JOptionPane.showMessageDialog(carsales,"Car Updated Successfully!");
					}catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
	private void nextClicked() 
	{
		if(!(carList.isEmpty()))
		{
			int i = cm.displayDetails(currentIndex);
			if(currentIndex < carList.size()-1)
			{
				currentIndex++;
				cm.displayDetails(currentIndex);
			}else
			{
				JOptionPane.showMessageDialog(carsales, "You can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
			}
		}else
		{
			next.setEnabled(false);
		}
	}
	
	private void previousClicked()
	{
		if(!(carList.isEmpty()))
		{
			if(currentIndex > 0)
			{
				currentIndex--;
				cm.displayDetails(currentIndex);
			}else
			{
				JOptionPane.showMessageDialog(carsales, "You can't navigate much more","Alert",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			previous.setEnabled(false);
		}
	}
	
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == carsales)
		{
			System.out.println("Hello");
			carUpdated = true;
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent ev) 
	{
		if (ev.getSource() instanceof JTabbedPane)
		{
			JTabbedPane tab = (JTabbedPane)ev.getSource();
			if (tab.getSelectedIndex() == 6)
			{
				if (true)
				{
					carList = tc.getCarModel();
					if(!(carList.isEmpty()))
					{
						cm.displayDetails(currentIndex);		
					}else if(carList.isEmpty())
					{
						ResetText();
					}
				}
			}
		}
	}
	public void ResetText()
	{
		cm.mfg.setText("");
		cm.yer.setText("");
		cm.mod.setText("");
		cm.prc.setText("");
		cm.tKm.setText("");
		cm.eInfo.setText("");
	}
}
