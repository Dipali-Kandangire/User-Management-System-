package JDBC;



import java.sql.SQLException;

public class UserMain {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		UserRegister.connect();
		UserRegister.register();
		UserRegister.updatePassword();
		UserRegister.deleteUser();
		System.out.println("All user");
		UserRegister.allUser();
		System.out.println("Validate User");
		UserRegister.validateUser();

	}

}
