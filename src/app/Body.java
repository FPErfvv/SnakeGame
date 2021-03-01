package app;

import javax.swing.*;
import java.awt.*;

public class Body {
    private Image horiz, verti;
    private int x, y, prevX, prevY, index;
    private String direction;

    public Body(int headx, int heady, int gridx[], int gridy[]){
        horiz = new ImageIcon("src/images/snake-body-horiz.png").getImage();
        verti = new ImageIcon("src/images/snake-body-verti.png").getImage();
        x = headx;
        y = heady;
    }

    public Body(int ax, int ay, int aindex) {
        horiz = new ImageIcon("src/images/snake-body-horiz.png").getImage();
        verti = new ImageIcon("src/images/snake-body-verti.png").getImage();
        x = ax;
        y = ay;
        index = aindex;
        prevX = x;
        prevY = y;
    }

    

    public void move(int ax, int ay, Graphics g, String d) {
        x = ax;
        y = ay;
        if (d.equals("north") || d.equals("south") || d.equals("vertical")) {
            g.drawImage(verti, x, y, null);
            
            direction = "vertical";
        }
            
        else if (d.equals("east") || d.equals("west") || d.equals("horizontal")) {
            g.drawImage(horiz, x, y, null);
            direction = "horizontal";
        }
            
    }

    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setCoor(int px, int py) {
        x = px;
        y = py;
    }

    public String getDirection() {
        return direction;
    }

    public int getIndex() {
        return index;
    }
}