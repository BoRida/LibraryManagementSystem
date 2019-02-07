import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookCopiesDaoImpl implements BookCopiesDao {
	
	static Connection conn;
	Scanner sc;
	
	public BookCopiesDaoImpl(Connection conn, Scanner sc) throws SQLException {
		BookCopiesDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}

	@Override
	public void getAllBookCopies() throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_book_copies";
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookCopies bookCopies;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookCopies = new BookCopies();
				bookCopies.setBookId(rs.getInt("bookId"));
				bookCopies.setBranchId(rs.getInt("branchId"));
				bookCopies.setNoOfCopies(rs.getInt("noOfCopies"));
				System.out.println(bookCopies);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getBookCopies(int bookId, int branchId) throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_book_copies WHERE bookId=? AND branchId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int noOfCopies = 0;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.setInt(2, branchId);
			rs = ps.executeQuery();
			while (rs.next()) {
				noOfCopies = rs.getInt("noOfCopies");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return noOfCopies;
	}
	
	

	@Override
	public void updateBookCopies(BookCopies bookCopies) {
		String sqlQuery = "UPDATE library.tbl_book_copies SET noOfCopies=? WHERE bookId=? AND branchId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookCopies.getNoOfCopies());
			ps.setInt(2, bookCopies.getBookId());
			ps.setInt(3, bookCopies.getBranchId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllBookCopies();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBookCopies(BookCopies bookCopies) {
		String sqlQuery = "INSERT INTO `library`.`tbl_book_copies` (`bookId`, `branchId`, `noOfCopies`) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookCopies.getBookId());
			ps.setInt(2, bookCopies.getBranchId());
			ps.setInt(3, bookCopies.getNoOfCopies());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllBookCopies();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBookCopies(BookCopies bookCopies) {
		String sqlQuery = "DELETE FROM library.tbl_book_copies WHERE bookID=? AND branchId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookCopies.getBookId());
			ps.setInt(2, bookCopies.getBranchId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookCopies();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookCopiesByBook(int bookId) {
		String sqlQuery = "DELETE FROM library.tbl_book_copies WHERE bookID=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookCopies();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookCopiesByLibraryBranch(int branchId) {
		String sqlQuery = "DELETE FROM library.tbl_book_copies WHERE branchId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, branchId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookCopies();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
