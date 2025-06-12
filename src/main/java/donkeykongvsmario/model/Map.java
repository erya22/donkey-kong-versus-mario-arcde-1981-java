package donkeykongvsmario.model;

import java.awt.image.BufferedImage;

import donkeykongvsmario.utils.GameConfigurator;
import donkeykongvsmario.utils.TileMapLoader;
import donkeykongvsmario.utils.TileUtils;

public class Map {
	BufferedImage tileset;
	private BufferedImage[] tiles;
	private TileMap tileMap;
	
	private int uTileWidth;
	private int uTileHeight;
	private int tileSize;
	
	public Map() {
		this.tileset = TileMapLoader.loadTileset();
		this.uTileWidth = GameConfigurator.U_TILE_SIZE;
		this.uTileHeight = GameConfigurator.U_TILE_SIZE;
		this.tileSize = GameConfigurator.TILE_SIZE;
		this.tiles = TileUtils.loadTiles(tileset, uTileWidth, uTileHeight, tileSize);
		this.tileMap = TileMapLoader.loadMap();
	}

	public BufferedImage getTileset() {
		return tileset;
	}

	public void setTileset(BufferedImage tileset) {
		this.tileset = tileset;
	}

	public BufferedImage[] getTiles() {
		return tiles;
	}

	public void setTiles(BufferedImage[] tiles) {
		this.tiles = tiles;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}
	
	
}
