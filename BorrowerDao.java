import java.sql.SQLException;

public interface BorrowerDao {
	public void getAllBorrowers() throws SQLException;
	public void updateBorrower(Borrower borrower);
	public void addBorrower(Borrower borrower);
	public void deleteBorrower(Borrower borrower) throws SQLException, RuntimeException;
	public Boolean verifyBorrower(int cardNo);
}
