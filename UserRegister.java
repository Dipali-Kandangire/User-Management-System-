package JDBC; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserRegister {
	
	static Connection con;
	static PreparedStatement pt;
	static Statement st;
	static Scanner sc=new Scanner(System.in);
//	connecting to the database
	public static void connect()throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
	
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "12345");
		System.out.println("Connected");
	}
//	inserting record
	public static void register()
	{
		
		System.out.println("Enter uid");
		int uid=sc.nextInt();
		System.out.println("Enter password");
		String pwd=sc.next();
		
			try {
				
				
				pt=con.prepareStatement("insert into login values(?,?)");
				pt.setInt(1, uid);
				pt.setString(2, pwd);
				pt.executeUpdate();
				System.out.println("User registered");
				
				
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		
	}
//	updating the password
	public static void updatePassword() throws SQLException
	{
		pt=con.prepareStatement("update login set unm=? where uid=?");
		System.out.println("Enter uid whose password needs to be updated");
		int uid=sc.nextInt();
		System.out.println("Enter password to be updated");
		String pwd=sc.next();
		pt.setInt(2, uid);
		pt.setString(1, pwd);
		pt.executeUpdate();
		System.out.println("Password updated");
	}
//	deleting the user
	public static void deleteUser() throws SQLException
	{
		pt=con.prepareStatement("delete from login where uid=?");
		System.out.println("Enter uid to be deleted");
		int uid=sc.nextInt();
		pt.setInt(1, uid);
		pt.executeUpdate();
		System.out.println("User deleted");
	}
//	fetching the data
	public static void allUser()
	{
		try {
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from login");
			while(rs.next())
			{
				System.out.println(rs.getInt("uid"));
				System.out.println(rs.getString("unm"));
				
			}
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void validateUser()
	{
		int flg=0;
		System.out.println("Enter uid");
		int uid=sc.nextInt();
		System.out.println("Enter upassword");
		String upass=sc.next();
		try {
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from login");
			while(rs.next())
			{
				if(uid==rs.getInt("uid") && upass.equals(rs.getString("unm")))
						{
							flg=1;
							break;
						}
				
			}
			if(flg==1)
				System.out.println("User is valid");
			else
				System.out.println("Invalid user");
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
 
