import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    List<GameObject> gameObjects = new ArrayList<>();
    private KeyInput keyInput;
    private static int PLAYER_ONE_TANK_OBJECT_INDEX = 0;
    private static int PLAYER_TWO_TANK_OBJECT_INDEX = 1;
    private int scoreP1 = 0;
    private int scoreP2 = 0;

    public Handler(KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    public void tick() {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick(keyInput);
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void checkCollisions() {
        Rectangle playerOneGetBounds = gameObjects.get(PLAYER_ONE_TANK_OBJECT_INDEX).getBounds();
        Rectangle playerTwoGetBounds = gameObjects.get(PLAYER_TWO_TANK_OBJECT_INDEX).getBounds();
        for(int i = 2; i < gameObjects.size(); i++) {
            BulletObject bulletObject = (BulletObject) gameObjects.get(i);
            if(playerOneGetBounds.contains(bulletObject.getHitPoint())) {
                bulletObject.explodeBullet();
                gameObjects.remove(bulletObject);
                scoreP2++;

                System.out.println(scoreP1 + ", " + scoreP2);

            }
            else if(playerTwoGetBounds.contains(bulletObject.getHitPoint())) {
                bulletObject.explodeBullet();
                gameObjects.remove(bulletObject);
                scoreP1++;
                System.out.println(scoreP1 + ", " + scoreP2);
            }

        }
    }
}
