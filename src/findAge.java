import java.sql.*;
import java.sql.ResultSet;
import java.util.*;


/**
 * @author Ashish
 *
 */
public class findAge {

  static ArrayList<Integer> IndexList(String a){
	  int h = Integer.valueOf(a.trim());
	  Connection con =DB.getConnection();
		ArrayList<String> al = new ArrayList<String>();
		try{
			String command = "select MfgYear from AddCar";
			PreparedStatement ps = con.prepareStatement(command);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				 String m = rs.getString("MfgYear"); 
					al.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		GregorianCalendar calendar = new GregorianCalendar();	
		
		AgePanel ap = new AgePanel(null);
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for(String x: al){
			if((calendar.get(Calendar.YEAR)-Integer.valueOf(x)) == h){
			    for (int i = 0; i < al.size(); i++)
			        if(x.equals(al.get(i)))
			            indexList.add(i);
			    break;
			}
		}
		System.out.println(indexList);
		    return indexList;
		}
}
