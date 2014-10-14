package com.example.defense_souls;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SignAction {

    @SuppressLint("NewApi")
    public void signAction(Context context, FragmentManager fragmentManager, SignObject sign, GameOnTouch gameOnTouch, StageDataObject stageData, Player player, MediaManager media, DrawSurfaceView view) {
        switch( sign.getStatus() ){
            // 初期化
            case 1:
                sign.imageSizeSet(sign.getImage());
                sign.setStatus(2);
                // 初期画面位置の設定
                int cx = stageData.getMybaseX()*-Common.CELLHARFSIZE_X;
                int cy = stageData.getMybaseY()*-Common.CELLHARFSIZE_Y;
                if (cx < gameOnTouch.getCanvasXAdjustMIN()) {
                    cx = gameOnTouch.getCanvasXAdjustMIN();
                } else if (cx > gameOnTouch.getCanvasXAdjustMAX()) {
                    cx = gameOnTouch.getCanvasXAdjustMAX();
                }
                if (cy < gameOnTouch.getCanvasYAdjustMIN()) {
                    cy = gameOnTouch.getCanvasYAdjustMIN();
                } else if (cy > gameOnTouch.getCanvasYAdjustMAX()) {
                    cy = gameOnTouch.getCanvasYAdjustMAX();
                }
                gameOnTouch.setCanvasX(cx);
                gameOnTouch.setCanvasY(cy);
                gameOnTouch.setKeepCanvasX(cx);
                gameOnTouch.setKeepCanvasY(cy);
                gameOnTouch.setCheckFlg(true);  // タッチ判定開始
                break;
                
            case 2:
                // 画面をタッチした時
                if (gameOnTouch.isOnFlg() == true && gameOnTouch.isButtonOnFlg() == false) {
                    sign.setStatus(3);
                    media.get().play(stageData.getBgmBattle(), context);

                }
                break;
                
            case 3:
                sign.setActionCount(sign.getActionCount() + 1);
                if (sign.getActionCount() > sign.getActionChange()) {
                    sign.setActionCount(0);
                    sign.setStatus(4);
                }
                break;
                
            case 4:
                view.setGameStatus(Common.GAMESTATUS_GAMEMAIN);

                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
                // 初めて起動した場合のスタートアップガイド表示
                if (pref.getBoolean(Common.PREF_INIT_GAMEGUIDE, false) == false) {
                    CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                    customDialogFragment.setArguments(player);
                    customDialogFragment.show(fragmentManager, "GameGuide");
                }
                break;
        }
    }
}
