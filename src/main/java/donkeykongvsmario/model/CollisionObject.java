package donkeykongvsmario.model;

import com.google.gson.annotations.SerializedName;

public class CollisionObject {
    private int id;
    private String name;
    private int x, y;
    private int width, height;

    @SerializedName("visible")
    private boolean isVisible;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isVisible() { return isVisible; }

    // Setters (opzionali, se non modifichi gli oggetti dopo il parsing)
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Collision[id=" + id + ", name=" + name + ", x=" + x + ", y=" + y +
               ", width=" + width + ", height=" + height + ", visible=" + isVisible + "]";
    }
}
