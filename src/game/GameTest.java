package game;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class GameTest {
	final int[] RIGHT = {1, 0};
	final int[] LEFT = {-1, 0};
	final int[] UP = {0, -1};
	final int[] DOWN = {0, 1};
	
	Game gameob = new Game();
	@Test
	public void merge() {
		
		int[] inp1 = {0, 0, 0, 0};
		int[] outp1 = {0, 0, 0, 0};
		
		int[] inp2 = {0, 2, 2, 0};
		int[] outp2 = {4, 0, 0, 0};
		
		int[] inp3 = {4, 0, 0, 4};
		int[] outp3 = {8, 0, 0, 0};
		
		int[] inp4 = {2, 4, 2, 4};
		int[] outp4 = {2, 4, 2, 4};
		
		int[] inp5 = {4, 4, 2, 4};
		int[] outp5 = {8, 2, 4, 0};
		
		assertTrue(Arrays.equals(outp1, gameob.merge(inp1)));
		assertTrue(Arrays.equals(outp2, gameob.merge(inp2)));
		assertTrue(Arrays.equals(outp3, gameob.merge(inp3)));
		assertTrue(Arrays.equals(outp4, gameob.merge(inp4)));
		assertTrue(Arrays.equals(outp5, gameob.merge(inp5)));
	}
	
	@Test
	public void genStartPoints(){
		int[] inp1 = {1, 0}; 
		int[] inp2 = {-1, 0}; 
		int[] inp3 = {0, 1}; 
		int[] inp4 = {0, -1}; 
		
		int[][] outp1 = {{3, 0}, {3, 1}, {3, 2}, {3, 3}};
		int[][] outp2 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}};
		int[][] outp3 = {{0, 3}, {1, 3}, {2, 3}, {3, 3}};
		int[][] outp4 = {{0, 0}, {1, 0}, {2, 0}, {3, 0}};

		
		assertTrue(Arrays.deepEquals(outp1, gameob.genStartPoints(inp1)));
		assertTrue(Arrays.deepEquals(outp2, gameob.genStartPoints(inp2)));
		assertTrue(Arrays.deepEquals(outp3, gameob.genStartPoints(inp3)));
		assertTrue(Arrays.deepEquals(outp4, gameob.genStartPoints(inp4)));
	}
	
	@Test
	public void move() {
		int[][] board = {
				{0, 2, 4, 4},
				{2, 2, 0, 0},
				{0, 32, 16, 4},
				{0, 32, 4, 4}
		};
		int[][] outcomeLeft = {
				{2, 8, 0, 0},
				{4, 0, 0, 0},
				{32, 16, 4, 0},
				{32, 8, 0, 0}
		};
		int[][] outcomeRight = {
				{0, 0, 2, 8},
				{0, 0, 0, 4},
				{0, 32, 16, 4},
				{0, 0, 32, 8}
		};
		int[][] outcomeDown = {
				{0, 0, 0, 0},
				{0, 0, 4, 0},
				{0, 4, 16, 4},
				{2, 64, 4, 8}
		};
		int[][] outcomeUp = {
				{2, 4, 4, 8},
				{0, 64, 16, 4},
				{0, 0, 4, 0},
				{0, 0, 0, 0}
		};
		gameob.setGrid(Game.deepCopy(board));
		gameob.move(LEFT);
		assertTrue(Arrays.deepEquals(outcomeLeft, gameob.getGrid()));
		
		gameob.setGrid(Game.deepCopy(board));
		gameob.move(RIGHT);
		assertTrue(Arrays.deepEquals(outcomeRight, gameob.getGrid()));
		
		gameob.setGrid(Game.deepCopy(board));
		gameob.move(DOWN);
		assertTrue(Arrays.deepEquals(outcomeDown, gameob.getGrid()));
	
		gameob.setGrid(board);
		gameob.move(UP);
		assertTrue(Arrays.deepEquals(outcomeUp, gameob.getGrid()));
	
	}
}
