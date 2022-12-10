import SSFML.Sprite;
import SSFML.Window;

class map
{
    private int hp;
    private int dm;
    private Sprite model;

    map(String name, int hp, int dm)
    {
        model = new Sprite();
        model.setTexture("src/img/" + name + ".png");
    }

    boolean isLife()
    {
        if (hp <= 0) return false;
        return true;
    }
    int getHp() { return hp; }
    int getDm() {return  dm; }
    Sprite getModel() { return model; }

    void setHp(int n) { hp = n; }
    void setDm(int d) { dm = d; }
}

class Engine
{
    Sprite mainmap;
    Window window;

    Engine(Window w)
    {
        window = w;
        mainmap = new Sprite();
        mainmap.setTexture("src/img/MainMap.png");
    }

    void start(map[] Mymaps, map[] Opmaps)
    {
        for (int j = 0; j < Mymaps.length; j++)
            window.draw(Mymaps[j].getModel());
        for (int j = 0; j < Opmaps.length; j++)
            window.draw(Opmaps[j].getModel());


        boolean meLife = false;
        boolean OpLife = false;
        //всего ходов 6 за цикл
        while (true) {
            //display
            window.display();

            for (int j = 0; j < 6; j++) {
                if (Mymaps[j].isLife() && Opmaps[j].isLife())
                    Opmaps[j].setHp(Opmaps[j].getHp() - Mymaps[j].getDm());
                else
                if (j+3 < 6)
                    if (Mymaps[j].isLife() && Opmaps[j+3].isLife())
                        Opmaps[j+3].setHp(Opmaps[j+3].getHp() - Mymaps[j].getDm());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (Opmaps[j].isLife() && Mymaps[j].isLife())
                    Mymaps[j].setHp(Mymaps[j].getHp() - Opmaps[j].getDm());
                else
                if (j+3 < 6)
                    if (Opmaps[j].isLife() && Mymaps[j+3].isLife())
                        Mymaps[j+3].setHp(Mymaps[j+3].getHp() - Opmaps[j].getDm());
            }
            for (int j = 0; j < 6; j++) {
                if (Opmaps[j].isLife()) {
                    OpLife = true;
                    break;
                }
                if (Mymaps[j].isLife()) {
                    meLife = true;
                    break;
                }
            }
            if (!meLife || !OpLife) break;
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Window window = new Window();
        window.setSize(400, 400);

        Engine e = new Engine(window);
        map[] m1 = new map[6];
        map[] m2 = new map[6];

        m1[0] = new map("Robot", 10, 1);
        m1[1] = new map("Robot", 10, 1);
        m1[2] = new map("Robot", 10, 1);
        m1[3] = new map("Robot", 10, 1);
        m1[4] = new map("Robot", 10, 1);
        m1[5] = new map("Robot", 10, 1);
        m2[0] = new map("Robot", 10, 1);
        m2[1] = new map("Robot", 10, 1);
        m2[2] = new map("Robot", 10, 1);
        m2[3] = new map("Robot", 10, 1);
        m2[4] = new map("Robot", 10, 1);
        m2[5] = new map("Robot", 10, 1);


        e.start(m1, m2);
    }
}