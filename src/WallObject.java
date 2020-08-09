import java.awt.*;

public class WallObject implements GameObject {

    private static final int WIDTH =  25;
    private static final int HEIGHT =  25;

    private int topLeftX;
    private int topLeftY;
    private Handler handler;
    private int moveX = 0;
    private int moveY = 0;

    public WallObject(int topLeftX, int topLeftY, Handler handler) {
        this.handler = handler;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
    }
    @Override
    public void tick(KeyInput keyInput) {
        TankObject playerOne = (TankObject) handler.getGameObjects().get(handler.PLAYER_ONE);
        TankObject playerTwo = (TankObject) handler.getGameObjects().get(handler.PLAYER_TWO);
        if(playerOne == null || playerTwo == null) {
            return;
        }
        if(playerOne.getBounds().intersects(getBounds())) {
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
        if(playerTwo.getBounds().intersects(getBounds())) {
            int lastKeyPressed2 = playerTwo.lastKeyPressed;
            if (lastKeyPressed2 == 73) {
                playerTwo.setMoveForward(false);
                playerTwo.moveBackward();
                playerTwo.setMoveForward(true);
            }
            else if (lastKeyPressed2 == 75) {
                playerTwo.setMoveBackward(false);
                playerTwo.moveForward();
                playerTwo.setMoveBackward(true);
            }
            if (lastKeyPressed2 == 74) {
                playerTwo.setTurnLeft(false);
//                playerOne.moveBackward();
                playerTwo.setTurnLeft(true);
            }
            else if (lastKeyPressed2 == 79) {
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
//                    playerTwo.setTurnLeft(true);
//                    break;
//
//                case 79:
//                    playerTwo.setTurnRight(false);
////                    playerTwo.updateHullAngleCounterClockWise();
//                    playerTwo.setTurnRight(true);
//                    break;
//            }

        }
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public void addToMoveX(int add) {
        moveX += add;
    }

    public void subtractFromMoveX(int subtract) {
        moveX-= subtract;
    }

    public void addToMoveY(int add) {
        moveY += add;
    }

    public void subtractFromMoveY(int subtract) {
        moveY-= subtract;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        //g.fillRect(topLeftX + moveX, topLeftY + moveY, WIDTH, HEIGHT);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(topLeftX + moveX, topLeftY + moveY, WIDTH, HEIGHT);

    }

    @Override
    public Polygon getPolygonBounds() {
        return null;
    }
}
