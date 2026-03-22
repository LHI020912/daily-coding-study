package sec01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memjoin")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String id=request.getParameter("memId");
		String pwd=request.getParameter("memPwd");
		String name=request.getParameter("memName");
		String email=request.getParameter("memEmail");
		
		MemberVO vo = new MemberVO();
		vo.setMemId(id);
		vo.setMemPwd(pwd);
		vo.setMemName(name);
		vo.setMemEmail(email);

		MemberDAO dao = new MemberDAO();
		int result = dao.join(vo);
		
		if(result > 0) {
			response.addHeader("Refresh", "3:url=/memlogin");
			
			out.print("<html><body>");
			out.print("<h2>회원가입이 완료되었습니다!</h2>");
			out.print("3초 후 가입 정보 확인(로그인) 페이지로 이동합니다.");
			out.print("</body></html>");
		}else {
			// 실패 시 이전 페이지 이동
			out.print("<script>alert('가입실패!'):history.back();</script>");
		}
	}
}
