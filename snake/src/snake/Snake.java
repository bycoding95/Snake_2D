package snake;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {
	
	
	public Snake() {
		// TODO Auto-generated constructor stub
		add(new Game());
		setResizable(false);
		pack();
		
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			
			@Override
			public void run() {
				JFrame snake = new Snake();
				snake.setVisible(true);
			}
		});
	}

}
