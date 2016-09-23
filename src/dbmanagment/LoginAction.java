package dbmanagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JdbcOperation.JDBCUtility;

public class LoginAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String uname=request.getParameter("username");
		String upass=request.getParameter("password");
		Connection con = JDBCUtility.GetConnection();
		DBAction dba=new DBAction();
		PreparedStatement pre;
		try{
			pre = con.prepareStatement("Select * from user  where username=? AND password=?");
			pre.setString(1, uname);
			pre.setString(2, upass);
			ResultSet rs =pre.executeQuery();
			if (dba.checkuser(uname,upass)) {	
				if(dba.checkstatus(uname)){
					dba.changestatus(uname,1);
					JDBCUtility.CloseConnection(null);
					return "Login.success";
				}
				else
				{
					System.out.println("Login Already Login.AlreadyLogin");
					return "Login.AlreadyLogin";
				}
				
			
		}else {
			return "Login.failed";
			}
	}catch (Exception e) {
		System.err.println("Error Occured :" + e);
	}
	return "Login.failed";

}
}