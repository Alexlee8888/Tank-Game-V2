import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<GameObject> hittableObjects = new ArrayList<>();
    private KeyInput keyInput;
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;

    public Handler(KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    public void tick() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick(keyInput);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);
        }
    }

    public void addHittableObject(GameObject gameObject) {
        hittableObjects.add(gameObject);
    }

    public void removeHittableObject(GameObject gameObject) {
        hittableObjects.remove(gameObject);
    }

    public List<GameObject> getHittableObjects() {
        return hittableObjects;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setTankIndexes() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if(gameObjects.get(i)instanceof TankObject) {
                PLAYER_ONE = i;
                PLAYER_TWO = ++i;
                break;
            }
        }
    }

}
