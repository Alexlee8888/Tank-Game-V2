import java.awt.*;

public abstract class AbstractGameObject implements GameObject {

    private double centerX, centerY;
    private int angle = 0;
    private int height;
    private int width;
    private Image objectImage;
    private GameObjectType gameObjectType;
    private int topLeftX;
    private int topLeftY;
    private int topRightX;
    private int topRightY;
    private int bottomLeftX;
    private int bottomLeftY;
    private int bottomRightX;
    private int bottomRightY;



    public AbstractGameObject(int centerX, int centerY, int height, int width, Image image, GameObjectType gameObjectType) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.height = height;
        this.width = width;
        this.objectImage = image;
        this.gameObjectType = gameObjectType;
        this.topLeftX = centerX - width/2;
        this.topLeftY = centerY - height/2;
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

    public void setBottomLeftX(int bottomLeftX) {
        this.bottomLeftX = bottomLeftX;
    }

    public void setBottomLeftY(int bottomLeftY) {
        this.bottomLeftY = bottomLeftY;
    }

    public void setBottomRightX(int bottomRightX) {
        this.bottomRightX = bottomRightX;
    }

    public void setBottomRightY(int bottomRightY) {
        this.bottomRightY = bottomRightY;
    }

    public double getTopLeftX() {
        topLeftX = (int) (centerX - width/2);
        return topLeftX;
    }

    public double getTopLeftY() {
        topLeftY = (int) (centerY - height/2);
        return topLeftY;
    }

    public int getTopRightX() {
        return topRightX;
    }

    public int getTopRightY() {
        return topRightY;
    }

    public int getBottomLeftX() {
        return bottomLeftX;
    }

    public int getBottomLeftY() {
        return bottomLeftY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }


    public Image getObjectImage() {
        return objectImage;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
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

    public void setGameObjectType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
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
