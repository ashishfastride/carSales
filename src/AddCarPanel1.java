import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.JFrame.*;
public class AddCarPanel1 extends JPanel implements ActionListener
{
	JPanel buttonPanel = new JPanel();
	JLabel headingLabel = new JLabel("Add a Car");
	JButton save = new JButton("Save");
	JButton reset = new JButton("Reset");
	JPanel contentpane;
	Exam1 carsales;
	Ccomponents cm = new Ccomponents();

	
	public AddCarPanel1(Exam1 carsys){
		carsales = carsys;
		save.addActionListener(this);
		reset.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);
		buttonPanel.add(save);
    	buttonPanel.add(reset);
		add(buttonPanel);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		headingLabel.setFont(new Font("Serif", Font.BOLD, 16));
		headingLabel.setForeground(Color.BLACK);
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		cm.add(buttonPanel, "Center");
		add(cm);
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(5,5,5,5));
	}


	@Override
	public void actionPerformed(ActionEvent e) {
				if(e.getSource() == reset){
					resetButtonClicked();
				}
				else if(e.getSource() == save){
					saveButtonClicked();
				}
			}
	
	public void resetButtonClicked()
	{
		cm.resetText();
	}
	
	public void saveButtonClicked(){
		String mfg1 = "";
		String CModel = "";
		String extra = "";
		double TvlKm = 0;
		int CPrice = 0;
		int year1 = 0;
		boolean valid = false;
		
		try{
		mfg1 = (cm.mfg).getText();
		year1 = Integer.parseInt((cm.yer).getText().trim());
		CModel = (cm.mod).getText().trim();
		CPrice = Integer.parseInt((cm.prc).getText().trim());
		TvlKm = Double.parseDouble((cm.tKm).getText().trim());
		extra = (cm.eInfo).getText();
		if (true)
		{
			if(year1>=1900 && year1<=9999)
			{
				if(true)
				{
					int selectionOption = JOptionPane.showConfirmDialog(null, "are u sure?","confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
					if(selectionOption == JOptionPane.YES_OPTION)
						{
							int i=AddCarDb.CarDb(mfg1,year1,CModel,CPrice,TvlKm,extra);
							JOptionPane.showMessageDialog(carsales, "Record added.","Congratulation", JOptionPane.INFORMATION_MESSAGE);
							resetButtonClicked();				
						}else 
							JOptionPane.showMessageDialog(AddCarPanel1.this, "Sorry Unable to add car?","unknown error?",JOptionPane.ERROR_MESSAGE);
				}else
							JOptionPane.showMessageDialog(AddCarPanel1.this, "an error has been occured due to incorrect \"car model\" textfield data.\n int this field must contain atleast two non-spaced characters","invalid field",JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(AddCarPanel1.this, "an error has been occured due to incorrect \"year\" textfield data.\n int this field must contain year between 1900 to 9999","invalid field",JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(AddCarPanel1.this, "an error has been occured due to incorrect \"Manufacturer\" textfield data.\n int this field must contain atleast two non-spaced characters","invalid field",JOptionPane.ERROR_MESSAGE);
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
		private boolean validateString(String arg)
		{
			boolean valid = false;
			String[] splitted = arg.split(" "); 
			for (int i = 0; i < splitted.length; i++)
			{
				valid = (splitted[i].length() > 2);
				if (valid)
					break;
			}

			return valid;
		}
}
