package audio;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
    public String sound = "Assets//Audio//1-01-Dark-Souls-III.wav"; // путь к файлу с фоновой музыкой
    private String shot = "Assets/Audio/HAN02.wav"; // путь к файлу со звуком выстрела
    private String kill = "Assets/Audio/r2d2_scream_converted.wav"; // путь к файлу со звуком смерти пришельца
    private Clip clip;
    public void soundtrack() {

        File sound = new File(this.sound);
        AudioInputStream tr = null; // поток аудиовывода
        try {
            tr = AudioSystem.getAudioInputStream(sound);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(tr);
            //clip.setFramePosition(0);
            clip.start(); // запуск потока
            clip.loop(Clip.LOOP_CONTINUOUSLY); // зацикливание потока
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void shot() {

        File shot = new File(this.shot);
        AudioInputStream tr1 = null; // поток аудиовывода
        try {
            tr1 = AudioSystem.getAudioInputStream(shot);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(tr1);
            //clip.setFramePosition(0);
            clip.start(); // запуск потока
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kill(){
        File kill = new File(this.kill);
        AudioInputStream tr2 = null; // поток аудиовывода
        try {
            tr2 = AudioSystem.getAudioInputStream(kill);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(tr2);
            //clip.setFramePosition(0);
            clip.start(); // запуск потока
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
