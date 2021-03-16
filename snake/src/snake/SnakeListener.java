package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key_id = e.getKeyCode();
		
		/* 0 = left; 1 = right; 2 = up; 3 = down */
		if(key_id == KeyEvent.VK_LEFT && !(Game.direction == 1))
		{
			Game.direction = 0;
		}
		
		if(key_id == KeyEvent.VK_RIGHT && !(Game.direction == 0))
		{
			Game.direction = 1;
		}
		
		if(key_id == KeyEvent.VK_UP && !(Game.direction == 3))
		{
			Game.direction = 2;
		}
		
		if(key_id == KeyEvent.VK_DOWN && !(Game.direction == 2))
		{
			Game.direction = 3;
		}
	}


}
