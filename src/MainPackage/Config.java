package MainPackage;

import java.io.*;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp){
        this.gp = gp;
    }

    public void saveConfig(){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));

            //Full Screen Setting
            if(gp.fullScreenOn == true){
                bw.write("On");
            }
            if(gp.fullScreenOn == false){
                bw.write("Off");
            }
            bw.newLine();

            //Music Volume Setting
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE Volume Setting
            bw.write(String.valueOf(gp.sound.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadConfig(){

        try {
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));

            String s = br.readLine();

            //Full Screen
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            //Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE Volume
            s = br.readLine();
            gp.sound.volumeScale = Integer.parseInt(s);
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
