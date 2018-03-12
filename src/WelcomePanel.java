import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
public class WelcomePanel extends JPanel implements ChangeListener{
	Exam1 ex;
	Manufacturer mf = new Manufacturer();
	TotalCar tc = new TotalCar();
	JLabel wLabel = new JLabel("Welcome to Cars Sales System");
	JLabel noCars =new JLabel();
	JLabel noManufacturers=new JLabel();
	JLabel avgPrice=new JLabel();
	JLabel avgKm=new JLabel();
	JLabel Age=new JLabel();
	JPanel statusPanel = new JPanel();
	JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
	private boolean carsUpdated = true;
	
	public WelcomePanel(Exam1 ex1){
		ex = ex1;
		setLayout(new BorderLayout(0,10));
		ex1.addCarUpdateListener(this);
		
		statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.Y_AXIS));
		centerPanel.add(statusPanel);
		wLabel.setBorder(new EmptyBorder(new Insets(10,200,10,0)));
		wLabel.setFont(new Font("Serif", Font.BOLD, 16));
		wLabel.setForeground(Color.BLACK);	
		UpdateCarInfo();
		statusPanel.add(noCars);
		statusPanel.add(noManufacturers);
		statusPanel.add(avgPrice);
		statusPanel.add(avgKm);
		statusPanel.add(Age);
		statusPanel.add(Box.createVerticalStrut(20));
		
		add(wLabel, "North");
		add(centerPanel, "Center");
		
	}
	
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == ex)
		{
			carsUpdated = true;
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent ev) 
	{
				if (ev.getSource() instanceof JTabbedPane)
				{
					JTabbedPane tab = (JTabbedPane)ev.getSource();
					if (tab.getSelectedIndex() == 0)
					{
						if (carsUpdated)
						{
							UpdateCarInfo();
							carsUpdated = false;
						}
						UpdateCarInfo();
					}
				}
	}
	
	public void UpdateCarInfo(){
		int n = mf.getManufacturer();
		ArrayList<String> carno = tc.getCarModel();
		ArrayList<String> AvgP = tc.getPrice();
		ArrayList<String> km = tc.getTravlKm();
		ArrayList<String> age = tc.getMfgYear();
		int ag1 = age.size();
		double y = 0, z = 0;
		
		for(String x: AvgP)
			y = y+Double.valueOf(x);	
		int cNo = carno.size();
		double avgPrc = Math.round(((y/(AvgP.size()))*100.0)/100.0);
		for(String f: AvgP)
			z = z + Double.valueOf(f);
		double avgKm1 = z/(km.size());
		double avgKm2 = Math.round( avgKm1 * 100.0 ) / 100.0;
		
		double ag2 = 0; 
		for(int i = 0; i < ag1; i++){
			int ag = tc.getAge(i);
			ag2 = ag2+ag;
		}
		double ag3 = ag2/(age.size());
		double ag4 = Math.round( ag3 * 100.0 ) / 100.0;
		 
		noCars.setText("Total no of cars :  "+ String.valueOf(cNo));
		noManufacturers.setText("Total no of Manufacturers :  "+ String.valueOf(n));
		avgPrice.setText("Average Car Price :  "+String.valueOf(avgPrc));
		avgKm.setText("Avg car Km :  "+String.valueOf(avgKm2));
		Age.setText("Avg Car Age :  "+String.valueOf(ag4));
	}
}
