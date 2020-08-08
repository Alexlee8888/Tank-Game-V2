import java.awt.*;

public class GameButton {

    private int centerX;
    private int centerY;
    private int width;
    private int height;
    private Rectangle boundary;
    private Image image;
    private String imageURL;
    private Game game;
    private boolean isClickable;


    public GameButton(int centerX, int centerY, Graphics g, String imageURL, Game game) {
        this.game = game;
        this.centerX = centerX;
        this.centerY = centerY;
        this.imageURL = imageURL;
        image = Toolkit.getDefaultToolkit().getImage(imageURL);
        this.width = image.getWidth(null) / 5 * 3;
        this.height = image.getHeight(null) / 5 * 3;
        setBoundary();
        drawButton(g);
        isClickable = true;
    }

    public void setIsClickable(boolean bool) {
        isClickable = bool;
    }

    public boolean getIsClickable() {
        return isClickable;
    }

    public void setBoundary() {
        boundary = new Rectangle(centerX - width / 2, centerY - height / 2, width, height);
    }

    public boolean isPressed(int clickX, int clickY) {
        if (boundary.contains(clickX, clickY)) {
            return true;
        } else {
            return false;
        }
    }

    public void drawButton(Graphics g) {
        g.drawImage(image, centerX - width / 2, centerY - height / 2, width, height, null);
    }

    public void doAction() {
        switch (imageURL) {
            case "home_button.png":
                game.setIsAtHomeScreen(true);
                break;
            case "cancel_button.png":
                System.exit(0);
                break;
            case "multiplayer_button.png":
                game.setIsPlayClicked(true);
                break;
            case "singleplayer_button.png":
                game.setIsPlayClicked(true);
                break;
            case "play_again_button.png":
                game.setIsPlayClicked(true);
                break;
            case "quit_game_button.png":
                System.exit(0);
                break;

        }
    }


}
