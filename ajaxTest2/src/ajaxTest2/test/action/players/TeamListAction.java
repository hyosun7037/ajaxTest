package ajaxTest2.test.action.players;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ajaxTest2.test.action.Action;
import ajaxTest2.test.model.TeamList;
import ajaxTest2.test.respository.TeamListRepository;

public class TeamListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeamListRepository teamListRepository = TeamListRepository.getInstnce();
		List<TeamList> teamLists = teamListRepository.findAll();
		
		request.setAttribute("teamLists", teamLists);
		
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
		
	}
}
