import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Borrower {
	private int cardNo;
	private String name;
	private String address;
	private int phone;
	static Connection conn;
	static Scanner sc;
	static AuthorDaoImpl authorDaoImpl;
	static BookDaoImpl bookDaoImpl;
	static BookCopiesDaoImpl bookCopiesDaoImpl;
	static BookLoanDaoImpl bookLoanDaoImpl;
	static BorrowerDaoImpl borrowerDaoImpl;
	static LibraryBranchDaoImpl libraryBranchDaoImpl;
	static PublisherDaoImpl publisherDaoImpl;
	
	public Borrower(Scanner sc) throws SQLException {
		Borrower.sc = sc;
		Borrower.conn = Utilities.getConnection();
		Borrower.authorDaoImpl = new AuthorDaoImpl(conn, sc);
		Borrower.bookDaoImpl = new BookDaoImpl(conn, sc);
		Borrower.bookCopiesDaoImpl = new BookCopiesDaoImpl(conn, sc);
		Borrower.bookLoanDaoImpl = new BookLoanDaoImpl(conn, sc);
		Borrower.borrowerDaoImpl = new BorrowerDaoImpl(conn, sc);
		Borrower.libraryBranchDaoImpl = new LibraryBranchDaoImpl(conn, sc);
		Borrower.publisherDaoImpl = new PublisherDaoImpl(conn, sc);
	}
	public Borrower() {
		//this constructor is called when changing the Borrower table
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Borrower [cardNo=" + cardNo + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
	
	public void BORR1() throws SQLException {
		do {
			System.out.println("Enter your Card Number: \n");
			int cardNo = sc.nextInt();
			if (!borrowerDaoImpl.verifyBorrower(cardNo)) {
				return;
			} //verifies if cardNo exists in the database
			sc.nextLine();
			System.out.println("1) Check out a book \n2) Return a book \n3) Quit to previous\n");
			String input = sc.nextLine();
			BookLoan bookLoan = new BookLoan();
			bookLoan.setCardNo(cardNo);
			if (input.toUpperCase().equals("CHECK OUT A BOOK") || input.toUpperCase().equals("CHECK") || input.toUpperCase().equals("C") || input.equals("1")) {
				bookDaoImpl.showAvailableBooks();
				bookLoan.setBookId(sc.nextInt());
				sc.nextLine();
				System.out.println("Please select branchId");
				bookLoan.setBranchId(sc.nextInt());
				sc.nextLine();
				LocalDate dateOut = LocalDate.now();
				LocalDate dueDate = dateOut.plus(1, ChronoUnit.WEEKS);
				bookLoan.setDateOut(java.sql.Date.valueOf(dateOut).toString());
				bookLoan.setDueDate(java.sql.Date.valueOf(dueDate).toString());
				bookLoanDaoImpl.addBookLoan(bookLoan);
				return;
			}
			else if (input.toUpperCase().equals("RETURN A BOOK") || input.toUpperCase().equals("RETURN") || input.toUpperCase().equals("R") || input.equals("2") ) {
				bookDaoImpl.showBorrowedBooks(cardNo);
				bookLoan.setBookId(sc.nextInt());
				sc.nextLine();
				System.out.println("Please select branchId");
				bookLoan.setBranchId(sc.nextInt());
				sc.nextLine();
				bookLoanDaoImpl.deleteBookLoan(bookLoan);
				return;
			}
			else if (input.toUpperCase().equals("QUIT TO PREVIOUS") || input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.equals("3")) {
				return;
			}
			else {
				System.out.println("What in tarnation\n");
			}
			
		} while(true);
	}
	
	
}
