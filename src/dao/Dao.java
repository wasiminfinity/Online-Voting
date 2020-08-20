package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Model;

public class Dao {
	static Connection con = null; 
	static    
	{
		try 
		{
			Class.forName("oracle.jdbc.OracleDriver");
				//database_name --> OEVS
		    con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "wasim", "pass");
		}		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static int register(Model m, String sql) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sql);
		int i = ps.executeUpdate();
		return i;
	}
	public static ResultSet validate(Model m, String sql) throws SQLException {	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	} 
	
	public static int register1(String sql) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sql);
		int i = ps.executeUpdate();
		return i;
	}
	public static ResultSet validate1(String sql) throws SQLException {	
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}
	public static int common(Model m, String sql,String update) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sql);
		int i =ps.executeUpdate();	
		return i;
	} 
	public static ResultSet change(String sql) throws SQLException
	{
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		return rs;
	}

}
