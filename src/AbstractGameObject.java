import java.awt.*;

public abstract class AbstractGameObject implements GameObject {

    private double x, y;
    private int angle = 0;
    private int height;
    private int width;
    private int topLeftX;
    private int topLeftY;
    private int topRightX;
    private int topRightY;
    private int bottomLeftX;
    private int bottomLeftY;
    private int bottomRightX;
    private int bottomRightY;

    private Image objectImage;
    private GameObjectType gameObjectType;

    public AbstractGameObject(int x, int y, int height, int width, Image image, GameObjectType gameObjectType) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.objectImage = image;
        this.gameObjectType = gameObjectType;
        this.topLeftX = x - (width/2);
        this.topLeftY = y - (height/2);
        this.topRightX = x + (width/2);
        this.topRightY = y - (height/2);
        this.bottomLeftX = x - (width/2);
        this.bottomLeftY = y + (height/2);
        this.bottomRightX = x + (width/2);
        this.bottomRightY = y + (height/2);
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

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getTopRightX() {
        return topRightX;
    }

    public int getTopRightY() {
        return topRightY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }

    public int getBottomLeftX() {
        return bottomLeftX;
    }

    public int getBottomLeftY() {
        return bottomLeftY;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public void setTopRightX(int topRightX) {
        this.topRightX = topRightX;
    }

    public void setTopRightY(int topRightY) {
        this.topRightY = topRightY;
    }

    public void setBottomRightX(int bottomRightX) {
        this.bottomRightX = bottomRightX;
    }

    public void setBottomRightY(int bottomRightY) {
        this.bottomRightY = bottomRightY;
    }

    public void setBottomLeftX(int bottomLeftX) {
        this.bottomLeftX = bottomLeftX;
    }

    public void setBottomLeftY(int bottomLeftY) {
        this.bottomLeftY = bottomLeftY;
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
