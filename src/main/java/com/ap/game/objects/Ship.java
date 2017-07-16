package com.ap.game.objects;

import com.ap.game.data.ShipType;

/**
 * Ship is identified with its height and width and type ( Weak , Strong) .A particular
 * position from where it is located. It also knows about who is its owner.
 * 
 * Ship is not functional until it is commissioned. So actor need to call commission while
 * in init phase.
 * 
 *
 */
public class Ship {

	private Player owner = null;
	private int width = 0;
	private int height = 0;
	private String position;
	private ShipType type;

	public Ship(Player owner, int width, int height, String position, ShipType type) {
		this.owner = owner;
		this.width = width;
		this.height = height;
		this.position = position;
		this.type = type;
	}

	public void commission() {
		char[][] battleArea = owner.getMyBattleArea();

		int coorX = (((int) position.charAt(0)) - 64) - 1;
		int coorY = Integer.parseInt(position.substring(1)) - 1;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				battleArea[coorX + j][coorY + i] = type.getValue().charAt(0);

			}

		}
	}
}
