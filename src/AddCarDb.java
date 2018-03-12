import java.sql.*;
public class AddCarDb {

	public static int CarDb(String manuf,int MfgYear,String CarModel,int CarPrice,double tvlKm,String Extra){
		int Status = 0;
		try{
			Connection con = DB.getConnection();
			String command = "insert into addcar values('"+manuf+"',"+MfgYear+",'"+CarModel+"',"+CarPrice+","+tvlKm+",'"+Extra+"');";
			PreparedStatement ps=con.prepareStatement(command);
			Status=ps.executeUpdate(command);
			con.close();
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return Status;
	}
}
