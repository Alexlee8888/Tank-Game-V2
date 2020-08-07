import com.sun.tools.classfile.Opcode;
import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.*;

public class KeyInput implements KeyListener, MouseListener {

    private final int NUM_KEYS = 256;
    private boolean[] keysPressed = new boolean[NUM_KEYS];
    private boolean[] keysLastPressed = new boolean[NUM_KEYS];
    private Point mouseClicked;

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

    public Point getClickPoint() {
        return mouseClicked;
    }

    public void resetClickPoint() {
        mouseClicked = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();
        mouseClicked = new Point(clickX, clickY);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
