package com.example.defense_souls;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements TabListener {
    
    public static Fragment storeFragment = new StoreFragment();
    public static Fragment statusFragment = new StatusFragment();
    public static Fragment historyFragment = new HistoryFragment();
    public static Fragment orderFragment = new OrderFragment();
    public static Fragment gameFragment = new GameFragment();
    
    public static int menuType = 1;
    public static final int MENU_TITLE = 1;
    public static final int MENU_STORE = 2;
    public static final int MENU_HISTORY = 3;
    public static final int MENU_ORDER = 4;
    public static final int MENU_GAME01 = 11;
    public static final int MENU_GAME02 = 12;
    
    @SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        initializeActionBar();      // ActionBarの初期セット
        initializePreferencesSet(); // SharedPreferencesの読み込みと初期セット
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        // 初めて起動した場合のスタートアップガイド表示
        if (pref.getBoolean(Common.PREF_INIT_STARTGUIDE, false) == false) {
            CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getFragmentManager(), "StartGuide");
        }
	}
    
	@Override
    public void onDestroy() {
        super.onDestroy();
        
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
	    // GameEndアクションの作成
	    MenuItem actionItem;
        actionItem = menu.add("Sound");
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            actionItem.setIcon(R.drawable.ic_sound_off);
        } else {
            actionItem.setIcon(R.drawable.ic_sound_on);
        }
        
        actionItem = menu.add("End");
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        actionItem.setIcon(android.R.drawable.ic_delete);
        
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
	    // サウンドのOnOff
        if (item.getTitle() == "Sound") {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor prefEditor = pref.edit();
            if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
                Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.submenu_soundoff), Toast.LENGTH_SHORT).show();
                prefEditor.putBoolean(Common.PREF_SOUND, false);
                prefEditor.commit();
                item.setIcon(R.drawable.ic_sound_on);
            } else {
                Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.submenu_soundon), Toast.LENGTH_SHORT).show();
                prefEditor.putBoolean(Common.PREF_SOUND, true);
                prefEditor.commit();
                item.setIcon(R.drawable.ic_sound_off);
            }
            
        }
	    // GameEnd実行
	    if (item.getTitle() == "End") {
	        finish();
	    }
	    
        return true;
    }
	
	@Override
	public void onTabReselected(Tab tabid, FragmentTransaction transaction) {
	    // TODO Auto-generated method stub
	}

	@Override
	public void onTabSelected(Tab tabid, FragmentTransaction transaction) {
	    // TODO Auto-generated method stub
	    // ActionBarが変更されたとき
        transaction = getFragmentManager().beginTransaction();
        if (tabid.getText() == getResources().getString(R.string.menu_order)) {
            transaction.replace(R.id.main, orderFragment);
	    } else if (tabid.getText() == getResources().getString(R.string.menu_store)) {
            transaction.replace(R.id.main, storeFragment);
        } else if (tabid.getText() == getResources().getString(R.string.menu_status)) {
            transaction.replace(R.id.main, statusFragment);
        } else if (tabid.getText() == getResources().getString(R.string.menu_history)) {
            transaction.replace(R.id.main, historyFragment);
	    }
        transaction.commit();
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
	    // TODO Auto-generated method stub
	    
	}
	
    public void ButtonClickGameStart(View v) {
        // ゲームスタートボタンが押下されたとき
        Log.v("gamestart","pressed");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main, gameFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
    
    public void ButtonClickGameBack(View v) {
        // タイトルへの移動が実行されたとき
        Log.v("title","pressed");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main, orderFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }
    
    @Override
    public void onBackPressed() {
        // バックキーが押下されたとき
        Log.v("backkey","pressed");
        ((GameFragment) gameFragment).onGameStop();
    }

    private void initializeActionBar() {
        ActionBar mActionBar = getActionBar();
        mActionBar.addTab(mActionBar.newTab().setText(getResources().getString(R.string.menu_order)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getResources().getString(R.string.menu_store)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getResources().getString(R.string.menu_status)).setTabListener(this));
        mActionBar.addTab(mActionBar.newTab().setText(getResources().getString(R.string.menu_history)).setTabListener(this));
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
    }
    
    private void initializePreferencesSet() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = pref.edit();
        
        if (pref.getBoolean(Common.PREF_INIT, false) == false) {  // 初期起動判定
            prefEditor.putBoolean(Common.PREF_INIT, true);
            prefEditor.putBoolean(Common.PREF_INIT_STARTGUIDE, false);
            prefEditor.putBoolean(Common.PREF_INIT_GAMEGUIDE, false);
            prefEditor.putBoolean(Common.PREF_INIT_STOREGUIDE, false);
            prefEditor.putBoolean(Common.PREF_INIT_STATUSGUIDE, false);
            prefEditor.putBoolean(Common.PREF_INIT_HISTORYGUIDE, false);
            
            // 資産
            prefEditor.putLong(Common.PREF_ASSET, 100);
            prefEditor.putLong(Common.PREF_ASSETMAX, 9999999);
            
            // 各ストア設定
            prefEditor.putInt(Common.PREF_STORE_LEVEL, 3);
            prefEditor.putInt(Common.PREF_STORE_LEVELMAX, 30);
            
            prefEditor.putInt(Common.PREF_STORE_LIFEUP, 1);
            prefEditor.putInt(Common.PREF_STORE_FUNDSUP, 10);
            prefEditor.putInt(Common.PREF_STORE_BONUSUP, 5);
            
            // 各ステージ設定
            prefEditor.putBoolean(Common.PREF_STAGE01_RELEASE, true);
            prefEditor.putBoolean(Common.PREF_STAGE02_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE03_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE04_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE05_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE06_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE07_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE08_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE09_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE10_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE11_RELEASE, false);
            prefEditor.putBoolean(Common.PREF_STAGE12_RELEASE, false);

            prefEditor.putInt(Common.PREF_STAGE01_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE02_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE03_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE04_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE05_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE06_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE07_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE08_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE09_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE10_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE11_LEVEL, 1);
            prefEditor.putInt(Common.PREF_STAGE12_LEVEL, 1);

            prefEditor.putInt(Common.PREF_STAGE01_LEVELMAX, 5);
            prefEditor.putInt(Common.PREF_STAGE02_LEVELMAX, 5);
            prefEditor.putInt(Common.PREF_STAGE03_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE04_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE05_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE06_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE07_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE08_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE09_LEVELMAX, 10);
            prefEditor.putInt(Common.PREF_STAGE10_LEVELMAX, 20);
            prefEditor.putInt(Common.PREF_STAGE11_LEVELMAX, 20);
            prefEditor.putInt(Common.PREF_STAGE12_LEVELMAX, 20);

            prefEditor.putInt(Common.PREF_STAGE01_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE02_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE03_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE04_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE05_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE06_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE07_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE08_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE09_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE10_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE11_LEVELMIN, 1);
            prefEditor.putInt(Common.PREF_STAGE12_LEVELMIN, 1);
            
            // グリッド設定
            prefEditor.putBoolean(Common.PREF_GLID, true);
            
            // サウンド設定
            prefEditor.putBoolean(Common.PREF_SOUND, true);
            
            // プレイヤー設定
            prefEditor.putInt(Common.PREF_LIFE_LEVEL, 1);
            prefEditor.putInt(Common.PREF_LIFE_MAX, 5);
            prefEditor.putInt(Common.PREF_FUNDS_LEVEL, 1);
            prefEditor.putInt(Common.PREF_INITIALFOUNDS, 50);
            
            // 各タワー設定
            prefEditor.putInt(Common.PREF_STORE_TOWER000LEVEL, 1);
            prefEditor.putInt(Common.PREF_STORE_TOWER000BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER001LEVEL, 1);
            prefEditor.putInt(Common.PREF_STORE_TOWER001BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER002LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_TOWER002RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_TOWER002BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER011LEVEL, 1);
            prefEditor.putInt(Common.PREF_STORE_TOWER011BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER012LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_TOWER012RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_TOWER012BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER021LEVEL, 1);
            prefEditor.putInt(Common.PREF_STORE_TOWER021BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER022LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_TOWER022RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_TOWER022BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER031LEVEL, 1);
            prefEditor.putInt(Common.PREF_STORE_TOWER031BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_TOWER032LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_TOWER032RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_TOWER032BONUS, 0);
            prefEditor.putInt(Common.PREF_STORE_SKILL000LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_SKILL000RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_SKILL000COUNT, 1);
            prefEditor.putInt(Common.PREF_STORE_SKILL001LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_SKILL001RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_SKILL001COUNT, 1);
            prefEditor.putInt(Common.PREF_STORE_SKILL002LEVEL, 1);
            prefEditor.putBoolean(Common.PREF_STORE_SKILL002RELEASE, false);
            prefEditor.putInt(Common.PREF_STORE_SKILL002COUNT, 1);
            
            // 各エネミー設定
            prefEditor.putLong(Common.PREF_ENEMY001_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY002_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY003_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY004_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY011_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY012_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY013_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY014_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY021_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY022_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY023_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY031_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY032_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY033_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY034_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY041_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY042_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY043_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY051_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY052_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY053_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY054_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY061_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY062_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY063_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY064_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY071_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY072_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY073_COUNT, 0);
            prefEditor.putLong(Common.PREF_ENEMY081_COUNT, 0);
            
        }
        prefEditor.putInt(Common.PREF_STAGE_NO, 1);             // ステージNoの初期値
        
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        
        prefEditor.putInt(Common.PREF_STAGE_SIZEX, size.x);    // ディスプレイサイズX 取得
        prefEditor.putInt(Common.PREF_STAGE_SIZEY, size.y);    // ディスプレイサイズY 取得
        prefEditor.commit();
    }
}
