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
        //g2d.translate(getX(), getY());
        // rotate tank

        g2d.rotate(Math.toRadians(getAngle()), getX(), getY());
        // draw tank

        g.setColor(Color.RED);

        g2d.draw(getBounds());

//        g.setColor(Color.GREEN);
//        g2d.draw(getBounds());
//        ((Graphics2D) g).scale(0.1, 0.1);
        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight(), null);
//        g.drawImage(getObjectImage(), (int) getTopLeftX(), (int) getTopLeftY(),  null);
//        g.drawRect((int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight());



    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getTopLeftX(), (int) getTopLeftY(), getWidth(), getHeight());
    }

//    public Point rotate_point(double cx,double cy,int angle,Point p)
//    {
//        double s = Math.sin(angle);
//        double c = Math.cos(angle);
//
//        // translate point back to origin:
//        ((int) (getTopLeftX() - getX())) ;
//        p.y -= cy;
//
//        // rotate point
//        double xnew = p.x * c - p.y * s;
//        double ynew = p.x * s + p.y * c;
//
//        // translate point back:
//        p.x = xnew + cx;
//        p.y = ynew + cy;
//        return p;
//    }


}
