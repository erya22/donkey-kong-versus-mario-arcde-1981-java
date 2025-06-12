package donkeykongvsmario.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import donkeykongvsmario.model.CollisionObject;
import donkeykongvsmario.model.Layer;

public class CollisionLoader {
    
	
	
	public static List<CollisionObject> loadCollisionMap() {
	    try (InputStream jsonStream = TileMapLoader.class.getResourceAsStream("/MAPPA32/JSON/mappa25m_collisioni.json")) {
	        if (jsonStream == null) throw new FileNotFoundException("File JSON non trovato!");

	        Reader reader = new InputStreamReader(jsonStream);
	        Gson gson = new Gson();

	        // Leggo l'intera mappa come JsonObject
	        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

	        // Ottengo l'array dei layer
	        JsonArray layers = jsonObject.getAsJsonArray("layers");

	        for (JsonElement layerElement : layers) {
	            JsonObject layer = layerElement.getAsJsonObject();

	            // Controllo che sia un object layer
	            if ("objectgroup".equals(layer.get("type").getAsString())) {
	                // Ottengo l'array degli oggetti da quel layer
	                JsonArray objectsArray = layer.getAsJsonArray("objects");

	                // Parsifico l'array degli oggetti in List<CollisionObject>
	                Type listType = new TypeToken<List<CollisionObject>>(){}.getType();
	                List<CollisionObject> collisionObjects = gson.fromJson(objectsArray, listType);

	                // Ora puoi restituire o usare collisionObjects
	                return collisionObjects;
	            }
	        }

	        // Se non trovi layer objectgroup
	        return null;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
