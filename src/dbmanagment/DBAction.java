package dbmanagment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JdbcOperation.JDBCUtility;

public class DBAction {
	
public boolean checkuser(String username,String password){
	Connection con=JDBCUtility.GetConnection();
	PreparedStatement pre;
	try{
		pre=con.prepareStatement("select * from user where username=? AND password=?");
		pre.setString(1, username);
		pre.setString(2, password);
		ResultSet rs=pre.executeQuery();
		if(rs.next())
		{
			return true;
		}else
		{
			return false;
		}
	}catch(Exception e)
	{
		System.out.println("Check user error");
	}
	return false;
}
public boolean checkstatus(String username){
	Connection con=JDBCUtility.GetConnection();
	PreparedStatement pre;
	try
	{
		pre=con.prepareStatement("select status from user where username=?");
		pre.setString(1, username);
		ResultSet rs=pre.executeQuery();
		rs.next();
		if(rs.getInt("status")==1)
		{
			return false;
		}else
		{
			return true;
		}
	}catch(Exception e){
		System.out.println("Check status error");
	}
	return false;
}
public boolean changestatus(String username,int status)
{
	Connection con=JDBCUtility.GetConnection();
	PreparedStatement pre;
	try
	{
		pre=con.prepareStatement("update user set status=? where username=?");
		pre.setInt(1, status);
		pre.setString(2, username);
		System.out.println("change Status");
		if(pre.executeUpdate()==1)
		{
			System.out.println("change status success");
			return true;
		}else
		{
			System.out.println("change status failure");
			return false;
		}
	}catch(Exception e){
		System.out.println("change status error occured	"+e);
	}
	return false;
}
public boolean registerUser(String username,String password)
{
	Connection con=JDBCUtility.GetConnection();
	PreparedStatement pre;
	try{
		pre=con.prepareStatement("insert into user values(?,?,?)");
		pre.setString(1, username);
		pre.setString(2, password);
		pre.setInt(3, 0);
		if(pre.executeUpdate()==1)
		{
			JDBCUtility.CloseConnection(null);
			return true;
		}else
		{
			System.out.println("Registration Failed");
			return false;
		}
	}catch(Exception e){
		System.out.println("register error");
	}
	return false;
}
public boolean checkuser(String username) {
	Connection con=JDBCUtility.GetConnection();
	PreparedStatement pre;
	try{
		pre=con.prepareStatement("select * from user where username=?");
		pre.setString(1, username);
		
		ResultSet rs=pre.executeQuery();
		if(rs.next())
		{
			return true;
		}else
		{
			return false;
		}
	}catch(Exception e)
	{
		System.out.println("Check user error");
	}
	return false;
}
}
