package game;

import java.util.Arrays;

public class Game {

	private int[][] grid = new int[4][4];
	final int SIZE = 4;

	
	Game(){
		// set up empty board
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				grid[i][j] = 0;
			}
		}
	}
	boolean move(int[] direction){
		// move grid in given direction
		int[][] originalGrid= deepCopy(grid);
		int[][] startPoints = genStartPoints(direction);
		for (int[] point:startPoints) {
			int [] line = genLine(point, direction);
			int [] newline = merge(line);
			setLine(point, direction, newline);	
		}
		return !Arrays.deepEquals(grid, originalGrid);
	}
	
	int[][] genStartPoints(int[] direction){
		/* Generate coordinates which help to merge the line later. 
		 * They are stored as {x, y} and represent the start points 
		 * to generate the array which will later be merged.
		 */
		int x = direction[0] > 0 ? SIZE - 1 : 0;
		int y = direction[1] > 0 ? SIZE - 1 : 0;
		int moveX = direction[0] != 0 ? 0 : 1;
		int moveY = direction[1] != 0 ? 0 : 1;
		int[][] output = new int[SIZE][2];
		for (int i = 0; i < SIZE; i++){
			output[i][0] = x;
			output[i][1] = y;
			x += moveX;
			y += moveY;
		}
		return output;
		
	}
	
	int[] genLine(int[] start, int[] direction){
		// gets all corresponding values given a start point and a direction
		int[] output = new int[SIZE];
		for (int i = 0;  i < SIZE; i++){
			int x = start[0] - direction[0] * i; 
			int y = start[1] - direction[1] * i;
			output[i] = grid[y][x];
		}
		return output;
	}

	void setLine(int[] start, int[] direction, int[] line){
		// sets the given values at the given positions
		for (int i = 0;  i < SIZE; i++){
			int x = start[0] - direction[0] * i; 
			int y = start[1] - direction[1] * i;
			grid[y][x] = line[i];
		}
	}
	
	int[] merge(int[] numbers) {
		// merges the given array to the left side
		int valueBuffer = 0;
		int[] output = {0, 0, 0, 0};
		int outputInd = 0;
		for (int number : numbers) {
			if (number != 0) {
				if (number == valueBuffer) {
					output[outputInd] = number + valueBuffer;
					outputInd++;
					valueBuffer = 0;
				} else if (valueBuffer != 0) {
					output[outputInd] = valueBuffer;
					outputInd++;
					valueBuffer = number;
				} else {
					valueBuffer = number;
				}
			}
		}
		if (valueBuffer != 0) {output[outputInd] = valueBuffer;}
		return output;
	}
	
	boolean spawnBlock(){
		// spawns new number at random location
		int rand1;
		int rand2;
		if (!isfinished()){
			do{
			rand1 = (int) (Math.random() * 4) ;
			rand2 = (int) (Math.random() * 4) ;
			System.out.printf("--%d %d \n", rand1, rand2);
			if (grid[rand1][rand2] == 0){
				boolean fourOrTwo = Math.random() <= 0.75;
				grid[rand1][rand2] = fourOrTwo ? 2 : 4;
				System.out.printf("--%d %d \n", rand1, rand2);
				return true;
			}
			} while (grid[rand1][rand2] != 0);
		}
		return false;
		
	}
	boolean isfinished(){
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				if (grid[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	public static int[][] deepCopy(int[][] board){
		// makes deep copy of two-dimensional Array
		int[][] newGrid = new int[4][4];
		for (int i = 0; i < 4; i++){
			newGrid[i] = board[i].clone();
		}
		return newGrid;
	}
	public void setGrid(int[][] inputGrid){
		grid = inputGrid;
	}
	public int[][] getGrid(){
		return grid;
	}
}
