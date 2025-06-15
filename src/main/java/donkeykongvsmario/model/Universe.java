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
	
	public Collision getBeamAboveMario(Player mario, List<Collision> beams) {
	    int marioHeadY = mario.getY(); // Top of Mario

	    for (Collision beam : beams) {
	        // Verifica che la beam sia sopra la testa di Mario, ma non troppo in alto
	        if (beam.getY() + beam.getHeight() <= marioHeadY &&
	            marioHeadY - (beam.getY() + beam.getHeight()) <= GameConfigurator.TILE_SIZE &&
	            mario.getX() + mario.getWidth() > beam.getX() &&
	            mario.getX() < beam.getX() + beam.getWidth()) {
	            return beam;
	        }
	    }
	    return null;
	}

	
//	public boolean canMarioStepUp(Collision next) {
//	    for (Collision nextBeam : getCollision()) {
//	    	log.info("Beam = {} \n", nextBeam.toString());
//	        //se è la beam corrente, essa viene saltata
//	    	if (nextBeam == getPlayer().getCurrentBeam()) continue;
//
//	        // Controlla se la nuova trave è adiacente (destra o sinistra) 
//	        boolean isAdjacentRight = (player.getX() + player.getWidth() >= nextBeam.getX() &&
//	                                   player.getX() + player.getWidth() <= nextBeam.getX() + 10);
//	        
//	        boolean isAdjacentLeft = (player.getX() <= nextBeam.getX() + nextBeam.getWidth() &&
//	                                  player.getX() >= nextBeam.getX() + nextBeam.getWidth() - 10);
//
//	        // Altezza della trave rispetto a quella su cui si trova
//	        int heightDifference = getPlayer().getCurrentBeam().getY() - nextBeam.getY();
//
//	        log.info("height difference {}", heightDifference);
//	        
//	        // Verifica se è salibile (es. altezza > 0 ma < soglia)
//	        if ((isAdjacentRight || isAdjacentLeft) &&
//	            heightDifference > 0 && heightDifference <= GameConfigurator.MAX_STEP_HEIGHT) {
//	            return true;
//	        }
//	    }
//
//	    return false;
//	}
	
	public boolean canMarioStepUp(Collision next) {
	    for (Collision beam : getCollision()) {
	        if (beam == getPlayer().getCurrentBeam()) continue;

	        boolean isAdjacentRight = (player.getX() + player.getWidth() >= beam.getX() &&
	                                   player.getX() + player.getWidth() <= beam.getX() + 10);
	        
	        boolean isAdjacentLeft = (player.getX() <= beam.getX() + beam.getWidth() &&
	                                  player.getX() >= beam.getX() + beam.getWidth() - 10);

	        int heightDifference = getPlayer().getCurrentBeam().getY() - beam.getY();

	        log.info("Testing beam: {} | AdjR: {} | AdjL: {} | HeightDiff: {}",
	                 beam.toString(), isAdjacentRight, isAdjacentLeft, heightDifference);

	        if ((isAdjacentRight || isAdjacentLeft) &&
	            heightDifference > 0 && heightDifference <= GameConfigurator.MAX_STEP_HEIGHT) {
	            log.info("Mario può salire sulla beam: {}", beam.toString());
	            return true;
	        }
	    }

	    log.info("Mario NON può salire su nessuna beam vicina");
	    return false;
	}



	public Collision getBeamUnderMario() {
	    for (Collision c : collision) {
	        if (isMarioOnBeam(player, c)) {
	            return c;
	        }
	    }
	    return null;
	}

	public Collision getNextBeam(Player mario, List<Collision> beams) {
	    Collision currentBeam = getBeamUnderMario();
	    if (currentBeam == null) return null;

	    for (Collision beam : beams) {
	        if (beam == currentBeam) continue;

	        int marioCenterX = mario.getX() + mario.getWidth() / 2;

	        // Check if this beam is horizontally adjacent to Mario (on either side)
	        boolean isRight = beam.getX() <= marioCenterX + GameConfigurator.TILE_SIZE &&
	                          beam.getX() > marioCenterX;

	        boolean isLeft = beam.getX() + beam.getWidth() >= marioCenterX - GameConfigurator.TILE_SIZE &&
	                         beam.getX() + beam.getWidth() < marioCenterX;

	        // Check that the beam is within climbable vertical range
	        int heightDifference = currentBeam.getY() - beam.getY(); // Positive if beam is above
	        boolean isStepHeightOK = heightDifference > 0 && heightDifference <= GameConfigurator.TILE_SIZE / 2;

	        if ((isRight || isLeft) && isStepHeightOK) {
	            return beam;
	        }
	    }

	    return null;
	}

	
	
	
}
