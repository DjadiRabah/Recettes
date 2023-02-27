package djadi.rabah.recipes.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnection 
{
	private static Connection connection;

	private DaoConnection() 
	{
		try 
		{  
			Class<?> c = Class.forName("com.mysql.jdbc.Driver");
			Driver pilote = (Driver) c.newInstance() ;
	        DriverManager.registerDriver(pilote);
	        
	        String protocole =  "jdbc:mysql:";
	        String ip =  "localhost";
	        String port =  "3307";  
	        String dataBaseName =  "recipes";
	        String conString = protocole +  "//" + ip +  ":" + port +  "/" + dataBaseName ;
	        String user =  "root";
	        String password =  "";


	        
	        connection = DriverManager.getConnection(conString, user, password);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Connection getInstance() throws SQLException
	{
		if (connection == null || connection.isClosed())
			new DaoConnection();
		return connection;
	}
	
	public void close()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
