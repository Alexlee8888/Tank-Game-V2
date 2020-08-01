import java.awt.*;

public abstract class AbstractGameObject implements GameObject {

    private double x, y;
    private int angle = 0;
    private int height;
    private int width;
    private Image objectImage;
    private GameObjectType gameObjectType;

    public AbstractGameObject(int x, int y, int height, int width, Image image, GameObjectType gameObjectType) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.objectImage = image;
        this.gameObjectType = gameObjectType;
    }



    public Image getObjectImage() {
        return objectImage;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getAngle() {
        return angle;
    }

    public double getTopLeftX() {
        return x - (width/2);
    }

    public double getTopLeftY() {
        return y - (height/2);
    }

    public void setGameObjectType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAngle(int angle) {
        this.angle = angle % 360;
    }
}
