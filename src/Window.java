import javax.swing.*;
import java.awt.*;

public class Window {

    public Window(Dimension dimension, Game game, String title) {
//        game.setPreferredSize(dimension);
//        game.setMaximumSize(dimension);
//        game.setMinimumSize(dimension);

        JFrame frame = new JFrame(title);
        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

}
