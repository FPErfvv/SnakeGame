package app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener, KeyListener {
    private Timer t;
    private Rectangle repaintArea;
    private int boardWidth, boardHeight, delay;
    private Head head;
    private List<Body> bodies;
    private Apple apple;

    private int repaintAreaWidth;
    private int repaintAreaHeight;
    private int headx;
    private int heady;
    private int prevHeadX;
    private int prevHeadY;
    private String prevHeadDirection;
    private int headVelX;
    private int headVelY;
    private int xGrid[];
    private int yGrid[];
    private String direction;
    private boolean newBody;
    private int numBodies;
    private boolean hasCollided;
    private int hasMoved;

    public Board() {
        hasMoved = 0;
        hasCollided = false;
        delay = 50;
        t = new Timer(delay, (ActionListener) this);
        bodies = new ArrayList<Body>();
        
        head = new Head();
        
        boardWidth = 600;
        boardHeight = 600;
        direction = "north";
        prevHeadDirection = direction;

        xGrid = new int[30];
        yGrid = new int[30];

        for (int i = 10; i <= 600; i += 20) {
            xGrid[(i-10)/20] = i-9;
            yGrid[(i-10)/20] = i-9;
        }

        apple = new Apple(xGrid);

        repaintAreaWidth = 65;
        repaintAreaHeight = 65;
        
        repaintArea = new Rectangle(xGrid[head.getX()], yGrid[head.getY()], repaintAreaWidth, repaintAreaHeight);
        headx = head.getX();
        heady = head.getY();
        numBodies = 0;
        //body1 = new Body(headx, heady, numBodies);
        bodies.add(new Body(headx, heady, numBodies));
        numBodies++;
        newBody = false;
        
        prevHeadX = headx;
        prevHeadY = heady;
        headVelX = 0;
        headVelY = 0;

        t.start();
        addKeyListener((KeyListener) this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paint(Graphics g) {
        super.paint(g);
        hasCollided = head.checkCollision(bodies, xGrid[headx], yGrid[heady], numBodies, g);
        if (hasCollided && hasMoved > 1) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, boardWidth, boardHeight);
            g.setColor(Color.BLACK);
            g.drawString("You got a score of "+ numBodies, 300, 300);
        }
        else {
            prevHeadDirection = direction;
            for (int i = 0; i <= boardWidth; i += 20) {
                g.fillRect(i, 0, 1, boardHeight);
                g.fillRect(0, i, boardWidth, 1);
            }
            g.drawRect((int)repaintArea.getX(), (int)repaintArea.getY(), (int)repaintArea.getWidth(), (int)repaintArea.getHeight());
            head.drawHead(xGrid[headx], xGrid[heady], g, direction);
            System.out.println(headx + " " + heady);
            
            if (head.hasTouchedApple(apple.getX(), apple.getY())) {
                apple.setLocation();
                newBody = true;
            }

            if (newBody) {
                bodies.add(new Body(headx, heady, numBodies));
                numBodies++;
                newBody = false;
            }

            apple.drawApple(g);
            for (int i = numBodies -1; i >=0; i--) {
                if (i == 0) {
                    bodies.get(i).move(xGrid[prevHeadX], yGrid[prevHeadY], g, prevHeadDirection);
                } else
                    bodies.get(i).move(bodies.get(i-1).getX(), bodies.get(i-1).getY(), g, bodies.get(i-1).getDirection());
            } 
        }       

    }

    public void actionPerformed(ActionEvent arg0) {
        if (numBodies > 1) {
            for (int i = 1; i < numBodies; i++) { //sets the previous coordinates to previous corrdinates before 
                bodies.get(i).setCoor(bodies.get(i).getX(), bodies.get(i).getY());
            } 
        }
        if (hasCollided  && (hasMoved > 1)) {
        }
        else {
            prevHeadX = headx;
            prevHeadY = heady;
            headx += headVelX;
            heady += headVelY;            
        }

        
        repaintArea.setLocation(xGrid[head.getX()] - 25, yGrid[head.getY()]-25);
        head.setCoordinates(headx, heady);
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        hasMoved++;
        if (code == KeyEvent.VK_UP) {
            headVelX = 0;
            headVelY = -1;
            
            direction = "north";
        }
        if (code == KeyEvent.VK_DOWN) {
            headVelX = 0;
            headVelY = 1;
            
            direction = "south";
        }
        if (code == KeyEvent.VK_LEFT) {
            headVelX = -1;
            headVelY = 0;
            
            direction = "west";
        }
        if (code == KeyEvent.VK_RIGHT) {
            headVelX = 1;
            headVelY = 0;
            
            direction = "east";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }





}