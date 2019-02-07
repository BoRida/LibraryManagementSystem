import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryBranchDaoImpl implements LibraryBranchDao {
	
	static Connection conn;
	Scanner sc;
	
	public LibraryBranchDaoImpl(Connection conn, Scanner sc) throws SQLException {
		LibraryBranchDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllLibraryBranches() throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_library_branch";
		PreparedStatement ps = null;
		ResultSet rs = null;
		LibraryBranch libraryBranch;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				libraryBranch = new LibraryBranch();
				libraryBranch.setBranchId(rs.getInt("branchId"));
				libraryBranch.setBranchName(rs.getString("branchName"));
				libraryBranch.setBranchAddress(rs.getString("branchAddress"));
				System.out.println(libraryBranch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LibraryBranch getLibraryBranch(int branchId) throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_library_branch WHERE branchId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		LibraryBranch libraryBranch = new LibraryBranch();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, branchId);
			rs = ps.executeQuery();
			while (rs.next()) {
				libraryBranch.setBranchId(rs.getInt("branchId"));
				libraryBranch.setBranchName(rs.getString("branchName"));
				libraryBranch.setBranchAddress(rs.getString("branchAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return libraryBranch;
	}

	@Override
	public void updateLibraryBranch(LibraryBranch libraryBranch) {
		String sqlQuery = "UPDATE library.tbl_library_branch SET branchName=?, branchAddress=? WHERE branchId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, libraryBranch.getBranchName());
			ps.setString(2, libraryBranch.getBranchAddress());
			ps.setInt(3, libraryBranch.getBranchId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllLibraryBranches();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLibraryBranch(LibraryBranch libraryBranch) {
		String sqlQuery = "INSERT INTO `library`.`tbl_library_branch` (`branchId`, `branchName`, `branchAddress`) VALUES (?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, libraryBranch.getBranchId());
			ps.setString(2, libraryBranch.getBranchName());
			ps.setString(3, libraryBranch.getBranchAddress());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllLibraryBranches();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, RuntimeException{
		/*String sqlQuery = "DELETE library.tbl_library_branch, library.tbl_book_loans, library.tbl_book_copies\r\n" + 
				"FROM (library.tbl_library_branch \r\n" + 
				"INNER JOIN library.tbl_book_loans ON library.tbl_book_loans.branchId=library.tbl_library_branch.branchId)\r\n" + 
				"INNER JOIN library.tbl_book_copies ON library.tbl_book_loans.branchId=library.tbl_book_copies.branchId\r\n" + 
				"WHERE library.tbl_library_branch.branchId=?";*/
		BookCopiesDaoImpl bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		bookCopiesDaoImpl.deleteBookCopiesByLibraryBranch((libraryBranch.getBranchId()));
		BookLoanDaoImpl bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
		bookLoanDaoImpl.deleteBookLoanByBook(libraryBranch.getBranchId());
		String sqlQuery = "DELETE FROM library.tbl_library_branch WHERE branchID=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, libraryBranch.getBranchId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllLibraryBranches();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public LibraryBranch options(Scanner sc) throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_library_branch";
		String sqlQuery2 = "SELECT MAX(branchId) AS quit FROM library.tbl_library_branch";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int quit = 0;
		int branchId = 0;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("branchId")+") "+rs.getString("branchName")+", "+rs.getString("branchAddress"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ps = conn.prepareStatement(sqlQuery2);
			rs = ps.executeQuery();
			while (rs.next()) {
				quit = rs.getInt("quit")+1;
				System.out.println(quit+") QUIT TO PREVIOUS\n");
			}
			branchId = sc.nextInt();
			sc.nextLine();
			if (branchId==quit) {
				return null;
			}
		}
		return getLibraryBranch(branchId);

	}

}
