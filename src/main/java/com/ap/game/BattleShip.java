package com.ap.game;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import com.ap.game.data.ShipType;
import com.ap.game.objects.Player;
import com.ap.game.objects.PlayerCache;
import com.ap.game.objects.Ship;
import com.ap.game.service.GameController;

public class BattleShip {

	static char[][] battleArea = null;

	// initialize to two, since only two players
	private static String[] missileAttemps = new String[2];
	static int shipCount = 0;
	static PlayerCache playerCache;

	public static void main(String[] args) {

		printBanner();
		readInputParams();
		printBattleArea();

		Thread controller = new Thread(new GameController(playerCache));
		controller.start();
	}

	static boolean readInputParams() throws IllegalArgumentException {
		try (Scanner sc = new Scanner(System.in)) {

			String battleFieldArea = sc.nextLine();

			parse(InputType.BOARD, battleFieldArea);

			shipCount = sc.nextInt();
			sc.nextLine(); // dummyread

			for (int i = 0; i < shipCount; i++) {
				String shipInput = sc.nextLine();
				parse(InputType.SHIP, shipInput);
			}
			missileAttemps[0] = sc.nextLine();
			parse(InputType.MISSILE, missileAttemps[0], PlayerCache.PLAYER1);
			missileAttemps[1] = sc.nextLine();
			parse(InputType.MISSILE, missileAttemps[1], PlayerCache.PLAYER2);
			return true;
		}
		catch (IllegalArgumentException ex) {
			throw ex;
		}

	}

	public static void parse(InputType type, String... argument)
			throws IllegalArgumentException {
		switch (type) {
		case BOARD:
			String[] heightWidth = argument[0].split("\\s+");
			if (!heightWidth[0].matches("[1-9]")) {
				throw new IllegalArgumentException(
						"Please provide range of Width between 1 - 9");
			}
			int widht = Integer.parseInt(heightWidth[0]);

			if (!heightWidth[1].matches("[A-Z]")) {
				throw new IllegalArgumentException(
						"Please provide range of Height between A-Z");
			}
			int height = ((int) heightWidth[1].charAt(0)) - 64;
			battleArea = new char[height][widht];
			break;
		case SHIP:
			// Q 1 1 A1 B2
			// 0 1 2 3 4
			// Type , width , height , Location(P1), Location(P2)
			String[] shipArgs = argument[0].split("\\s+");

			// INIT FOR FIRST PLAYER
			Player player1 = PlayerCache.getPlayer(PlayerCache.PLAYER1);
			Ship ship = new Ship(player1, Integer.parseInt(shipArgs[1]),
					Integer.parseInt(shipArgs[2]), shipArgs[3],
					ShipType.getShipType(shipArgs[0]));
			player1.assignBattleArea(battleArea.clone());
			ship.commission();

			// INIT FOR SECOND PLAYER
			Player player2 = PlayerCache.getPlayer(PlayerCache.PLAYER2);
			Ship ship2 = new Ship(player2, Integer.parseInt(shipArgs[1]),
					Integer.parseInt(shipArgs[2]), shipArgs[4],
					ShipType.getShipType(shipArgs[0]));
			player2.assignBattleArea(battleArea.clone());
			ship2.commission();

			break;
		case MISSILE:
			// A1 B2 B3 A1 D1 E1 D4 D4 D5 D5
			// Height-Width of position
			// Attempts Queue for Players
			Player player = PlayerCache.getPlayer(argument[1]);
			player.setAttempts(argument[0]);
			break;
		}
	}

	static void printBanner() {
		System.out.println("                       #  		");
		System.out.println("                      ## 		");
		System.out.println("                     #### 		");
		System.out.println("                    ####### 		");
		System.out.println("                  ###########");
		System.out.println(
				"#########################################################################");
		System.out.println(
				"   ##########################     BATTLESHIP GAME  ####################");
		System.out.println(
				"    #################################################################");
		System.out.println(
				"        #########################################################");
		System.out.println(
				"            #############################################              ");
		System.out.println("	           ########################### ");

	}

	static void printBattleArea() {
		System.out.println("############ BATTLE AREA IS PLOTTED BELOW   ######");
		for (Player player : PlayerCache.getPlayers()) {

			Stream.of(player.getMyBattleArea()).map(Arrays::toString)
					.forEach(System.out::println);
			System.out.println("###############################################");

		}
	}

}

enum InputType {
	BOARD, SHIP, MISSILE;
}
