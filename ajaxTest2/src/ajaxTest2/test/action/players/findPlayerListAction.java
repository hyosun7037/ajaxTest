package ajaxTest2.test.action.players;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ajaxTest2.test.action.Action;
import ajaxTest2.test.model.Players;
import ajaxTest2.test.respository.PlayersRepository;

public class findPlayerListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int id = Integer.parseInt(request.getParameter("id"));
		String playerTeam = request.getParameter("playerTeam");
		
		PlayersRepository playersRepository = PlayersRepository.getInstnce();
		List<Players> playersList = playersRepository.findById(playerTeam);
		
		Gson gson = new Gson();
		String playersJson = gson.toJson(playersList);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); //bufferedWriter랑 똑같은데 autoFlush(), println() 추가
		out.println(playersJson);
		

		
	}
}
