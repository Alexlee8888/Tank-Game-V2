import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

public class BulletObject implements GameObject {
    private static final int BULLET_SPEED = 12;
    private static int PLAYER_ONE_TANK_OBJECT_INDEX = 0;
    private static int PLAYER_TWO_TANK_OBJECT_INDEX = 1;

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


    public BulletObject(double x, double y, int angle, TankType tankType, Handler objectHandler) {
        bulletImage = tankType.getBulletImage();
        this.x = x;
        this.y = y;
        this.hitPointX = x + tankType.getTurretWidth() / 2;
        this.hitPointY = y;
        this.angle = angle;
        this.tankType = tankType;
        this.objectHandler = objectHandler;
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        removeBullet = true;
                    }
                },
                1000
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
        }

    }

    public void checkCollisions() {
        Rectangle playerOneGetBounds = objectHandler.gameObjects.get(PLAYER_ONE_TANK_OBJECT_INDEX).getBounds();
        Rectangle playerTwoGetBounds = objectHandler.gameObjects.get(PLAYER_TWO_TANK_OBJECT_INDEX).getBounds();

//        if (playerOneGetBounds.getX() < getHitPoint().getX()
//                && playerOneGetBounds.getX() + playerOneGetBounds.getWidth() > getHitPoint().getX()
//                && playerOneGetBounds.getY() < getHitPoint().getY()
//                && playerOneGetBounds.getY() + playerOneGetBounds.getHeight() > getHitPoint().getY()) {
//            explodeBullet();
////                gameObjects.remove(bulletObject);
//            System.out.println("HIT P1 : bounds " + playerOneGetBounds + ", hitPoint:" + bulletObject.getHitPoint());
//
//        }
//        if (playerTwoGetBounds.getX() < bulletObject.getHitPoint().getX()
//                && playerTwoGetBounds.getX() + playerTwoGetBounds.getWidth() > bulletObject.getHitPoint().getX()
//                && playerTwoGetBounds.getY() < bulletObject.getHitPoint().getY()
//                && playerTwoGetBounds.getY() + playerTwoGetBounds.getHeight() > bulletObject.getHitPoint().getY()) {
//            bulletObject.explodeBullet();
////                gameObjects.remove(bulletObject);
//            System.out.println("HIT P2 : bounds " + playerTwoGetBounds + ", hitPoint:" + bulletObject.getHitPoint());
//        }

            if(playerOneGetBounds.contains(getHitPoint())) {
                explodeBullet();
//                gameObjects.remove(bulletObject);
//                System.out.println("HIT P1 : bounds " + playerOneGetBounds + ", hitPoint:" + getHitPoint());
//                System.out.println("----- x = " + playerOneGetBounds.getX() + " < " + getHitPoint().x + " < " + (playerOneGetBounds.getX() + playerOneGetBounds.getWidth()));
//                System.out.println("----- y = " + playerOneGetBounds.getY() + " < " + getHitPoint().y + " < " + (playerOneGetBounds.getY() + playerOneGetBounds.getHeight()));

            }
            else if(playerTwoGetBounds.contains(getHitPoint())) {
                explodeBullet();
//                gameObjects.remove(bulletObject);
//                System.out.println("HIT P2 : bounds " + playerTwoGetBounds + ", hitPoint:" + getHitPoint());
//                System.out.println("----- x = " + playerTwoGetBounds.getX() + " < " + getHitPoint().x + " < " + (playerTwoGetBounds.getX() + playerTwoGetBounds.getWidth()));
//                System.out.println("----- y = " + playerTwoGetBounds.getY() + " < " + getHitPoint().y + " < " + (playerTwoGetBounds.getY() + playerTwoGetBounds.getHeight()));


            }


    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw rect
        AffineTransform identity = new AffineTransform();
        g2d.setTransform(identity);
        // translate tank
//        g2d.translate(x, y);
        // rotate tank
        g2d.rotate(Math.toRadians(angle), x, y);
        // draw tank
        g.fillOval((int) (hitPointX) - 4, (int) (hitPointY) - 4, 8, 8);
        g.drawRect(((int) x - (tankType.getTurretWidth()) / 2), (int) (y - (tankType.getTurretHeight()) / 2), tankType.getTurretWidth(), tankType.getTurretHeight());
        g.drawImage(bulletImage, ((int) x - (tankType.getTurretWidth()) / 2), (int) (y - (tankType.getTurretHeight()) / 2), tankType.getTurretWidth(), tankType.getTurretHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public Point getHitPoint() {
        return new Point((int) (hitPointX), (int) (hitPointY));
    }

    public void explodeBullet() {
        isExploding = true;
        isBulletMoving = false;
        Image bullet_explosion_1 = Toolkit.getDefaultToolkit().getImage("bullet_explosion_1.png");
        setBulletImage(bullet_explosion_1);
    }

    public void setBulletImage(Image image) {
        bulletImage = image;
    }


}
