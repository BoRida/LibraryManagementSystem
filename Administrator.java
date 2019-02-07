import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Administrator {
	static Connection conn;
	static Scanner sc;
	static AuthorDaoImpl authorDaoImpl;
	static BookDaoImpl bookDaoImpl;
	static BookCopiesDaoImpl bookCopiesDaoImpl;
	static BookLoanDaoImpl bookLoanDaoImpl;
	static BorrowerDaoImpl borrowerDaoImpl;
	static LibraryBranchDaoImpl libraryBranchDaoImpl;
	static PublisherDaoImpl publisherDaoImpl;
	
	public Administrator(Scanner sc) throws SQLException {
		Administrator.sc = sc;
		Administrator.conn = Utilities.getConnection();
		Administrator.authorDaoImpl = new AuthorDaoImpl(conn, sc);
		Administrator.bookDaoImpl = new BookDaoImpl(conn, sc);
		Administrator.bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		Administrator.bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
		Administrator.borrowerDaoImpl = new BorrowerDaoImpl(conn, sc);
		Administrator.libraryBranchDaoImpl = new LibraryBranchDaoImpl(conn, sc);
		Administrator.publisherDaoImpl = new PublisherDaoImpl(conn, sc);
	}
	
	public void ADMIN() throws SQLException { //could also make an ADMIN2 method that you pass an object to, or new parent class that all of the table classes can extend from. or just make this function very long
		do {
			System.out.println("Sup Admin. What you want? \n\t1) Add/Update/Delete Author \n\t2) Add/Update/Delete Book \n\t3) Add/Update/Delete Publishers \n\t4) Add/Update/Delete Library Branches \n\t5) Add/Update/Delete Borrowers \n\t6) Over-ride Due Date for a Book Loan\n\t7) Quit to cancel operation\n");
			String input = sc.nextLine();
			if (input.toUpperCase().equals("AUTHOR") ||  input.equals("1")) {
				AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl(conn, sc);
				Author author = new Author();
				do {
					System.out.println("What would you like to do? \n\t1) Add Author \n\t2) Update Author \n\t3) Delete Author \n\t4) Quit to previous/n");
					input = sc.nextLine();
					if (input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.equals("1")) {
						System.out.println("Please enter new authorId");
						author.setAuthorId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new author name");
						author.setAuthorName(sc.nextLine());
						authorDaoImpl.addAuthor(author);
						return;
					}
					else if (input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.equals("2")) {
						System.out.println("Please enter authorId");
						author.setAuthorId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new author name");
						author.setAuthorName(sc.nextLine());
						authorDaoImpl.updateAuthor(author);
						return;
					}
					else if (input.toUpperCase().equals("DELETE") || input.toUpperCase().equals("D") || input.equals("3")) {
						System.out.println("Please enter authorId");
						author.setAuthorId(sc.nextInt());
						sc.nextLine();
						authorDaoImpl.deleteAuthor(author);
						return;
					}
					else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("4")) {
						return;
					}
					else {
						System.out.println("wut");
					}
				} while(true);
				
			}
			else if (input.toUpperCase().equals("BOOK") || input.equals("2")) {
				BookDaoImpl bookDaoImpl = new BookDaoImpl(conn, sc);
				Book book = new Book();
				do {
					System.out.println("What would you like to do? \n\t1) Add Book \n\t2) Update Book \n\t3) Delete Book \n\t4) Quit to previous/n");
					input = sc.nextLine();
					if (input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.equals("1")) {
						System.out.println("Please enter new bookId");
						book.setBookId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new title");
						book.setTitle(sc.nextLine());
						System.out.println("Please enter new authId");
						book.setAuthId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new pubId");
						book.setPubId(sc.nextInt());
						sc.nextLine();
						bookDaoImpl.addBook(book);
						return;
					}
					else if (input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.equals("2")) {
						System.out.println("Please enter bookId");
						book.setBookId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new title");
						book.setTitle(sc.nextLine());
						System.out.println("Please enter new authId");
						book.setAuthId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new pubId");
						book.setPubId(sc.nextInt());
						sc.nextLine();
						bookDaoImpl.updateBook(book);
						return;
					}
					else if (input.toUpperCase().equals("DELETE") || input.toUpperCase().equals("D") || input.equals("3")) {
						System.out.println("Please enter bookId");
						book.setBookId(sc.nextInt());
						sc.nextLine();
						bookDaoImpl.deleteBook(book);
						return;
					}
					else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("4")) {
						return;
					}
					else {
						System.out.println("wut");
					}
				} while(true);
			}
			else if (input.toUpperCase().equals("PUBLISHERS") || input.equals("3")) {
				PublisherDaoImpl publisherDaoImpl = new PublisherDaoImpl(conn, sc);
				Publisher publisher = new Publisher();
				do {
					System.out.println("What would you like to do? \n\t1) Add Publisher \n\t2) Update Publisher \n\t3) Delete Publisher \n\t4) Quit to previous/n");
					input = sc.nextLine();
					if (input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.equals("1")) {
						System.out.println("Please enter new publisherId");
						publisher.setPublisherId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new publisher name");
						publisher.setPublisherName(sc.nextLine());
						System.out.println("Please enter new publisher address");
						publisher.setPublisherAddress(sc.nextLine());
						System.out.println("Please enter new publisher phone number");
						publisher.setPublisherPhone(sc.nextInt());
						sc.nextLine();
						publisherDaoImpl.addPublisher(publisher);
						return;
					}
					else if (input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.equals("2")) {
						System.out.println("Please enter publisherId");
						publisher.setPublisherId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new publisher name");
						publisher.setPublisherName(sc.nextLine());
						System.out.println("Please enter new publisher address");
						publisher.setPublisherAddress(sc.nextLine());
						System.out.println("Please enter new publisher phone number");
						publisher.setPublisherPhone(sc.nextInt());
						sc.nextLine();
						publisherDaoImpl.updatePublisher(publisher);
						return;
					}
					else if (input.toUpperCase().equals("DELETE") || input.toUpperCase().equals("D") || input.equals("3")) {
						System.out.println("Please enter publisherId");
						publisher.setPublisherId(sc.nextInt());
						sc.nextLine();
						publisherDaoImpl.deletePublisher(publisher);
						return;
					}
					else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("4")) {
						return;
					}
					else {
						System.out.println("wut");
					}
				} while(true);
			}
			else if (input.toUpperCase().equals("LIBRARY BRANCHES") || input.equals("4")) {
				LibraryBranchDaoImpl libraryBranchDaoImpl = new LibraryBranchDaoImpl(conn, sc);
				LibraryBranch libraryBranch = new LibraryBranch();
				do {
					System.out.println("What would you like to do? \n\t1) Add Library Branch \n\t2) Update Library Branch \n\t3) Delete Library Branch \n\t4) Quit to previous/n");
					input = sc.nextLine();
					if (input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.equals("1")) {
						System.out.println("Please enter new library branch ID");
						libraryBranch.setBranchId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new branch name");
						libraryBranch.setBranchName(sc.nextLine());
						System.out.println("Please enter new branch address");
						libraryBranch.setBranchAddress(sc.nextLine());
						libraryBranchDaoImpl.addLibraryBranch(libraryBranch);
						return;
					}
					else if (input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.equals("2")) {
						System.out.println("Please enter library branch ID");
						libraryBranch.setBranchId(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new branch name");
						libraryBranch.setBranchName(sc.nextLine());
						System.out.println("Please enter new branch address");
						libraryBranch.setBranchAddress(sc.nextLine());
						libraryBranchDaoImpl.updateLibraryBranch(libraryBranch);
						return;
					}
					else if (input.toUpperCase().equals("DELETE") || input.toUpperCase().equals("D") || input.equals("3")) {
						System.out.println("Please enter library branch ID");
						libraryBranch.setBranchId(sc.nextInt());
						sc.nextLine();
						libraryBranchDaoImpl.deleteLibraryBranch(libraryBranch);
						return;
					}
					else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("4")) {
						return;
					}
					else {
						System.out.println("wut");
					}
				} while(true);
			}
			else if (input.toUpperCase().equals("BORROWERS") || input.equals("5")) {
				BorrowerDaoImpl borrowerDaoImpl = new BorrowerDaoImpl(conn, sc);
				Borrower borrower = new Borrower();
				do {
					System.out.println("What would you like to do? \n\t1) Add Borrower \n\t2) Update Borrower \n\t3) Delete Borrower \n\t4) Quit to previous/n");
					input = sc.nextLine();
					if (input.toUpperCase().equals("ADD") || input.toUpperCase().equals("A") || input.equals("1")) {
						System.out.println("Please enter new card number");
						borrower.setCardNo(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new name");
						borrower.setName(sc.nextLine());
						System.out.println("Please enter new address");
						borrower.setAddress(sc.nextLine());
						System.out.println("Please enter new phone number");
						borrower.setPhone(sc.nextInt());
						sc.nextLine();
						borrowerDaoImpl.addBorrower(borrower);
						return;
					}
					else if (input.toUpperCase().equals("UPDATE") || input.toUpperCase().equals("U") || input.equals("2")) {
						System.out.println("Please enter card number");
						borrower.setCardNo(sc.nextInt());
						sc.nextLine();
						System.out.println("Please enter new name");
						borrower.setName(sc.nextLine());
						System.out.println("Please enter new address");
						borrower.setAddress(sc.nextLine());
						System.out.println("Please enter new phone number");
						borrower.setPhone(sc.nextInt());
						sc.nextLine();
						borrowerDaoImpl.updateBorrower(borrower);
						return;
					}
					else if (input.toUpperCase().equals("DELETE") || input.toUpperCase().equals("D") || input.equals("3")) {
						System.out.println("Please enter card number");
						borrower.setCardNo(sc.nextInt());
						sc.nextLine();
						borrowerDaoImpl.deleteBorrower(borrower);
						return;
					}
					else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("4")) {
						return;
					}
					else {
						System.out.println("wut");
					}
				} while(true);
			}
			else if (input.toUpperCase().equals("DUE DATE") || input.toUpperCase().equals("OVERRIDE") || input.equals("6")) {
				BookLoanDaoImpl bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
				BookLoan bookLoan = new BookLoan();
				do {
					System.out.println("Please enter bookId");
					int bookId = sc.nextInt();
					sc.nextLine();
					System.out.println("Please enter branchId");
					int branchId = sc.nextInt();
					sc.nextLine();
					System.out.println("Please enter cardNo");
					int cardNo = sc.nextInt();
					sc.nextLine();
					System.out.println("Please enter new Due Date in the format yyyy-mm-dd");
					String dueDate = sc.nextLine();
					bookLoan = bookLoanDaoImpl.getBookLoan(bookId, branchId, cardNo);
					bookLoan.setDueDate(dueDate);
					bookLoanDaoImpl.updateBookLoan(bookLoan);
					return;
				} while(true);
			}
			else if (input.toUpperCase().equals("QUIT TO CANCEL OPERATION") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("7")) {
				return;
			}
			else {
				System.out.println("Wat\n");
			}
		} while(true);
	}
}
