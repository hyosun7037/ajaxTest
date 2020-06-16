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

public class DeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		ProductRepository productRepository = ProductRepository.getInstnce();
		int result = productRepository.delete(id);

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); // bufferedWriter랑 똑같은데 autoFlush(), println() 추가
		out.println(result +""); //문자열로 변경

	}
}
