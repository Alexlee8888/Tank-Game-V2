import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private static int backgroundWidth = 240 * 25 / 6;
    private static int backgroundHeight = 204 * 25 / 6;
    private static int canvasWidth = backgroundWidth;
    private static int canvasHeight = backgroundHeight + 22;


    private static Image BACKGROUND = Toolkit.getDefaultToolkit().getImage("background.png");
    private static Image BACKGROUND2 = Toolkit.getDefaultToolkit().getImage("background2.png");
    //    public static final Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension windowDimension = new Dimension(canvasWidth, canvasHeight);


    private final double UPDATE_CAP = 1.0 / 60.0;
    private KeyInput keyInput;
    private Handler objectHandler;
    private boolean isRunning = false;
    private Thread thread;
    private boolean isPlayButtonClicked = false;
    private boolean isGameOver = false;
    private boolean gameStarted = false;
    private boolean isAtHomeScreen = true;
    private Window window;
    private TankObject winner;
    private Camera camera;

    public void setIsPlayClicked(boolean bool) {
        isPlayButtonClicked = bool;
        isGameOver = false;
        isAtHomeScreen = false;
    }

    public void setIsGameOver(boolean bool) {
        isGameOver = bool;
        isPlayButtonClicked = false;
        isAtHomeScreen = false;
    }

    public void setIsAtHomeScreen(boolean bool) {
        isAtHomeScreen = bool;
        isPlayButtonClicked = false;
        isGameOver = false;
    }

    public Game() {

        window = new Window(windowDimension, this, "Tank Game");

        keyInput = new KeyInput();
        this.addKeyListener(keyInput);
        this.addMouseListener(keyInput);
        objectHandler = new Handler(keyInput);


        requestFocus();
        start();
    }

    public void startMultiplayerGame() {
        winner = null;
        if (objectHandler != null) {
            camera = new Camera(0, 0, windowDimension, objectHandler);
        }
        if (gameStarted) {
            return;
        }
        Scanner sc = null;
        try {
            sc = new Scanner(new File("WallPlacements"));
        } catch (FileNotFoundException e) {
        }

        for (int i = 0; i < (34 * 40); i++) {
            int currInt = sc.nextInt();
            if (currInt == 0) {
                continue;
            }
            WallObject wallObject = new WallObject(((i % 40) * 25), (i / 40) * 25, objectHandler);
            objectHandler.addObject(wallObject);
            if (currInt == 1) {
                objectHandler.addHittableObject(wallObject);
            }
        }

        TankObject player1 = new TankObject(100, 700, TankType.PLAYER_ONE_TANK_TYPE, objectHandler, this);
        TankObject player2 = new TankObject(800, 150, TankType.PLAYER_TWO_TANK_TYPE, objectHandler, this);

        objectHandler.addObject(player1);
        objectHandler.addHittableObject(player1);
        objectHandler.addObject(player2);
        objectHandler.addHittableObject(player2);
        objectHandler.setTankIndexes();
        gameStarted = true;
    }

    public void start() {

        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        isRunning = true;
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (isRunning) {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;


            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                requestFocus();
                tick();
                keyInput.update();
                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }


            }
            if (render) {
                frames++;
                render();
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose() {

    }


//    public void run() {
//        this.requestFocus();
//        long lastTime = System.nanoTime();
//        double amountOfTicks = 60.0;
//        double ns = 1000000000 / amountOfTicks;
//        double delta = 0;
//        long timer  = System.currentTimeMillis();
//        int frames = 0;
//        while (isRunning) {
//            keyInput.update();
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while (delta >= 1) {
//
//                tick();
//
//                delta--;
//            }
//
//            render();
//
//            frames++;
//
//            if(System.currentTimeMillis() - timer > 1000) {
//                timer += 1000;
//                frames = 0;
//            }
//            keyInput.update();
//        }
//        stop();
//    }

    public void tick() {
        objectHandler.tick();
        for (int i = 0; i < objectHandler.getGameObjects().size(); i++) {
            if (objectHandler.getGameObjects().get(i) instanceof TankObject) {
                TankObject tankObject = (TankObject) objectHandler.getGameObjects().get(i);
                if (tankObject.getTankHull().getGameObjectType() == GameObjectType.PLAYER_ONE) {
                    camera.tick(tankObject);

                }
            }
        }
    }

    public void drawHearts(Graphics g, GameObject gameObject) {
        TankObject tankObject = (TankObject) gameObject;
        int health = tankObject.getHealth();
        TankPartsObject tankHull = tankObject.getTankHull();
        Image healthImage = Toolkit.getDefaultToolkit().getImage("hearts" + health + ".png");
        g.drawImage(healthImage, (int) tankHull.getTopLeftX() + tankHull.getWidth() / 2 - (healthImage.getWidth(null) * 4) / 2, (int) tankHull.getTopLeftY() + tankHull.getHeight() + 10, healthImage.getWidth(null) * 4, healthImage.getHeight(null) * 4, null);
    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;


//        AffineTransform identity = new AffineTransform();
//        g2d.setTransform(identity);

        if (camera != null) {
//            g2d.translate(-camera.getX(), -camera.getY());

        }
        g.drawImage(BACKGROUND, 0, 0, backgroundWidth, backgroundHeight, null);
        g.drawImage(BACKGROUND2, backgroundWidth, 0, backgroundWidth, backgroundHeight, null);

        if (isAtHomeScreen) {
            objectHandler.getButtons().removeAll(objectHandler.getButtons());
            paintHomeScreen(g);
        } else if (isPlayButtonClicked) {
            for (int i = 0; i < objectHandler.getButtons().size(); i++) {
                GameButton button = (GameButton) objectHandler.getButtons().get(i);
                button.setIsClickable(false);
            }
            startMultiplayerGame();
//            GameSounds.playBackgroundMusic();
        } else if (isGameOver) {


            objectHandler.getButtons().removeAll(objectHandler.getButtons());
            gameStarted = false;
            paintEndScreen(g);
        }


        if (objectHandler.getGameObjects().size() != 0) {
            drawHearts(g, objectHandler.getGameObjects().get(Handler.PLAYER_ONE));
            drawHearts(g, objectHandler.getGameObjects().get(Handler.PLAYER_TWO));
        }

        // custom logic code
        //g2d.setTransform(identity);

        renderGame(g);

//        if (camera != null) {
//            g2d.translate(camera.getX(), camera.getY());
//
//        }

        g.dispose();
        bs.show();
    }

    // game rendering code
    private void renderGame(Graphics g) {
        objectHandler.render(g);
    }

    public void paintHomeScreen(Graphics g) {
        Image startMenu = Toolkit.getDefaultToolkit().getImage("start_menu_background.png");
        Image title = Toolkit.getDefaultToolkit().getImage("title.png");
        g.drawImage(startMenu, 0, 0, backgroundWidth, backgroundHeight, null);
        g.drawImage(title, 250, 170, 872 * 4 / 7, 411 * 4 / 7, null);
        GameButton singleButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 30, g, "singleplayer_button.png", this);
        objectHandler.addButton(singleButton);
        singleButton.setIsClickable(true);
        GameButton multiButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 130, g, "multiplayer_button.png", this);
        objectHandler.addButton(multiButton);
        multiButton.setIsClickable(true);
        GameButton cancelButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 230, g, "cancel_button.png", this);
        objectHandler.addButton(cancelButton);
        cancelButton.setIsClickable(true);
    }

    public void paintEndScreen(Graphics g) {
        Image startMenu = Toolkit.getDefaultToolkit().getImage("end_menu_background.png");
        g.drawImage(startMenu, 0, 0, backgroundWidth, backgroundHeight, null);
        GameButton playAgainButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 30, g, "play_again_button.png", this);
        objectHandler.addButton(playAgainButton);
        playAgainButton.setIsClickable(true);
        GameButton homeButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 130, g, "home_button.png", this);
        objectHandler.addButton(homeButton);
        homeButton.setIsClickable(true);
        GameButton quitGameButton = new GameButton(backgroundWidth / 2, backgroundHeight / 2 + 230, g, "quit_game_button.png", this);
        objectHandler.addButton(quitGameButton);
        quitGameButton.setIsClickable(true);
        for (int i = 0; i < objectHandler.getGameObjects().size(); i++) {
            if (objectHandler.getGameObjects().get(i) instanceof TankObject) {
                TankObject winner = (TankObject) objectHandler.getGameObjects().get(i);
                if (winner.getHealth() <= 0) {
                    continue;
                }
                this.winner = winner;

            }
        }
        objectHandler.getGameObjects().removeAll(objectHandler.getGameObjects());
        objectHandler.getHittableObjects().removeAll(objectHandler.getHittableObjects());
        int centerX = 330;
        int centerY = 290;
        g.drawImage(winner.getTankHull().getObjectImage(), centerX - (winner.getTankHull().getWidth()), centerY - (winner.getTankHull().getHeight()), winner.getTankHull().getWidth() * 2, winner.getTankHull().getHeight() * 2, this);
        g.drawImage(winner.getTankTurret().getObjectImage(), centerX - (winner.getTankTurret().getWidth()), centerY - (winner.getTankTurret().getHeight()), winner.getTankTurret().getWidth() * 2, winner.getTankTurret().getHeight() * 2, this);
        Image winText = Toolkit.getDefaultToolkit().getImage("win_text.png");
        g.drawImage(winText, centerX + 85 + (winner.getTankHull().getWidth()), centerY - (winner.getTankHull().getHeight()), null);
    }

    public static void main(String[] args) throws IOException {

        Game game = new Game();
        game.repaint();
    }


}
