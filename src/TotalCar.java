import java.util.*;
import java.sql.*;
public class TotalCar {

	static Connection con = DB.getConnection();
	 Car[] car = new Car[0]; 
	 
	public ArrayList<String> getCarModel(){
		ArrayList<String> manu = new ArrayList<String>();
		try{
			String command = "select CarModel from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("CarModel"); 
					manu.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manu;
	}
	
	
	public ArrayList<String> getPrice(){
		ArrayList<String> manu1 = new ArrayList<String>();
		try{
			String command = "select CarPrice from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("CarPrice"); 
					manu1.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manu1;
	}
	
	public ArrayList<String> getMfgYear(){
		ArrayList<String> manu2 = new ArrayList<String>();
		try{
			String command = "select MfgYear from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("MfgYear"); 
					manu2.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manu2;
	}
	
	public ArrayList<String> getTravlKm(){
		ArrayList<String> manu3 = new ArrayList<String>();
		try{
			String command = "select TravelledKm from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("TravelledKm"); 
					manu3.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manu3;
	}
	
	public ArrayList<String> getExtraInfo(){
		ArrayList<String> manu4 = new ArrayList<String>();
		try{
			String command = "select Exttra from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("Exttra"); 
					manu4.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return manu4;
	}
	
		public ArrayList<String> getManufacturer(){
			ArrayList<String> manu5 = new ArrayList<String>();
			//Connection con = DB.getConnection();
			try{
				String command = "select Manufacturer from AddCar";
				PreparedStatement ps = con.prepareStatement(command);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					 String m = rs.getString("manufacturer"); 
						manu5.add(m);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		return manu5;
	}
	
	public void getAgeDetail()
	{
		ArrayList<String> manu6 = new ArrayList<String>();
		try
		{
			String command = "select MfgYear from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				 String m = rs.getString("MfgYear"); 
				 manu6.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getAge(int z){
		GregorianCalendar calendar = new GregorianCalendar();
		int age = calendar.get(Calendar.YEAR)-Integer.valueOf(getMfgYear().get(z));
		return age;
	}
		
	
	
	public ArrayList<String> search(int minAge, int maxAge) {
		ArrayList<String> allCar = getCarModel();
		ArrayList<String> ac = new ArrayList<String>();
		if(maxAge == -1){
			for(int i=0; i < allCar.size(); i++){
				if(getAge(i) >= minAge){
					ac.add(allCar.get(i));
				}
			}
		}else{
			for(int i=0; i < allCar.size(); i++){
			
				if(getAge(i) >= minAge && getAge(i) <= maxAge){
					ac.add(allCar.get(i));
				}
			}
		}
		return ac;
	}


	public ArrayList<String> search1(int minPrice, int maxPrice, double minDistance, double maxDistance) {

		ArrayList<String> allCar = getCarModel();
		ArrayList<String> ac1 = new ArrayList<String>();
		int price;
		double distance;
		
		for(int i=0; i<allCar.size(); i++){
			price = Integer.valueOf(getPrice().get(i).trim());
			distance = Integer.valueOf(getTravlKm().get(i).trim());
			
			if (price >= minPrice && price <= maxPrice)
 				if (distance >= minDistance && distance <= maxDistance)
					ac1.add(allCar.get(i));
		}
		return ac1;
	}
		
}
