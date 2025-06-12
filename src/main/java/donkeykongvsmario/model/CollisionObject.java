package donkeykongvsmario.model;

import com.google.gson.annotations.SerializedName;

public class CollisionObject {
    private int id;
    private String name;
    private float x, y;
    private float width, height;

    @SerializedName("visible")
    private boolean isVisible;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public boolean isVisible() { return isVisible; }

    // Setters (opzionali, se non modifichi gli oggetti dopo il parsing)
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Collision[id=" + id + ", name=" + name + ", x=" + x + ", y=" + y +
               ", width=" + width + ", height=" + height + ", visible=" + isVisible + "]";
    }
}
