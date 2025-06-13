package donkeykongvsmario.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import donkeykongvsmario.model.Map;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.Universe;
import donkeykongvsmario.utils.GameConfigurator;

public class UniversePanel extends JPanel {

	private Map map;
	private MapRenderer mapPanel;
	
	private Player player;
	private PlayerView playerView;
	
	
	private Universe universe;
	/**
	 * Si occupa di gestire le view di tutte le entità di gioco.
	 */
	public UniversePanel(Universe universe) {
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
		
		setBackground(Color.BLACK);
		
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.anchor = GridBagConstraints.CENTER; // centro orizzontale e verticale
//		add(mapPanel, gbc);
		
		Timer animator = new Timer(10, e -> {
				applyControls();
				repaint();
			});
		 
		 animator.start();
	}
	
	private ArrayList<Integer> currentActiveKeys = new ArrayList<>();
	
	private void applyControls() {
		Player p = universe.getPlayer();
		if (p == null) {
			return;
		}

		if (currentActiveKeys.isEmpty()) {
			p.idle();
			return;
		}

		
		for(Integer keycode: currentActiveKeys) {
			switch(keycode) {
				case KeyEvent.VK_UP: 	p.climb(p.getMovement());			 	break;
				case KeyEvent.VK_DOWN: 	p.climb(p.getMovement()); 				break;
				case KeyEvent.VK_RIGHT: p.walk(p.getDirection());				break;
				case KeyEvent.VK_LEFT:	p.walk(p.getDirection());				break;
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
		int offsetX = (getWidth() - mapWidth) / 2;
		int offsetY = (getHeight() - mapHeight) / 2;
		
		mapPanel.render(g2, offsetX, offsetY);
//		g2.translate(offsetX, offsetY);
		playerView.draw(g2, offsetX, offsetY);
		
//		g2.translate(-offsetX, -offsetY);
	}

	
}
