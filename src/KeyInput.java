import com.sun.tools.classfile.Opcode;
import javafx.scene.input.KeyCode;

import java.awt.event.*;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class KeyInput implements KeyListener {

    private final int NUM_KEYS = 256;
    private boolean[] keysPressed = new boolean[NUM_KEYS];
    private boolean[] keysLastPressed = new boolean[NUM_KEYS];

    public void update() {
        for(int i = 0; i < NUM_KEYS; i++) {
            keysLastPressed[i] = keysPressed[i];
        }

    }

    public boolean isKey(int keyCode) {
        return keysPressed[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keysPressed[keyCode] && keysLastPressed[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keysPressed[keyCode] && !keysLastPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed[e.getKeyCode()] = true;
        System.out.println("updated key is:" + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
