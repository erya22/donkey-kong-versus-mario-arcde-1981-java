package donkeykongvsmario.utils;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class GameConfigurator {
	//SCREEN SETTINGS
	public static int LARGHEZZA_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int ALTEZZA_SCHERMO = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	//GLOBAL VARIABLES THAT HELP SET THE MAP
	public static final int U_TILE_COLS = 28;
	public static final int  U_TILE_ROWS = 32;
	public static final int U_TILE_SIZE = 32;
	public static int TILE_SIZE = ALTEZZA_SCHERMO / U_TILE_ROWS;
	
	//GLOBAL SETTING
	public final int GRAVITY = 2;
	
	private static final Map<Integer, Integer> BEAM_HEIGHTS = new HashMap<>();
    private static final Map<Integer, Integer> LADDER_HEIGHTS = new HashMap<>();

    static {
        BEAM_HEIGHTS.put(30, 2);  
        BEAM_HEIGHTS.put(28, 4);
        BEAM_HEIGHTS.put(26, 6);
        BEAM_HEIGHTS.put(24, 8);
        BEAM_HEIGHTS.put(22, 10);
        BEAM_HEIGHTS.put(20, 12);
        BEAM_HEIGHTS.put(18, 14);
        BEAM_HEIGHTS.put(16, 16);
        BEAM_HEIGHTS.put(14, 18);
        BEAM_HEIGHTS.put(12, 20);
        BEAM_HEIGHTS.put(10, 22);
        BEAM_HEIGHTS.put( 8, 24);
        BEAM_HEIGHTS.put( 6, 26);
        BEAM_HEIGHTS.put( 4, 28);
        BEAM_HEIGHTS.put( 2, 30);
        BEAM_HEIGHTS.put( 1, 32);
        
        LADDER_HEIGHTS.put(32, 32);
        LADDER_HEIGHTS.put(44, 24);
        LADDER_HEIGHTS.put(32, 32);
        

    }

	
	
	
	
}
