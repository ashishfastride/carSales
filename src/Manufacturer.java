import java.sql.*;
import java.util.*;

public class Manufacturer {

	public int getManufacturer(){
		Connection con = DB.getConnection();
		Set<String> set = new HashSet<String>();
		try{
			String command = "select Manufacturer from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("manufacturer"); 
					set.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		int i= set.size();
		return i;
	}
}
