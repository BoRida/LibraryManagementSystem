import java.sql.SQLException;
import java.util.Scanner;

public interface LibraryBranchDao {
	public void getAllLibraryBranches() throws SQLException;
	public LibraryBranch getLibraryBranch(int branchId) throws SQLException;
	public void updateLibraryBranch(LibraryBranch libraryBranch);
	public void addLibraryBranch(LibraryBranch libraryBranch);
	public void deleteLibraryBranch(LibraryBranch libraryBranch) throws SQLException, RuntimeException;
	public LibraryBranch options(Scanner sc) throws SQLException;
}
