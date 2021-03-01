package app;

//private List<Missile> missiles;
//missiles = new ArrayList<>();
import javax.swing.JFrame;

public class SnakeGame {
    static JFrame f = new JFrame();

    public static void main(String[] args) {

        Board board = new Board();
        f.add(board);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(615, 637);
        

        
    }

}