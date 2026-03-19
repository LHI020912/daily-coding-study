package sec01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// servlet path 매핑 이름은 다른 uri의 servlet path이름과 중복되지 않도록 주의
// context 내에 동일한 이름의 servlet path가 있으면 오류
@WebServlet("/AnnotServlet")
public class AnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("AnotationServlet init 메서드 호출");
	}

	public void destroy() {
		System.out.println("AnotationServlet destroy 메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("AnotationServlet doGet 메서드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AnotationServlet doPost 메서드 호출");
		doGet(request, response);
	}

}
