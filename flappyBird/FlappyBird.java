package flappyBird;

import iaModule.Utils;

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
	// sprites
	public BufferedImage backgroundSprite, birdSprite, groundSprite;
	// unica istanza della classe
	public static FlappyBird flappyBird;
	// larghezza e altezza della finestra
	public final int WIDTH = 800, HEIGHT = 800;
	// un jPanel che si occupa di disegnare ogni volta gli elementi grafici
	public Renderer renderer;
	// rappresenta il Flappy Bird
	public Rectangle bird;
	// tubi
	public ArrayList<Rectangle> columns;
	// ticks = counter per diminuire la gravita', yMotion = gravita', score = punteggio
	public int ticks, yMotion, score;
	// variabili che definiscono se il gioco e' finito o e' iniziato
	public boolean gameOver, started;
	// serve per la randomizzare la lunghezza dei tubi
	public Random rand;
	// serve per tener conto della distanza
	public double metri = 0;
	private static final DecimalFormat df = new DecimalFormat("0.00");

	// costruttore in cui ogni 20 ms viene refreshato il jFrame definendo l' animazione
	public FlappyBird() throws IOException {
		this.backgroundSprite = ImageIO.read(new File("sprites/background.png"));
		this.birdSprite = ImageIO.read(new File("sprites/fia.png"));
		this.groundSprite = ImageIO.read(new File("sprites/ground.png"));

		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		// si puo' saltare sia con click del mouse che con qualunque tasto della tastiera
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		// crea le prime 4 coppie di tubi
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		// fa partire il timer
		timer.start();
	}

	// gestisce la crezione delle coppie di tubi
	public void addColumn(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		// se il gioco e' partito crea due tubi uno sopra e uno sotto
		if (start) {
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}

		// si entra qui in actionPerformed quando la coppia di tubi arriva alla fine dello schermo ne viene creata un altra dopo l' ultima coppia di tubi
		else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	// colora le coppie di tubi
	public void paintColumn(Graphics g, Rectangle column) {
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	// permette di far partire il gioco
	public void jump() {
		// ogni volta che si perde se si preme un tasto si riparte con il gioco
		if (gameOver) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;

			score = 0;
			// Reset dei metri (New Game)
			this.metri = 0;
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}
		// Permette di calcolare i metri percorsi
		if(!gameOver)
			this.metri += 0.2;

		// si indica che il gioco e' iniziato
		if (!started) {
			started = true;
		}

		// si gestisce la gravita' quando finisce il gioco decrementando yMotion
		else if (!gameOver) {
			if (yMotion > 0) {
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	// chiamata ogni volta che viene clickato il mouse, fa saltare il bird e gestisce la gravita'
	@Override
	public void actionPerformed(ActionEvent e) {
		// velocita' con cui vengono ridisegnati i tubi
		int speed = 10;

		// counter per gestire la gravita'
		ticks++;

		if (started) {
			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);

				// permette lo scorrimento dei tubi
				column.x -= speed;
			}

			// ogni 2 click si abbassa la gravita'
			if (ticks % 2 == 0 && yMotion < 15) {
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++) {
				Rectangle column = columns.get(i);

				// quando i tubi 'escono dallo schermo', scorrendo, vengono rimossi
				if (column.x + column.width < 0) {
					columns.remove(column);

					// se la coppia di tubi sta all' inizio dello schermo, ne crea un' altra coppia
					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			// permette l' effettivo salto del bird
			bird.y += yMotion;

			for (Rectangle column : columns) {
				// ogni volta che supera una coppia di tubi aumenta il punteggio
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {
					score++;
				}

				// permette il game over
				if (column.intersects(bird)) {
					gameOver = true;

					// animazione del bird che rimane per terra
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

			// se si tocca il terreno e' game over
			if (bird.y > HEIGHT - 120 || bird.y < 0) {
				gameOver = true;
			}

			// se si salta troppo in alto e' game over
			if (bird.y + yMotion >= HEIGHT - 120) {
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}

		// ridisegna il frame ogni volta che si preme
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

		// mostra il centro delle pipe
		for(int i = 0; i < columns.size() - 1; i++){
			Utils utils = new Utils();
			Point p = utils.getPipeHole(columns.get((i * 2) % 8));

			g.setColor(Color.yellow);
			g.fillRect((int) p.getX(), (int) p.getY(), bird.width, bird.height);

			i++;
		}

		g.setColor(new Color(69, 117, 214));
		g.setFont(new Font("Arial", 1, 100));

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

	// evento di click del mouse
	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
	}

	// evento di rilascio della spacebar
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