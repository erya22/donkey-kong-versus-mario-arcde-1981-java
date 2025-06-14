package donkeykongvsmario.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.utils.CollisionManager;
import donkeykongvsmario.utils.GameConfigurator;

public class Universe {
	private static final Logger log = LoggerFactory.getLogger(Universe.class);
	private Map map;
	private List<Collision> collision;
	
	//OGGETTI
	private List<GameItem> items = new ArrayList<GameItem>();
	
	//ENTITA'
	private Player player;
	
	public Universe() {
		collision = CollisionManager.loadSampleCollisions();
		this.player = new Player(this, "Mario");
		this.map = new Map();
		
		if (collision != null) {
            for (Collision obj : collision) {
                System.out.println("Caricata collisione: " + obj);
            }
        } else {
            System.out.println("Errore nel caricamento della mappa delle collisioni!");
        }

		
	}
	
	public boolean checkCollision() {
	   for (Collision collision : this.getCollision()) {
		   if (isMarioOnBeam(this.getPlayer(), collision) ) {
			   getPlayer().setTerrain(Terrain.BEAM);
			   return true;
		   }
	   }
	   return false;
		   
//		   } else if (isMarioOnAir(this.getPlayer(), collision)) {
//			   
//		   } else if (isMarioOnLadder(this.getPlayer(), collision)) {
//			   
//		   }
//		   
//	   }
	}
	
	


	
	public List<Collision> getCollision() {
		return collision;
	}

	public void setCollision(List<Collision> collision) {
		this.collision = collision;
	}

	private boolean isMarioOnAir(Player mario, CollisionObject collision) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isMarioOnLadder(Player mario, CollisionObject collision) {
		// TODO Auto-generated method stub
		return false;
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
	
	/***----GESTIONE COLLISIONI----***/
//	public boolean isMarioOnBeam(Player mario, Collision collision) {
//		log.info("x{} y{} collisionx{} collisiony{}", mario.getX(), mario.getY(), collision.getX(), collision.getY());
//		boolean controllo = (mario.getY() + mario.getHeight() >= (collision.getY() - GameConfigurator.TILE_SIZE)) && 
//		           (mario.getY() + mario.getHeight() <= (collision.getY() + collision.getHeight() - GameConfigurator.TILE_SIZE)) &&
//		           (mario.getX() + (mario.getWidth()/2) > collision.getX()) &&
//		           (mario.getX() + mario.getWidth()/2 < collision.getX() + collision.getWidth());
//		log.info("controllo {}", controllo);
//		return controllo;
// 
//	}
	
	public boolean isMarioOnBeam(Player mario, Collision collision) {
	    int marioBottom = mario.getY() + mario.getHeight();
	    int beamTop = collision.getY();
	    int beamBottom = collision.getY() + collision.getHeight();
	    
	    // Mario tocca la parte superiore della trave
	    boolean verticalOverlap = marioBottom >= beamTop && marioBottom <= beamTop + 5; // margine tolleranza
	    int marioCenterX = mario.getX() + mario.getWidth() / 2;
	    boolean horizontalInside = marioCenterX >= collision.getX() && marioCenterX <= collision.getX() + collision.getWidth();

	    boolean controllo = verticalOverlap && horizontalInside;
	    log.info("marioBottom={} beamTop={} beamBottom={} marioCenterX={} beamX={} beamW={} controllo={}",
	        marioBottom, beamTop, beamBottom, marioCenterX, collision.getX(), collision.getWidth(), controllo);
	    
	    return controllo;
	}

	
	
	
	
}
