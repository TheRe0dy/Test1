package SSFML;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/*
Тестовая версия библиотеки SSFML, похожей на библиотеку SFML C++.
SSFML - графическая библиотека, разработанная для упрощения работы с графикой на Java.

 */

public class Window extends JPanel  {

    JFrame frame;
    Graphics graphics;
    Controller controller;

    private Stack<Sprite> list = new Stack<>();

    public Window() {
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graphics = frame.getGraphics();
        controller = new Controller(frame);

    }

    public void clear()
    {
        list.clear();
    }

    public void draw(Sprite sprite)
    {
        list.add(sprite);
    }
    public void drawToExisting(Sprite sprite)
    {
        if (list.search(sprite) != -1)
            list.remove(sprite); //удаляем такую же текстуру
        list.add(sprite); //добавляем заного
    }

    public void display()
    {
        frame.repaint(); //обновлять paint
    }

    @Override
    public void paint(Graphics g) {
        for (Sprite spr : list)
            g.drawImage(spr.getImg(), (int)spr.getPos().x, (int)spr.getPos().y, null);
    }

    public Point getMousePixels() {
        return frame.getMousePosition(); //возвращаем координаты мышки
    }
    public boolean getKeyKeyboard(String key) {
        return controller.getKeyKeyboard(key);
    }
    public boolean getKeyMouse(Controller.TypeMouse key) {
        return controller.getKeyMouse(key);
    }
    public boolean IntRect(int x, int y, int widthx, int heighty)
    {
        //System.out.println(controller.point.x + " " + controller.point.y);
        if (controller.point.x > x && controller.point.x < x + widthx &&
            controller.point.y > y && controller.point.y < y + heighty)
            return true;

        return false;
    }
}
