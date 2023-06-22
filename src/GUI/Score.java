package GUI;

import main.GameSettings;
import main.GameWindow;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Score {

    public long score = 0;
    public int wave = 1;
    private static final Path relativePath = Paths.get("src/Assets/HighScore.txt");
    private final File scoreFile = new File(relativePath.toUri());
    private static long highScore;

    public void InitScore() throws FileNotFoundException {

        if (!scoreFile.exists()) {
            try {
                boolean fileCreated = scoreFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Scanner reader = new Scanner(scoreFile);
        String data = null;
        while (reader.hasNextLine()) {
            data = reader.nextLine();
        }

        if (data != null) {
            highScore = Long.parseLong(data);
        }

        reader.close();
    }

    public void RenderScore(Graphics graphics) {

        graphics.setFont(GameSettings.GUIFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + score, (int) ((int) (GameWindow.size.getWidth() * 0.9) - ("HighScore: ".length() * GameSettings.GUIFont.getSize() * 0.6) / 2), GameSettings.GUIFont.getSize());
    }

    // счетчик волн
    public void RenderWave(Graphics graphics) {

        graphics.setFont(GameSettings.GUIFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Wave: " + wave, (int) ((int) (GameWindow.size.getWidth() * 0.8) - ("HighScore: ".length() * GameSettings.GUIFont.getSize() * 0.6) / 2), GameSettings.GUIFont.getSize());
    }

    public void RenderHighScore(Graphics graphics) {

        graphics.setFont(GameSettings.GUIFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("HighScore: " + highScore, (int) ((int) (GameWindow.size.getWidth() / 2) - ("HighScore: ".length() * GameSettings.GUIFont.getSize() * 0.6) / 2), GameSettings.GUIFont.getSize());
    }

    public void SaveHighScore() throws IOException {

        if (score > highScore) {
            highScore = score;
            try (FileWriter writer = new FileWriter(scoreFile, false)) {
                writer.write(String.valueOf(highScore));
                writer.flush();
            }
        }
    }

}
