import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    private List<GameObject> gameObjects = new ArrayList<>();
    private KeyInput keyInput;

    private int scoreP1 = 0;
    private int scoreP2 = 0;

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

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }


}
