package MainPackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/Sound/BeansLullaby.wav");
        soundURL[1] = getClass().getResource("/res/Sound/CoinTrimmed.wav");
        soundURL[2] = getClass().getResource("/res/Sound/FanfareTrimmed.wav");
        soundURL[3] = getClass().getResource("/res/Sound/PowerUpTrimmed.wav");
        soundURL[4] = getClass().getResource("/res/Sound/UnlockTrimmed.wav");
        soundURL[5] = getClass().getResource("/res/Sound/TitleTrimmed.wav");
        soundURL[6] = getClass().getResource("/res/Sound/OverworldTrimmed2.wav");
        soundURL[7] = getClass().getResource("/res/Sound/MenuChime.wav");
        soundURL[8] = getClass().getResource("/res/Sound/SlimeA.wav");
        soundURL[9] = getClass().getResource("/res/Sound/PlayerA.wav");
        soundURL[10] = getClass().getResource("/res/Sound/MonsterHit.wav");
        soundURL[11] = getClass().getResource("/res/Sound/Fall.wav");
        soundURL[12] = getClass().getResource("/res/Sound/LevelUp.wav");
        soundURL[13] = getClass().getResource("/res/Sound/InventoryChime.wav");
        soundURL[14] = getClass().getResource("/res/Sound/Cursor.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
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

}
