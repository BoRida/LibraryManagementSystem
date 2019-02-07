import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorDaoImpl implements AuthorDao{

	static Connection conn;
	Scanner sc;
	
	public AuthorDaoImpl(Connection conn, Scanner sc) throws SQLException {
		AuthorDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllAuthors() {
		String sqlQuery = "SELECT * FROM library.tbl_author";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Author author;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				author = new Author();
				author.setAuthorId(rs.getInt("authorId"));
				author.setAuthorName(rs.getString("authorName"));
				System.out.println(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthor(Author author) {
		String sqlQuery = "UPDATE library.tbl_author SET authorName=? WHERE authorId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, author.getAuthorName());
			ps.setInt(2, author.getAuthorId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllAuthors();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAuthor(Author author) {
		String sqlQuery = "INSERT INTO `library`.`tbl_author` (`authorId`, `authorName`) VALUES (?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, author.getAuthorId());
			ps.setString(2, author.getAuthorName());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllAuthors();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAuthor(Author author) throws SQLException {
		BookDaoImpl bookDaoImpl = new BookDaoImpl(conn, sc);
		bookDaoImpl.deleteBookByAuthor(author.getAuthorId());
		String sqlQuery = "DELETE FROM library.tbl_author WHERE authorID=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, author.getAuthorId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllAuthors();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
