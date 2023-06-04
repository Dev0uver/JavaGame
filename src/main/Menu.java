package main;

import buttons.*;

import java.awt.*;


public class Menu {
    private final GamePanel gamePanel;

    Font font = new Font("Courier", Font.BOLD, 70);

    private final int xPos = (int) GameWindow.size.getWidth() / 2;
    private final int yPos = (int) GameWindow.size.getHeight() / 2;

    Buttons play, exit, retry;


    public void InitButtons() {

        play = new PlayButton(xPos - PlayButton.width / 2, yPos + PlayButton.height / 2, gamePanel);
        exit = new ExitButton(xPos - ExitButton.width / 2, (int) (yPos + ExitButton.height * 1.5), gamePanel);
        retry = new RetryButton(xPos - RetryButton.width / 2, yPos + RetryButton.height / 2, gamePanel);
    }

    public void Defeat() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("GameOver", xPos - 90, yPos - 70);

        gamePanel.buttonsList.put("exit", exit);
        gamePanel.buttonsList.put("retry", retry);
        gamePanel.buttonsList.get("retry").RenderButton(gamePanel.getGraphics()); // отрисовка кнопок
        gamePanel.buttonsList.get("exit").RenderButton(gamePanel.getGraphics());
    }



    public void MainMenu() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Main menu", xPos - 90, yPos - 70);

        gamePanel.buttonsList.put("play", play);
        gamePanel.buttonsList.put("exit", exit);
        gamePanel.buttonsList.get("play").RenderButton(gamePanel.getGraphics()); // отрисовка кнопок
        gamePanel.buttonsList.get("exit").RenderButton(gamePanel.getGraphics());
    }


    public Menu(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }
}

