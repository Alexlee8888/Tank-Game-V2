import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;


//    public static final Dimension windowDimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension windowDimension = new Dimension(1000, 750);



    private final double UPDATE_CAP = 1.0/60.0;
    private KeyInput keyInput;
    private Handler objectHandler;
    private boolean isRunning = false;
    private Thread thread;



    public Game() {

        new Window(windowDimension, this, "Tank Game");

        keyInput = new KeyInput();
        this.addKeyListener(keyInput);
        objectHandler = new Handler(keyInput);

        objectHandler.addObject(new TankObject(100, 120, TankType.PLAYER_ONE_TANK_TYPE, objectHandler));
        objectHandler.addObject(new TankObject(800, 75, TankType.PLAYER_TWO_TANK_TYPE, objectHandler ));
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
        g.fillRect(0, 0, (int) windowDimension.getWidth(), (int) windowDimension.getHeight());
        // custom logic code
        renderGame(g);
        bs.show();
        g.dispose();
    }

    // game rendering code
    private void renderGame(Graphics g) {


        objectHandler.render(g);
    }

    public static void main (String [] args) {
        new Game();

    }
}
