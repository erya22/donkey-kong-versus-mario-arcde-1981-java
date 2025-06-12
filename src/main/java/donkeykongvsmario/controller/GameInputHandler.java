package donkeykongvsmario.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Si occupa di gestire gli input da tastiera legati alla gestione dello schermo.
 * Se premo esc, esco dal gioco.
 */
public class GameInputHandler extends KeyAdapter {

	private boolean escPressed = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			escPressed = true;
			new Thread(() -> {
				try {
					Thread.sleep(500);
					if (escPressed) {
						System.exit(0);
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
					
				}
			}).start();
		}
	}
		
		@Override
	    public void keyReleased(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	            escPressed = false;
	        }
	    }

	
}
