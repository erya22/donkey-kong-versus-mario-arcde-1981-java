package donkeykongvsmario.main;

import javax.swing.SwingUtilities;

import donkeykongvsmario.view.GameWindow;

/**
 * Hello world!
 *
 */
public class GameApp 
{
    public static void main( String[] args )
    {
    	SwingUtilities.invokeLater(() -> {
        	GameWindow window = new GameWindow();
        	window.setVisible(true);
        }); 
    }
}
