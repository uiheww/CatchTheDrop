import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GameWindow extends JFrame {

    private static GameWindow game_window;
    private static Image background;
    private static  long last_frame_time;
    private static Image game_over;
    private static Image drop;
    private static float drop_left = -100;
    private static float drop_top = -100;
    private static float drop_down = 100;
    private static float drop_right = 200;



    public static void main (String [] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(500, 300);
        game_window.setSize(906, 478);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        GAmeField game_field = new GAmeField();
        game_window.add(game_field);
        game_window.setVisible(true);

    }

    private static void onRepaint (Graphics g) {
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        drop_top = drop_top + drop_down *delta_time; // движение по вертикали
        drop_left = drop_left + drop_right *delta_time; // движение по горизонтали
        g.drawImage(background, 0, 0, null);
        g.drawImage(drop, (int) drop_left, (int) drop_top, null);
//        g.drawImage(game_over, 280, 120, null);
    }

    private  static class GAmeField extends JPanel{

        @ Override
        protected void paintComponent (Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();

        }
    }
}

//реализовать падение капли по диагонали