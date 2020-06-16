package ajaxTest.test.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajaxTest.test.action.Action;
import ajaxTest.test.action.product.CountAction;
import ajaxTest.test.action.product.DeleteAction;
import ajaxTest.test.action.product.PriceAction;
import ajaxTest.test.action.product.ProductAction;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String TAG = "ProductController  : ";

	public ProductController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("home")) {
			// 회원가입 페이지로 이동
			return new ProductAction(); // Board의 목록
		}else if(cmd.equals("price")) {
			return new PriceAction();
		}else if(cmd.equals("count")) {
			return new CountAction();
		}else if(cmd.equals("delete")) {
			return new DeleteAction();
		}
		return null;
	}
}
