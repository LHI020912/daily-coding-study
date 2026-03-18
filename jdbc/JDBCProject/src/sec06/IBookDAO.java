package sec06;

public interface IBookDAO {
	// book crud
	// 도서 등록
	public void insertBook(BookDTO dto);
	
	// 도서 정보 조회
	public BookDTO detailBook (String bookNo);
	
	// 도서 정보 수정
	public void updateBook(BookDTO dto);
	
	// 도서 정보 삭제
	public void deleteBook(String bookNo);
	
	// 도서 번호(id) 존재여부 반환
	public boolean searchBookNo(String bookNo);

}