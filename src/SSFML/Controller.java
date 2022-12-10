package SSFML;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

/*
Контроллер отвечающий за Mouse и Keyboard.
*используется в классе window
 */
public class Controller {

    public enum TypeMouse { BUTTON1, BUTTON2, BUTTON3 }
    private Stack<Character> keysPressed  = new Stack<Character>();
    private Stack<TypeMouse> mousePressed = new Stack<TypeMouse>();
    public Point point = new Point();

    public Controller(JFrame frame) {

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (keysPressed.search(e.getKeyChar()) == -1)
                    keysPressed.add(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.removeElement(e.getKeyChar());
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && mousePressed.search(TypeMouse.BUTTON1) == -1)
                    mousePressed.add(TypeMouse.BUTTON1);
                if (e.getButton() == MouseEvent.BUTTON2 && mousePressed.search(TypeMouse.BUTTON2) == -1)
                    mousePressed.add(TypeMouse.BUTTON2);
                if (e.getButton() == MouseEvent.BUTTON3 && mousePressed.search(TypeMouse.BUTTON3) == -1)
                    mousePressed.add(TypeMouse.BUTTON3);
                point = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    mousePressed.removeElement(TypeMouse.BUTTON1);
                if (e.getButton() == MouseEvent.BUTTON2)
                    mousePressed.removeElement(TypeMouse.BUTTON2);
                if (e.getButton() == MouseEvent.BUTTON3)
                    mousePressed.removeElement(TypeMouse.BUTTON3);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        MouseMotionAdapter adapter = new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                point = e.getPoint();
            }
        };
        frame.addMouseMotionListener(adapter);
    }

    //Keyboard
    public boolean getKeyKeyboard(String key) {
        for (int j = 0; j < keysPressed.size(); j++)
            if (keysPressed.get(j) == key.charAt(0))
                return true;
        return false;
    }


    //Mouse
    public boolean getKeyMouse(TypeMouse key)
    {
        for (int j = 0; j < mousePressed.size(); j++)
            if (mousePressed.get(j) == key)
                return true;
        return false;
    }
}
