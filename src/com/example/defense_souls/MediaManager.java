package com.example.defense_souls;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

public class MediaManager {
    static MediaManager instance = new MediaManager();  
    private MediaPlayer player;  
    private int currentPlayBgm;  
    private int nextPlayBgm;  
    private int vol, targetVol;
    
    enum PLAY_STAT{  
        STOP,FADEIN,PLAYING,FADEOUT;  
    }  
    private PLAY_STAT stat;  
  
    private MediaManager() {  
        stat = PLAY_STAT.PLAYING;  
        currentPlayBgm = 0;  
        nextPlayBgm = 0;  
        vol = 100;  
        targetVol = 100;
    }  
  
    public static MediaManager get() {  
        return instance;  
    }  
  
    public static void release() {  
        instance.stop();  
        instance = null;  
    }  
  
    public void play(int id, Context context) {
            if (id == currentPlayBgm) { // 現在再生しているので何もしない  
                return;  
            } else {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
                    nextPlayBgm = id;  
                    stat = PLAY_STAT.FADEOUT;  
                }
            }  
    }  
  
    public void update(Context context) {  
        switch (stat) {  
            case STOP:  
                if(nextPlayBgm != 0){  
                    change();
                } else {
                    stop();   
                }
                break;  
            case FADEIN:  
                if (player == null) {  
                    player = MediaPlayer.create(context, currentPlayBgm);  
                    player.setLooping(true);  
                    player.start();  
                    vol = 0;  
                    player.setVolume(vol/100.0f, vol/100.0f);  
                }  
                vol += 2;  
                if (targetVol < vol) {  
                    vol = targetVol;  
                    stat = PLAY_STAT.PLAYING;  
                }  
                player.setVolume(vol/100.0f, vol/100.0f);  
                break;  
            case PLAYING:  
                break;  
            case FADEOUT:  
                if (player != null) {  
                    vol -= 2;  
                    if (vol < 0) {  
                        vol = 0;  
                        stat = PLAY_STAT.STOP;  
                    }  
                    player.setVolume(vol/100.0f, vol/100.0f);  
                } else {  
                    stat = PLAY_STAT.STOP;  
                }  
                break;  
        }  
    }  
  
    public void stop() { // 現在の曲を止める
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        currentPlayBgm = 0;
        nextPlayBgm = 0;
        stat = PLAY_STAT.PLAYING;
    }
  
    public void change() { // 現在の曲を止めて次の曲へ移る
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        currentPlayBgm = nextPlayBgm;
        nextPlayBgm = 0;
        stat = PLAY_STAT.FADEIN;
    }
    
    public void setVolume(int vol) {  
        this.vol = vol;  
    }  
  
    public int getVolume() {  
        return vol;  
    }  
}
