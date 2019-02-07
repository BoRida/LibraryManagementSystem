import java.sql.SQLException;

public interface AuthorDao {
	public void getAllAuthors() throws SQLException;
	public void updateAuthor(Author author);
	public void addAuthor(Author author);
	public void deleteAuthor(Author author) throws SQLException;
}
