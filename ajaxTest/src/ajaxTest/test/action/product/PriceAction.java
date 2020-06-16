package ajaxTest.test.action.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ajaxTest.test.action.Action;
import ajaxTest.test.model.Product;
import ajaxTest.test.repository.ProductRepository;

public class PriceAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductRepository productRepository = ProductRepository.getInstnce();
		List<Product> products = productRepository.priceDESC();
		
		Gson gson = new Gson();
		String productJson = gson.toJson(products);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); //bufferedWriter랑 똑같은데 autoFlush(), println() 추가
		out.println(productJson);
		
	}
}
 