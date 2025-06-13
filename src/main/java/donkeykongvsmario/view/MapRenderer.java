package donkeykongvsmario.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import donkeykongvsmario.model.Layer;
import donkeykongvsmario.model.Map;
import donkeykongvsmario.utils.GameConfigurator;

public class MapRenderer {
	private Map map;
	
	//prima era un JPanel
	public MapRenderer(Map map) {
		this.map = map;
//		setBackground(Color.BLACK);
//		int width = GameConfigurator.U_TILE_COLS * GameConfigurator.TILE_SIZE;
//	    int height = GameConfigurator.U_TILE_ROWS * GameConfigurator.TILE_SIZE;
//	    setPreferredSize(new java.awt.Dimension(width, height));
	}

	
	public void render(Graphics g, int offsetX, int offsetY) {
	    for (Layer layer : map.getTileMap().getLayers()) {
	        if (!"tilelayer".equals(layer.getType())) continue;

	        int[] data = layer.getData();
	        int width = map.getTileMap().getWidth();
	        int height = map.getTileMap().getHeight();

	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                int tileId = data[y * width + x];
	                if (tileId > 0) {
	                    BufferedImage tileImage = map.getTiles()[tileId - 1]; 
	                    g.drawImage(
	                        tileImage,
	                        offsetX + x * GameConfigurator.TILE_SIZE,
	                        offsetY + y * GameConfigurator.TILE_SIZE,
	                        GameConfigurator.TILE_SIZE,
	                        GameConfigurator.TILE_SIZE,
	                        null
	                    );
	                }
	            }
	        }
	    }
	}

	
	
//	public void render(Graphics g) {
//	    	
//	        for (Layer layer : map.getTileMap().getLayers()) {
//	            if (!"tilelayer".equals(layer.getType())) continue;
//	
//	            int[] data = layer.getData();
//	            int width = map.getTileMap().getWidth();
//	            int height = map.getTileMap().getHeight();
//	
//	            for (int y = 0; y < height; y++) {
//	                for (int x = 0; x < width; x++) {
//	                    int tileId = data[y * width + x];
//	                    if (tileId > 0) {
//	                    	BufferedImage tileImage = map.getTiles()[tileId - 1]; 
//	                    	g.drawImage(tileImage, x * GameConfigurator.TILE_SIZE, y * GameConfigurator.TILE_SIZE, 
//	                                GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE, null);
//	                    }
//	                }
//	            }
//	        }
//	    }
//	
//	@Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        render(g);
//    }

}


