package audio;


import com.sun.jdi.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;
import java.util.Random;

public class Audio{
    private Clip musicClip;
    private Clip shotClip;
    private Clip deathClip;
    private Clip lossClip;
    private float max;
    private float min;
    public FloatControl musicVolume = null;
    public FloatControl shotVolume = null;
    public FloatControl deathVolume = null;

    public FloatControl lossVolume = null;

    public int musicVolumePer = 70;
    private String prevSound;
    private List<String> soundtracks = Arrays.asList(new File("src/Assets/Audio/Soundtrack").list());
    private List<String> shotSounds = Arrays.asList(new File("src/Assets/Audio/ShotSounds").list());
    private List<String> deathSounds = Arrays.asList(new File("src/Assets/Audio/DeathSounds").list());
    private List<String> lossSounds = Arrays.asList(new File("src/Assets/Audio/LossSounds").list());
    public File soundtrack;
    public File shotSound;
    public File deathSound;
    public File lossSound;

    //обработчик
    private final LineListener listener = new LineListener() {
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
            musicClip = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(soundtrack);
            //подключение обработчика событий
            musicClip.addLineListener(listener);
            musicClip.open(input);


            musicVolume = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            max = musicVolume.getMaximum();
            min = musicVolume.getMinimum();
            SetMusicVolume();

            musicClip.start(); // запуск потока




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
            shotClip = AudioSystem.getClip();
            shotClip.open(AudioSystem.getAudioInputStream(shotSound));
            shotVolume = (FloatControl) shotClip.getControl(FloatControl.Type.MASTER_GAIN);
            shotVolume.setValue((max - min) * (musicVolumePer / 100f) + min);
            shotClip.start(); // запуск потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


    public void Death() {

        try {
            deathSound = new File("src/Assets/Audio/DeathSounds/" + deathSounds.get(0));
            deathClip = AudioSystem.getClip();
            deathClip.open(AudioSystem.getAudioInputStream(deathSound));
            deathVolume = (FloatControl) deathClip.getControl(FloatControl.Type.MASTER_GAIN);
            deathVolume.setValue((max - min) * (musicVolumePer / 100f) + min);
            deathClip.start(); // запуск потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void Loss(){
        Collections.shuffle(lossSounds);

        try {
            lossSound = new File("src/Assets/Audio/LossSounds/" + lossSounds.get(0));
            lossClip = AudioSystem.getClip();
            lossClip.open(AudioSystem.getAudioInputStream(lossSound));
            lossVolume = (FloatControl) lossClip.getControl(FloatControl.Type.MASTER_GAIN);
            lossVolume.setValue((max - min) * (musicVolumePer / 100f) + min);
            lossClip.start(); // запуск потока
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }


}
