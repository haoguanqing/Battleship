package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BattleshipGameTest {
	BattleshipGame game;

	@Before
	public void setUp() throws Exception {
		game = new BattleshipGame();
	}

	@Test
	public void testCheckValidInput() {
		String input1 = "gSwE eFa";
		String input2 = "a b";
		String input3 = "12 3";
		String input4 = "2 6";
		assertFalse(game.checkValidInput(input1));
		assertFalse(game.checkValidInput(input2));
		assertFalse(game.checkValidInput(input3));
		assertTrue(game.checkValidInput(input4));
		
	}

	@Test
	public void testGetCoordinates() {
		String input = "2 6";
		int[] coordinates = game.getCoordinates(input);
		assertEquals(2, coordinates[0]);
		assertEquals(6, coordinates[1]);
	}

}
