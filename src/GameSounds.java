import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GameSounds {

    // background music
    public static void playBackgroundMusic() {
        String tinyButtonPush = "backgroundMusic.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);

    }

    // tank moving
    public static void playTankMoving() {
        String tinyButtonPush = "tank_moving.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    // p1 shooting
    public static void playSimpleBombExplosion1() {
        String tinyButtonPush = "shot_effect2.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    // p2 shooting
    public static void playShotEffect3() {
        String tinyButtonPush = "shot_effect3.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    //blast explosion random 1
    public static void playExplosionsBlast1() {
        String tinyButtonPush = "explosions_blast1.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    // blast explosion random 2
    public static void playExplosionsBlast2() {
        String tinyButtonPush = "explosions_blast2.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    // choose explosion
    public static void chooseAndPlayExplosion() {
        int random = (int) (Math.random() * 1);
        switch (random) {
            case 0:
                playExplosionsBlast1();
                break;
            case 1:
//                playExplosionsBlast2();
//                break;
        }
    }

    // tank explosion
    public static void playCrashingExplosion() {
        String tinyButtonPush = "crashing_explosion.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }











    // not use

    public static void playSmallBombExplosion() {
        String tinyButtonPush = "small_bomb_explosion.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    public static void playShotEffect2() {
        String tinyButtonPush = "bomb_explosion.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    public static void playDistantTankShotExplosion() {
        String tinyButtonPush = "distant_tank_shot_explosion.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

    public static void playShotEffect1() {
        String tinyButtonPush = "shot_effect.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(tinyButtonPush);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }





}
