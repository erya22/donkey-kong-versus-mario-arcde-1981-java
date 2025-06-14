package donkeykongvsmario.utils;
import com.google.gson.*;
import java.io.FileReader;
import java.util.ArrayList;

public class CollisionLayerParser {

    public static int[][] parseCollisionLayer(String jsonFilePath, int tileSize, int mapWidthInTiles, int mapHeightInTiles) {
        int[][] collisionMatrix = new int[mapHeightInTiles][mapWidthInTiles];

        try {
            JsonObject root = JsonParser.parseReader(new FileReader(jsonFilePath)).getAsJsonObject();
            JsonArray layers = root.getAsJsonArray("layers");

            for (JsonElement layerElement : layers) {
                JsonObject layer = layerElement.getAsJsonObject();
                String layerName = layer.get("name").getAsString();

                if (layerName.equals("Collision")) {
                    JsonArray objects = layer.getAsJsonArray("objects");

                    for (JsonElement objElement : objects) {
                        JsonObject obj = objElement.getAsJsonObject();
                        float x = obj.get("x").getAsFloat();
                        float y = obj.get("y").getAsFloat();
                        float width = obj.get("width").getAsFloat();
                        float height = obj.get("height").getAsFloat();

                        int startX = (int)(x / tileSize);
                        int startY = (int)(y / tileSize);
                        int widthInTiles = (int)(width / tileSize);
                        int heightInTiles = (int)(height / tileSize);

                        for (int row = startY; row < startY + heightInTiles; row++) {
                            for (int col = startX; col < startX + widthInTiles; col++) {
                                if (row >= 0 && row < mapHeightInTiles && col >= 0 && col < mapWidthInTiles) {
                                    collisionMatrix[row][col] = 1;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return collisionMatrix;
    }
}
