package com.example.defense_souls;

import android.content.Context;
import android.content.SharedPreferences;

public class PlayAction {
    
    public void playAction(Context context, Player Player, GameOnTouch gameOnTouch, SharedPreferences pref) {

        for (int i = 0; i < gameOnTouch.getPlaylist().size() ; i++) {
            PlayObject play = gameOnTouch.getPlaylist().get(i);
            
            if (play.isHide() == true) {
                switch( play.getStatus() ){
                // 初期化
                case 1:
                    play.setStatus(2);
                    break;
                    // ストップ・再生・早送り
                case 2:
                    // 画面をタッチした時
                    if (gameOnTouch.isOnFlg() == true) {
                        if (Common.hitCheck(gameOnTouch.getStartTouchX(), gameOnTouch.getStartTouchY(), 1, 1,
                                play.getPositionX() - play.getDispHarfSizeX(), play.getPositionY() - play.getDispHarfSizeY(), 
                                play.getDispSizeX(), play.getDispSizeY())) {
                            gameOnTouch.setPlayOnFlg(true);
                                switch (play.getPlayNo()) {
                                case 0:    // ストップアクション
                                    Player.setGameSpeed(0);
                                    play.setHide(false);
                                    gameOnTouch.getPlaylist().get(i+1).setHide(true);
                                    gameOnTouch.getPlaylist().get(i+2).setHide(true);
                                    playSubMenuSet(context, gameOnTouch, pref);
                                    return;
                                case 1:    // 再生アクション
                                    Player.setGameSpeed(1);
                                    play.setHide(false);
                                    gameOnTouch.getPlaylist().get(i-1).setHide(true);
                                    gameOnTouch.getPlaylist().get(i+1).setHide(true);
                                    gameOnTouch.setSubmenuHideFlg(false);
                                    gameOnTouch.subMenuClear();
                                    return;
                                case 2:    // 早送りアクション
                                    Player.setGameSpeed(3);
                                    play.setHide(false);
                                    gameOnTouch.getPlaylist().get(i-1).setHide(true);
                                    gameOnTouch.getPlaylist().get(i-2).setHide(true);
                                    gameOnTouch.setSubmenuHideFlg(false);
                                    gameOnTouch.subMenuClear();
                                    return;
                                }
                        }
                    }
                    break;
                }
            }
        }
    }
    
    public void playSubMenuSet(Context context, GameOnTouch gameOnTouch, SharedPreferences pref) {
        int offy = 0;
        int x = (int) ((gameOnTouch.getCanvasX() * -1) + (gameOnTouch.getDisplayX()/2) - 230);
        int y = (int) ((gameOnTouch.getCanvasY() * -1) + 100);
        
        gameOnTouch.setSubMenulist(new SubMenuObject(21, x, y + offy, 
                ""+context.getResources().getString(R.string.submenu_retry)+""));
        offy += 90;
        gameOnTouch.setSubMenulist(new SubMenuObject(22, x, y + offy, 
                ""+context.getResources().getString(R.string.submenu_title)+""));
        offy += 90;
        
        if (pref.getBoolean(Common.PREF_GLID, true) == false) {
            gameOnTouch.setSubMenulist(new SubMenuObject(23, x, y + offy, 
                    ""+context.getResources().getString(R.string.submenu_glidon)+""));
            offy += 90;
        } else {
            gameOnTouch.setSubMenulist(new SubMenuObject(24, x, y + offy, 
                    ""+context.getResources().getString(R.string.submenu_glidoff)+""));
            offy += 90;
        }
        
        if (pref.getBoolean(Common.PREF_SOUND, true) == false) {
            gameOnTouch.setSubMenulist(new SubMenuObject(25, x, y + offy, 
                    ""+context.getResources().getString(R.string.submenu_soundon)+""));
            offy += 90;
        } else {
            gameOnTouch.setSubMenulist(new SubMenuObject(26, x, y + offy, 
                    ""+context.getResources().getString(R.string.submenu_soundoff)+""));
            offy += 90;
        }
        
        gameOnTouch.setSubmenuHideFlg(true);
    }
}
