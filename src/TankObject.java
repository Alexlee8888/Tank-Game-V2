import java.awt.*;
import java.awt.event.KeyEvent;

public class TankObject implements GameObject {


    private static int TURRET_DEGREES_TURNED = 1;
    private static int HULL_DEGRESS_TURNED = 2;
    private static int HULLSPEED = 6;
    private static int ANGLE = 0;
    private TankType tankType;
    private TankPartsObject tankHull;
    private TankPartsObject tankTurret;
    private Handler objectHandler;
    private long lastShootP1;
    private long lastShootP2;
    private static final long threshold = 750;


    public TankObject(int x, int y, TankType tankType, Handler objectHandler) {
        this.tankType = tankType;
        this.objectHandler = objectHandler;
        tankHull = new TankPartsObject(x, y, tankType.getHullHeight(), tankType.getHullWidth(), tankType.getHullImage(), tankType.getGameObjectType());
        tankTurret = new TankPartsObject(x, y, tankType.getTurretHeight(), tankType.getTurretWidth(), tankType.getTurretImage(), tankType.getGameObjectType());
    }

    public void updateHullAngleClockWise () {
        tankHull.setAngle(tankHull.getAngle() + HULL_DEGRESS_TURNED);
        updateTurretAngleClockWise(1);
    }

    public void updateHullAngleCounterClockWise() {
        tankHull.setAngle(tankHull.getAngle() - HULL_DEGRESS_TURNED);
        updateTurretAngleCounterClockWise(1);
    }

    public void updateTurretAngleClockWise (int option) {
        // 0 = turret degree 1 = hull degree
        switch (option) {
            case 0:
                tankTurret.setAngle(tankTurret.getAngle() + TURRET_DEGREES_TURNED);
                break;
            case 1:
                tankTurret.setAngle(tankTurret.getAngle() + HULL_DEGRESS_TURNED);
        }

    }

    public void updateTurretAngleCounterClockWise(int option) {
        // 0 = turret degree 1 = hull degree
        switch (option) {
            case 0:
                tankTurret.setAngle(tankTurret.getAngle() - TURRET_DEGREES_TURNED);
                break;
            case 1:
                tankTurret.setAngle(tankTurret.getAngle() - HULL_DEGRESS_TURNED);
        }
    }

    public void moveForward() {
        moveHull(1);
        moveTurret(1);
    }

    public void moveBackward() {
        moveHull(-1);
        moveTurret(-1);
    }

    public void moveHull(int forwardBackward) {
        double deltaX = forwardBackward * (HULLSPEED*Math.cos(Math.toRadians(tankHull.getAngle())));
        double deltaY = forwardBackward * (HULLSPEED*Math.sin(Math.toRadians(tankHull.getAngle())));
        tankHull.setX(tankHull.getX() + deltaX);
        tankHull.setY(tankHull.getY() + deltaY);
    }

    public void moveTurret(int forwardBackward) {
        double deltaX = forwardBackward * (HULLSPEED*Math.cos(Math.toRadians(tankHull.getAngle())));
        double deltaY = forwardBackward * (HULLSPEED*Math.sin(Math.toRadians(tankHull.getAngle())));
        tankTurret.setX(tankTurret.getX() + deltaX);
        tankTurret.setY(tankTurret.getY() + deltaY);
    }

    @Override
    public void tick(KeyInput keyInput) {
        if(tankType.getGameObjectType() == GameObjectType.PLAYER_ONE) {
            if(keyInput.isKey(KeyEvent.VK_UP)) {
                moveForward();
            }
            else if(keyInput.isKey(KeyEvent.VK_DOWN)) {
                moveBackward();
            }
            if(keyInput.isKey(KeyEvent.VK_D)) {
                updateTurretAngleCounterClockWise(0);
            }
            else if(keyInput.isKey(KeyEvent.VK_F)) {
                updateTurretAngleClockWise(0);
            }
            if(keyInput.isKey(KeyEvent.VK_LEFT)) {
                updateHullAngleCounterClockWise();
            }
            else if(keyInput.isKey(KeyEvent.VK_RIGHT)) {
                updateHullAngleClockWise();
            }
            long nowP1 = System.currentTimeMillis();
            if(keyInput.isKeyUp(KeyEvent.VK_SPACE)) {
                if(nowP1 - lastShootP1 > threshold) {
                    BulletObject newBullet = new BulletObject(tankTurret.getX(), tankTurret.getY(), tankTurret.getAngle(), tankType, objectHandler);
                    objectHandler.addObject(newBullet);
                    lastShootP1 = nowP1;
                }
            }
        }
        if(tankType.getGameObjectType() == GameObjectType.PLAYER_TWO) {
            if(keyInput.isKey(KeyEvent.VK_G)) {
                moveForward();
            }
            else if(keyInput.isKey(KeyEvent.VK_B)) {
                moveBackward();
            }
            if(keyInput.isKey(KeyEvent.VK_1)) {
                updateTurretAngleCounterClockWise(0);
            }
            else if(keyInput.isKey(KeyEvent.VK_2)) {
                updateTurretAngleClockWise(0);
            }
            if(keyInput.isKey(KeyEvent.VK_V)) {
                updateHullAngleCounterClockWise();
            }
            else if(keyInput.isKey(KeyEvent.VK_H)) {
                updateHullAngleClockWise();
            }


            long nowP2 = System.currentTimeMillis();
            if(keyInput.isKeyUp(KeyEvent.VK_E)) {
                if(nowP2 - lastShootP2 > threshold) {
                    BulletObject newBullet = new BulletObject(tankTurret.getX(), tankTurret.getY(), tankTurret.getAngle(), tankType, objectHandler);
                    objectHandler.addObject(newBullet);
                    lastShootP2 = nowP2;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        tankHull.render(g);
        tankTurret.render(g);


    }

    @Override
    public Rectangle getBounds() {
        return tankHull.getBounds();
    }

}
