import java.awt.*;

public class TankType {
    private static Image P1_HULL = Toolkit.getDefaultToolkit().getImage("alex_hull.png");
    private static Image P1_TURRET = Toolkit.getDefaultToolkit().getImage("alex_turret.png");
    private static Image P2_HULL = Toolkit.getDefaultToolkit().getImage("chris_tank_hull.png");
    private static Image P2_TURRET = Toolkit.getDefaultToolkit().getImage("chris_tank_turret.png");
    private static Image P1_BULLET_IMAGE = Toolkit.getDefaultToolkit().getImage("alex_bullet.png");
    private static Image P2_BULLET_IMAGE = Toolkit.getDefaultToolkit().getImage("chris_tank_bullet.png");

    private static int HULL_HEIGHTP1 = 105 * 2 / 3;
    private static int HULL_WIDTHP1 = 130 * 2 / 3;
    private static int TURRET_HEIGHTP1 = 70 * 2 / 3;
    private static int TURRET_WIDTHP1 = 250 * 2 / 3;

    private static int HULL_HEIGHTP2 = 105 * 2 / 3;
    private static int HULL_WIDTHP2 = 160 * 2 / 3;
    private static int TURRET_HEIGHTP2 = 70 * 2 / 3;
    private static int TURRET_WIDTHP2 = 250 * 2 / 3;


    public static TankType PLAYER_ONE_TANK_TYPE = new TankType(P1_HULL, P1_TURRET, P1_BULLET_IMAGE, GameObjectType.PLAYER_ONE, HULL_HEIGHTP1, HULL_WIDTHP1, TURRET_HEIGHTP1, TURRET_WIDTHP1);
    public static TankType PLAYER_TWO_TANK_TYPE = new TankType(P2_HULL, P2_TURRET, P2_BULLET_IMAGE, GameObjectType.PLAYER_TWO, HULL_HEIGHTP2, HULL_WIDTHP2, TURRET_HEIGHTP2, TURRET_WIDTHP2);

    private Image bulletImage;
    private Image hullImage;
    private Image turretImage;

    private int hull_height;
    private int hull_width;
    private int turret_height;
    private int turret_width;
    private GameObjectType gameObjectType;

    public TankType(Image hullImage, Image turretImage, Image bulletImage, GameObjectType gameObjectType, int hull_height, int hull_width, int turret_height, int turret_width) {
        this.hull_height = hull_height;
        this.hull_width = hull_width;
        this.turret_height = turret_height;
        this.turret_width = turret_width;
        this.hullImage = hullImage;
        this.turretImage = turretImage;
        this.bulletImage = bulletImage;
        this.gameObjectType = gameObjectType;
    }

    public int getHullHeight() {
        return hull_height;
    }

    public int getHullWidth() {
        return hull_width;
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
    public int getTurretHeight() {
        return turret_height;
    }
    public int getTurretWidth() {
        return turret_width;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

}
