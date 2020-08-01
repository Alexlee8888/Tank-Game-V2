import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class TankObject implements GameObject {


    private static int TURRET_DEGREES_TURNED = 3;
    private static int HULL_DEGRESS_TURNED = 2;
    private static int HULLSPEED = 4;
    private static int ANGLE = 0;
    private TankType tankType;
    private TankPartsObject tankHull;
    private TankPartsObject tankTurret;
    private Handler objectHandler;


    public TankObject(int x, int y, TankType tankType, Handler objectHandler) {
        this.tankType = tankType;
        this.objectHandler = objectHandler;
        tankHull = new TankPartsObject(x, y, tankType.getHeight(), tankType.getWidth(), tankType.getHullImage(), tankType.getGameObjectType());
        tankTurret = new TankPartsObject(x, y, tankType.getHeight(), tankType.getWidth(), tankType.getTurretImage(), tankType.getGameObjectType());
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
            if(keyInput.isKey(KeyEvent.VK_Z)) {
                updateTurretAngleCounterClockWise(0);
            }
            else if(keyInput.isKey(KeyEvent.VK_X)) {
                updateTurretAngleClockWise(0);
            }
            if(keyInput.isKey(KeyEvent.VK_LEFT)) {
                updateHullAngleCounterClockWise();
            }
            else if(keyInput.isKey(KeyEvent.VK_RIGHT)) {
                updateHullAngleClockWise();
            }
            if(keyInput.isKeyUp(KeyEvent.VK_SPACE)){
                BulletObject newBullet = new BulletObject(tankHull.getX(), tankHull.getY(), tankTurret.getAngle(), tankType);
                objectHandler.addObject(newBullet);
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
        return null;
    }

}
