package ajaxTest2.test.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajaxTest2.test.action.Action;
import ajaxTest2.test.action.players.TeamListAction;
import ajaxTest2.test.action.players.findPlayerAction;
import ajaxTest2.test.action.players.findPlayerListAction;



@WebServlet("/baseBall")
public class BaseBallController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String TAG = "baseballController  : ";

	public BaseBallController() {
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
			return new TeamListAction(); // Board의 목록
		}else if(cmd.equals("findPlayerList")) {
			// 회원가입 페이지로 이동
			return new findPlayerListAction(); // Board의 목록
		}else if(cmd.equals("findPlayer")) {
			// 회원가입 페이지로 이동
			return new findPlayerAction(); // Board의 목록
		}
		return null;
	}
}
