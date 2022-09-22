package MainPackage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/res/Sound/OverworldTrimmed.wav");
        soundURL[1] = getClass().getResource("/res/Sound/CoinTrimmed.wav");
        soundURL[2] = getClass().getResource("/res/Sound/FanfareTrimmed.wav");
        soundURL[3] = getClass().getResource("/res/Sound/PowerUpTrimmed.wav");
        soundURL[4] = getClass().getResource("/res/Sound/UnlockTrimmed.wav");
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
