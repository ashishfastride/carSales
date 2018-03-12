import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CarComponents extends JPanel{

	//Color color = Color.lightGray;
	JPanel buttonPanel = new JPanel();
	JLabel headingLabel = new JLabel("Add a Car");
	JLabel manuf= new JLabel("Manufacturer");
	JLabel year= new JLabel("Mfg Year");
	JLabel model= new JLabel("Car Model");
	JLabel price= new JLabel("Car Price");
	JLabel trvlKm= new JLabel("Traveled Km.");
	JLabel extraInfo= new JLabel("Extra Specification");
	JLabel  mfg= new JLabel();
	JLabel  yer= new JLabel();
	JLabel  mod= new JLabel();
	JLabel  prc= new JLabel();
	JLabel  tKm= new JLabel();
	JLabel  eInfo= new JLabel();
	TotalCar tc= new TotalCar();
	Car car = new Car();
	//mfg.setBackground(color);
	public CarComponents(){
		JPanel compPanel = new JPanel(new GridBagLayout());
		//String currentFont = manufacturerLabel.getFont().getName();
		Insets currentInsets;
		GridBagConstraints gridBagConstraints;
		setLayout(new BorderLayout(0,20));
		currentInsets =  new Insets(0, 10, 0, 30);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = currentInsets;
        compPanel.add(manuf, gridBagConstraints);
		
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=1;
		gridBagConstraints.anchor=GridBagConstraints.WEST;
		gridBagConstraints.insets = currentInsets;
	    compPanel.add(year, gridBagConstraints);
		
		 gridBagConstraints = new GridBagConstraints();
	     gridBagConstraints.gridx = 0;
	     gridBagConstraints.gridy = 2;
	     gridBagConstraints.anchor = GridBagConstraints.WEST;
	     gridBagConstraints.insets = currentInsets;
	     compPanel.add(model, gridBagConstraints);
	     
	     //price.setFont(new Font(currentFont, Font.BOLD, 12));
	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 0;
	        gridBagConstraints.gridy = 3;
	        gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.insets = currentInsets;
	        compPanel.add(price, gridBagConstraints);

	        //trvlKm.setFont(new Font(currentFont, Font.BOLD, 12));
	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 0;
	        gridBagConstraints.gridy = 4;
	        gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.insets = currentInsets;
	        compPanel.add(trvlKm, gridBagConstraints);

	       // extraInfo.setFont(new Font(currentFont, Font.BOLD, 12));
	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 0;
	        gridBagConstraints.gridy = 5;
	        gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.insets = currentInsets;
	        compPanel.add(extraInfo, gridBagConstraints);

	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.weightx = 1.0;
	        compPanel.add(mfg, gridBagConstraints);

	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 1;
	        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.weightx = 1.0;
	        compPanel.add(yer, gridBagConstraints);

	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 2;
	        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.weightx = 1.0;
	        compPanel.add(mod, gridBagConstraints);

	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 3;
	        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
	        gridBagConstraints.weightx = 1.0;
	        compPanel.add(prc, gridBagConstraints);

	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 4;
	        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE;
			gridBagConstraints.anchor = gridBagConstraints.WEST;
	        gridBagConstraints.weightx = 1.0;
	        compPanel.add(tKm, gridBagConstraints);

	      //  eInfo.setLineWrap(true);
			currentInsets = new Insets(2, 20, 0, 20);
	        gridBagConstraints = new GridBagConstraints();
	        gridBagConstraints.gridx = 1;
	        gridBagConstraints.gridy = 5;
	        gridBagConstraints.anchor = gridBagConstraints.WEST;
			gridBagConstraints.weightx = 1.0;
	        compPanel.add(new JScrollPane(eInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), gridBagConstraints);
	
	        add(compPanel, "North");
	}
	public void resetText(){
		mfg.setText("");
		yer.setText("");
		mod.setText("");
		prc.setText("");
		tKm.setText("");
		eInfo.setText("");
	}
	
	public int displayDetails(int inde)
	{
		int index = inde;
		mfg.setText(tc.getManufacturer().get(index));
		yer.setText(tc.getMfgYear().get(index));
		//System.out.println(index);
		mod.setText(tc.getCarModel().get(index));
		prc.setText(tc.getPrice().get(index));
		tKm.setText(tc.getTravlKm().get(index));
		eInfo.setText(tc.getExtraInfo().get(index));
		return 0;
	}
}
