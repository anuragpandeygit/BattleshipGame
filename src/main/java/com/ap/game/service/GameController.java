package com.ap.game.service;

import java.util.List;
import java.util.Queue;

import com.ap.game.objects.Player;
import com.ap.game.objects.PlayerCache;

/**
 * Game controller actually initiates the game after param initialization. It runs until
 * both players exhaust their attempts or any event such as all ships of a player has sunk
 * 
 *
 */
public class GameController implements Runnable {

	PlayerCache playerCache = null;

	// turn Even for Player1 , Odd for Player2
	int turn = 0;

	public GameController() {

	}

	public GameController(PlayerCache playerCache) {
		this.playerCache = playerCache;
	}

	@Override
	public void run() {
		List<Player> players = PlayerCache.getPlayers();

		if (players.size() < 2) {
			System.err.println("There was error in initialization step.");
		}

		Player playerA = players.get(0);
		Player playerB = players.get(1);
		Queue<String> playerAChances = playerA.getAttempts();
		Queue<String> playerBChances = playerB.getAttempts();

		String target = null;

		while (playerAChances.isEmpty() == false || playerBChances.isEmpty() == false) {
			if (turn % 2 == 0) {
				target = playerAChances.poll();
				if (fireMissile(playerA, target, playerB))
					continue;
				turn++;
			}
			else {
				target = playerBChances.poll();
				if (fireMissile(playerB, target, playerA))
					continue;
				turn++;
			}
		}
		if (!playerA.anyShipSurvived() && playerB.anyShipSurvived()) {
			System.out.println(playerB + " has won the battle.");
		}
		else if (!playerB.anyShipSurvived() && playerA.anyShipSurvived()) {
			System.out.println(playerA + " has won the battle.");
		}
		else {
			System.out.println("*****Game over , PEACE Declare ***************");
		}
	}

	private boolean fireMissile(Player sourcePlayer, String target, Player targetPlayer) {

		boolean nextChange = false;

		if (target == null || target.isEmpty()) {
			System.out.println(sourcePlayer + " has no more missiles left to launch");
		}
		else {
			if (targetPlayer.anyShipSurvived() == false) {
				System.out.println(sourcePlayer + " has won the battle.");
				System.exit(1);
			}
			boolean isHit = targetPlayer.fireAt(target);

			if (isHit) {
				System.out.println(sourcePlayer + " fires a missile with target " + target
						+ " which got hit");
				nextChange = true;
			}
			else {
				System.out.println(sourcePlayer + " fires a missile with target " + target
						+ " which got miss");
			}

		}
		return nextChange;
	}

}
