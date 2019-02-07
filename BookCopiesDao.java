import java.sql.SQLException;

public interface BookCopiesDao {
	public void getAllBookCopies() throws SQLException;
	public int getBookCopies(int bookId, int branchId) throws SQLException;
	public void updateBookCopies(BookCopies bookCopies);
	public void addBookCopies(BookCopies bookCopies);
	public void deleteBookCopies(BookCopies bookCopies);
	public void deleteBookCopiesByBook(int bookId) throws SQLException;
	public void deleteBookCopiesByLibraryBranch(int branchId) throws SQLException;
}
