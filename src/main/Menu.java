package main;

import buttons.*;

import java.awt.*;


public class Menu {
    private final GamePanel gamePanel;
    Font font = new Font("Courier", Font.BOLD, 70);
    Font textFont = new Font("Courier", Font.BOLD, 26);
    private final int xPos = (int) GameWindow.size.getWidth() / 2;
    private final int yPos = (int) GameWindow.size.getHeight() / 2;

    public Buttons play;
    public Buttons exit;
    public Buttons retry;
    public Buttons settings;
    public Buttons back;
    public Buttons increaseVolume;
    public Buttons reduceVolume;


    public void InitButtons() {

        play = new PlayButton(xPos - PlayButton.width / 2, yPos + PlayButton.height / 2, gamePanel);
        exit = new ExitButton(xPos - ExitButton.width / 2, (yPos + ExitButton.height * 3), gamePanel);
        retry = new RetryButton(xPos - RetryButton.width / 2, yPos + RetryButton.height / 2, gamePanel);
        settings = new SettingsButton(xPos - SettingsButton.width / 2, (int)(yPos + SettingsButton.height * 1.75), gamePanel);
        increaseVolume = new IncreaseVolume(xPos + (BackButton.width / 2) - IncreaseVolume.width, yPos + IncreaseVolume.height / 2, gamePanel);
        reduceVolume = new ReducedVolume(xPos - BackButton.width / 2, yPos + ReducedVolume.height / 2, gamePanel);
        back = new BackButton(xPos - BackButton.width / 2, (int)(yPos + BackButton.height * 1.75), gamePanel);
    }

    public void GameOver() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Game over", xPos - (int)("Game over".length() * font.getSize() * 0.6) / 2 , yPos - font.getSize() / 2);
        RenderButtons(graphics);
    }



    public void MainMenu() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("Main menu", xPos - (int)("Main menu".length() * font.getSize() * 0.6) / 2 , yPos - font.getSize() / 2);
        RenderButtons(graphics);
    }


    public void Settings() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(textFont);
        graphics.setColor(Color.white);

        graphics.drawString(
                "Volume level: " + gamePanel.GetGame().GetVolumePer() + "%",
                gamePanel.buttonsList.get(0).xPosition + (int)(ReducedVolume.width * 1.5),
                gamePanel.buttonsList.get(0).yPosition + (int)(ReducedVolume.height * 0.75)
        );

        RenderButtons(graphics);
    }

    private void RenderButtons(Graphics graphics) {
        for (int i = 0; i < gamePanel.buttonsList.size(); i++) {
            gamePanel.buttonsList.get(i).RenderButton(graphics); // отрисовка кнопок
        }
    }

    public Menu(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        InitButtons();
    }
}

