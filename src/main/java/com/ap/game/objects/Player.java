package com.ap.game.objects;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.ap.game.data.ShipType;

/**
 * Player class is responsible for many operations and hold data required to play the
 * game. Player knows about its battle area, has information about it missile or attempts
 * where to fire on opponent. It also tells the result of fireOperation, whether fire was
 * successful or failed. Accordingly updates its battle area.
 * 
 *
 */
public class Player {

	private String id;
	char[][] myBattleArea = null;
	private Queue<String> attempts = new LinkedList<>();

	public Player(String id) {
		super();
		this.id = id;
	}

	public void assignBattleArea(char[][] battleArea) {
		// Cloning to its array

		if (battleArea == null || myBattleArea != null) {
			return;
		}

		this.myBattleArea = new char[battleArea.length][];
		for (int i = 0; i < battleArea.length; i++) {
			myBattleArea[i] = Arrays.copyOf(battleArea[i], battleArea[i].length);
		}
	}

	public void setAttempts(String attemptList) {
		if (attemptList == null || attemptList.isEmpty()) {
			return;
		}
		String[] inputAttempts = attemptList.split("\\s+");
		for (String attempt : inputAttempts) {
			attempts.offer(attempt);
		}
	}

	public String getId() {
		return id;
	}

	public char[][] getMyBattleArea() {
		return myBattleArea;
	}

	public Queue<String> getAttempts() {
		return attempts;
	}

	@Override
	public String toString() {

		return id;
	}

	/**
	 * This function works by superimposition of launch target on my battleArea. If
	 * missile is a hit it does
	 * 
	 * ****************- If hit on P type ship --- clear that pixel value
	 * ********************* If hit on Q type ship --- convert Q to P type as to allow one
	 * more chance on it
	 * 
	 * **********************In case of miss , simply return false
	 * **********************In case of target it out of battleArea simple call it a miss.
	 * 
	 * @param position where to fire at me.
	 * @return if attempt was successful or not
	 */
	public boolean fireAt(String position) {
		boolean status = false;
		int coorX = (((int) position.charAt(0)) - 64) - 1;
		int coorY = Integer.parseInt(position.substring(1)) - 1;

		// search for value in my battleArea
		char whatIsHere = ' ';
		try {
			whatIsHere = myBattleArea[coorX][coorY];
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Missile fired out of play area.." + position);
			return false;
		}
		ShipType ship = ShipType.getShipType(String.valueOf(whatIsHere));

		if (ship == null) {
			status = false;
		}
		if (ship == ShipType.STRONG) {
			myBattleArea[coorX][coorY] = 'P';
			status = true;

		}
		else if (ship == ShipType.WEAK) {
			myBattleArea[coorX][coorY] = ' ';
			status = true;
		}

		return status;
	}

	/**
	 * This method explores possibility of any ship available in my battle area.
	 * Optimization are possible here to avoid check every time. A ship array can be
	 * maintained and check on that. For on i just lived with it.
	 * 
	 * @return if any ships has survived on my battle area
	 */
	public boolean anyShipSurvived() {
		boolean shipAlive = false;

		for (int row = 0; row < myBattleArea.length; row++) {

			char[] columnVals = myBattleArea[row];
			for (int col = 0; col < columnVals.length; col++) {
				char currValue = myBattleArea[row][col];
				if (currValue == 'P' || currValue == 'Q') {
					// System.out.println("Ship is ");
					shipAlive = true;
					break;
				}
			}
			if (shipAlive)
				break;
		}
		return shipAlive;

	}

}
