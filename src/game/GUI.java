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

public class GUI extends JFrame implements KeyListener{
	

	private Game gameob = new Game();
	private JFrame frame = new JFrame("2048");
	private JPanel panel = new JPanel();
	private HashMap<Integer, int[]> keyToDirection = new HashMap<Integer, int[]>();
	
	private GUI(){
		setSize(550, 550);
		setTitle("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		panel.setLayout(new GridLayout(0, 4));
		addKeyListener(this);
		// Map keys and directions:
		keyToDirection.put(37, gameob.LEFT);
		keyToDirection.put(38, gameob.UP);
		keyToDirection.put(39, gameob.RIGHT);
		keyToDirection.put(40, gameob.DOWN);
	}
	public static void main(String[] args){
		

		GUI gui = new GUI();
		gui.startGame();
		gui.drawGrid();
		gui.setVisible(true);
	}
	
	private void startGame(){
		gameob.spawnBlock();
		gameob.spawnBlock();
	}
	
	private void drawGrid(){
		
		for (int[] line : gameob.getGrid()){
			for (int column : line){
				// Only show number if it is not zero
				String displayedNumber = column != 0 ? String.valueOf(column) : "";
		
				JLabel label = new JLabel(displayedNumber, SwingConstants.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				int fontSize = getFontsize(column);
				label.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
				panel.add(label);
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
	private void update(){
		panel.removeAll();
		drawGrid();
		invalidate();
		validate();
		repaint();
	}

	public void keyReleased(KeyEvent arg0){}

	public void keyTyped(KeyEvent arg0){}
	


}
