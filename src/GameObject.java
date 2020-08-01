import java.awt.*;

public interface GameObject {
    void tick(KeyInput keyInput);
    void render(Graphics g);
    Rectangle getBounds();
}
