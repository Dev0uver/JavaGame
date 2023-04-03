package main;

import buttons.Buttons;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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



        frame.add(gamePanel);

        frame.addMouseListener(new MouseAdapter() { // добавление слушателя
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x =  e.getX(); // получение координат нажатия
                int y =  e.getY();
                if (gamePanel.buttonsList != null){ // проверка на попадание по кнопке
                    for (Buttons button: gamePanel.buttonsList){
                        try {
                            button.onHit(x,y);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }

        });

        frame.setVisible(true);

    }

}
