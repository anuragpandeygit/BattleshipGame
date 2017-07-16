package com.ap.game;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameValidation {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@Test
	public void player2WinsBattle() {

		StringBuilder sb = new StringBuilder();
		sb.append("5 E\n");
		sb.append("2\n");
		sb.append("Q 1 1 A1 B2\n");
		sb.append("P 2 1 D4 C3\n");
		sb.append("A1 B2 B2 B4\n");
		sb.append("A1 B2 B3 A1 D1 E1 D4 D4 D5 D5");

		InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
		System.setIn(in);

		String[] arguments = {};
		BattleShip.main(arguments);
		String expected = "PLAYER_1 has won the battle.";
		assertTrue(expected == outContent.toString());
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

}
