import java.awt.*;
import java.awt.geom.AffineTransform;

public class TankPartsObject extends AbstractGameObject {

    public TankPartsObject(int x, int y, int height, int width, Image image, GameObjectType gameObjectType) {
        super(x, y, height, width, image, gameObjectType);
        if (gameObjectType == GameObjectType.PLAYER_ONE) {
            setAngle(0);
        } else {
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


        g.setColor(Color.RED);
//        g2d.fillOval((int) (getTopLeftX() - 4), (int) (getTopLeftY() - 4), 8, 8);

        // translate tank
        //g2d.translate(getCenterX(), getCenterY());
        // rotate tank


//        g2d.draw(getPolygonBounds());

        g2d.rotate(Math.toRadians(getAngle()), getCenterX(), getCenterY());


        // draw tank


//        g.setColor(Color.GREEN);
//        g2d.draw(getBounds());
//        ((Graphics2D) g).scale(0.1, 0.1);
//        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(),  null);
//        g.drawRect((int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight());
        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight(), null);
//        g2d.dispose();
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight());
    }

    @Override
    public Polygon getPolygonBounds() {
        Polygon p = new Polygon();
        p.addPoint((int) getTopLeftX(), (int) getTopLeftY());
        p.addPoint((int) getTopLeftX() + getWidth(), (int) getTopLeftY());
        p.addPoint((int) getTopLeftX() + getWidth(), (int) getTopLeftY() + getHeight());
        p.addPoint((int) getTopLeftX(), (int) getTopLeftY() + getHeight());
        return p;
    }

    public void rotatePoint(int deltaAngle) {
        double sin = Math.sin(Math.toRadians(getAngle()));
        double cos = Math.cos(Math.toRadians(getAngle()));

        // translate point back to origin:
        setTopLeftX((int) (getTopLeftX() - getCenterX()));
        setTopLeftY((int) (getTopLeftY() - getCenterY()));

        double oldX = getTopLeftX();
        double oldY = getTopLeftY();

        // rotate point
        double newX = getTopLeftX() * cos - getTopLeftY() * sin;
        double newY = getTopLeftX() * sin + getTopLeftY() * cos;

        // translate point back:
        setTopLeftX((int) (newX + getCenterX()));
        setTopLeftY((int) (newY + getCenterY()));

//        double newTopLeftX = (getCenterX() + Math.cos(Math.toRadians(getAngle() + 180 - Math.atan(((getHeight() / 2.0) / getWidth() / 2.0))) * ((getWidth() / 2.0))));
//        double newTopLeftY = (getCenterY() + Math.sin(Math.toRadians(getAngle() + 180 - Math.atan(((getHeight() / 2.0) / getWidth() / 2.0))) * ((getWidth() / 2.0))));
//        setTopLeftX((int) newTopLeftX);
//        setTopLeftX((int) newTopLeftY);

//        System.out.println("newX: " + (int) (newX + getCenterX()) + "newY: " + (int) (newY + (getCenterY())));
    }


}
