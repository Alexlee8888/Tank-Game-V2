import java.awt.*;
import java.awt.geom.AffineTransform;

public class BulletObject implements GameObject {
    private static final int BULLET_SPEED = 7;
    private double x;
    private double y;
    private int angle;
    private TankType tankType;

    public BulletObject(double x, double y, int angle, TankType tankType) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.tankType = tankType;
    }

    public void moveBullet() {
        double deltaX = (BULLET_SPEED*Math.cos(Math.toRadians(angle)));
        double deltaY = (BULLET_SPEED*Math.sin(Math.toRadians(angle)));
        x += deltaX;
        y += deltaY;
    }

    @Override
    public void tick(KeyInput keyInput) {
        moveBullet();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw rect
        AffineTransform identity = new AffineTransform();
        g2d.setTransform(identity);
        // translate tank
        g2d.translate(x, y);
        // rotate tank
        g2d.rotate(Math.toRadians(angle), x, y);
        // draw tank
        g.drawImage(tankType.getBulletImage(),((int) x - (tankType.getWidth()/2)), (int) (y - (tankType.getHeight()/2)), tankType.getWidth(), tankType.getHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}