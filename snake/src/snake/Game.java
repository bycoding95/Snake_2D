package snake;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * Ideen:
 * -während dem Spiel pausieren und in's hauptmenü gelangen
 * -objekte (hindernisse) im Spielfeld
 * -hintergrundmusik
 * -mehrere äpfel
 * -Farben: Schlange, Apfel, Hintergrund usw.
 * -Spielgeschwindigkeit verändern
 * 
 * Bug:
 * ab tail = 4 wenn snake nach rechts läuft und man drückt oben und links dann ist das Spiel verloren
 */
public class Game extends JPanel implements ActionListener {
	private int width = 300;
	private int height = 300;

	private Image apple;
	private Image head;
	private Image tail;
	private Image gameover;

	private int tail_amount = 3;
	private int snake_width = 10; // die Breite eines Schlangen-Elements: 10 Pixel
	private int snake_x[] = new int[width * height / (snake_width * snake_width)];
	private int snake_y[] = new int[width * height / (snake_width * snake_width)];

	private int apple_x;
	private int apple_y;
	private boolean running;

	private Timer t;
	private Menu menu;

	public static enum STATE {
		MENU, GAMEEASY, GAMEHARD
	};

	public static STATE State = STATE.MENU;

	/* 0 = left; 1 = right; 2 = up; 3 = down */
	public static int direction;

	// Konstruktor
	public Game() {
		addKeyListener(new SnakeListener());
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		setBackground(Color.DARK_GRAY);
		this.addMouseListener(new MouseInput());

		ImageIcon icon_apple = new ImageIcon("apple.png");
		ImageIcon icon_head = new ImageIcon("head.png");
		ImageIcon icon_tail = new ImageIcon("tail.png");
		ImageIcon icon_gameover = new ImageIcon("gameover1.png");
		
		apple = icon_apple.getImage();
		head = icon_head.getImage();
		tail = icon_tail.getImage();
		gameover = icon_gameover.getImage();

		menu = new Menu();

		for (int i = 0; i < tail_amount; i++) {
//		Snake wird an den Startpunkt gesetzt
			snake_x[i] = 100 - i * 10; // snake_x[0] -> Kopf; snake_x[1] -> Rumpf; snake_x[2] -> Rumpf Ende --x
			snake_y[i] = 50;
		}

		running = true;
		
		t = new Timer(200, this);
		t.start();
		
		spawn_apple();
	}

	private void spawn_apple() {
		int random = (int) (Math.random() * 29); // typcasting
		apple_x = random * snake_width;
		random = (int) (Math.random() * 29);
		apple_y = random * snake_width;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		Lasse Menü laufen
		if (State == STATE.MENU) {
			menu.render(getGraphics());
		}

//		Lasse Gameeasy laufen
		if (State == STATE.GAMEEASY) {

			if (running) {

				check_apple();
				check_death_easy();
				move_snake_easy();

			}
			repaint();	// ruft ständig die paintComponent()-Methode auf
		}

//		Lasse Gamehard laufen
		if (State == STATE.GAMEHARD) {

			if (running) {

				check_apple();
				check_death_hard();
				move_snake_hard();
			}
			repaint();
		}

	}

//	Schwierigkeitsstufe: einfach
	private void move_snake_easy() {

//		Snake taucht auf der Gegenseite auf
		if (snake_x[0] == width && snake_x[1] == width - snake_width) {
			snake_x[0] = 0;
		}

		if (snake_x[0] == 0 && snake_x[1] == 10) {
			snake_x[0] = width;
		}

		if (snake_y[0] == height && snake_y[1] == height - snake_width) {
			snake_y[0] = 0;
		}

		if (snake_y[0] == 0 && snake_y[1] == 10) {
			snake_y[0] = height;
		}

		for (int i = tail_amount; i > 0; i--) { // tail_amount = 3
			snake_x[i] = snake_x[i - 1];
			snake_y[i] = snake_y[i - 1];
		}

		/* 0 = left; 1 = right; 2 = up; 3 = down */
		switch (direction) {
		case 0:
			snake_x[0] -= snake_width;
			break;

		case 1:
			snake_x[0] += snake_width;
			break;

		case 2:
			snake_y[0] -= snake_width;
			break;

		case 3:
			snake_y[0] += snake_width;
			break;

		default:
			break;
		}
	}

//	Schwierigkeitsstufe: schwer
	private void move_snake_hard() {

		for (int i = tail_amount; i > 0; i--) { // tail_amount = 3
			snake_x[i] = snake_x[i - 1];
			snake_y[i] = snake_y[i - 1];
		}

		/* 0 = left; 1 = right; 2 = up; 3 = down */
		switch (direction) {
		case 0:
			snake_x[0] -= snake_width;
			break;

		case 1:
			snake_x[0] += snake_width;
			break;

		case 2:
			snake_y[0] -= snake_width;
			break;

		case 3:
			snake_y[0] += snake_width;
			break;

		default:
			break;
		}
	}

//	Schwierigkeitsstufe: einfach
	private void check_death_easy() {
//		Schlange stirbt nicht wenn sie gegen die Wand läuft
		for (int i = tail_amount; i > 3; i--) {
			if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
				running = false;
			}
		}

		if (!running) {
			t.stop();
		}
	}

//	Schwierigkeitsstufe: schwer
	private void check_death_hard() {
		for (int i = tail_amount; i > 3; i--) {
			if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
				running = false;
			}
		}
//		Wenn die Schlange gegen die Wand läuft stirbt Sie
		if (snake_y[0] > height || snake_x[0] > width || snake_y[0] < 0 || snake_x[0] < 0) {
			running = false;
		}

		if (!running) {
			t.stop();
		}
	}

	private void check_apple() {
		if (snake_x[0] == apple_x && snake_y[0] == apple_y) {
			tail_amount++; // Schlange frisst und sein Körper wächst
			spawn_apple();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (State == STATE.GAMEEASY || State == STATE.GAMEHARD) {
			if (running) {
				g.drawImage(apple, apple_x, apple_y, this);

				for (int i = 1; i < tail_amount; i++) {
					g.drawImage(tail, snake_x[i], snake_y[i], this);
				}

				g.drawImage(head, snake_x[0], snake_y[0], this);

				Toolkit.getDefaultToolkit().sync();
			} else {
				Font f = new Font("arial", Font.BOLD, 18);
				FontMetrics metrics = getFontMetrics(f);

				g.setColor(Color.LIGHT_GRAY);
				g.setFont(f);
				/*g.drawString("Game Over - You died, noob!", (width/2) - (metrics.stringWidth("GAME OVER - You died, noob!")) /2,
						height / 2);*/
				g.drawImage(gameover, 1, (height/2) -92, this);
			}
		}
	}
}