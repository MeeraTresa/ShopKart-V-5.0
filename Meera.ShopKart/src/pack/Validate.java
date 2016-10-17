package pack;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {
	public static boolean checkUser(String un, String pw){
		boolean st = false;
		try(Connection con = DBConnection.getConnect()){
			PreparedStatement ps =con.prepareStatement
                    ("select * from user where userid=? and password=?");
			ps.setString(1, un);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			st=rs.next();
			
			}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return st;
	}
}
