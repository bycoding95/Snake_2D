package snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		/*
		 * public Rectangle playEasyButton = new Rectangle(100, 70 , 100, 40); public
		 * Rectangle playHardButton = new Rectangle(100, 150 , 100, 40); public
		 * Rectangle quitButton = new Rectangle(100, 230 , 100, 40);
		 */
//		Play Easy Button
		if (mx >= 100 && mx <= 200) {
			if (my >= 70 && my <= 110) {
//				Pressed playEasyButton
				Game.State = Game.STATE.GAMEEASY;
			}
		}
		
//		Play Hard Button
		if (mx >= 100 && mx <= 200) {
			if (my >= 150 && my <= 190) {
//				Pressed playEasyButton
				Game.State = Game.STATE.GAMEHARD;
			}
		}

//		Quit Button
		if (mx >= 100 && mx <= 200) {
			if (my >= 230 && my <= 270) {
//				Pressed Quit Button
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
