package sec01;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memlogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("memId");
		String pwd = request.getParameter("memPwd");
		
		MemberDAO dao = new MemberDAO();
		
		if(dao.login(id,pwd) != null) {
			// 관리자
			if(id.equals("admin")) {
				ArrayList<MemberVO> memberList = dao.listMembers();
				request.setAttribute("membersList", memberList);
				
				RequestDispatcher dis = request.getRequestDispatcher("memberList.jsp");
				dis.forward(request, response);
			}
			// 일반 사용자
			else {	
				response.sendRedirect("main.html");
			}
		}else {
			response.sendRedirect("login.html");
		}
	}
}
