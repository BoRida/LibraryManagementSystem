import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BorrowerDaoImpl implements BorrowerDao {
	
	static Connection conn;
	Scanner sc;
	
	public BorrowerDaoImpl(Connection conn, Scanner sc) throws SQLException {
		BorrowerDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllBorrowers() throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_borrower";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Borrower borrower;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				borrower = new Borrower();
				borrower.setCardNo(rs.getInt("cardNo"));
				borrower.setName(rs.getString("name"));
				borrower.setAddress(rs.getString("address"));
				borrower.setPhone(rs.getInt("phone"));
				System.out.println(borrower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBorrower(Borrower borrower) {
		String sqlQuery = "UPDATE library.tbl_borrower SET name=?, address=?, phone=? WHERE cardNo=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, borrower.getName());
			ps.setString(2, borrower.getAddress());
			ps.setInt(3, borrower.getPhone());
			ps.setInt(4, borrower.getCardNo());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllBorrowers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBorrower(Borrower borrower) {
		String sqlQuery = "INSERT INTO `library`.`tbl_borrower` (`cardNo`, `name`, `address`, `phone`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, borrower.getCardNo());
			ps.setString(2, borrower.getName());
			ps.setString(3, borrower.getAddress());
			ps.setInt(4, borrower.getPhone());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllBorrowers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBorrower(Borrower borrower) throws SQLException, RuntimeException {
		BookLoanDaoImpl bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
		bookLoanDaoImpl.deleteBookLoanByCardNo(borrower.getCardNo());
		String sqlQuery = "DELETE FROM library.tbl_borrower WHERE cardNo=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, borrower.getCardNo());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBorrowers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Boolean verifyBorrower(int cardNo) {
		Boolean verified = false;
		String sqlQuery = "SELECT * FROM library.tbl_borrower";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("cardNo") == cardNo) {
					verified = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return verified;
	}

}
