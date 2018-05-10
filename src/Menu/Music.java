package Menu;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music {

    private String musicName;
    private URI uri;
    private URL url;
    private AudioClip aau;

    public Music(String musicName) {

        this.musicName = musicName;

        File music = new File(musicName);

        try {
            this.uri = music.toURI();
            //解析地址
            this.url = uri.toURL();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Play(boolean start){

        try{
            if (start == true){
                aau = Applet.newAudioClip(url);
                aau.loop();
            }else {
                stopPlay(aau);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stopPlay(AudioClip ac){

        try {
            ac.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
