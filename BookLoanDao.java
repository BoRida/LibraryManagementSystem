import java.sql.SQLException;

public interface BookLoanDao {
	public void getAllBookLoans() throws SQLException;
	public BookLoan getBookLoan(int bookId, int branchId, int cardNo) throws SQLException;
	public void updateBookLoan(BookLoan bookLoan);
	public void addBookLoan(BookLoan bookLoan) throws SQLException;
	public void deleteBookLoan(BookLoan bookLoan) throws SQLException;
	public void deleteBookLoanByBook(int bookId);
	public void deleteBookLoanByBranch(int branchId);
	public void deleteBookLoanByCardNo(int cardNo);
}
