package it.unibs.pajc.dk1981.donkeykongvsmario;

import javax.swing.SwingUtilities;

import it.unibs.pajc.dk1981.donkeykongvsmario.view.GameWindow;

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
