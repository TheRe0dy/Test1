package SSFML;

import javax.swing.*;
import java.awt.*;

public class Sprite {
    private Image img;
    private Coord coord = new Coord();
    private int Width, Height; //сохраняем размеры изображения
    class Coord {
        float x, y;
    }

    public Sprite() {
        img = null;
        coord.x = 0;
        coord.y = 0;
    }
    public Sprite(int x_, int y_)
    {
        img = null;
        coord.x = x_;
        coord.y = y_;
    }

    public void setTexture(String path)
    {
        img = new ImageIcon(path).getImage();
        Width = img.getWidth(null);
        Height = img.getHeight(null);
    }
    public void setPos(float x_, float y_)
    {
        coord.x = x_;
        coord.y = y_;
    }
    public void addPos(float x_, float y_)
    {
        coord.x += x_;
        coord.y += y_;
    }
    public void setSize(int sizex, int sizey)
    {
        img = img.getScaledInstance(sizex, sizey, Image.SCALE_DEFAULT);
    }
    public void setScale(int sizex, int sizey)
    {
        //Поскольку setSize сбрасывает размеры Width и Height мы их сохранили заранее
        img = img.getScaledInstance(Width*sizex, Height*sizey, Image.SCALE_DEFAULT);

        //img = img.getScaledInstance(img.getWidth(null)*sizex, img.getHeight(null)*sizey, Image.SCALE_DEFAULT);
    }

    public Image getImg()
    {
        return img;
    }
    public Coord getPos()
    {
        return coord;
    }
    public int getWidth()  { return img.getWidth(null); }
    public int getHeight()
    {
        return img.getHeight(null);
    }
}
