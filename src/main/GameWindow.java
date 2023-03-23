package main;

import buttons.buttons;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Класс игрового окна
public class GameWindow {

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    public static int width = (int)size.getWidth();
    public static int height = (int)size.getHeight();



    // Создание игрового окна
    public GameWindow(GamePanel gamePanel) {

        //GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //DisplayMode newMode = new DisplayMode(width, height, 32, 60);
        //device.setDisplayMode(newMode);

        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setUndecorated(true);
        frame.setResizable(false);
        //frame.getContentPane().add(gamePanel);
        //frame.pack();
        frame.setSize(width, height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);

        frame.addMouseListener(new MouseAdapter() { // добавление слушателя
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x =  e.getX(); // получение координат нажатия
                int y =  e.getY();
                if (gamePanel.buttonsList != null){ // проверка на попадание по кнопке
                        for (buttons button: gamePanel.buttonsList){
                            button.onHit(x,y);
                    }
                }
            }

        });

        frame.add(gamePanel);

        frame.setVisible(true);

    }

}
