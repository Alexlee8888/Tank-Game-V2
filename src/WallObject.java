import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WallObject extends AbstractGameObject implements GameObject {

    private static final int WIDTH =  25;
    private static final int HEIGHT =  25;

    private int topLeftX;
    private int topLeftY;
    private Handler handler;

    public WallObject(int topLeftX, int topLeftY, Handler handler) {
        super(topLeftX + 25 / 2, topLeftY + 25 / 2, HEIGHT, WIDTH, null, GameObjectType.WALL);
        this.handler = handler;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
    }
    @Override
    public void tick(KeyInput keyInput) {
        TankObject playerOne = (TankObject) handler.getGameObjects().get(handler.PLAYER_ONE);
        TankObject playerTwo = (TankObject) handler.getGameObjects().get(handler.PLAYER_TWO);
        if(playerOne.getBounds().intersects(getRectangleBounds())) {
            int lastKeyPressed = playerOne.lastKeyPressed;
//            switch (playerOne.lastKeyPressed) {
//                case 38:
//                    playerOne.setMoveForward(false);
//                    playerOne.moveBackward();
////                    playerOne.setMoveForward(true);
//                    break;
//                case 40:
//                    playerOne.setMoveBackward(false);
//                    playerOne.moveForward();
////                    playerOne.setMoveBackward(true);
//                    break;
//                case 37:
//                    playerOne.setTurnLeft(false);
////                    playerOne.updateHullAngleClockWise();
////                    playerOne.setTurnLeft(true);
//                    break;
//                case 39:
//                    playerOne.setTurnRight(false);
////                    playerOne.updateHullAngleCounterClockWise();
////                    playerOne.setTurnRight(true);
//                    break;
//            }
            if (lastKeyPressed == 38) {
                playerOne.setMoveForward(false);
                    playerOne.moveBackward();
                    playerOne.setMoveForward(true);
            }
            else if (lastKeyPressed == 40) {
                playerOne.setMoveBackward(false);
                playerOne.moveForward();
                playerOne.setMoveBackward(true);
            }
            if (lastKeyPressed == 37) {
                playerOne.setTurnLeft(false);
//                playerOne.moveBackward();
                playerOne.setTurnLeft(true);
            }
            else if (lastKeyPressed == 39) {
                playerOne.setTurnRight(false);
//                playerOne.moveForward();
                playerOne.setTurnRight(true);
            }


        }
        if(playerTwo.getBounds().intersects(getRectangleBounds())) {
            int lastKeyPressed = playerTwo.lastKeyPressed;
            if (lastKeyPressed == 73) {
                playerTwo.setMoveForward(false);
                playerTwo.moveBackward();
                playerTwo.setMoveForward(true);
            }
            else if (lastKeyPressed == 75) {
                playerTwo.setMoveBackward(false);
                playerTwo.moveForward();
                playerTwo.setMoveBackward(true);
            }
            if (lastKeyPressed == 74) {
                playerTwo.setTurnLeft(false);
//                playerOne.moveBackward();
                playerTwo.setTurnLeft(true);
            }
            else if (lastKeyPressed == 79) {
                playerTwo.setTurnRight(false);
//                playerOne.moveForward();
                playerTwo.setTurnRight(true);
            }
//            switch (playerTwo.lastKeyPressed) {
//                case 73:
//                    playerTwo.setMoveForward(false);
//                    playerTwo.moveBackward();
//                    playerTwo.setMoveForward(true);
//                    break;
//                case 75:
//                    playerTwo.setMoveBackward(false);
//                    playerTwo.moveForward();
//                    playerTwo.setMoveBackward(true);
//                    break;
//                case 74:
//                    playerTwo.setTurnLeft(false);
////                    playerTwo.updateHullAngleClockWise();
////                    playerTwo.setTurnLeft(true);
//                    break;
//
//                case 79:
//                    playerTwo.setTurnRight(false);
////                    playerTwo.updateHullAngleCounterClockWise();
////                    playerTwo.setTurnRight(true);
//                    break;
//            }

        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(topLeftX, topLeftY, WIDTH, HEIGHT);
    }

    @Override
    public Polygon getBounds() {
        return null;
    }

    public Rectangle2D getRectangleBounds() {
        Rectangle2D r = new Rectangle2D.Double(getTopLeftX(), getTopLeftY(), WIDTH, HEIGHT);
        return r;
    }

}
