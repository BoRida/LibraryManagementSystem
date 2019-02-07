import java.sql.SQLException;
import java.util.Scanner;

public interface BookDao {
	public void getAllBooks() throws SQLException;
	public void updateBook(Book book);
	public void addBook(Book book);
	public void deleteBook(Book book) throws SQLException, RuntimeException;
	public void deleteBookByAuthor(int authId);
	public void deleteBookByPublisher(int pubId);
	public int options(Scanner sc) throws SQLException;
	public void showAvailableBooks();
	public void showBorrowedBooks(int cardNo);
}
