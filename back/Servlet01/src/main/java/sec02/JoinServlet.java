package sec02;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JoinServlet")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}

	public void destroy() {
		System.out.println("destroy");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user_name = request.getParameter("user_name");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		String first_num = request.getParameter("first_num");
		String midle_num = request.getParameter("midle_num");
		String last_num = request.getParameter("last_num");
		
		String grade = request.getParameter("grade");
		String[] subject = request.getParameterValues("subject");
		String stack = request.getParameter("stack");

		System.out.println("성명: "+ user_name);
		System.out.println("ID: "+ user_id);
		System.out.println("비밀번호: "+ user_pw);
		System.out.println("휴대폰 번호: "+first_num+"-"+midle_num+"-"+last_num);
		System.out.println("학년: "+ grade);
		
		System.out.print("선택한 관심분야: ");
		for(String s:subject) {
			System.out.print(s+" ");
		}
		
		System.out.println("학과 "+stack);
		System.out.println();
	}
}