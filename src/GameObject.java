import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

import java.awt.*;
import java.awt.geom.Path2D;

public interface GameObject {
    void tick(KeyInput keyInput);
    void render(Graphics g);
    Polygon getBounds();
}