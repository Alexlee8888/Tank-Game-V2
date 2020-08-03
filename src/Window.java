import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(Dimension dimension, Game game, String title) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

}
