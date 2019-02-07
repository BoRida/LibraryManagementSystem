import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookLoanDaoImpl implements BookLoanDao {

	static Connection conn;
	Scanner sc;
	
	public BookLoanDaoImpl(Connection conn, Scanner sc) throws SQLException {
		BookLoanDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllBookLoans() throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_book_loans";
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookLoan bookLoan;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookLoan = new BookLoan();
				bookLoan.setBookId(rs.getInt("bookId"));
				bookLoan.setBranchId(rs.getInt("branchId"));
				bookLoan.setCardNo(rs.getInt("cardNo"));
				bookLoan.setDateOut(rs.getDate("dateOut").toString());
				bookLoan.setDueDate(rs.getDate("dueDate").toString());
				System.out.println(bookLoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public BookLoan getBookLoan(int bookId, int branchId, int cardNo) throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		BookLoan bookLoan = new BookLoan();
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.setInt(2, branchId);
			ps.setInt(3, cardNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				bookLoan.setBookId(rs.getInt("bookId"));
				bookLoan.setBranchId(rs.getInt("branchId"));
				bookLoan.setCardNo(rs.getInt("cardNo"));
				bookLoan.setDateOut(rs.getDate("dateOut").toString());
				bookLoan.setDueDate(rs.getDate("dueDate").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bookLoan;
	}

	@Override
	public void updateBookLoan(BookLoan bookLoan) {
		String sqlQuery = "UPDATE library.tbl_book_loans SET dateOut=?, dueDate=? WHERE bookId=? AND branchId=? AND cardNo=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setObject(1, java.sql.Date.valueOf(bookLoan.getDateOut()));
			ps.setObject(2, java.sql.Date.valueOf(bookLoan.getDueDate()));
			ps.setInt(3, bookLoan.getBookId());
			ps.setInt(4, bookLoan.getBranchId());
			ps.setInt(5, bookLoan.getCardNo());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBookLoan(BookLoan bookLoan) throws SQLException {
		String sqlQuery = "INSERT INTO `library`.`tbl_book_loans` (`bookId`, `branchId`, `cardNo`, `dateOut`, `dueDate`) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookLoan.getBookId());
			ps.setInt(2, bookLoan.getBranchId());
			ps.setInt(3, bookLoan.getCardNo());
			ps.setObject(4, java.sql.Date.valueOf(bookLoan.getDateOut()));
			ps.setObject(5, java.sql.Date.valueOf(bookLoan.getDueDate()));
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BookCopies bookCopies = new BookCopies();
		BookCopiesDaoImpl bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		bookCopies.setBookId(bookLoan.getBookId());
		bookCopies.setBranchId(bookLoan.getBranchId());
		bookCopies.setNoOfCopies(bookCopiesDaoImpl.getBookCopies(bookLoan.getBookId(), bookLoan.getBranchId())-1);//subtract 1 from noOfCopies
		bookCopiesDaoImpl.updateBookCopies(bookCopies);
	}

	@Override
	public void deleteBookLoan(BookLoan bookLoan) throws SQLException {
		String sqlQuery = "DELETE FROM library.tbl_book_loans WHERE bookID=? AND branchId=? AND cardNo=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookLoan.getBookId());
			ps.setInt(2, bookLoan.getBranchId());
			ps.setInt(3, bookLoan.getCardNo());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BookCopies bookCopies = new BookCopies();
		BookCopiesDaoImpl bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		bookCopies.setBookId(bookLoan.getBookId());
		bookCopies.setBranchId(bookLoan.getBranchId());
		bookCopies.setNoOfCopies(bookCopiesDaoImpl.getBookCopies(bookLoan.getBookId(), bookLoan.getBranchId())+1);//add 1 to noOfCopies
		bookCopiesDaoImpl.updateBookCopies(bookCopies);
	}
	
	@Override
	public void deleteBookLoanByBook(int bookId) {
		String sqlQuery = "DELETE FROM library.tbl_book_loans WHERE bookID=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookLoanByBranch(int branchId) {
		String sqlQuery = "DELETE FROM library.tbl_book_loans WHERE branchId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, branchId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookLoanByCardNo(int cardNo) {
		String sqlQuery = "DELETE FROM library.tbl_book_loans WHERE cardNo=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, cardNo);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBookLoans();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
