import com.sun.tools.classfile.Opcode;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class KeyInput implements KeyListener {

    private final int NUM_KEYS = 256;
    private static final List<Integer> KEYS = Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_F, KeyEvent.VK_I, KeyEvent.VK_O, KeyEvent.VK_J, KeyEvent.VK_K);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
