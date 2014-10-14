package com.example.defense_souls;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;

public class Sound {
    private SoundPool mSoundPool = new SoundPool(Common.SOUND_MAX, AudioManager.STREAM_MUSIC, 0);
    private int mSound[] = new int[Common.SOUND_MAX];
    private Context context;
    
    public Sound(Context context) {
        this.context = context;
        mSound[Common.SOUND_SYSTEM001] = mSoundPool.load(context, R.raw.system002, 0);
        mSound[Common.SOUND_ATTACK001] = mSoundPool.load(context, R.raw.attack001, 0);
        mSound[Common.SOUND_ATTACK002] = mSoundPool.load(context, R.raw.attack002, 0);
        mSound[Common.SOUND_ATTACK003] = mSoundPool.load(context, R.raw.attack003, 0);
        mSound[Common.SOUND_SHOT001] = mSoundPool.load(context, R.raw.shot001, 0);
        mSound[Common.SOUND_FIRE001] = mSoundPool.load(context, R.raw.fire001, 0);
        mSound[Common.SOUND_ICE001] = mSoundPool.load(context, R.raw.ice001, 0);
        mSound[Common.SOUND_LIGHT001] = mSoundPool.load(context, R.raw.light001, 0);
        mSound[Common.SOUND_EXPLOSION001] = mSoundPool.load(context, R.raw.explosion001, 0);
        mSound[Common.SOUND_SUPPORT001] = mSoundPool.load(context, R.raw.support001, 0);
        mSound[Common.SOUND_SUPPORT002] = mSoundPool.load(context, R.raw.support002, 0);
        mSound[Common.SOUND_TELEPORT001] = mSoundPool.load(context, R.raw.teleport001, 0);
        mSound[Common.SOUND_WATER001] = mSoundPool.load(context, R.raw.water001, 0);
    }

    public void playSound(int id) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            mSoundPool.play(mSound[id], 1.0F, 1.0F, 0, 0, 1.0F);
        }
    }

    public void releaseSound() {
        mSoundPool.release();
    }

}
