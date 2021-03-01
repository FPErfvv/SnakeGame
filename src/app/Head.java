package app;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Head {
    private Image north, east, south, west;
    private int x, y;

    public Head() {
        x = 15;
        y = 15;
        north = new ImageIcon("src/images/snake-head-north.png").getImage();
        east = new ImageIcon("src/images/snake-head-east.png").getImage();
        south = new ImageIcon("src/images/snake-head-south.png").getImage();
        west = new ImageIcon("src/images/snake-head-west.png").getImage();

    }

    public void drawHead(int headX, int headY, Graphics g, String direction) {
        if (direction.equals("north"))
            g.drawImage(north, headX, headY, null);
        else if (direction.equals("east"))
            g.drawImage(east, headX, headY, null);
        else if (direction.equals("south"))
            g.drawImage(south, headX, headY, null);
        else if (direction.equals("west"))
            g.drawImage(west, headX, headY, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinates(int headX, int headY) {
        x = headX;
        y = headY;
    }

    public boolean hasTouchedApple(int appleX, int appleY) {
        if (appleX == x && appleY == y) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCollision(List<Body> bodies, int headx, int heady, int numBodies, Graphics g) {
        boolean hasCollided = false;
        int boardWidth = 615;
        int boardHeigth = 637;
        
        for (Body b: bodies) 
            if (b.getX() == headx && b.getY() == heady) {
                hasCollided = true;
                
            }
        if (headx < 0 || headx > 600 || heady < 0 || heady > 600)
            hasCollided = true;
            

        return hasCollided;
        
        
    }

}