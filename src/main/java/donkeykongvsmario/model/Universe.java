package donkeykongvsmario.model;

import java.util.ArrayList;
import java.util.List;

import donkeykongvsmario.utils.TileMapLoader;

public class Universe {
	private Map map;
	
	
	//OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	//ENTITA'
	private Player player;
	
	public Universe() {
		
		this.player = new Player(this, "Mario");
		this.map = new Map();
		
		boolean[][] collisionMap = TileMapLoader.loadCollisionMap(map);
	}
	
	
}
