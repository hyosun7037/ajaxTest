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


public class PlayersRepository {
	private static final String TAG = "PlayersRepository : "; // TAG 생성 (오류 발견시 용이)
	private static PlayersRepository instance = new PlayersRepository();

	private PlayersRepository() {}
	
	public static PlayersRepository getInstnce() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//TeamList 가지고오기
	public List<Players> findById(String playerTeam) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, playerName FROM players WHERE playerTeam = ?";
		List<Players> playersList = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, playerTeam);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Players player = Players.builder()
								.id(rs.getInt(1))
								.playerName(rs.getString(2))
								.build();
				playersList.add(player);
			}	
			return playersList;
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
		
	}
	
	public List<Players> findByPlayerInfo(String playerName, String playerTeam) { // object 받기(안에 내용 다 받아야 하니까)
		final String SQL = "SELECT id, playerName, playerPosition FROM players WHERE playerName = ? and playerTeam = ? ";
		List<Players> playersList = new ArrayList<>();
		try {
			conn = DBConn.getConnection(); // DB에 연결
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, playerName);
			pstmt.setString(2, playerTeam);
			// while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Players player = Players.builder()
								.id(rs.getInt(1))
								.playerName(rs.getString(2))
								.playerPosition(rs.getString(3))
								.build();
				playersList.add(player);
			}	
			return playersList;
		} catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println(TAG + "findByPlayerInfo : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		return null; // 실패시
		
	}
	
	
}
