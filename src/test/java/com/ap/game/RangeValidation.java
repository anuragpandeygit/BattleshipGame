package com.ap.game;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

public class RangeValidation {

	@Test
	public void inValidWidhtParameter() {
		// InputOutput inputOutput = new InputOutput();

		String input = "0 E";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		try {
			assertTrue(BattleShip.readInputParams());
		}
		catch (Exception ex) {
			assertTrue(ex instanceof IllegalArgumentException);
		}

	}

	@Test
	public void inValidHeightParameter() {
		// InputOutput inputOutput = new InputOutput();

		String input = "1 $";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		try {
			assertTrue(BattleShip.readInputParams());
		}
		catch (Exception ex) {
			assertTrue(ex instanceof IllegalArgumentException);
		}

	}

}
