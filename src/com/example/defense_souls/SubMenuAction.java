package com.example.defense_souls;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

public class SubMenuAction {
    
    @SuppressLint("NewApi")
    public void submenuAction(Context context, StageDataObject stageData, Player Player, GameOnTouch gameOnTouch, SharedPreferences pref, Button button, Sound sound, MediaManager media, DrawSurfaceView view) {

        if (gameOnTouch.isSubmenuHideFlg() == true) {
            
            // カラーチェンジ
            for (int i = 0; i < gameOnTouch.getSubMenulist().size() ; i++) {
                SubMenuObject submenu = gameOnTouch.getSubMenulist().get(i);
                switch (submenu.getSubmenuNo()) {
                    case 0:
                        if (Player.getGold() >= gameOnTouch.getTower().getLevelupCost()) {
                            submenu.setPaintFilter(null);
                        } else {
                            submenu.setPaintFilter(Common.redColor);
                        }
                        break;
                    case 1:
                        if (Player.getGold() >= gameOnTouch.getTower().getChangeCost()) {
                            submenu.setPaintFilter(null);
                        } else {
                            submenu.setPaintFilter(Common.redColor);
                        }
                        break;
                    case 2:
                        break;
                }
            }
            
            if (gameOnTouch.isOnFlg() == true) {
                gameOnTouch.setOnFlg(false);
                for (int i = 0; i < gameOnTouch.getSubMenulist().size() ; i++) {
                    SubMenuObject submenu = gameOnTouch.getSubMenulist().get(i);
                    SharedPreferences.Editor prefEditor;
    
                    // サブメニューを押下したとき
                    if (Common.hitCheck(gameOnTouch.getStartTouchX(), gameOnTouch.getStartTouchY(), 1, 1,
                            gameOnTouch.getCanvasX() + submenu.getPositionX(), gameOnTouch.getCanvasY() + submenu.getPositionY(), 400, 90)) {
                        
                        gameOnTouch.setSubmenuOnFlg(true);
                        
                        switch (submenu.getSubmenuNo()) {
                        case 0: // レベルアップ
                            sound.playSound(Common.SOUND_SYSTEM001);
                            if (Player.getGold() >= gameOnTouch.getTower().getLevelupCost()) {
                                Player.setGold(Player.getGold()-gameOnTouch.getTower().getLevelupCost());
                                gameOnTouch.getTower().setStatus(5);
                                gameOnTouch.getTower().setPaintFilter(null);
                                gameOnTouch.setTower(null);
                                gameOnTouch.setSubmenuHideFlg(false);
                                subMenuClear(gameOnTouch.getSubMenulist());
                                return;
                            }
                            break;
                        case 1: // チェンジ
                            sound.playSound(Common.SOUND_SYSTEM001);
                            if (Player.getGold() >= gameOnTouch.getTower().getChangeCost()) {
                                Player.setGold(Player.getGold()-gameOnTouch.getTower().getChangeCost());
                                gameOnTouch.getTower().setStatus(6);
                                gameOnTouch.getTower().setPaintFilter(null);
                                gameOnTouch.setTower(null);
                                gameOnTouch.setSubmenuHideFlg(false);
                                subMenuClear(gameOnTouch.getSubMenulist());
                                return;
                            }
                            break;
                        case 2: // リターン
                            sound.playSound(Common.SOUND_SYSTEM001);
                            gameOnTouch.getTower().setStatus(4);
                            gameOnTouch.getTower().setPaintFilter(null);
                            gameOnTouch.setTower(null);
                            gameOnTouch.setSubmenuHideFlg(false);
                            subMenuClear(gameOnTouch.getSubMenulist());
                            return;
                        case 11: // 全体攻撃
                            sound.playSound(Common.SOUND_SYSTEM001);
                            gameOnTouch.getTower().setStatus(11);
                            gameOnTouch.getTower().setExtraSkill(1);
                            gameOnTouch.setTower(null);
                            gameOnTouch.setSubmenuHideFlg(false);
                            Player.setSkillCount000(Player.getSkillCount000() - 1);
                            subMenuClear(gameOnTouch.getSubMenulist());
                            return;
                        case 12: // 全体アシスト
                            sound.playSound(Common.SOUND_SYSTEM001);
                            gameOnTouch.getTower().setStatus(11);
                            gameOnTouch.getTower().setExtraSkill(2);
                            gameOnTouch.setTower(null);
                            gameOnTouch.setSubmenuHideFlg(false);
                            Player.setSkillCount001(Player.getSkillCount001() - 1);
                            subMenuClear(gameOnTouch.getSubMenulist());
                            return;
                        case 13: // 無敵化
                            sound.playSound(Common.SOUND_SYSTEM001);
                            gameOnTouch.getTower().setStatus(11);
                            gameOnTouch.getTower().setExtraSkill(3);
                            gameOnTouch.setTower(null);
                            gameOnTouch.setSubmenuHideFlg(false);
                            Player.setSkillCount002(Player.getSkillCount002() - 1);
                            subMenuClear(gameOnTouch.getSubMenulist());
                            return;
                        case 21: // 再挑戦
                            sound.playSound(Common.SOUND_SYSTEM001);
                            media.get().stop();
                            gameOnTouch.setSubmenuHideFlg(false);
                            view.initializeSet();
                            return;
                        case 22: // タイトルへ戻る
                            sound.playSound(Common.SOUND_SYSTEM001);
                            gameOnTouch.setSubmenuHideFlg(false);
                            view.setGameStatus(99);
                            return;
                        case 23: // グリッド表示
                            sound.playSound(Common.SOUND_SYSTEM001);
                            prefEditor = pref.edit();
                            prefEditor.putBoolean(Common.PREF_GLID, true);
                            prefEditor.commit();
                            
                            submenu.setSubmenuNo(24);
                            submenu.setMenuText(""+context.getResources().getString(R.string.submenu_glidoff)+"");
                            return;
                        case 24: // グリッド非表示
                            sound.playSound(Common.SOUND_SYSTEM001);
                            prefEditor = pref.edit();
                            prefEditor.putBoolean(Common.PREF_GLID, false);
                            prefEditor.commit();
                            
                            submenu.setSubmenuNo(23);
                            submenu.setMenuText(""+context.getResources().getString(R.string.submenu_glidon)+"");
                            return;
                        case 25: // サウンドON
                            sound.playSound(Common.SOUND_SYSTEM001);
                            prefEditor = pref.edit();
                            prefEditor.putBoolean(Common.PREF_SOUND, true);
                            prefEditor.commit();
                            
                            media.get().play(stageData.getBgmBattle(), context);
                            
                            submenu.setSubmenuNo(26);
                            submenu.setMenuText(""+context.getResources().getString(R.string.submenu_soundoff)+"");
                            return;
                        case 26: // サウンドOFF
                            media.get().stop();
                            prefEditor = pref.edit();
                            prefEditor.putBoolean(Common.PREF_SOUND, false);
                            prefEditor.commit();
                            
                            submenu.setSubmenuNo(25);
                            submenu.setMenuText(""+context.getResources().getString(R.string.submenu_soundon)+"");
                            return;
                        }
                    } else {
                        submenu.setStatus(0);
                    }
                }
                
                if (gameOnTouch.isSubmenuOnFlg() == false) {
                    if (gameOnTouch.getTower() != null) {
                        gameOnTouch.getTower().setPaintFilter(null);
                        gameOnTouch.setTower(null);
                        gameOnTouch.setSubmenuHideFlg(false);
                        subMenuClear(gameOnTouch.getSubMenulist());
                    }
                }
            }
        }
    }
    
    public void subMenuClear(List<SubMenuObject> submenulist) {
        submenulist.removeAll(submenulist);
    }
}
