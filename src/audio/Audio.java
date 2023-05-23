package audio;


import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
import java.util.Random;

public class Audio{
    private static Clip clip;

    private static String prevSound;

    private static Random random = new Random();
    private static List<String> soundtracks = Arrays.asList(new File("src/Assets/Audio/Soundtrack").list());
    private static List<String> shotSounds = Arrays.asList(new File("src/Assets/Audio/ShotSounds").list());
    private static List<String> deathSounds = Arrays.asList(new File("src/Assets/Audio/DeathSounds").list());
    public static File soundtrack;
    public static File shotSound;
    public static File deathSound;

    //обработчик
    private static LineListener listener = new LineListener() {
        @Override
        public void update(LineEvent event) {
            if (LineEvent.Type.STOP == event.getType()){
                Soundtrack();
            }
        }
    };


    //метод генерации случайного саундтрека


    public static void Soundtrack() {
            int index = 0;

        try {
            Collections.shuffle(soundtracks);
            if ((prevSound == soundtracks.get(index)) & (soundtracks.size() > 1)){
                index++;
            }
            prevSound = soundtracks.get(index);
            soundtrack  = new File("src/Assets/Audio/Soundtrack/" + soundtracks.get(index));
            clip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(soundtrack);
            //подключение обработчика событий
            clip.addLineListener(listener);
            clip.open(input);
            clip.start(); // запуск потока


        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


    public static void Shot() {

        Collections.shuffle(shotSounds);

        try {
            shotSound = new File("src/Assets/Audio/ShotSounds/" + shotSounds.get(0));
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

        Collections.shuffle(deathSounds);

        try {
            deathSound = new File("src/Assets/Audio/DeathSounds/" + deathSounds.get(0));
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
