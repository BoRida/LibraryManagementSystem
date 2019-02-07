
public class BookLoan {
	private int bookId;
	private int branchId;
	private int cardNo;
	private String dateOut;
	private String dueDate;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String date) {
		this.dateOut = date;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {
		return "Book_Loans [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + "]";
	}
}
