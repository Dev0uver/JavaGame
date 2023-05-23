package main;

import buttons.*;

import java.awt.*;


public class Menu {
    private final GamePanel gamePanel;

    Font font = new Font("Courier", Font.BOLD, 70);


    public void Defeat(){
        int xPos = (int) GameWindow.size.getWidth() / 2;
        int yPos = (int) GameWindow.size.getHeight() / 2;

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("GameOver", xPos - 90, yPos - 70);

        Buttons playAgain = new PlayAgain(xPos - 90, yPos, gamePanel); // создание кнопки "Играть"
        Buttons exit = new ExitButton(xPos - 35, yPos + 70, gamePanel); // создание кнопки "Выход"
        gamePanel.buttonsList.add(playAgain);
        gamePanel.buttonsList.add(exit);
        playAgain.RenderButton(gamePanel.getGraphics()); // отрисовка кнопок
        exit.RenderButton(gamePanel.getGraphics());
    }



    public void MainMenu() {

        // получение размеров окна
        if(gamePanel.buttonsList.size() == 0) {
            int xPos = (int) GameWindow.size.getWidth() / 2;
            int yPos = (int) GameWindow.size.getHeight() / 2;

            Graphics graphics = gamePanel.getGraphics();
            graphics.setFont(font);
            graphics.setColor(Color.white);
            graphics.drawString("Main menu", xPos - 90, yPos - 70);

            Buttons play = new PlayButton(xPos - 35, yPos, gamePanel); // создание кнопки "Играть"
            Buttons exit = new ExitButton(xPos - 35, yPos + 70, gamePanel); // создание кнопки "Выход"
            gamePanel.buttonsList.add(play);
            gamePanel.buttonsList.add(exit);
            play.RenderButton(gamePanel.getGraphics()); // отрисовка кнопок
            exit.RenderButton(gamePanel.getGraphics());
        }

    }


    public Menu(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

}

