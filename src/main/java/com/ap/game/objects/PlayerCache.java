package com.ap.game.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintains a list of players. This could be thought of as singleton.
 *
 */
public class PlayerCache {
	public static final String PLAYER1 = "PLAYER_1";
	public static final String PLAYER2 = "PLAYER_2";

	public static Map<String, Player> playerList = new HashMap<String, Player>();

	public static Player getPlayer(String playerId) {

		if (playerList.containsKey(playerId))
			return playerList.get(playerId);

		// Create if not present

		Player player = new Player(playerId);
		playerList.put(playerId, player);
		return player;

	}

	public static List<Player> getPlayers() {
		List<Player> players = new ArrayList<Player>(playerList.size());
		playerList.forEach((k, v) -> players.add(v));
		return players;
	}
}
