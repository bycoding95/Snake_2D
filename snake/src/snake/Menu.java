package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	public Rectangle playEasyButton = new Rectangle(100, 70 , 100, 40);
	public Rectangle playHardButton = new Rectangle(100, 150 , 100, 40);
	public Rectangle quitButton = new Rectangle(100, 230 , 100, 40);
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt0);
		g.setColor(Color.green);
		g.drawString("SNAKE", 95, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 19);
		g.setFont(fnt1);
		g.drawString("Play Easy", playEasyButton.x+4, playEasyButton.y+25);
		g.drawString("Play Hard", playHardButton.x+4, playHardButton.y+25);
		g.drawString("Quit", quitButton.x+27, quitButton.y+25);
		
		g2d.draw(playEasyButton);
		g2d.draw(playHardButton);
		g2d.draw(quitButton);
	}
}