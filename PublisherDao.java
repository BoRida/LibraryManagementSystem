import java.sql.SQLException;

public interface PublisherDao {
	public void getAllPublishers() throws SQLException;
	public void updatePublisher(Publisher publisher);
	public void addPublisher(Publisher publisher);
	public void deletePublisher(Publisher publisher) throws SQLException;
}
