package ajaxTest2.test.respository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ajaxTest2.test.db.DBConn;
import ajaxTest2.test.model.Players;
import ajaxTest2.test.model.TeamList;


public class TeamListRepository {
	private static final String TAG = "TeamListRepository : "; // TAG 생성 (오류 발견시 용이)
	private static TeamListRepository instance = new TeamListRepository();

	private TeamListRepository() {}
	
	public static TeamListRepository getInstnce() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//TeamList 가지고오기
	public List<TeamList> findAll() { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, teamName FROM teamList";
		List<TeamList> teamLists = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				TeamList teamList = TeamList.builder()
								.id(rs.getInt(1))
								.teamName(rs.getString(2))
								.build();
				teamLists.add(teamList);
			}
			return teamLists;
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
	}
	
}
