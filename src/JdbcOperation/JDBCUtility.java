package JdbcOperation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class JDBCUtility {

	static Properties prop;
	
	static {

		try {
						
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Load Success");

		} catch (Exception e) {
			System.out.println("Driver Load Failed " + e);
		}

	}

	private static final ThreadLocal<Connection> tlocal = new ThreadLocal<Connection>();
	

	public static Connection GetConnection() {

		try {
			Connection con = null;
			con = tlocal.get();

			if (con == null) {

				con = DriverManager.getConnection("jdbc:mysql://localhost/userlogin","root","root");
				tlocal.set(con);
				System.out.println("connection created");
				con.setAutoCommit(false);
				return con;
			} else {
				return con;
			}

		} catch (Exception e) {
			System.out.println("Connection Creation Failed");
			return null;
		}
		
		
	}
	
	public static void CloseConnection(Exception e1)
	{
		Connection con=tlocal.get();
		
		if(con!=null)
		{
			try{
				if(e1==null)
			{
					con.commit();
					System.out.println("Database has been commited");
			
			}
			else
			{
				con.rollback();
				System.out.println("Database has been rollback");
			}
			tlocal.remove();
			con=null;
			}
			catch(Exception e)
			{
				System.out.println("Error On Close Connection "+e);
			}
		}
		
		
	}
	
}
