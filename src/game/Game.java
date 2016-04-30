package game;


public class Game {

	public int[][] grid = new int[4][4];
	final int SIZE = 4;

	
	Game(){
		// set up empty board
		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				grid[i][j] = 0;
			}
		}
	}
	void move(int[] direction){
		// move grid in given direction
		int[][] startPoints = genStartPoints(direction);
		for (int[] point:startPoints) {
			int [] line = genLine(point, direction);
			int [] newline = merge(line);
			setLine(point, direction, newline);	
		}
	}
	
	int[][] genStartPoints(int[] direction){
		int x = direction[0] < 0 ? SIZE - 1 : 0;
		int y = direction[1] < 0 ? SIZE - 1 : 0;
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
		int[] output = new int[SIZE];
		for (int i = 0;  i < SIZE; i++){
			int x = start[0] + direction[0] * i; 
			int y = start[1] + direction[1] * i;
			output[i] = grid[y][x];
		}
		return output;
	}

	void setLine(int[] start, int[] direction, int[] line){
		for (int i = 0;  i < SIZE; i++){
			int x = start[0] + direction[0] * i; 
			int y = start[1] + direction[1] * i;
			grid[y][x] = line[i];
		}
	}
	
	int[] merge(int[] numbers) {
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
	
	boolean spawn_block(){
		int rand1;
		int rand2;
		if (!isfinished()){
			do{
			rand1 = (int) Math.random() * 5 ;
			rand2 = (int) Math.random() * 5 ;
			if (grid[rand1][rand2] == 0){
				boolean fourOrTwo = Math.random() <= 0.75;
				grid[rand1][rand2] = fourOrTwo ? 2 : 4;
				return true;
			}
			} while (grid[rand1][rand2] != 0);
		}
		return false;
		
	}
	boolean isfinished(){
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++){
				if (grid[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	public void setGrid(int[][] inputGrid){
		grid = inputGrid;
	}
	public int[][] getGrid(){
		return grid;
	}
}
