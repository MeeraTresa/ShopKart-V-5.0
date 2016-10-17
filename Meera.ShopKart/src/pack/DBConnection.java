package pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
public static Connection getConnect() {
	Connection con=null;
	try (FileInputStream fi=new FileInputStream("C:/Users/I333628/workspace/JAVA EE/Shopkart/Meera.ShopKart/WebContent/jdbc.properties")){
		Properties prop=new Properties();
		prop.load(fi);
		Class.forName(prop.getProperty("driver"));
		System.out.println("Driver loaded succesfully");
		String url=prop.getProperty("url");
		con=DriverManager.getConnection(url,prop.getProperty("uname"),prop.getProperty("pwd"));
		System.out.println("Connected successfully with MYSQL");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return con;
}
}
