package main;

import buttons.*;

import java.awt.*;


public class Menu {
    private final GamePanel gamePanel;

    Font font = new Font("Courier", Font.BOLD, 70);

    Font textFont = new Font("Courier", Font.BOLD, 26);

    private final int xPos = (int) GameWindow.size.getWidth() / 2;
    private final int yPos = (int) GameWindow.size.getHeight() / 2;

    Buttons play, exit, retry, settings, volume, back, incrVolume, reducedVolume;


    public void InitButtons() {

        play = new PlayButton(xPos - PlayButton.width / 2, yPos + PlayButton.height / 2, gamePanel);
        exit = new ExitButton(xPos - ExitButton.width / 2, (yPos + ExitButton.height * 3), gamePanel);
        retry = new RetryButton(xPos - RetryButton.width / 2, yPos + RetryButton.height / 2, gamePanel);
        settings = new SettingsButton(xPos - SettingsButton.width / 2, (int)(yPos + SettingsButton.height * 1.75), gamePanel);
        //volume = new VolumeButton(xPos - VolumeButton.width / 2, yPos + VolumeButton.height / 2, gamePanel);
        incrVolume = new IncreaseVolume(xPos + (BackButton.width / 2) - IncreaseVolume.width, yPos + IncreaseVolume.height / 2, gamePanel);
        reducedVolume = new ReducedVolume(xPos - BackButton.width / 2, yPos + ReducedVolume.height / 2, gamePanel);
        back = new BackButton(xPos - BackButton.width / 2, (int)(yPos + BackButton.height * 1.75), gamePanel);
    }

    public void Defeat() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString("GameOver", xPos - 90, yPos - 70);
        gamePanel.buttonsList.clear();
        gamePanel.buttonsList.add(exit);
        gamePanel.buttonsList.add(settings);
        gamePanel.buttonsList.add(retry);
        RenderButtons(graphics);
    }



    public void MainMenu() {

        Graphics graphics = gamePanel.getGraphics();
        graphics.setFont(font);
        graphics.setColor(Color.white);
        // - ("Main menu".length() * font.getSize()) / 2 - font.getSize() / 2
        graphics.drawString("Main menu", xPos - (int)("Main menu".length() * font.getSize() * 0.6) / 2 , yPos - font.getSize() / 2);
        gamePanel.buttonsList.clear();
        gamePanel.buttonsList.add(play);
        gamePanel.buttonsList.add(settings);
        gamePanel.buttonsList.add(exit);
        RenderButtons(graphics);
    }


    public void Settings() {

        Graphics graphics = gamePanel.getGraphics();
        //gamePanel.paintComponent(graphics);
        graphics.setFont(textFont);
        graphics.setColor(Color.white);

        gamePanel.buttonsList.clear();
        gamePanel.buttonsList.add(reducedVolume);
        gamePanel.buttonsList.add(incrVolume);
        gamePanel.buttonsList.add(back);

        graphics.drawString(
                "Volume level: " + gamePanel.GetGame().GetVolumePer() + "%",
                gamePanel.buttonsList.get(0).xPosition + (int)(ReducedVolume.width * 1.5) ,
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

