import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class TankPartsObject extends AbstractGameObject {

    public TankPartsObject(int x, int y, int height, int width, Image image, GameObjectType gameObjectType) {
        super(x, y , height, width, image, gameObjectType);
        if(gameObjectType == GameObjectType.PLAYER_ONE) {
            setAngle(0);
        }
        else{
            setAngle(180);
        }

    }

    @Override
    public void tick(KeyInput keyInput) {

    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // draw rect

        AffineTransform identity = new AffineTransform();

        g2d.setTransform(identity);
        // translate tank
        //g2d.translate(getX(), getY());
        // rotate tank

        g.setColor(Color.RED);
        g.drawPolygon(getBounds());
        g2d.rotate(Math.toRadians(getAngle()), getX(), getY());


        // draw tank


//        g.setColor(Color.GREEN);
//        g2d.draw(getBounds());
//        ((Graphics2D) g).scale(0.1, 0.1);
        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight(), null);
//        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(),  null);
//        g.drawRect((int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight());




    }


    @Override
    public Polygon getBounds() {
        Polygon p = new Polygon();
        p.addPoint(getTopLeftX(), getTopLeftY());
        p.addPoint(getTopRightX(), getTopRightY());
        p.addPoint(getBottomRightX(), getBottomRightY());
        p.addPoint(getBottomLeftX(), getBottomLeftY());
        return p;
    }
    public void rotateBounds() {
        final double hypotenuse = Math.sqrt(Math.pow(getWidth()/2.0 , 2.0) + Math.pow(getHeight() / 2.0, 2.0));
        int angle = getAngle();



        double newDistanceX1 = Math.cos(angle) * (getTopLeftX() - getX()) - Math.sin(angle) * (getTopLeftY() - getY()) + getX();
        double newDistanceY1 = Math.sin(angle) * (getTopLeftX() - getX()) + Math.cos(angle) * (getTopLeftY() - getY()) + getY();
        double newDistanceX2 = Math.cos(angle) * (getTopRightX() - getX()) - Math.sin(angle) * (getTopRightY() - getY()) + getX();
        double newDistanceY2 = Math.sin(angle) * (getTopRightX() - getX()) + Math.cos(angle) * (getTopRightY() - getY()) + getY();
        double newDistanceX3 = Math.cos(angle) * (getBottomRightX() - getX()) - Math.sin(angle) * (getBottomRightY() - getY()) + getX();
        double newDistanceY3 = Math.sin(angle) * (getBottomRightX() - getX()) + Math.cos(angle) * (getBottomRightY() - getY()) + getY();
        double newDistanceX4 = Math.cos(angle) * (getBottomLeftX() - getX()) - Math.sin(angle) * (getBottomLeftY() - getY()) + getX();
        double newDistanceY4 = Math.sin(angle) * (getBottomLeftX() - getX()) + Math.cos(angle) * (getBottomLeftY() - getY()) + getY();
//        double newDistanceX2 = Math.sin(Math.toRadians(angle)) * hypotenuse;
//        double newDistanceY2 = Math.cos(Math.toRadians(angle)) * hypotenuse;
//        double newDistanceX3 = Math.cos(Math.toRadians(angle)) * hypotenuse;
//        double newDistanceY3 = Math.sin(Math.toRadians(angle)) * hypotenuse;
//        double newDistanceX4 = Math.sin(Math.toRadians(angle)) * hypotenuse;
//        double newDistanceY4 = Math.cos(Math.toRadians(angle)) * hypotenuse;
//        setTopLeftX((int) newDistanceX1);
//        setTopLeftY((int) newDistanceY1);
//        setTopRightX((int) newDistanceX2);
//        setTopRightY((int) newDistanceY2);
//        setBottomRightX((int) newDistanceX3);
//        setBottomRightY((int) newDistanceY3);
//        setBottomLeftX((int) newDistanceX4);
//        setBottomLeftY((int) newDistanceY4);

    }
    public void translateBounds(int speed) {
        int angle = getAngle();

        double deltaX = (speed * Math.cos(Math.toRadians(angle)));
        double deltaY = (speed * Math.sin(Math.toRadians(angle)));
        setTopLeftX((int) (getTopLeftX() + deltaX));
        setTopLeftY((int) (getTopLeftY() + deltaY));
        setTopRightX((int) (getTopRightX() + deltaX));
        setTopRightY((int) (getTopRightY() + deltaY));
        setBottomRightX((int) (getBottomRightX() + deltaX));
        setBottomRightY((int) (getBottomRightY() + deltaY));
        setBottomLeftX((int) (getBottomLeftX() + deltaX));
        setBottomLeftY((int) (getBottomLeftY() + deltaY));
    }

}
