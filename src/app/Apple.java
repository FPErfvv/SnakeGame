package app;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple {
    private int x;
    private int y;
    private Image image;
    private Random rand;
    private int[] grid;

    public Apple(int[] agrid) {

        image = new ImageIcon("src/images/snake-apple.png").getImage();
        grid = agrid;
        rand = new Random();
        x = rand.nextInt(30);;
        y = rand.nextInt(30);;
    }

    public void setLocation() {
        x = rand.nextInt(30);
        y = rand.nextInt(30);
    }
    public void drawApple(Graphics g) {
        g.drawImage(image, grid[x], grid[y], null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}