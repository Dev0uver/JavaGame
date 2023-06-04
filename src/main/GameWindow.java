package main;

import buttons.Buttons;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

// Класс игрового окна
public class GameWindow {

    public static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    
    // Создание игрового окна
    public GameWindow(GamePanel gamePanel) {


        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setUndecorated(true);
        frame.setResizable(false);

        frame.add(gamePanel);

        frame.addMouseListener(new MouseAdapter() { // добавление слушателя
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int x =  e.getX(); // получение координат нажатия
                int y =  e.getY();
                if (gamePanel.buttonsList != null){ // проверка на попадание по кнопке
                    for (Buttons button: gamePanel.buttonsList.values()){
                        try {
                            button.onHit(x, y);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.GetGame().WindowFocusLost();
            }
        });
    }
}
