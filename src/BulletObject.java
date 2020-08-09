import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

public class BulletObject implements GameObject {

    private static final int BULLET_SPEED = 12;
    //    private static final int PLAYER_ONE_TANK_OBJECT_INDEX = 1;
//    private static final int PLAYER_TWO_TANK_OBJECT_INDEX = 2;
    private static final int BULLET_OFFSET = 35 / 2;

    private TankType tankType;
    private Handler objectHandler;
    private double x;
    private double y;
    private int angle;
    private boolean removeBullet = false;
    private double hitPointX;
    private double hitPointY;

    private Image bulletImage;
    private boolean isBulletMoving = true;
    private boolean isExploding = false;

    private int countStatusTick = 0;
    private int statusOfExplosions = 1;


    public BulletObject(double x, double y, int angle, TankType tankType, Handler objectHandler) {
        bulletImage = tankType.getBulletImage();
        this.tankType = tankType;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.hitPointX = x + Math.cos(Math.toRadians(angle)) * ((tankType.getTurretWidth() / 2.0) - BULLET_OFFSET);
        this.hitPointY = y + Math.sin(Math.toRadians(angle)) * ((tankType.getTurretWidth() / 2.0) - BULLET_OFFSET);

        this.objectHandler = objectHandler;
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        removeBullet = true;
                    }
                },
                900
        );
    }

    public void moveBullet() {
        double deltaX = (BULLET_SPEED * Math.cos(Math.toRadians(angle)));
        double deltaY = (BULLET_SPEED * Math.sin(Math.toRadians(angle)));
        hitPointX += deltaX;
        hitPointY += deltaY;
        x += deltaX;
        y += deltaY;
        if (removeBullet) {
            objectHandler.removeObject(this);
        }
    }

    @Override
    public void tick(KeyInput keyInput) {
        checkCollisions();
        if (isBulletMoving) {
            moveBullet();
        } else if (countStatusTick % 5 == 0 && isExploding) {
            Image bullet_explosion = Toolkit.getDefaultToolkit().getImage("chris_bullet_explosion" + statusOfExplosions + ".png");
//            Image bullet_explosion = Toolkit.getDefaultToolkit().getImage("chris_tank_supercharge_bullet" + statusOfExplosions + ".png");

            setBulletImage(bullet_explosion);
            statusOfExplosions++;
        } else if (countStatusTick > 120 && isExploding) {
            objectHandler.removeObject(this);
        }

        countStatusTick++;

    }

    public void checkCollisions() {
        for (int i = 0; i < objectHandler.getHittableObjects().size(); i++) {
            GameObject gameObject = objectHandler.getHittableObjects().get(i);
            if (gameObject instanceof TankObject && gameObject.getPolygonBounds().contains(getHitPoint())) {

                if (!isExploding) {
                    ((TankObject) gameObject).takeDamage();
                    GameSounds.playExplosionsBlast2();
                }
                explodeBullet();
            } else if (gameObject.getBounds().contains(getHitPoint())) {

                if (!isExploding) {
                    GameSounds.chooseAndPlayExplosion();
                }
                explodeBullet();
            }
        }
    }

    @Override
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // draw rect
        AffineTransform identity = new AffineTransform();
        identity = new AffineTransform();


        g2d.setTransform(identity);
        // translate tank
//        g2d.translate(x, y);
        // rotate tank
        g2d.rotate(Math.toRadians(angle), x, y);


        // draw tank
//        g.drawRect(((int) x - (tankType.getTurretWidth()) / 2), (int) (y - (tankType.getTurretHeight()) / 2), tankType.getTurretWidth(), tankType.getTurretHeight());
        g.drawImage(bulletImage, ((int) x - (tankType.getTurretWidth()) / 2), (int) (y - (tankType.getTurretHeight()) / 2), tankType.getTurretWidth(), tankType.getTurretHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Polygon getPolygonBounds() {
        return null;
    }

    public Point getHitPoint() {
        return new Point((int) (hitPointX), (int) (hitPointY));
    }

    public void explodeBullet() {
        isBulletMoving = false;

        isExploding = true;

    }

    public void setBulletImage(Image image) {
        bulletImage = image;
    }


}
