package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductVo;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductDAO productDAO = null;

    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = null;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getPathInfo();
        System.out.println(action);

        if (action == null || action.equals("/listProducts.do")) { // 전체 상품 목록
            List<ProductVo> productList = productDAO.listProducts();
            request.setAttribute("productList", productList);
            nextPage = "/listProducts.jsp";

        } else if (action.equals("/productForm.do")) { // 상품 등록 폼
            nextPage = "/productForm.jsp";

        } else if (action.equals("/addProduct.do")) { // 상품 등록 처리
            String prdNo      = request.getParameter("prdNo");
            String prdName    = request.getParameter("prdName");
            int    prdPrice   = Integer.parseInt(request.getParameter("prdPrice"));
            String prdCompany = request.getParameter("prdCompany");
            ProductVo vo = new ProductVo(prdNo, prdName, prdPrice, prdCompany);
            productDAO.addProduct(vo);
            request.setAttribute("msg", "add");
            nextPage = "/product/listProducts.do";

        } else if (action.equals("/modProductForm.do")) { // 상품 수정 폼
            String prdNo = request.getParameter("prdNo");
            ProductVo prdInfo = productDAO.findProduct(prdNo);
            request.setAttribute("prdInfo", prdInfo);
            nextPage = "/modProductForm.jsp";

        } else if (action.equals("/modProduct.do")) { // 상품 수정 처리
            String prdNo      = request.getParameter("modPrdNo");
            String prdName    = request.getParameter("prdName");
            int    prdPrice   = Integer.parseInt(request.getParameter("prdPrice"));
            String prdCompany = request.getParameter("prdCompany");
            ProductVo vo = new ProductVo(prdNo, prdName, prdPrice, prdCompany);
            productDAO.modProduct(vo);
            request.setAttribute("msg", "mod");
            nextPage = "/product/listProducts.do";

        } else if (action.equals("/delProduct.do")) { // 상품 삭제 처리
            String prdNo = request.getParameter("prdNo");
            productDAO.delProduct(prdNo);
            request.setAttribute("msg", "del");
            nextPage = "/product/listProducts.do";

        } else {
            List<ProductVo> productList = productDAO.listProducts();
            request.setAttribute("productList", productList);
            nextPage = "/listProducts.jsp";
        }

        RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
        dispatch.forward(request, response);
    }
}
