package donkeykongvsmario.model;

import java.util.ArrayList;
import java.util.List;

import donkeykongvsmario.utils.CollisionLoader;
import donkeykongvsmario.utils.TileMapLoader;

public class Universe {
	private Map map;
	private List<CollisionObject> collisionObjects;
	
	//OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	//ENTITA'
	private Player player;
	
	public Universe() {
		collisionObjects = CollisionLoader.loadCollisionMap();
		this.player = new Player(this, "Mario");
		this.map = new Map();
		
		if (collisionObjects != null) {
            for (CollisionObject obj : collisionObjects) {
                System.out.println("Caricata collisione: " + obj);
            }
        } else {
            System.out.println("Errore nel caricamento della mappa delle collisioni!");
        }

		
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<GameItem> getItems() {
		return items;
	}

	public void setItems(List<GameItem> items) {
		this.items = items;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
	
	
	
	
}
