package ajaxTest.test.action.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajaxTest.test.action.Action;
import ajaxTest.test.model.Product;
import ajaxTest.test.repository.ProductRepository;

public class ProductAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductRepository productRepository = ProductRepository.getInstnce();
		List<Product> products = productRepository.findAll();
		
		request.setAttribute("products", products);
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		


	}
}
