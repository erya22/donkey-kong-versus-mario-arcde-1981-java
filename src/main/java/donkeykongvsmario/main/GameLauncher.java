package donkeykongvsmario.main;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.view.GameWindow;

/**
 * Hello world!
 *
 */
public class GameLauncher 
{
	private static final Logger log = LoggerFactory.getLogger(GameLauncher.class);
    public static void main( String[] args )
    {
    	log.info("START -");
    	SwingUtilities.invokeLater(() -> {
        	GameWindow window = new GameWindow();
        	window.setVisible(true);
        }); 
    }
}
