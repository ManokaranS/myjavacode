package dbmanagment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JdbcOperation.JDBCUtility;

public class LogoutAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		DBAction dba=new DBAction();
		String uname=request.getParameter("username");
		if(dba.checkuser(uname))
		{
			dba.changestatus(uname,0);
			System.out.println("Status change success logout success");
			JDBCUtility.CloseConnection(null);
			return "Logout.success";
		}
		else
		{
			System.out.println("Logout Failed");
			return "Logout.failed";
		}
		
		
	}
	}