package GUI;

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

    public static long score = 0;

    public static final Path relativePath = Paths.get("Assets/HighScore.txt");
    public static final File scoreFile = new File(relativePath.toUri());
    private static long highScore;

    public static Font font = new Font("Courier", Font.BOLD, 30); // объявление шрифта

    private final int xPosition = (int) GameWindow.size.getWidth() - 150;

    public static void InitScore() throws FileNotFoundException {
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

        assert data != null;
        highScore = Long.parseLong(data);

        reader.close();
    }

    public void PaintScore(Graphics graphics) {

        Font resetFont = graphics.getFont();
        //graphics.drawLine(100, 100, 150, 150);
        //graphics.setFont(font);

        graphics.drawString("Score: " + String.valueOf(score), xPosition - 50, 40);
        graphics.setFont(resetFont);
        //graphics.drawString("Score: ", xPosition - 35, yPosition + 30);
    }

    public static void PaintHighScore(Graphics graphics) {
        //graphics.setFont(font);
        graphics.drawString("HighScore: " + String.valueOf(highScore), (int) (GameWindow.size.getWidth() / 2), 40);
    }

    public static void saveHighScore() throws IOException {
        if (score > highScore) {
            highScore = score;
            try (FileWriter writer = new FileWriter(scoreFile, false)) {
                writer.write(String.valueOf(highScore));
                writer.flush();
            }
        }
    }

}