import java.awt.*;
import java.awt.geom.AffineTransform;

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
        g2d.translate(getX(), getY());
        // rotate tank
        g2d.rotate(Math.toRadians(getAngle()), getX(), getY());
        // draw tank
        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }


}
