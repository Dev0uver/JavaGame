package audio;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
    private static Clip clip;

    public static File soundtrack = new File("src/Assets/Audio/1-01-Dark-Souls-III.wav");
    public static File shotSound = new File("src/Assets/Audio/HAN02.wav");
    public static File deathSound = new File("src/Assets/Audio/r2d2_scream_converted.wav");


    public static void Soundtrack() {

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundtrack));
            clip.start(); // запуск потока
            clip.loop(Clip.LOOP_CONTINUOUSLY); // зацикливание потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


    public static void Shot() {


        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(shotSound));
            clip.start(); // запуск потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


    public static void Death() {

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(deathSound));
            clip.start(); // запуск потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

}
