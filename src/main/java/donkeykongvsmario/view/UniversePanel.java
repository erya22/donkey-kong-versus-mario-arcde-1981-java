package donkeykongvsmario.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import donkeykongvsmario.model.ActionState;
import donkeykongvsmario.model.AnimationType;
import donkeykongvsmario.model.Collision;
import donkeykongvsmario.model.Direction;
import donkeykongvsmario.model.Map;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.Universe;
import donkeykongvsmario.utils.CollisionManager;
import donkeykongvsmario.utils.GameConfigurator;

public class UniversePanel extends JPanel {
	private static final Logger log = LoggerFactory.getLogger(UniversePanel.class);

	private Map map;
	private MapRenderer mapPanel;
	
	private Player player;
	private PlayerView playerView;
	
	
	private Universe universe;
	
	private List<Collision> collisions;
	private CollisionRenderer collisionRenderer;
	/**
	 * Si occupa di gestire le view di tutte le entità di gioco.
	 */
	public UniversePanel(Universe universe) {
		log.info("UniverseView created");
		
		setBackground(Color.BLACK);

		setLayout(null);
//		setLayout(new GridBagLayout());
		
		this.universe = universe;
		this.player = universe.getPlayer();
		this.playerView = new PlayerView(player);
		
		playerView.setOpaque(false);
		playerView.setBounds(0, 0, getWidth(), getHeight());
		
		this.map = new Map();
		this.mapPanel = new MapRenderer(map);
		
		this.collisions = CollisionManager.loadSampleCollisions();
		this.collisionRenderer = new CollisionRenderer(collisions);
		
		
		setBackground(Color.BLACK);
		
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.anchor = GridBagConstraints.CENTER; // centro orizzontale e verticale
//		add(mapPanel, gbc);
		
		setFocusable(true);
        requestFocusInWindow();

        setupKeyBindings();
		
		Timer animator = new Timer(10, e -> {
				applyControls();
				repaint();
			});
		 
		 animator.start();
	}
	
	private void setupKeyBindings() {
        // InputMap e ActionMap
        var im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        var am = getActionMap();

        // Freccia destra - walk right
        im.put(KeyStroke.getKeyStroke("pressed RIGHT"), "moveRight");
        am.put("moveRight", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                player.walk(Direction.RIGHT);
            }
        });

        im.put(KeyStroke.getKeyStroke("released RIGHT"), "stopRight");
        am.put("stopRight", new AbstractAction() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
                player.setActionState(ActionState.IDLE);
                player.setAnimationType(AnimationType.IDLE_RIGHT);
                player.setCurrentFrameIndex(0); // Assicura il primo frame idle
            }

        });

        // Freccia sinistra - walk left
        im.put(KeyStroke.getKeyStroke("pressed LEFT"), "moveLeft");
        am.put("moveLeft", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                player.walk(Direction.LEFT);
            }
        });

        im.put(KeyStroke.getKeyStroke("released LEFT"), "stopLeft");
        am.put("stopLeft", new AbstractAction() {
        	public void actionPerformed(java.awt.event.ActionEvent e) {
                player.setActionState(ActionState.IDLE);
                player.setAnimationType(AnimationType.IDLE_LEFT);
                player.setCurrentFrameIndex(0);
            }
        });

        // Freccia su - salire (es. scale)
        im.put(KeyStroke.getKeyStroke("pressed UP"), "climbUp");
        am.put("climbUp", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
//                player.climb();
            }
        });

        // Freccia giù - scendere
        im.put(KeyStroke.getKeyStroke("pressed DOWN"), "climbDown");
        am.put("climbDown", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
//                player.climb(player.getMovement());
            }
        });

        // Spazio - salto (se vuoi implementarlo)
        im.put(KeyStroke.getKeyStroke("pressed SPACE"), "jump");
        am.put("jump", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
//                player.jump(); // Assicurati che il metodo jump() esista
            }
        });

        // ESC per uscire dal gioco
        im.put(KeyStroke.getKeyStroke("pressed ESCAPE"), "exit");
        am.put("exit", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });
    }
	
	private ArrayList<Integer> currentActiveKeys = new ArrayList<>();
	
	private void applyControls() {
		Player p = universe.getPlayer();
		if (p == null) {
			return;

		}

		
		for(Integer keycode: currentActiveKeys) {
			switch(keycode) {
				case KeyEvent.VK_UP: 	/*p.climb(p.getMovement());*/			 	break;
				case KeyEvent.VK_DOWN: 	/*p.climb(p.getMovement());*/ 				break;
				case KeyEvent.VK_RIGHT: p.walk(Direction.RIGHT);				break;
				case KeyEvent.VK_LEFT:	p.walk(Direction.LEFT);				break;
				case KeyEvent.VK_SPACE:					break;
			}
		}
		
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);      // disegna la mappa
		Graphics2D g2 = (Graphics2D) g;
//		mapPanel.paintComponent(g2);
//		playerView.draw(g2);
		
		// Calcola l’offset per centrare la mappa
		int mapWidth = GameConfigurator.U_TILE_COLS * GameConfigurator.TILE_SIZE;
		int mapHeight = GameConfigurator.U_TILE_ROWS * GameConfigurator.TILE_SIZE;
		int offsetX = GameConfigurator.MAP_OFFSET_X;
		int offsetY = GameConfigurator.MAP_OFFSET_Y;
		
		mapPanel.render(g2, offsetX, offsetY);
		playerView.draw(g2, offsetX, offsetY); // passa offset
		collisionRenderer.draw(g2, offsetX, offsetY);

		
		
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(2));
		g2.draw(GameConfigurator.MAP_BOUNDS);

	}

	
}
