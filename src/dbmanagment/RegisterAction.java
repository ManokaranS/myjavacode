package dbmanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JdbcOperation.JDBCUtility;

public class RegisterAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String uname=request.getParameter("username");
		String upass=request.getParameter("password");
		DBAction db=new DBAction();
		Connection con = JDBCUtility.GetConnection();
		try {
			
			if(!db.checkuser(uname, upass)){
			String sql = "INSERT INTO user(username,password,status) VALUES(?,?,?)";
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1,uname);
			prep.setString(2,upass);
			prep.setInt(3, 0);
			if (prep.executeUpdate() == 1) {
				JDBCUtility.CloseConnection(null);
				return "Register.success";
			} else {
				System.out.println("Registration Failed");
				return "Register.failed";
			}}
			else
			{
				System.out.println("Entered User Alrady Exist");
				return "Register.exist";
			}
		} catch (Exception e) {
			JDBCUtility.CloseConnection(e);
		}
		
		
	
		
		
		return "Register.failed";
	}

}
