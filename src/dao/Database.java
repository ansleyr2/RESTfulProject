package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public Connection Get_Connection() throws Exception
	{
		try
		{
			//String connectionURL = "jdbc:mysql://localhost:3306/workingbrain";
			Connection connection = null;
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connection = DriverManager.getConnection(connectionURL, "root", "");
		    //return connection;
		    
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database = 
		        "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=F:\\Work\\AngularJs\\RequireJSDemo\\database\\AppDB.accdb;";
			connection = DriverManager.getConnection(database, "", "");
			return connection;
		    
		}
		catch (SQLException e)
		{
		throw e;	
		}
		catch (Exception e)
		{
		throw e;	
		}
	}

}
