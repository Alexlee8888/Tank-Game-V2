import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

public class TankObject implements GameObject {


    private static int TURRET_DEGREES_TURNED = 1;
    private static int HULL_DEGRESS_TURNED = 2;
    private static int HULLSPEED = 3;
    private static int ANGLE = 0;
    private TankType tankType;
    private TankPartsObject tankHull;
    private TankPartsObject tankTurret;
    private Handler objectHandler;
    private long lastShootP1;
    private long lastShootP2;
    private static final long threshold = 600;
    private int hullSpeed;
    private boolean canMoveForward = true;
    private boolean canMoveBackward = true;
    private boolean canTurnLeft = true;
    private boolean canTurnRight = true;
    public int lastKeyPressed;


    public TankObject(int x, int y, TankType tankType, Handler objectHandler) {
        this.tankType = tankType;
        this.objectHandler = objectHandler;
        this.hullSpeed = HULLSPEED;
        tankHull = new TankPartsObject(x, y, tankType.getHullHeight(), tankType.getHullWidth(), tankType.getHullImage(), tankType.getGameObjectType());
        tankTurret = new TankPartsObject(x, y, tankType.getTurretHeight(), tankType.getTurretWidth(), tankType.getTurretImage(), tankType.getGameObjectType());
    }

    public void setMoveForward(boolean bool) {
        canMoveForward = bool;
    }

    public void setMoveBackward (boolean bool){
        canMoveBackward = bool;
    }

    public void setTurnLeft(boolean bool) {
        canTurnLeft = bool;
    }

    public void setTurnRight(boolean bool) {
        canTurnRight = bool;
    }

    public void updateHullAngleClockWise () {
        tankHull.setAngle(tankHull.getAngle() + HULL_DEGRESS_TURNED);
        updateTurretAngleClockWise(1);
        tankHull.rotateBounds();
    }

    public void updateHullAngleCounterClockWise() {
        tankHull.setAngle(tankHull.getAngle() - HULL_DEGRESS_TURNED);
        updateTurretAngleCounterClockWise(1);
        tankTurret.rotateBounds();
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
        double deltaX = forwardBackward * (hullSpeed*Math.cos(Math.toRadians(tankHull.getAngle())));
        double deltaY = forwardBackward * (hullSpeed*Math.sin(Math.toRadians(tankHull.getAngle())));
        tankHull.setX(tankHull.getX() + deltaX);
        tankHull.setY(tankHull.getY() + deltaY);

        tankHull.translateBounds(HULLSPEED);
    }

    public void moveTurret(int forwardBackward) {
        double deltaX = forwardBackward * (hullSpeed*Math.cos(Math.toRadians(tankHull.getAngle())));
        double deltaY = forwardBackward * (hullSpeed*Math.sin(Math.toRadians(tankHull.getAngle())));
        tankTurret.setX(tankTurret.getX() + deltaX);
        tankTurret.setY(tankTurret.getY() + deltaY);

        tankTurret.translateBounds(HULLSPEED);
    }

    @Override
    public void tick(KeyInput keyInput) {
        if(tankType.getGameObjectType() == GameObjectType.PLAYER_ONE) {
            if(keyInput.isKey(KeyEvent.VK_UP) && canMoveForward) {
                moveForward();
                lastKeyPressed = KeyEvent.VK_UP;
                canMoveBackward = true;
//                canTurnRight = true;
//                canTurnLeft = true;
            }
            else if(keyInput.isKey(KeyEvent.VK_DOWN) && canMoveBackward) {
                moveBackward();
                lastKeyPressed = KeyEvent.VK_DOWN;
                canMoveForward = true;
//                canTurnRight = true;
//                canTurnLeft = true;
            }
            if(keyInput.isKey(KeyEvent.VK_Z)) {
                updateTurretAngleCounterClockWise(0);
                lastKeyPressed = KeyEvent.VK_Z;
            }
            else if(keyInput.isKey(KeyEvent.VK_X)) {
                updateTurretAngleClockWise(0);
                lastKeyPressed = KeyEvent.VK_X;
            }
            if(keyInput.isKey(KeyEvent.VK_LEFT) && canTurnLeft) {
                updateHullAngleCounterClockWise();
                lastKeyPressed = KeyEvent.VK_LEFT;
                canTurnRight = true;
            }
            else if(keyInput.isKey(KeyEvent.VK_RIGHT) && canTurnRight) {
                updateHullAngleClockWise();
                lastKeyPressed = KeyEvent.VK_RIGHT;
                canTurnLeft = true;
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
            if(keyInput.isKey(KeyEvent.VK_I) && canMoveForward) {
                moveForward();
                lastKeyPressed = KeyEvent.VK_I;
                canMoveBackward = true;
            }
            else if(keyInput.isKey(KeyEvent.VK_K) && canMoveBackward) {
                moveBackward();
                lastKeyPressed = KeyEvent.VK_K;
                canMoveForward = true;
            }
            if(keyInput.isKey(KeyEvent.VK_W) ) {
                updateTurretAngleCounterClockWise(0);
                lastKeyPressed = KeyEvent.VK_W;
            }
            else if(keyInput.isKey(KeyEvent.VK_E) ) {
                updateTurretAngleClockWise(0);
                lastKeyPressed = KeyEvent.VK_E;
            }
            if(keyInput.isKey(KeyEvent.VK_J) && canTurnLeft) {
                updateHullAngleCounterClockWise();
                lastKeyPressed = KeyEvent.VK_J;
                canTurnRight = true;
            }
            else if(keyInput.isKey(KeyEvent.VK_O) && canTurnRight) {
                updateHullAngleClockWise();
                lastKeyPressed = KeyEvent.VK_O;
                canTurnLeft = true;
            }


            long nowP2 = System.currentTimeMillis();
            if(keyInput.isKeyUp(KeyEvent.VK_F)) {
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
    public Polygon getBounds() {
        return tankHull.getBounds();
    }



}
