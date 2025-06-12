package donkeykongvsmario.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import donkeykongvsmario.model.Map;
import donkeykongvsmario.model.Player;
import donkeykongvsmario.model.Universe;

public class UniversePanel extends JPanel {

	private Map map;
	private MapRenderer mapPanel;
	
	private PlayerView playerView;
	
	/**
	 * Si occupa di gestire le view di tutte le entità di gioco.
	 */
	public UniversePanel() {
		setBackground(Color.BLACK);
		setLayout(new GridBagLayout());
		
		this.map = new Map();
		this.mapPanel = new MapRenderer(map);
		
		 GridBagConstraints gbc = new GridBagConstraints();
		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 gbc.anchor = GridBagConstraints.CENTER; // centro orizzontale e verticale
		 add(mapPanel, gbc);
		 
		 Universe universe = new Universe();
		 this.playerView = new PlayerView(universe.getPlayer());
	}
	
	public void addComponents() {
	    this.add(playerView);
	}

	
	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);  
	}

	
}
