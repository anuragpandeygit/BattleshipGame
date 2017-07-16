package com.ap.game.data;

/**
 * Specifies type of Ship
 *
 */
public enum ShipType {

	WEAK("P"), STRONG("Q");

	private String value;

	private ShipType(String value) {
		this.value = value;
	}

	public static ShipType getShipType(String type) {
		for (ShipType curr : ShipType.values()) {
			if (curr.value.equalsIgnoreCase(type)) {
				return curr;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}
}
