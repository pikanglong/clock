package cn.edu.njupt.clock.utils;

import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MP3Player extends Thread {
    private String filename;
    private AdvancedPlayer player;

    public MP3Player(String filename) {
        this.filename = filename;
    }

    public void run() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filename));
            player = new AdvancedPlayer(bufferedInputStream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
