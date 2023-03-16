package main;

import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class Menu implements Runnable {
    public GamePanel gamePanel;

    //public static JScrollPane content = new JScrollPane();
    public boolean flag = true;
    public List<JButton> controls = new ArrayList<>();
    public GamePanel mainMenu(GamePanel gamePanel){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.requestFocusInWindow();
                Component[] components = gamePanel.getComponents(); // получение компонентов JPanel (кнопки и прочее)
                gamePanel.removeAll();// удаление всех объектов JPanel
                System.exit(0);


            }

        };

        JButton exit = new JButton("Сжечь комплюктер");
        exit.addActionListener(listener);

        gamePanel.add(exit);

        return gamePanel;

    }


    public void run() {
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePanel.requestFocusInWindow();
                        Component[] components = gamePanel.getComponents(); // получение компонентов JPanel (кнопки и прочее)
                        gamePanel.removeAll();// удаление всех объектов JPanel
                        System.exit(0);

                    }

                };

                    JButton b = new JButton("Сжечь комплюктер");
                    b.addActionListener(listener);

                    gamePanel.add(b);
                    try {
                        wait(1);
                    }
                    catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                    //gamePanel.repaint();

                    Component[] components = gamePanel.getComponents();
                }



    public Menu(){

    }



}

