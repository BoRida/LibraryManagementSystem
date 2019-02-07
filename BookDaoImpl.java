import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDaoImpl implements BookDao{
	
	static Connection conn;
	Scanner sc;
	
	public BookDaoImpl(Connection conn, Scanner sc) throws SQLException {
		BookDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllBooks() {
		String sqlQuery = "SELECT * FROM library.tbl_book";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt("bookId"));
				book.setTitle(rs.getString("title"));
				book.setAuthId(rs.getInt("authId"));
				book.setPubId(rs.getInt("pubId"));
				System.out.println(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(Book book) {
		String sqlQuery = "UPDATE library.tbl_book SET title=?, authId=?, pubId=? WHERE bookId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getAuthId());
			ps.setInt(3, book.getPubId());
			ps.setInt(4, book.getBookId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllBooks();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBook(Book book) {
		String sqlQuery = "INSERT INTO `library`.`tbl_book` (`bookId`, `title`, `authId`, `pubId`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, book.getBookId());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getAuthId());
			ps.setInt(4, book.getPubId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllBooks();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBook(Book book) throws SQLException, RuntimeException {
		//String sqlQuery = "DELETE library.tbl_book, library.tbl_book_copies, library.tbl_book_loans FROM (library.tbl_book_copies INNER JOIN library.tbl_book ON library.tbl_book_copies.bookId=library.tbl_book.bookId) INNER JOIN library.tbl_book_loans ON library.tbl_book_loans.bookId=library.tbl_book.bookId WHERE library.tbl_book.bookId=library.tbl_book_copies.bookId AND library.tbl_book.bookId=library.tbl_book_loans.bookId AND library.tbl_book.bookId=?"; //inner join here
		BookCopiesDaoImpl bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		bookCopiesDaoImpl.deleteBookCopiesByBook((book.getBookId()));
		BookLoanDaoImpl bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
		bookLoanDaoImpl.deleteBookLoanByBook(book.getBookId());
		String sqlQuery = "DELETE FROM library.tbl_book WHERE bookId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, book.getBookId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBooks();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookByAuthor(int authId) {
		//String sqlQuery = "DELETE library.tbl_book, library.tbl_book_copies, library.tbl_book_loans FROM (library.tbl_book_copies INNER JOIN library.tbl_book ON library.tbl_book_copies.bookId=library.tbl_book.bookId) INNER JOIN library.tbl_book_loans ON library.tbl_book_loans.bookId=library.tbl_book.bookId WHERE library.tbl_book.bookId=library.tbl_book_copies.bookId AND library.tbl_book.bookId=library.tbl_book_loans.bookId AND library.tbl_book.authId=?"; //inner join here
		String sqlQuery = "DELETE FROM library.tbl_book WHERE authId=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, authId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBooks();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteBookByPublisher(int pubId) {
		//String sqlQuery = "DELETE library.tbl_book, library.tbl_book_copies, library.tbl_book_loans FROM (library.tbl_book_copies INNER JOIN library.tbl_book ON library.tbl_book_copies.bookId=library.tbl_book.bookId) INNER JOIN library.tbl_book_loans ON library.tbl_book_loans.bookId=library.tbl_book.bookId WHERE library.tbl_book.bookId=library.tbl_book_copies.bookId AND library.tbl_book.bookId=library.tbl_book_loans.bookId AND library.tbl_book.pubId=?"; //inner join here
		String sqlQuery = "DELETE FROM library.tbl_book WHERE pubId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, pubId);
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllBooks();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int options(Scanner sc) throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_book";
		String sqlQuery2 = "SELECT MAX(bookId) AS quit FROM library.tbl_book";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int quit = 0;
		int bookId = 0;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("bookId")+") "+rs.getString("title"));
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
			bookId = sc.nextInt();
			sc.nextLine();
			if (bookId==quit) {
				return 0;
			}
		}
		return bookId;
	}
	
	@Override
	public void showAvailableBooks() {
		String sqlQuery = "SELECT * FROM (library.tbl_book INNER JOIN library.tbl_book_copies ON library.tbl_book.bookId=library.tbl_book_copies.bookId) INNER JOIN tbl_library_branch ON tbl_library_branch.branchId=tbl_book_copies.branchId WHERE tbl_book_copies.noOfCopies>0";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("bookId")+") "+rs.getString("title")+", "+rs.getString("branchName")+" with "+rs.getInt("noOfCopies")+" copies left");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void showBorrowedBooks(int cardNo) {
		String sqlQuery = "SELECT * FROM (tbl_book_loans INNER JOIN tbl_book ON tbl_book_loans.bookId=tbl_book.bookId) INNER JOIN tbl_library_branch ON tbl_library_branch.branchId=tbl_book_loans.branchId WHERE cardNo=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, cardNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("bookId")+") "+rs.getString("title")+", "+rs.getString("branchName")+" borrowed on "+rs.getObject("dateOut")+" due on "+rs.getObject("dueDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
