import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utilities {
	
	static void printHello() {
		System.out.println("Hallo da");
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC", "root", "Assword1");
		return conn;
	}
	
}
