import java.awt.*;
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
//    public static final Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension windowDimension = new Dimension(canvasWidth, canvasHeight);



    private final double UPDATE_CAP = 1.0/60.0;
    private KeyInput keyInput;
    private Handler objectHandler;
    private boolean isRunning = false;
    private Thread thread;



    public Game() throws IOException {
        new Window(windowDimension, this, "Tank Game");
        keyInput = new KeyInput();
        this.addKeyListener(keyInput);
        objectHandler = new Handler(keyInput);





//        WallObject wallObjectLeft = new WallObject(0, 0, 25, backgroundHeight, objectHandler);
//        objectHandler.addObject(wallObjectLeft);
//        WallObject wallObjectTop = new WallObject(0, 0, backgroundWidth, 25, objectHandler);
//        objectHandler.addObject(wallObjectTop);
//        WallObject wallObjectRight = new WallObject(backgroundWidth - 25, 0, 25, backgroundHeight, objectHandler);
//        objectHandler.addObject(wallObjectRight);
//        WallObject wallObjectBottom = new WallObject(0, backgroundHeight - 25, backgroundWidth - 200, 25, objectHandler);
//        objectHandler.addObject(wallObjectBottom);
        Scanner sc = new Scanner(new File("WallPlacements"));


        for (int i = 0; i < (34 * 40); i++) {
            int currInt = sc.nextInt();
            if(currInt == 0) {
                continue;
            }
            WallObject wallObject = new WallObject(((i % 40) * 25) , (i / 40) * 25, objectHandler);
            objectHandler.addObject(wallObject);
            if(currInt == 1) {
                objectHandler.addHittableObject(wallObject);
            }
        }

        TankObject player1 = new TankObject(100, 500, TankType.PLAYER_ONE_TANK_TYPE, objectHandler);
        TankObject player2 = new TankObject(100, 700, TankType.PLAYER_TWO_TANK_TYPE, objectHandler);

        objectHandler.addObject(player1);
        objectHandler.addHittableObject(player1);
        objectHandler.addObject(player2);
        objectHandler.addHittableObject(player2);



        objectHandler.setTankIndexes();

        requestFocus();
        start();
    }

//    public void start() {
//        keyInput = new KeyInput();
//        isRunning = true;
//        thread = new Thread(this);
//        thread.run();
//    }

    public void start() {

        thread = new Thread(this);
        thread.run();
    }

    public void stop() {
        isRunning = false;
        try{
            thread.join();
        }
        catch (InterruptedException e) {
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


            while(unprocessedTime >= UPDATE_CAP) {
                unprocessedTime-= UPDATE_CAP;
                render = true;
                requestFocus();
                tick();
                keyInput.update();
                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }


            }
            if(render) {
                frames++;
                render();
            }
            else{
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
    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.drawImage(BACKGROUND, 0, 0, backgroundWidth, backgroundHeight,this);
        // custom logic code
        renderGame(g);
        bs.show();
        g.dispose();
    }

    // game rendering code
    private void renderGame(Graphics g) {
        objectHandler.render(g);
    }

    public static void main (String [] args) throws IOException {
        new Game();

    }
}
