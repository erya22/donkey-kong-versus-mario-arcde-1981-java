package donkeykongvsmario.utils;

import java.awt.Toolkit;

public class GameConfigurator {
	//SCREEN SETTINGS
	public static int LARGHEZZA_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int ALTEZZA_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	//GLOBAL VARIABLES THAT HELP SET THE MAP
	public static final int U_TILE_COLS = 28;
	public static final int  U_TILE_ROWS = 32;
	public static final int U_TILE_SIZE = 32;
	public static int TILE_SIZE = ALTEZZA_SCHERMO / U_TILE_ROWS;
	
	
	
	
}
