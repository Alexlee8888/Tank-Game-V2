import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    List<GameObject> gameObjects = new LinkedList<>();
    private KeyInput keyInput;

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



}
