import java.awt.*;

public class TankType {
    private static Image P1_HULL = Toolkit.getDefaultToolkit().getImage("hull.png");
    private static Image P1_TURRET = Toolkit.getDefaultToolkit().getImage("turret.png");
    private static Image P2_HULL = Toolkit.getDefaultToolkit().getImage("chris_tank_hull.png");
    private static Image P2_TURRET = Toolkit.getDefaultToolkit().getImage("chris_tank_turret.png");
    private static Image P1_BULLET_IMAGE = Toolkit.getDefaultToolkit().getImage("bullet2.png");
    private static Image P2_BULLET_IMAGE = Toolkit.getDefaultToolkit().getImage("bullet.png");
    private static int HEIGHT = 160;
    private static int WIDTH = 200;

    public static TankType PLAYER_ONE_TANK_TYPE = new TankType(P1_HULL, P1_TURRET, P1_BULLET_IMAGE, GameObjectType.PLAYER_ONE);
    public static TankType PLAYER_TWO_TANK_TYPE = new TankType(P2_HULL, P2_TURRET, P2_BULLET_IMAGE, GameObjectType.PLAYER_TWO);


    private Image hullImage;
    private Image turretImage;
    private Image bulletImage;
    private int height;
    private int width;
    private GameObjectType gameObjectType;

    public TankType(Image hullImage, Image turretImage, Image bulletImage, GameObjectType gameObjectType) {
        this.height = HEIGHT;
        this.width = WIDTH;
        this.hullImage = hullImage;
        this.turretImage = turretImage;
        this.bulletImage = bulletImage;
        this.gameObjectType = gameObjectType;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public Image getHullImage() {
        return hullImage;
    }
    public Image getTurretImage() {
        return turretImage;
    }
    public Image getBulletImage() {
        return bulletImage;
    }

}
