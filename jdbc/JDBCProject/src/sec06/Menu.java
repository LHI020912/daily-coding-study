package sec06;

import java.util.Scanner;

public class Menu {
	private static IBookDAO idao = new BookDAO();
	private static BookDAO dao = new BookDAO();
	private static Scanner sc = new Scanner(System.in);
	
	public static void Info() {
		idao.insertBook(ReadWrite.getBookInfo(sc));
		BookDTO dto = null;
		
		
	}
}
