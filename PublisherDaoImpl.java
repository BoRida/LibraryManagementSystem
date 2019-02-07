import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PublisherDaoImpl implements PublisherDao {
	
	static Connection conn;
	Scanner sc;
	
	public PublisherDaoImpl(Connection conn, Scanner sc) throws SQLException {
		PublisherDaoImpl.conn = Utilities.getConnection();
		this.sc = sc;
	}
	
	@Override
	public void getAllPublishers() throws SQLException {
		String sqlQuery = "SELECT * FROM library.tbl_publisher";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Publisher publisher;
		try {
			ps = conn.prepareStatement(sqlQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				publisher = new Publisher();
				publisher.setPublisherId(rs.getInt("publisherId"));
				publisher.setPublisherName(rs.getString("publisherName"));
				publisher.setPublisherAddress(rs.getString("publisherAddress"));
				publisher.setPublisherPhone(rs.getInt("publisherPhone"));
				System.out.println(publisher);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		String sqlQuery = "UPDATE library.tbl_publisher SET publisherName=?, publisherAddress=?, publisherPhone=? WHERE pubId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, publisher.getPublisherName());
			ps.setString(2, publisher.getPublisherAddress());
			ps.setInt(3, publisher.getPublisherPhone());
			ps.setInt(4, publisher.getPublisherId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY UPDATED\n**************************************************");
			getAllPublishers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPublisher(Publisher publisher) {
		String sqlQuery = "INSERT INTO `library`.`tbl_publisher` (`publisherId`, `publisherName`, `publisherAddress`, `publisherPhone`) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, publisher.getPublisherId());
			ps.setString(2, publisher.getPublisherName());
			ps.setString(3, publisher.getPublisherAddress());
			ps.setInt(4, publisher.getPublisherPhone());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY ADDED\n**************************************************");
			getAllPublishers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePublisher(Publisher publisher) throws SQLException {
		BookDaoImpl bookDaoImpl = new BookDaoImpl(conn, sc);
		bookDaoImpl.deleteBookByPublisher(publisher.getPublisherId());
		String sqlQuery = "DELETE FROM library.tbl_publisher WHERE publisherId=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, publisher.getPublisherId());
			ps.executeUpdate();
			System.out.println("SUCCESSFULLY DELETED\n**************************************************");
			getAllPublishers();
			System.out.println("**************************************************\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
