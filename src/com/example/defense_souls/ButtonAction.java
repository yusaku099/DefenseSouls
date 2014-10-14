package com.example.defense_souls;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;

public class ButtonAction {
    
    public void buttonAction(Context context, Player Player, List<TowerObject> towerlist, GameOnTouch gameOnTouch, StageDataObject stageData) {
        int targetX;
        int targetY;
        int touchX = (int)gameOnTouch.getStartTouchX();
        int touchY = (int)gameOnTouch.getStartTouchY();

        for (int i = 0; i < gameOnTouch.getButtonlist().size() ; i++) {
            ButtonObject button = gameOnTouch.getButtonlist().get(i);
            
            if (button.isHide() == true) {
                switch( button.getStatus() ){
                // 初期化
                case 1:
                    button.setStatus(2);
                    break;
                // 通常時
                case 2:
                    if (Player.getGold() < button.getGold()) {
                        button.setPaintFilter(new LightingColorFilter(Color.rgb(0xAA, 0x55, 0x55), 0xFF000000));
                    } else {
                        button.setPaintFilter(null);
                        // 画面をタッチした時
                        if (gameOnTouch.isOnFlg() == true && gameOnTouch.isButtonOnFlg() == false) {
                            if (Common.hitCheck(touchX, touchY, 1, 1,
                                    button.getPositionX() - button.getDispHarfSizeX(), button.getPositionY() - button.getDispHarfSizeY(), 
                                    button.getDispSizeX(), button.getDispSizeY())) {
                                if (button.getImageNo() == Common.IMAGE_FACE001) {
                                    gameOnTouch.setTower(new TowerObject(context, Player, 1, 1, 1, 0, 0));
                                } else if (button.getImageNo() == Common.IMAGE_FACE011) {
                                    gameOnTouch.setTower(new TowerObject(context, Player, 11, 1, 1, 0, 0));
                                } else if (button.getImageNo() == Common.IMAGE_FACE021) {
                                    gameOnTouch.setTower(new TowerObject(context, Player, 21, 1, 1, 0, 0));
                                } else if (button.getImageNo() == Common.IMAGE_FACE031) {
                                    gameOnTouch.setTower(new TowerObject(context, Player, 31, 1, 1, 0, 0));
                                }
                                button.setStatus(3);
                                targetX = gameOnTouch.getTargetX(0, 0, stageData.getCellx());
                                targetY = gameOnTouch.getTargetY(0, 0, stageData.getCelly());
                                gameOnTouch.setButtonOnFlg(true);
                                gameOnTouch.getTower().setX(targetX);
                                gameOnTouch.getTower().setY(targetY);
                            }
                        }
                    }
                    break;
                    
                case 3:
                    if (gameOnTouch.getTower() != null) {
                        targetX = gameOnTouch.getTargetX(1, 0, stageData.getCellx());
                        targetY = gameOnTouch.getTargetY(1, 0, stageData.getCelly());
                        gameOnTouch.getTower().setX(targetX);
                        gameOnTouch.getTower().setY(targetY);
                        gameOnTouch.getTower().setPositionX(gameOnTouch.getNowTouchedX());
                        gameOnTouch.getTower().setPositionY(gameOnTouch.getNowTouchedY());
                        // 画面をタッチしていないとき
                        if (gameOnTouch.isButtonOnFlg() == false) {
                            // お金が足りているとき
                            if (button.getGold() <= Player.getGold()) {
                                // トラップタイプではないとき
                                if (gameOnTouch.getTower().getType() != 9) {
                                    // 配置可能なとき
                                    if (stageData.getPathData(targetX, targetY) == Common.AREA_OK  && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_SPEAR && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_MINE && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_NOGROUND && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_DAMAGE && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_ENEMYBASE && 
                                            stageData.getAreaData(targetX, targetY) != Common.AREA_MYBASE && 
                                            stageData.getEnemyData(targetX, targetY) == false) {
                                    
                                        stageData.setVirtualData(stageData.getMoveData(), stageData.getPathData());
                                        stageData.setVirtualPathData(Common.AREA_NO, targetX, targetY);
                                        // タワー配置時を仮想し、配置チェックを行う
                                        if (stageData.updateMoveData(stageData.getVirtualMoveData(), stageData.getVirtualPathData())) {
                                            stageData.setPathData(Common.AREA_NO, targetX, targetY);
                                            if (button.getImageNo() == Common.IMAGE_FACE001) {
                                                towerlist.add(new TowerObject(context, Player, 1, 1, 1, targetX, targetY));
                                                
                                            } else if (button.getImageNo() == Common.IMAGE_FACE011) {
                                                towerlist.add(new TowerObject(context, Player, 11, 1, 1, targetX, targetY));
                                                
                                            } else if (button.getImageNo() == Common.IMAGE_FACE021) {
                                                towerlist.add(new TowerObject(context, Player, 21, 1, 1, targetX, targetY));
                                                
                                            }
                                            Player.setGold(Player.getGold() - button.getGold());
                                            stageData.updateMoveData(stageData.getMoveData(), stageData.getPathData());
                                        }
                                    }
                                } else {
                                    // 配置可能なとき
                                    if (stageData.getPathData(targetX, targetY) == Common.AREA_OK && stageData.getAreaData(targetX, targetY) == Common.AREA_FREE && stageData.getEnemyData(targetX, targetY) == false) {
                                        towerlist.add(new TowerObject(context, Player, 31, 1, 1, targetX, targetY));
                                            Player.setGold(Player.getGold() - button.getGold());
                                    }
                                }
                            }
                            gameOnTouch.setTower(null);
                            button.setStatus(1);
                        }
                    } else {
                        gameOnTouch.setTower(null);
                        button.setStatus(1);
                    }
                    break;
                    
                }
            }
        }
    }
}
