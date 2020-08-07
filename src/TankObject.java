import java.awt.*;
import java.awt.event.KeyEvent;

public class TankObject implements GameObject {



    private static int TURRET_DEGREES_TURNED = 1;
    private static int HULL_DEGRESS_TURNED = 2;
    private static int HULL_SPEED = 3;
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
    private boolean canShoot = true;
    private int health = 6;
    public int lastKeyPressed;



    public TankObject(int x, int y, TankType tankType, Handler objectHandler) {
        this.tankType = tankType;
        this.objectHandler = objectHandler;
        this.hullSpeed = HULL_SPEED;
        tankHull = new TankPartsObject(x, y, tankType.getHullHeight(), tankType.getHullWidth(), tankType.getHullImage(), tankType.getGameObjectType());
        tankTurret = new TankPartsObject(x, y, tankType.getTurretHeight(), tankType.getTurretWidth(), tankType.getTurretImage(), tankType.getGameObjectType());
    }

    public void takeDamage() {
        if (health == 0) {
            return;
        }
        health--;
    }
    public int getHealth() {
        return health;
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
        tankHull.rotatePoint(-HULL_DEGRESS_TURNED);
    }

    public void updateHullAngleCounterClockWise() {
        tankHull.setAngle(tankHull.getAngle() - HULL_DEGRESS_TURNED);
        updateTurretAngleCounterClockWise(1);
        tankHull.rotatePoint(HULL_DEGRESS_TURNED);
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
        tankHull.setCenterX(tankHull.getCenterX() + deltaX);
        tankHull.setCenterY(tankHull.getCenterY() + deltaY);
    }

    public void moveTurret(int forwardBackward) {
        double deltaX = forwardBackward * (hullSpeed*Math.cos(Math.toRadians(tankHull.getAngle())));
        double deltaY = forwardBackward * (hullSpeed*Math.sin(Math.toRadians(tankHull.getAngle())));
        tankTurret.setCenterX(tankTurret.getCenterX() + deltaX);
        tankTurret.setCenterY(tankTurret.getCenterY() + deltaY);
    }

    public void drawHearts(Graphics g) {
        Image healthImage = Toolkit.getDefaultToolkit().getImage("hearts" + health + ".png");
        g.drawImage(healthImage, (int) tankHull.getTopLeftX() + tankHull.getWidth()/2 - (healthImage.getWidth(null) * 4)/2, (int) tankHull.getTopLeftY() + tankHull.getHeight() , healthImage.getWidth(null) * 4, healthImage.getHeight(null) * 4, null);
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
//                lastKeyPressed = KeyEvent.VK_Z;
            }
            else if(keyInput.isKey(KeyEvent.VK_X)) {
                updateTurretAngleClockWise(0);
//                lastKeyPressed = KeyEvent.VK_X;
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
            if(canShoot && keyInput.isKeyUp(KeyEvent.VK_SPACE)) {

                if(nowP1 - lastShootP1 > threshold) {
                    BulletObject newBullet = new BulletObject(tankTurret.getCenterX(), tankTurret.getCenterY(), tankTurret.getAngle(), tankType, objectHandler);
                    objectHandler.addObject(newBullet);
                    lastShootP1 = nowP1;
                    GameSounds.playSimpleBombExplosion1();

                }
            }
//            if(keyInput.isKey(KeyEvent.VK_SPACE)) {
//                    BulletObject newBullet = new BulletObject(tankTurret.getCenterX(), tankTurret.getCenterY(), tankTurret.getAngle(), tankType, objectHandler);
//                    objectHandler.addObject(newBullet);
//            }
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
//                lastKeyPressed = KeyEvent.VK_W;
            }
            else if(keyInput.isKey(KeyEvent.VK_E) ) {
                updateTurretAngleClockWise(0);
//                lastKeyPressed = KeyEvent.VK_E;
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
                if(canShoot && nowP2 - lastShootP2 > threshold) {
                    BulletObject newBullet = new BulletObject(tankTurret.getCenterX(), tankTurret.getCenterY(), tankTurret.getAngle(), tankType, objectHandler);
                    objectHandler.addObject(newBullet);
                    lastShootP2 = nowP2;
                    GameSounds.playShotEffect3();
                }
            }
//            if(keyInput.isKey(KeyEvent.VK_F)) {
//                BulletObject newBullet = new BulletObject(tankTurret.getCenterX(), tankTurret.getCenterY(), tankTurret.getAngle(), tankType, objectHandler);
//                objectHandler.addObject(newBullet);
//            }
        }
    }

    @Override
    public void render(Graphics g) {

        if(health == 0){
            setTurnRight(false);
            setTurnLeft(false);
            setMoveBackward(false);
            setMoveForward(false);
            canShoot = false;
            return;
        }

        tankHull.render(g);
        tankTurret.render(g);
        drawHearts(g);



    }

    @Override
    public Rectangle getBounds() {
        return tankHull.getBounds();
    }

    @Override
    public Polygon getPolygonBounds() {
        return tankHull.getPolygonBounds();
    }


}
