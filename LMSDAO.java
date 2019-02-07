import java.sql.SQLException;
import java.util.Scanner;

public class LMSDAO {
	
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		String input;
		Librarian l = new Librarian(sc);
		Administrator a = new Administrator(sc);
		Borrower b = new Borrower(sc);
		do {
			System.out.println("Welcome to the GCIT Library Management System. What category of a user are you \n\n \t1) Librarian \n\t2) Administrator \n\t3) Borrower\n\t");
			input = sc.nextLine();
			if (input.toUpperCase().equals("LIBRARIAN") || (input.toUpperCase().equals("L")) || (input.equals("1"))) {
				l.LIB1();
			}
			else if (input.toUpperCase().equals("ADMINISTRATOR") || (input.toUpperCase().equals("A")) || (input.equals("2"))) {
				a.ADMIN();
			}
			else if (input.toUpperCase().equals("BORROWER") || (input.toUpperCase().equals("B")) || (input.equals("3"))) {
				b.BORR1();
			}
			else if (input.toUpperCase().equals("QUIT") || input.toUpperCase().equals("Q") || input.toUpperCase().equals("EXIT") || input.toUpperCase().equals("E")) {
				break;
			}
			
			else {
				System.out.println("Sorry, maybe next time\n");
			}
		} while(true);
		System.out.println("Goodbye.");
		sc.close();
	}
}
