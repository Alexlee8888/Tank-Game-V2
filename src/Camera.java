import java.awt.*;

public class Camera {
     private float x = 0, y = 0;
     private float prevX = 0, prevY = 0;
     private Dimension windowDimension;
     private Handler handler;

     public Camera(float x, float y, Dimension windowDimension,Handler handler) {
         this.x = x;
         this.y = y;
         this.windowDimension = windowDimension;
         this.handler = handler;
     }

    public void moveAllObjects() {
        for(int i = 0; i < handler.getGameObjects().size(); i++) {
            if(handler.getGameObjects().get(i) instanceof TankObject) {
                TankObject tankObject = (TankObject) handler.getGameObjects().get(i);
            }
        }
    }

     public void tick(TankObject tankObject) {
         x += ((tankObject.getTankHull().getTopLeftX()) - windowDimension.getWidth()/2) * 1f;
         y += ((tankObject.getTankHull().getTopLeftY()) - windowDimension.getHeight()/2) * 1f;
//         for(int i = 0; i < handler.getGameObjects().size(); i++) {
//             if(handler.getGameObjects().get(i) instanceof TankObject) {
//
//             }
//             else if(handler.getGameObjects().get(i) instanceof WallObject) {
//                 WallObject wallObject = (WallObject) handler.getGameObjects().get(i);
//                 wallObject.addToMoveX((int) (((tankObject.getTankHull().getTopLeftX()) - windowDimension.getWidth()/2) * 1f));
//                 wallObject.addToMoveY((int) (((tankObject.getTankHull().getTopLeftY()) - windowDimension.getHeight()/2) * 1f));
//             }
//         }
         if(x<= 0) {
             x = 0;
         }
         if(x >= windowDimension.getWidth()) {
             x = (float) windowDimension.getWidth();
         }

         if(y <= 0) {
             y = 0;
         }

         if(y >= windowDimension.getHeight()) {
             y = (float) windowDimension.getHeight();
         }

         prevX = x;
         prevY = y;

     }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
