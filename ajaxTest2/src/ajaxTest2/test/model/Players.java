package ajaxTest2.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Players {
	int id;
	int playerNumber;
	String playerName;
	String playerPosition;
	String playerTeam;
}
