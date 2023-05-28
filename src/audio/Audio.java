package audio;


import com.sun.jdi.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
import java.util.Random;

public class Audio{
    private Clip clip;

    private float max;
    private float min;
    public FloatControl musicVolume = null;
    public FloatControl soundsVolume = null;

    public int musicVolumePer = 50;
    private int soundVolumePer = 50;
    private String prevSound;

    private List<String> soundtracks = Arrays.asList(new File("src/Assets/Audio/Soundtrack").list());
    private List<String> shotSounds = Arrays.asList(new File("src/Assets/Audio/ShotSounds").list());
    private List<String> deathSounds = Arrays.asList(new File("src/Assets/Audio/DeathSounds").list());
    public File soundtrack;
    public File shotSound;
    public File deathSound;

    //обработчик
    private LineListener listener = new LineListener() {
        @Override
        public void update(LineEvent event) {
            if (LineEvent.Type.STOP == event.getType()){
                Soundtrack();
            }
        }
    };

     // Регулировка громкости музыки
    public void SetMusicVolume(){

        musicVolume.setValue((max - min) * (musicVolumePer / 100f) + min);
        float a = musicVolume.getValue();

    }
    // Регулировка громкости звуков
    public void SetSoundsVolume(int action){

    }

    //метод генерации случайного саундтрека


    public void Soundtrack() {
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


            musicVolume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            max = musicVolume.getMaximum();
            min = musicVolume.getMinimum();

            //musicVolume.setValue((max + min) / 2);

            //clip.setFramePosition(180000);
            clip.start(); // запуск потока


        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


    public void Shot() {

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


    public void Death() {

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
