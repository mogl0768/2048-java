package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI{
	

	private Game gameob = new Game();
	private final JFrame frame = new JFrame("2048");
	private final JPanel panel = new JPanel();
	private static final HashMap<Integer, Direction> keyToDirection = new HashMap<Integer, Direction>();
	private JLabel[][] labels;
	static{
		// Map keys and directions:
		keyToDirection.put(KeyEvent.VK_LEFT, Direction.LEFT);
		keyToDirection.put(KeyEvent.VK_UP, Direction.UP);
		keyToDirection.put(KeyEvent.VK_RIGHT, Direction.RIGHT);
		keyToDirection.put(KeyEvent.VK_DOWN, Direction.DOWN);
	}
	
	
	private GUI(){
		frame.setSize(550, 550);
		frame.setTitle("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(new GridLayout(0, 4));
		initLabels();
		frame.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (keyToDirection.containsKey(key) &&
						gameob.move(keyToDirection.get(key))){
					
					gameob.spawnBlock();
					update();
				}
				if (gameob.isLost()){
					System.out.println("You lost!");
					gameob = new Game();
					startGame();
					update();
				}
				
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}	
		});
		

	}
	private void initLabels(){
		labels = new JLabel[Game.SIZE][Game.SIZE];
		for (int y = 0; y < Game.SIZE; y++){
			for (int x = 0; x < Game.SIZE; x++){
				labels[y][x] = new JLabel("", SwingConstants.CENTER);
				labels[y][x].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.add(labels[y][x]);
			}
		}
	}
	
	public static void main(String[] args){
		GUI gui = new GUI();
		gui.startGame();
		gui.drawGrid();
		gui.frame.setVisible(true);
	}
	
	private void startGame(){
		gameob.spawnBlock();
		gameob.spawnBlock();
	}
	
	private void drawGrid(){
		for (int row = 0; row < Game.SIZE; row++){
			for (int column = 0; column < Game.SIZE; column++){
				JLabel label = labels[row][column];
				// Only show number if it is not zero
				int value = gameob.getGridValue(column, row);
				String displayedNumber = value != 0 ? String.valueOf(value) : "";
				label.setText(displayedNumber);
				
				int fontSize = getFontsize(value);
				label.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

			}
		}
	}

	private int getFontsize(int number){
		// finds fitting font size to display the number
		if (number < 100){
			return 125;
		} if (number < 1000){
			return 80;
		} else {
			return 50;
		}
	}
		
	private void update(){
		drawGrid();
		frame.repaint();
	}
}
