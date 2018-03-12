import java.sql.*;
import java.awt.*;
public class AllCarDb {

	Car[] carlist;
	int CurrentIndex = 0;
	static Connection con = DB.getConnection();
	public static int AllDb(){
		try{
			String cmd = "select * from AddCar";
			PreparedStatement ps = con.prepareStatement(cmd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String manu = rs.getString("manufacturer");
				String yr = rs.getString("MfgYear");
				String model = rs.getString("CarModel");
				String price = rs.getString("CarPrice");
				String tvl = rs.getString("TravelledKm");
				String info = rs.getString("Exttra");
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return 0;
	}
}
