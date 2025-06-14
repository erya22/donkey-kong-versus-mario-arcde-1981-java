package donkeykongvsmario.view;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import donkeykongvsmario.model.Collision;

public class CollisionRenderer {
    private List<Collision> collisions;

    public CollisionRenderer(List<Collision> collisions) {
        this.collisions = collisions;
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        g.setColor(new Color(255, 0, 0, 128)); // rosso semi-trasparente
        for (Collision c : collisions) {
            if (c.isVisible()) {
                g.fillRect(c.getX() + offsetX, c.getY() + offsetY, c.getWidth(), c.getHeight());
                g.setColor(Color.BLACK);
                g.drawRect(c.getX()+ offsetX, c.getY()+offsetY, c.getWidth(), c.getHeight());
                g.setColor(new Color(255, 0, 0, 128));
            }
        }
    }
}
