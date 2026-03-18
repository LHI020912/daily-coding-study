package sec06;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// 입출력 기능
public class ReadWrite {
	
	// 0. 로그인
	
	// 1. 도서 정보 입력(insert / update) 받아 DTO에 저장 후 반환
	public static BookDTO getBookInfo(Scanner sc) {
		BookDTO dto = null;
		IBookDAO idao = null;
		String bookNo = "";
		
		try {
			idao = new BookDAO();
			// 도서 데이터 입력
			System.out.println("도서 정보 등록");
			
			// 중복확인
			while(true) {
				System.out.print("도서 번호 입력 : ");
				bookNo = sc.nextLine();
				
				boolean res = idao.searchBookNo(bookNo);
				if(!res)
					System.out.println("기존에 있는 도서입니다. 종료합니다.");
				else
					break;
			}

			System.out.print("제목 입력 : ");
			String bookName = sc.nextLine();
			
			System.out.print("작가 입력 : ");
			String bookAuthor = sc.nextLine();
			
			System.out.print("가격 입력 : ");
			int bookPrice = sc.nextInt();
			sc.nextLine();
			
			System.out.print("발간일 입력 : ");
			String bookDate = sc.nextLine();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = fm.parse(bookDate); 
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			System.out.print("재고 입력 : ");
			int bookStock = sc.nextInt();
			sc.nextLine();
			
			System.out.print("출판사 번호 입력 : ");
			String pubNo = sc.nextLine();
			
			dto = new BookDTO(bookNo, bookName, bookAuthor,bookPrice, bookDate, bookStock, pubNo);
			
		}catch(Exception e) {
			System.out.println("입력 오류");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 2. 도서 정보 출력
	public static void writeBookInfo(BookDTO dto) {
		// 제목 출력
		System.out.println("----- 도서 정보 조회 -----");
		System.out.format("%-10s\t %-10s\t %-4s %-20s \t%13s %5s \t%13s\n", 
				"도서번호", "제목", "작가", "가격", "발간일", "재고","출판사 번호");
		
			// 정보 추출
			String bookNo = dto.getBookNo();
			String bookName = dto.getBookName();
			String bookAuthor = dto.getBookAuthor();
			int bookPrice = dto.getBookPrice();
			String bookDate = dto.getBookDate();
			int bookStock = dto.getBookStock();
			String pubNo = dto.getPubNo();
		
			// 출력
			System.out.format("%-10s\t %-10s\t %-4s %-20s \t%13s %5s \t%-13s \n", 
					bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
	}
	
	// 3. 도서 정보 수정
	public static BookDTO updateDtdInfo(Scanner sc, String bookNo) {
		BookDTO dto = null;
		
		try {
			System.out.println(bookNo + "의 학생 정보 수정");
			
			System.out.print("제목 입력 : ");
			String bookName = sc.nextLine();
			
			System.out.print("작가 입력 : ");
			String bookAuthor = sc.nextLine();
			
			System.out.print("가격 입력 : ");
			int bookPrice = sc.nextInt();
			sc.nextLine();
			
			System.out.print("발간일 입력 : ");
			String bookDate = sc.nextLine();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = fm.parse(bookDate); 
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			System.out.print("재고 입력 : ");
			int bookStock = sc.nextInt();
			sc.nextLine();
			
			System.out.print("출판사 번호 입력 : ");
			String pubNo = sc.nextLine();
			
			dto = new BookDTO(bookNo, bookName, bookAuthor, bookPrice, bookDate, bookStock, pubNo);
			
		}catch (Exception e) {
			System.out.println("입력 오류");
			e.printStackTrace();
		}
		return dto;
	}
}
