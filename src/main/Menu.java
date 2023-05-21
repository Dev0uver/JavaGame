package main;

import buttons.*;


public class Menu implements Runnable {
    private final GamePanel gamePanel;


//    public void mainMenu(GamePanel gamePanel){
//        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//        int xPos = (int)size.getWidth() / 2;
//        int yPos = (int)size.getHeight() / 2 ;
//
//        buttons play = new playButton(xPos, yPos, gamePanel);
//        buttons exit = new exitButton(xPos, yPos + 70, gamePanel);
//        gamePanel.buttonsList.add(exit);
//        gamePanel.buttonsList.add(play);
//        play.paintButton(gamePanel.getGraphics());
//        exit.paintButton(gamePanel.getGraphics());
//        while (true){
//
//        }
//    }


    public void run() {
//                ActionListener listener = new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        gamePanel.requestFocusInWindow();
//                        Component[] components = gamePanel.getComponents(); // получение компонентов JPanel (кнопки и прочее)
//                        gamePanel.removeAll();// удаление всех объектов JPanel
//                        System.exit(0);
//
//                    }
//
//                };
//
//                    JButton b = new JButton("Сжечь комплюктер");
//                    b.addActionListener(listener);
//
//                    gamePanel.add(b);
//                    try {
//                        wait(1);
//                    }
//                    catch(InterruptedException ex){
//                        ex.printStackTrace();
//                    }
//                    //gamePanel.repaint();
//
//                    Component[] components = gamePanel.getComponents();

        // получение размеров окна
        int xPos = (int) GameWindow.size.getWidth() / 2;
        int yPos = (int) GameWindow.size.getHeight() / 2;

        Buttons play = new PlayButton(xPos, yPos, gamePanel); // создание кнопки "Играть"
        Buttons exit = new ExitButton(xPos, yPos + 70, gamePanel); // создание кнопки "Выход"
        gamePanel.buttonsList.add(play);
        gamePanel.buttonsList.add(exit);
        play.RenderButton(gamePanel.getGraphics()); // отрисовка кнопок
        exit.RenderButton(gamePanel.getGraphics());

    }


    public Menu(GamePanel gamePanel){

        this.gamePanel = gamePanel;
    }

}

