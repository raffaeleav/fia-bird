package main.flappyBird;

import main.iaModule.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {
	public BufferedImage backgroundSprite, birdSprite, groundSprite, coinSprite;
	public static FlappyBird flappyBird;
	public final int WIDTH = 800, HEIGHT = 800;
	public Renderer renderer;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public int ticks, yMotion, score;
	public boolean gameOver, started;
	public Random rand;
	public double metri = 0;
	private static final DecimalFormat df = new DecimalFormat("0.00");

	// costruttore in cui ogni 20 ms viene refreshato il jFrame definendo l' animazione
	public FlappyBird() throws IOException {
		this.backgroundSprite = ImageIO.read(new File("sprites/background.png"));
		this.birdSprite = ImageIO.read(new File("sprites/fia2.png"));
		this.groundSprite = ImageIO.read(new File("sprites/ground.png"));
		this.coinSprite = ImageIO.read(new File("sprites/coin.png"));

		JFrame jframe = new JFrame();
		Timer timer = new Timer(25, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		
		timer.start();
	}

	// metodo che gestisce la crezione delle coppie di tubi
	public void addColumn(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(200);

		if (start) {
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}

		else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump() {
		if (gameOver) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;

			score = 0;
			
			this.metri = 0;
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}
		
		if(!gameOver)
			this.metri += 0.2;

		if (!started) {
			started = true;
		}

		else if (!gameOver) {
			if (yMotion > 0) {
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	// metodo chiamato ogni volta che viene clickato il mouse, fa saltare il bird e gestisce la gravità
	@Override
	public void actionPerformed(ActionEvent e) {
		int speed = 10;

		ticks++;

		if (started) {
			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);

				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15) {
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0) {
					columns.remove(column);

					if (column.y == 0) {
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns) {
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {
					score++;
				}

				if (column.intersects(bird)) {
					gameOver = true;

					if (bird.x <= column.x) {
						bird.x = column.x - bird.width;

					}

					else {
						if (column.y != 0) {
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height) {
							bird.y = column.height;
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0) {
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120) {
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}

		renderer.repaint();
	}

	public void repaint(Graphics g) throws IOException {

		g.drawImage(backgroundSprite,0,0,WIDTH,HEIGHT,renderer);

		g.setColor(new Color(84, 47, 29));
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(new Color(26, 122, 17));
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		g.drawImage(birdSprite, bird.x - 10, bird.y - 10, bird.width + 20, bird.height + 20, renderer);

		for (Rectangle column : columns) {
			paintColumn(g, column);
		}

		for(int i = 0; i < columns.size(); i++){
			Utils utils = new Utils();
			Point p = utils.getPipeHole(columns.get((i * 2) % 8));


			g.drawImage(coinSprite,(int) p.getX() ,(int) p.getY(), bird.width + 10, bird.height + 10, renderer);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 90));

		if (!started) {
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}

		if (gameOver) {
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
		}

		if (!gameOver && started) {
			g.drawString(String.valueOf(df.format(metri) + " m"), WIDTH / 2 - 150, 100);
		}
	}

	public static void main(String[] args) throws IOException {
		flappyBird = new FlappyBird();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}
