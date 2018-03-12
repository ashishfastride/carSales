import java.sql.*;
public class DeleteDb {

	static TotalCar tc = new TotalCar();
	static Connection con = DB.getConnection();
	public static int DeleteCar(int currentIndex){
		int Status = 0;
		try{
			String model = tc.getCarModel().get(currentIndex);
			  // String query = "delete from users where id = ?";
			String command = "delete from addcar where CarModel = '"+model+"';";
			PreparedStatement ps=con.prepareStatement(command);
			Status=ps.executeUpdate(command);
			//con.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return Status;
	}
}
