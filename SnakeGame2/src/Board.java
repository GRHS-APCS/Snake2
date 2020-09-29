import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int B_WIDTH = 1000;
	private final int B_HEIGHT = 1000;
	private int delay = 5;
	private int score;
	private int maxScore;

	private final int MENU=0;
	private final int GAME=1;
	private final int END=2;
	private int stage=MENU;
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	public boolean start;

	private Timer timer;

	private Snake snek;
	private Apple granny;

	public Board() {

		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		maxScore=0;
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		initGame();
	}


	private void initGame() {
		leftDirection = false;
		rightDirection = true;
		upDirection = false;
		downDirection = false;
		score=0;
		snek=new Snake(10,10);
		granny=new Apple(B_WIDTH, B_HEIGHT);
		//delay=0;
		timer = new Timer(delay, this);
		timer.start();
		Sound.START.play();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		switch(stage) {
		case MENU:
			menu(g);
			break;
		case GAME:
			granny.draw(g,this);
			snek.draw(g,this);
			Toolkit.getDefaultToolkit().sync();
			String msg = "Score:"+score;
			Font large = new Font("Helvetica", Font.BOLD, 28);
			FontMetrics metrL = getFontMetrics(large);

			g.setColor(Color.white);
			g.setFont(large);
			g.drawString(msg, (B_WIDTH - metrL.stringWidth(msg)) / 2, (int)((B_HEIGHT)-(1.5*metrL.getStringBounds(msg, g).getHeight())));

			break;
		default:
			gameOver(g);
			start=true;
		}
	}

	private void menu(Graphics g) {
		// TODO Auto-generated method stub
		String msg = "Snake";
		String msg2="Press SPACE to start";
		Font large = new Font("Helvetica", Font.BOLD, 28);
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metrL = getFontMetrics(large);
		FontMetrics metrS = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(large);
		g.drawString(msg, (B_WIDTH - metrL.stringWidth(msg)) / 2, B_HEIGHT / 2);
		g.setFont(small);
		g.drawString(msg2, (B_WIDTH - metrS.stringWidth(msg2)) / 2, (int)((B_HEIGHT / 2)+(1.5*metrL.getStringBounds(msg, g).getHeight())));
	}

	private void gameOver(Graphics g) {
		String msg = "Game Over";
		String msg2="Press SPACE to restart";
		Font large = new Font("Helvetica", Font.BOLD, 28);
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metrL = getFontMetrics(large);
		FontMetrics metrS = getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(large);
		g.drawString(msg, (B_WIDTH - metrL.stringWidth(msg)) / 2, B_HEIGHT / 2);
		g.setFont(small);
		g.drawString(msg2, (B_WIDTH - metrS.stringWidth(msg2)) / 2, (int)((B_HEIGHT / 2)+(1.5*metrL.getStringBounds(msg, g).getHeight())));
		if(score>maxScore) {
			maxScore=score;
			System.out.println(score);
		}
	}

	private void move() {
		//snek.act(leftDirection,rightDirection,upDirection,downDirection);
		snek.act(granny);
	}

	private void checkCollision() {
		if(snek.collide(granny)) {
			Sound.EAT.play();
			score++;
			//delay=0;
			timer.setDelay(delay);
			snek.addBody();
			do {
				granny.locateApple(B_WIDTH,B_HEIGHT);
			}while(snek.collide(granny));
		}
		if(snek.stopHittingYourself())
			inGame=false;
		if(snek.getX()<0||snek.getX()>B_WIDTH/10)
			inGame=false;
		if(snek.getY()<0||snek.getY()>B_HEIGHT/10)
			inGame=false;
		if (!inGame) {
			timer.stop();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (stage==GAME) {
			move();
			checkCollision();
			if(!inGame)
				stage=END;
		}
		if(start) {
			initGame();
			stage=GAME;
			start=false;
			inGame=true;
		}
		repaint();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				leftDirection = true;
				rightDirection = false;
				upDirection = false;
				downDirection = false;
			}

			if (key == KeyEvent.VK_RIGHT) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
				leftDirection = false;
			}

			if (key == KeyEvent.VK_UP) {
				upDirection = true;
				downDirection = false;
				rightDirection = false;
				leftDirection = false;
			}

			if (key == KeyEvent.VK_DOWN) {
				upDirection = false;
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			if((key == KeyEvent.VK_SPACE))
				start=true;
		}
	}
}