package MainPackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[50];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){
        soundURL[0] = getClass().getResource("/res/Sound/BeansLullaby.wav");
        soundURL[1] = getClass().getResource("/res/Sound/CoinTrimmed.wav");
        soundURL[2] = getClass().getResource("/res/Sound/FanfareTrimmed.wav");
        soundURL[3] = getClass().getResource("/res/Sound/PowerUpTrimmed.wav");
        soundURL[4] = getClass().getResource("/res/Sound/UnlockTrimmed.wav");
        soundURL[5] = getClass().getResource("/res/Sound/TitleTrimmed.wav");
        soundURL[6] = getClass().getResource("/res/Sound/OverworldTrimmed2.wav");
        soundURL[7] = getClass().getResource("/res/Sound/MenuChime.wav");
        soundURL[8] = getClass().getResource("/res/Sound/PlayerTakesHit.wav");
        soundURL[9] = getClass().getResource("/res/Sound/PlayerA.wav");
        soundURL[10] = getClass().getResource("/res/Sound/MonsterHit.wav");
        soundURL[11] = getClass().getResource("/res/Sound/Fall.wav");
        soundURL[12] = getClass().getResource("/res/Sound/LevelUp.wav");
        soundURL[13] = getClass().getResource("/res/Sound/InventoryChime.wav");
        soundURL[14] = getClass().getResource("/res/Sound/Cursor.wav");
        soundURL[15] = getClass().getResource("/res/Sound/Fireball.wav");
        soundURL[16] = getClass().getResource("/res/Sound/PlayerA2.wav");
        soundURL[17] = getClass().getResource("/res/Sound/TreeFall.wav");
        soundURL[18] = getClass().getResource("/res/Sound/OptionsD.wav");
        soundURL[19] = getClass().getResource("/res/Sound/OptionsU.wav");
        soundURL[20] = getClass().getResource("/res/Sound/PlayerDeath.wav");
        soundURL[21] = getClass().getResource("/res/Sound/DoorOpen.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){

        }
    }

    public void play(){
        clip.start();

    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void loopOnce(){
        clip.loop(1);

    }
    public void stop(){
        clip.stop();

    }
    public void checkVolume(){
        switch(volumeScale){
            case 0 : volume =  - 80f; break;
            case 1 : volume =  -20f; break;
            case 2 : volume =  -12f; break;
            case 3 : volume =  -5f; break;
            case 4 : volume =  1f; break;
            case 5 : volume =  6f; break;
        }
        fc.setValue(volume);
    }

}
