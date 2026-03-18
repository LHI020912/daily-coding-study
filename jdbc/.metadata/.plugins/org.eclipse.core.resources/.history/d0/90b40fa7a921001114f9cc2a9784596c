package encryption.sec02;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;


public class MemberLoginMD5 {
	// 로그인 할 때 사용할 해시 생성메소드는 join할 때 사용했던 메소드와 모든 연산이 동일해야 한다.
	public static String md5(String pass) {
		String encData = "";

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] bytes = pass.getBytes();
			
			md.update(bytes); // 해시로 변환할 데이터를 digest에 전달하면 update메소드는 해시구조에 맞게 구조 변환
			
			byte[] digest = md.digest(); // update된 데이터를 byte타입의 해시값으로 변경해서 반환
			System.out.println("byte 타입 해시값: "+digest);
			
			// byte 타입의 해시값을 문자열로 변환
			for(int i=0;i<digest.length;i++) {
				encData += Integer.toHexString(digest[i] & 0xff);
			}
			System.out.println(encData);
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encData;
	}

	public static void main(String[] args) {
		// DB에 저장된 비밀번호가 plain text기 때문에 사용자 입력값과 db에서 조회한 값을 바로 비교
		// sql 쿼리를 문자열을 결합해서 동적으로 생성하고 있음 : sql 삽입공격에 노출됨		
		// 취약한 프로그램 생성
		DBConnect dbCon = new DBConnect();		
		Connection con = dbCon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //select 구문
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("ID 입력 : ");
			String memID = sc.nextLine();
			System.out.print("비밀번호 입력 : ");
			String memPass = sc.nextLine();
			
			//select 쿼리문 작성
			String sql = "select * from member where memID = '" + memID + "'" +  "and memPWD = '" + memPass + "'";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//회원가입 진행 시 중복 아이디 허용하지 않았다고 가정
			//위 쿼리가 진행되었다면 한명에 대한 정보가 나오거나 정보가 추출되지 않았을 것임
			if(rs.next()) {
				System.out.println("로그인 되었습니다");	
				//웹에서는 로그인되었다면 자격증명을 포함해서 클라이언트에게 전달
			}else {
				System.out.println("로그인 실패");
			}
						
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.close(con, pstmt, rs);
			sc.close();
		}
			
	}

}