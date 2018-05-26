package com.betterme.tankwar.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music {

    private URL url;
    private AudioClip aau;

    public Music(){}
    public Music(String musicUrl) {

        File music = new File(musicUrl);

        try {
            URI uri = music.toURI();
            //解析地址
            this.url = uri.toURL();

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.aau = Applet.newAudioClip(this.url);
    }

    public void play(){
        aau.play();
    }

    public void loopPlay(){
        aau.loop();
    }

    public void stopPlay(){
        aau.stop();
    }

}
