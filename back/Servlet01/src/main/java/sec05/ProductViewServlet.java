package sec05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/proView")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) request.getAttribute("list");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><table border=1>");
		out.print("<tr align='center' bgcolor='gray'>");
		out.print("<td>번호</td><td>이름</td><td>가격</td><td>회사명</td><td>삭제</td><td>수정</td>");
		out.print("</tr>");
		for(int i=0;i<list.size();i++) {
			ProductVo vo = list.get(i);
			String prdNo = vo.getPrdNo();
			String prdName = vo.getPrdName();
			int prdPrice = vo.getPrdPrice();
			String prdCompany = vo.getPrdCompany();
			out.print("<tr><td>"+prdNo+"</td><td>"+prdName+"</td><td>"+
					prdPrice+"</td><td>"+prdCompany+"</td><td>"+
					"<a href='/Servlet01/memberDelete?id="+prdNo+"'>삭제</a></td><td>"+
					"<a href='/Servlet01/memberUpdate?id="+prdNo+"'>수정</a></td></tr>");
		}
		out.print("</table></body></html>");
	}
}
