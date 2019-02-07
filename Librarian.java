import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


	public class Librarian {
		static Connection conn;
		static Scanner sc;
		static LibraryBranchDaoImpl libraryBranchDaoImpl;
		static BookCopiesDaoImpl BookCopiesDaoImpl;
		static BookDaoImpl BookDaoImpl;
		
		public Librarian(Scanner sc) throws SQLException {
			Librarian.sc = sc;
			Librarian.conn = Utilities.getConnection();
			Librarian.libraryBranchDaoImpl = new LibraryBranchDaoImpl(conn, sc);
			Librarian.BookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
			Librarian.BookDaoImpl = new BookDaoImpl(conn, sc);
		}
		
		void LIB1() throws SQLException {
			do {
				System.out.println("1) Enter branch you manage \n2) Quit to previous\n");
				String input = sc.nextLine();
				if (input.toUpperCase().equals("ENTER BRANCH YOU MANAGE") || input.toUpperCase().equals("ENTER") || input.toUpperCase().equals("E") || input.equals("1")) {
					LIB2();
				}
				else if (input.toUpperCase().equals("QUIT TO PREVIOUS") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("2")) {
					return;
				}
				else {
					System.out.println("Wat\n");
				}
			} while(true);
			
		}
		
		static void LIB2() throws SQLException {
			do {
				LibraryBranchDaoImpl libraryBranchDaoImpl = new LibraryBranchDaoImpl(conn, sc);
				LibraryBranch libraryBranch = libraryBranchDaoImpl.options(sc);
				if (libraryBranch == null) { //if the user quits, libraryBranch is null and program returns
					return;
				}
				LIB3(libraryBranch);
			} while(true);
		}
		
		static void LIB3(LibraryBranch libraryBranch) throws SQLException {
			do {
				System.out.println("1) Update the details of the library \n2) Add copies of a Book to the branch \n3) Quit to previous\n"); 
				String input = sc.nextLine();
				if (input.toUpperCase().equals("UPDATE THE DETAILS OF THE LIBRARY") || input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.toUpperCase().equals("1")) {
					System.out.println("Please enter new branch name or type N/A for no change");
					String branchName = sc.nextLine();
					if (!branchName.toUpperCase().equals("N/A")) {
						libraryBranch.setBranchName(branchName);
					}
					System.out.println("Please enter new branch address or type N/A for no change");
					String branchAddress = sc.nextLine();
					if (!branchAddress.toUpperCase().equals("N/A")) {
						libraryBranch.setBranchAddress(branchAddress);
					}
					libraryBranchDaoImpl.updateLibraryBranch(libraryBranch);
				}
				else if (input.toUpperCase().equals("ADD COPIES OF A BOOK TO THE BRANCH") || input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.toUpperCase().equals("2")) {
					BookCopiesDaoImpl bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
					BookCopies bookCopies = new BookCopies();
					BookDaoImpl bookDaoImpl = new BookDaoImpl(conn, sc);
					int bookId = 0;
					bookCopies.setBranchId(libraryBranch.getBranchId());
					System.out.println("Please select a book to add"); //or delete, since this can actually decrease number of books
					bookId = bookDaoImpl.options(sc);
					if (bookId == 0) {
						return;
					}
					bookCopies.setBookId(bookId);
					System.out.println("Please enter new number of copies");
					int noOfCopies = sc.nextInt();
					sc.nextLine();
					bookCopies.setNoOfCopies(noOfCopies);
					bookCopiesDaoImpl.updateBookCopies(bookCopies);
					//does not update/insert books if there is no listing in the table
				}
				else if (input.toUpperCase().equals("QUIT TO PREVIOUS") || input.toUpperCase().equals("Q") || input.toUpperCase().equals("QUIT") || input.equals("3")) {
					return;
				}
				else {
					System.out.println("Que?");
				}
			} while(true);
			
		}
		
}
