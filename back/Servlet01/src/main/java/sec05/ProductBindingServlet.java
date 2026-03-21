package sec05;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/proBinding")
public class ProductBindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductVo vo1 = new ProductVo("1","양정원",10000,"엔하이픈");
		ProductVo vo2 = new ProductVo("2","박종성",20000,"빌리프랩");
		ProductVo vo3 = new ProductVo("3","심재윤",30000,"엔진");
		
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		
		request.setAttribute("list", list);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("proView");
		dispatch.forward(request, response);
	}

}
