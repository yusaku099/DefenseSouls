package com.example.defense_souls;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

public class Media {
    private MediaPlayer mediaBattle;
    private MediaPlayer mediaBoss;
    private MediaPlayer mediaVictory;
    private MediaPlayer mediaDefeat;
    private Context context;
    
    public Media(Context context) {
        this.context = context;
        mediaBattle = MediaPlayer.create(context, R.raw.battle000);
//        mediaBattle.setLooping(true);
        
        mediaBoss = MediaPlayer.create(context, R.raw.battle003);
//        mediaBoss.setLooping(true);
        
        mediaVictory = MediaPlayer.create(context, R.raw.victory001);
        mediaDefeat = MediaPlayer.create(context, R.raw.defeat001);
    }

    public void playMedia(int id) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (id == Common.MEDIA_BATTLE) {
                if (mediaBattle.isPlaying() == false) {
                    mediaBattle.start();
                }
            }
            if (id == Common.MEDIA_BOSS) {
                if (mediaBoss.isPlaying() == false) {
                    mediaBoss.start();
                }
            }
            if (id == Common.MEDIA_VICTORY001) {
                if (mediaVictory.isPlaying() == false) {
                    mediaVictory.start();
                }
            }
            if (id == Common.MEDIA_DEFEAT001) {
                if (mediaDefeat.isPlaying() == false) {
                    mediaDefeat.start();
                }
            }
        }
    }

    public void pauseMedia(int id) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (id == Common.MEDIA_BATTLE) {
                if (mediaBattle.isPlaying() == true) {
                    mediaBattle.pause();
                }
            }
            if (id == Common.MEDIA_BOSS) {
                if (mediaBoss.isPlaying() == true) {
                    mediaBoss.pause();
                }
            }
            if (id == Common.MEDIA_VICTORY001) {
                if (mediaVictory.isPlaying() == true) {
                    mediaVictory.pause();
                }
            }
            if (id == Common.MEDIA_DEFEAT001) {
                if (mediaDefeat.isPlaying() == true) {
                    mediaDefeat.pause();
                }
            }
        }
    }

    public void pauseAllMedia() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (mediaBattle.isPlaying() == true) {
                mediaBattle.pause();
            }
            if (mediaBoss.isPlaying() == true) {
                mediaBoss.pause();
            }
            if (mediaVictory.isPlaying() == true) {
                mediaVictory.pause();
            }
            if (mediaDefeat.isPlaying() == true) {
                mediaDefeat.pause();
            }
        }
    }
    
    public MediaPlayer getMediaBattle() {
        return mediaBattle;
    }

    public void setMediaBattle(MediaPlayer mediaBattle) {
        this.mediaBattle = mediaBattle;
    }

    public MediaPlayer getMediaBoss() {
        return mediaBoss;
    }

    public void setMediaBoss(MediaPlayer mediaBoss) {
        this.mediaBoss = mediaBoss;
    }

    public MediaPlayer getMediaVictory() {
        return mediaVictory;
    }

    public void setMediaVictory(MediaPlayer mediaVictory) {
        this.mediaVictory = mediaVictory;
    }

    public MediaPlayer getMediaDefeat() {
        return mediaDefeat;
    }

    public void setMediaDefeat(MediaPlayer mediaDefeat) {
        this.mediaDefeat = mediaDefeat;
    }

}
