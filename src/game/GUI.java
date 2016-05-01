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
	
	final int[] RIGHT = {1, 0};
	final int[] LEFT = {-1, 0};
	final int[] UP = {0, -1};
	final int[] DOWN = {0, 1};
	Game gameob = new Game();
	JFrame frame = new JFrame("2048");
	JPanel panel = new JPanel();
	HashMap<Integer, int[]> keyToDirection = new HashMap<Integer, int[]>();
	
	public GUI(){
		setSize(550, 550);
		setTitle("2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panel);
		panel.setLayout(new GridLayout(0, 4));
		addKeyListener(this);
		
		keyToDirection.put(37, LEFT);
		keyToDirection.put(38, UP);
		keyToDirection.put(39, RIGHT);
		keyToDirection.put(40, DOWN);
	}
	public static void main(String[] args){
		

		GUI gui = new GUI();
		gui.startGame();
		gui.drawGrid();
		gui.setVisible(true);
	}
	
	void startGame(){
		gameob.spawnBlock();
		gameob.spawnBlock();
	}
	
	void drawGrid(){
		
		for (int[] line : gameob.getGrid()){
			for (int column : line){
				String displayedNumber = column != 0 ? String.valueOf(column) : "";
				JLabel label = new JLabel(displayedNumber, SwingConstants.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setFont(new Font("TimesRoman", Font.PLAIN, 125));
				panel.add(label);
			}
		}
	}


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (keyToDirection.containsKey(key) &&
				gameob.move(keyToDirection.get(key))){
			
			panel.removeAll();
			gameob.spawnBlock();
			drawGrid();
			invalidate();
			validate();
			repaint();
		}
		
	}

	public void keyReleased(KeyEvent arg0){}

	public void keyTyped(KeyEvent arg0){}
	


}
