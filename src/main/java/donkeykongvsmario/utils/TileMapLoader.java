package donkeykongvsmario.utils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import donkeykongvsmario.model.Layer;
import donkeykongvsmario.model.Map;
import donkeykongvsmario.model.TileMap;

public class TileMapLoader {
	private static final Logger log = LoggerFactory.getLogger(TileMapLoader.class);

    public static TileMap loadMap() {
        try (InputStream jsonStream = TileMapLoader.class.getResourceAsStream("/MAPPA32/JSON/mappa25m_collisioni.json")) {
            if (jsonStream == null) throw new FileNotFoundException("File JSON non trovato!");
            Reader reader = new InputStreamReader(jsonStream);
            return new Gson().fromJson(reader, TileMap.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage loadTileset() {
        try (InputStream imageStream = TileMapLoader.class.getResourceAsStream("/MAPPA32/TILESET/TraviScale.png")) {
            if (imageStream == null) throw new FileNotFoundException("Tileset non trovato!");
            return ImageIO.read(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean[][] loadCollisionMap(Map map) {
        int width = map.getTileMap().getWidth();
        int height = map.getTileMap().getHeight();

        boolean[][] collisionMap = new boolean[height][width];

        //TODO: COLLISION NON E' UN ARRAY
        int[] flatData = map.getTileMap().getLayers().get(2).getData();
        
        for (int y = 0; y < height; y++) {
        	for (int x = 0; x < width; x++) {
        		int index = y * width + x;
        		if (index < flatData.length && flatData[index] != 0) {
        			collisionMap[y][x] = true;
        			log.info("{}",collisionMap[y][x]);
        		}
        	}
        }
      


        return collisionMap;
    }



}
