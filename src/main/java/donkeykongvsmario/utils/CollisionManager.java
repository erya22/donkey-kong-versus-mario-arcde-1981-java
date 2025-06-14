package donkeykongvsmario.utils;
import java.util.ArrayList;
import java.util.List;

import donkeykongvsmario.model.Collision;

public class CollisionManager {
    private static List<Collision> collisions = new ArrayList<>();

    public static List<Collision> loadSampleCollisions() {
        collisions.add(new Collision(2, 0 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(4, 4 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 2, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(5, 8 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 4, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(6, 12 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 6, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(7, 16 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 8, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(8, 20 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 10, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(9, 24 * GameConfigurator.TILE_SIZE, 31 * GameConfigurator.TILE_SIZE - 12, GameConfigurator.TILE_SIZE * 4 , GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(10, 20 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(11, 16 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE + 2, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(12, 12 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE - 4, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(13, 8 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE - 6, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(14, 4 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE - 8, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(15, 0 * GameConfigurator.TILE_SIZE, 27 * GameConfigurator.TILE_SIZE - 10, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(16, 4 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(17, 8 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE-2, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(18, 12 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE-4, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(19, 16 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE-6, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(20, 20 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE-8, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(21, 24 * GameConfigurator.TILE_SIZE, 23 * GameConfigurator.TILE_SIZE-10, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(22, 0 * GameConfigurator.TILE_SIZE, 19 * GameConfigurator.TILE_SIZE - 10, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(23, 20 * GameConfigurator.TILE_SIZE, 19 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(24, 16 * GameConfigurator.TILE_SIZE, 19 * GameConfigurator.TILE_SIZE - 2, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(25, 12 * GameConfigurator.TILE_SIZE, 19 * GameConfigurator.TILE_SIZE - 4, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(26, 8 * GameConfigurator.TILE_SIZE,  19 * GameConfigurator.TILE_SIZE - 6, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(27, 4 * GameConfigurator.TILE_SIZE, 19 *GameConfigurator.TILE_SIZE - 8, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(28, 4 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(29, 8 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE - 2, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(30, 12 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE - 4, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(31, 16 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE - 6, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(33, 20 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE - 8, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(34, 24 * GameConfigurator.TILE_SIZE, 15 * GameConfigurator.TILE_SIZE - 10, GameConfigurator.TILE_SIZE * 4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(35, 10 * GameConfigurator.TILE_SIZE, 7 * GameConfigurator.TILE_SIZE, GameConfigurator.TILE_SIZE*7, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(36, 0 * GameConfigurator.TILE_SIZE, 11 * GameConfigurator.TILE_SIZE - 12, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(37, 4 * GameConfigurator.TILE_SIZE, 11 * GameConfigurator.TILE_SIZE - 10, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(38, 8 * GameConfigurator.TILE_SIZE, 11 * GameConfigurator.TILE_SIZE - 8, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(39, 12 * GameConfigurator.TILE_SIZE, 11 * GameConfigurator.TILE_SIZE - 4, GameConfigurator.TILE_SIZE*4, GameConfigurator.TILE_SIZE, true));
        collisions.add(new Collision(40, 16 * GameConfigurator.TILE_SIZE, 11 * GameConfigurator.TILE_SIZE - 2, GameConfigurator.TILE_SIZE*8, GameConfigurator.TILE_SIZE, true));
        return collisions;
    }

    public List<Collision> getCollisions() {
        return collisions;
    }
}
