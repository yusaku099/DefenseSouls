package com.example.defense_souls;

import java.util.List;

import android.content.Context;

public class TowerAction {

    public void towerAction(Context context, Player Player, Image image, 
            List<TowerObject> towerlist, List<EnemyObject> enemylist, List<ShotObject> shotlist, List<EffectObject> effectlist, 
            GameOnTouch gameOnTouch, StageDataObject stageData, int gameStatus) {
        
        TowerObject tower;
        towerAssistSet(towerlist);
        
        for (int i = 0; i < towerlist.size(); i++) {
            tower = towerlist.get(i);
            
            // 無敵状態発動中
            if (tower.getTowerNo() == 0) {
                if (Player.isNoDamage() == true) {
                    tower.setPaintFilter(Common.ironColor);
                }
            }

            switch( tower.getStatus() ){
                // 初期化
                case 1:
                    tower.setAnimationCount(0);
                    tower.setAnimationChange(15);
                    tower.setAnimationChangeCount(0);
                    tower.setAnimationChangeCountMax(2);
                    tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                    tower.setExtraSkill(0);
                    tower.setDir(12);
                    tower.setStatus(9);
                    break;
                    
                // 通常時
                case 2:
                    tower.setShotCount(tower.getShotCount() + (tower.getShotCountSpeed() * Player.getGameSpeed()));
                    if (tower.getShotCount() > tower.getShotChange()) {
                        tower.setStatus(3);
                        tower.setShotCount(0);
                    }
                    
                case 3:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setAnimationChangeCount(tower.getAnimationChangeCount() + 1);
                        if (tower.getAnimationChangeCount() > tower.getAnimationChangeCountMax()) {
                            tower.setAnimationChangeCount(0);
                        }
                        tower.setAnimationCount(0);
                    }
                    
                    // スキルによる補助の効果時間経過
                    if (tower.getAssistSkillTime() > 0) {
                        tower.setAssistSkillTime(tower.getAssistSkillTime() - 1);
                    } else {
                        tower.setAssistSkillPower(0);
                        tower.setAssistSkillTime(0);
                        if (tower.getTowerNo() == 0) {
                            if (Player.isNoDamage() == true) {
                                Player.setNoDamage(false);
                              tower.setPaintFilter(null);
                            }
                        }
                    }
                    
                    if (gameOnTouch.getTower() == null && 
                            gameOnTouch.isOnFlg() == true && gameOnTouch.isMoveFlg() == false &&
                            gameOnTouch.isPlayOnFlg() == false && gameOnTouch.isSubmenuOnFlg() == false) {
                        // タワーセレクト
                        if (gameOnTouch.getTargetX(0, 0, stageData.getCellx()) == tower.getX() && gameOnTouch.getTargetY(0, 0, stageData.getCelly()) == tower.getY()) {
                            gameOnTouch.subMenuClear();
                            
                            towerSubMenuSet(context, gameOnTouch, Player, tower);
                            tower.setPaintFilter(Common.whiteColor);
                            gameOnTouch.setOnFlg(false);
                        }
                    }
                    
                    if (tower.getStatus() == 3) {
                        
                        EnemyObject enemy;
                        for (int j = 0 ; j < enemylist.size() ; j++) {
                            enemy = enemylist.get(j);
                            // 敵のステータスが通常時のとき
                            if ((enemy.getStatus() == 2 || enemy.getStatus() == 3) && enemy.isHide() == false) {
                                // 敵が射程内にいたとき
                                if (Common.rangeCheck(enemy.getX(), enemy.getY(), tower.getX(), tower.getY(), tower.getRange(), tower.isRangeCorner())) {
                                    
                                    // 攻撃方向設定
                                    double x,y,r;
                                    float angle;
                                    x = enemy.getPositionX() - tower.getPositionX();
                                    y = enemy.getPositionY() - tower.getPositionY();
                                    r = Common.getRadians(x,y);
                                    angle = (float)r + 90;
                                    if (angle > 360) {
                                        angle -= 360;
                                    }
                                    
                                    if (angle >= 338 || angle < 23) {   // 上
                                        tower.setDir(1);
                                    } else if (angle >= 23 && angle < 68) { // 右上
                                        tower.setDir(2);
                                    } else if (angle >= 68 && angle < 113) { // 右
                                        tower.setDir(3);
                                    } else if (angle >= 113 && angle < 158) { // 右下
                                        tower.setDir(4);
                                    } else if (angle >= 158 && angle < 203) { // 下
                                        tower.setDir(5);
                                    } else if (angle >= 203 && angle < 248) { // 左下
                                        tower.setDir(6);
                                    } else if (angle >= 248 && angle < 293) { // 左
                                        tower.setDir(7);
                                    } else if (angle >= 293 && angle < 338) { // 左上
                                        tower.setDir(8);
                                    }
                                    
                                    // 弾を放つ場合
                                    if (tower.isShotAppear() == true) {
                                        shotlist.add(new ShotObject(tower.getShotType(), tower.getPositionX(), tower.getPositionY() - 10, r, 
                                                tower.getShotPower() + tower.getShotPowerPlus() + tower.getAssistSkillPower()));
                                        tower.setStatus(2);
                                        break;
                                        
                                        // 弾を放たない場合
                                    } else {
                                        
                                        // 単体攻撃
                                        if (tower.getTowerNo() == 0 || tower.getTowerNo() == 1 || tower.getTowerNo() == 2) {
                                            if (tower.getTowerNo() == 0 || tower.getTowerNo() == 2) {
                                                effectlist.add(new EffectObject(tower.getShotType(), enemy.getPositionX(), enemy.getPositionY(), 0, 50, Common.whiteColor));
                                            } else if (tower.getTowerNo() == 1) {
                                                if (tower.getPositionX() > enemy.getPositionX()) {
                                                    effectlist.add(new EffectObject(tower.getShotType(), enemy.getPositionX(), enemy.getPositionY(), 0, 50, Common.whiteColor));
                                                } else {
                                                    effectlist.add(new EffectObject(tower.getShotType()+1, enemy.getPositionX(), enemy.getPositionY(), 0, 50, Common.whiteColor));
                                                }
                                            }
                                            tower.setAnimationCount(0);
                                            tower.setAnimationChangeCount(0);
                                            tower.setStatus(7);
                                            tower.setPositionX(enemy.getPositionX() + ((tower.getPositionX() - enemy.getPositionX())/2));
                                            tower.setPositionY(enemy.getPositionY() + ((tower.getPositionY() - enemy.getPositionY())/2));
                                            
                                            enemy.setLife(Common.setDamage(enemy.getLife(), enemy.getDefence(), enemy.getType(), tower.getShotPower() + tower.getShotPowerPlus() + tower.getAssistSkillPower(), 1));
                                            // 敵の体力が0以下のとき
                                            if (enemy.getLife() <= 0) {
                                                enemy.setLife(0);
                                                enemy.setDamageCount(0);
                                                enemy.setDamageChange(30);
                                                enemy.setStatus(4);
                                            } else {
                                                enemy.setDamageType(1);
                                                enemy.setDamageCount(0);
                                                enemy.setDamageChange(30);
                                                enemy.setPaintFilter(Common.whiteColor);
                                            }
                                            break;
                                            
                                            // 複数攻撃
                                        } else if (tower.getTowerNo() == 22) {
                                            effectlist.add(new EffectObject(tower.getShotType(), tower.getPositionX(), 
                                                    tower.getPositionY() - 20, tower.getShotPower() + tower.getShotPowerPlus() + tower.getAssistSkillPower(), 50, Common.cyanColor));
                                            tower.setDir(12);
                                            tower.setAnimationCount(0);
                                            tower.setAnimationChangeCount(0);
                                            tower.setStatus(9);
                                        }
                                    }
                                    
                                    // アシスト射程がある場合
                                } else if (tower.getType() == 3 && tower.getRangeAssist() != 0) {
                                    if (Common.rangeCheck(enemy.getX(), enemy.getY(), tower.getX(), tower.getY(), tower.getRangeAssist(), tower.isRangeCornerAssist())) {
                                        enemy.setSlowCount(0);
                                        if (enemy.getSlowChange() < 30 ) {
                                            enemy.setSlowChange(30);
                                        }
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
                case 7:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setStatus(2);
                        tower.setPositionX((tower.getX() * Common.CELLSIZE_X));
                        tower.setPositionY((tower.getY() * Common.CELLSIZE_Y));
                    }
                    break;
                    
                // 帰還時
                case 4:
                    Player.setGold(Player.getGold()+tower.getReturnCost());
                    stageData.setPathData(Common.AREA_OK, tower.getX(), tower.getY());
                    stageData.updateMoveData(stageData.getMoveData(), stageData.getPathData());
                    towerlist.remove(i);
                    break;
                // レベルアップ時
                case 5:
                    if (tower.getTowerNo() == 0) {  // プレイヤー
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 0, 2, 1, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 0, 3, 1, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 1) {  // ガンナー
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 1, 2, 1, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 1, 3, 1, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 2) {  // 勇士
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 2, 2, 2, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 2, 3, 2, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 11) {   // 炎の術師
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 11, 2, 1, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 11, 3, 1, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 12) {   // 雷の術師
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 12, 2, 2, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 12, 3, 2, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 21) {   // 凍結の術師
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 21, 2, 1, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 21, 3, 1, tower.getX(), tower.getY()));
                        }
                    } else if (tower.getTowerNo() == 22) {   // 光の術師
                        if (tower.getLevel() == 1) {
                            towerlist.add(new TowerObject(context, Player, 22, 2, 2, tower.getX(), tower.getY()));
                        } else if (tower.getLevel() == 2) {
                            towerlist.add(new TowerObject(context, Player, 22, 3, 2, tower.getX(), tower.getY()));
                        }
                    }
                    towerlist.remove(i);
                    break;
                // ランクアップ時
                case 6:
                    if (tower.getTowerNo() == 1) {  // ガンナー
                        towerlist.add(new TowerObject(context, Player, 2, 1, 2, tower.getX(), tower.getY()));
                    } else if (tower.getTowerNo() == 11) {   // 炎の術師
                        towerlist.add(new TowerObject(context, Player, 12, 1, 2, tower.getX(), tower.getY()));
                    } else if (tower.getTowerNo() == 21) {   // 凍結の術師
                        towerlist.add(new TowerObject(context, Player, 22, 1, 2, tower.getX(), tower.getY()));
                    } else if (tower.getTowerNo() == 31) {   // 槍の罠
                        towerlist.add(new TowerObject(context, Player, 32, 1, 2, tower.getX(), tower.getY()));
                    }
                    towerlist.remove(i);
                    break;
                // ポーズ
                case 9:
                    if (tower.getTowerNo() == 0) {  // 隊長のとき
                        tower.setPositionX((tower.getX() * Common.CELLSIZE_X)-10);
                        tower.setPositionY((tower.getY() * Common.CELLSIZE_Y));
                    } else {
                        tower.setPositionX((tower.getX() * Common.CELLSIZE_X));
                        tower.setPositionY((tower.getY() * Common.CELLSIZE_Y));
                    }

                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setAnimationChangeCount(tower.getAnimationChangeCount() + 1);
                        if (tower.getAnimationChangeCount() > tower.getAnimationChangeCountMax()) {
                            tower.setAnimationCount(0);
                            tower.setAnimationChange(15);
                            tower.setAnimationChangeCount(0);
                            tower.setAnimationChangeCountMax(2);
                            tower.setPositionX((tower.getX() * Common.CELLSIZE_X));
                            
                            tower.setDir(5);
                            tower.setStatus(2);
                            tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                            if (tower.getType() == 3) {
                                tower.setDir(11);
                            }
                            break;
                        }
                        tower.setAnimationCount(0);
                        
                        if (tower.getAnimationChangeCount() > 1) {
                            tower.setAnimationChange(30);
                        }
                    }
                    break;
                // ダメージ時
                case 10:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setAnimationChangeCount(tower.getAnimationChangeCount() + 1);
                        if (tower.getAnimationChangeCount() > tower.getAnimationChangeCountMax()) {
                            tower.setAnimationChangeCount(0);
                        }
                        tower.setAnimationCount(0);
                    }
                    
                    tower.setDamageCount(tower.getDamageCount() + (1 * Player.getGameSpeed()));
                    if (tower.getDamageCount() > tower.getDamageChange()) {
                        tower.setDir(5);
                        tower.setStatus(2);
                        tower.setDamageCount(0);
                    }
                    break;
                    
                // 特殊初期化
                case 11:
                    tower.setPaintFilter(null);
                    tower.setDir(12);
                    tower.setAnimationCount(0);
                    tower.setAnimationChange(30);
                    tower.setAnimationChangeCount(0);
                    tower.setAnimationChangeCountMax(1);
                    tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                    tower.setStatus(12);
                    break;
                // スキル使用
                case 12:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setAnimationChangeCount(tower.getAnimationChangeCount() + 1);
                        if (tower.getAnimationChangeCount() > tower.getAnimationChangeCountMax()) {
                            tower.setAnimationCount(0);
                            tower.setAnimationChangeCount(1);
                            tower.setStatus(13);
                            if (tower.getExtraSkill() > 0) {
                                // 全体攻撃
                                if (tower.getExtraSkill() == 1) {
                                    effectlist.add(new EffectObject(41, tower.getPositionX(), tower.getPositionY() - 20, 
                                            tower.getShotPower() + tower.getShotPowerPlus() + tower.getAssistSkillPower(), 50, Common.redColor));
                                    tower.setAnimationChange(80);
                                    // 全体アシスト
                                } else if (tower.getExtraSkill() == 2) {
                                    effectlist.add(new EffectObject(42, tower.getPositionX(), tower.getPositionY() - 20, 0, 0, null));
                                    TowerObject tower2;
                                    for (int j = 0; j < towerlist.size(); j++) {
                                        tower2 = towerlist.get(j);
                                        if (tower2.getTowerNo() != 0) {
                                            tower2.setAssistSkillPower(tower.getAssistPower());
                                            tower2.setAssistSkillTime(500);
                                        }
                                    }
                                    tower.setAnimationChange(80);
                                    // 無敵化
                                } else if (tower.getExtraSkill() == 3) {
                                    effectlist.add(new EffectObject(43, tower.getPositionX(), tower.getPositionY() - 20, 0, 0, null));
                                    Player.setNoDamage(true);
                                    tower.setAssistSkillTime(500);
                                    tower.setAnimationChange(80);
                                }
                                tower.setStatus(14);
                                tower.setAnimationCount(0);
                                tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                            }
                        }
                        tower.setAnimationCount(0);
                    }
                    break;
                case 13:
                    if (gameStatus == Common.GAMESTATUS_GAMEMAIN) {
                        tower.setAnimationCount(0);
                        tower.setAnimationChange(15);
                        tower.setAnimationChangeCount(0);
                        tower.setAnimationChangeCountMax(2);
                        
                        tower.setDir(5);
                        tower.setStatus(2);
                        tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                        break;
                    }
                    break;
                case 14:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setStatus(13);
                    }
                    break;
                    
                    // トラップ初期化
                case 16:
                    if (tower.getTowerNo() == 31) {
                        stageData.setAreaData(Common.AREA_SPEAR, tower.getX(), tower.getY());
                    } else if (tower.getTowerNo() == 32) {
                        stageData.setAreaData(Common.AREA_MINE, tower.getX(), tower.getY());
                    }
                    tower.imageSizeSet(image.getImagelist().get(tower.getImageNo()));
                    tower.setStatus(17);
                    break;
                    // トラップ待機
                case 17:
                    if (gameOnTouch.getTower() == null && 
                        gameOnTouch.isOnFlg() == true && gameOnTouch.isMoveFlg() == false &&
                        gameOnTouch.isPlayOnFlg() == false && gameOnTouch.isSubmenuOnFlg() == false) {
                        // タワーセレクト
                        if (gameOnTouch.getTargetX(0, 0, stageData.getCellx()) == tower.getX() && gameOnTouch.getTargetY(0, 0, stageData.getCelly()) == tower.getY()) {
                            gameOnTouch.subMenuClear();
                            
                            towerSubMenuSet(context, gameOnTouch, Player, tower);
                            tower.setPaintFilter(Common.whiteColor);
                        }
                    }
                    break;
                    // トラップ発動
                case 18:
                    tower.setAnimationCount(tower.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (tower.getAnimationCount() > tower.getAnimationChange()) {
                        tower.setAnimationChangeCount(tower.getAnimationChangeCount() + 1);
                        if (tower.getAnimationChangeCount() > tower.getAnimationChangeCountMax()) {
                            tower.setAnimationChangeCount(0);
                            towerlist.remove(i);
                            break;
                        }
                        tower.setAnimationCount(0);
                    }
                    break;
            }
        }
    }
    
    private void towerSubMenuSet(Context context, GameOnTouch gameOnTouch, Player Player, TowerObject tower) {
        int inity = 0;
        int offy = 0;
        int offx = 0;
        
        inity = 80;
        if (tower.getLevelupCost() != 0) {
            inity -= 90;
        }
        if (tower.getChangeCost() != 0) {
            if ( (tower.getTowerNo() == 1 && Player.getRelease(2) == true) ||
                    (tower.getTowerNo() == 11 && Player.getRelease(12) == true) ||
                    (tower.getTowerNo() == 21 && Player.getRelease(22) == true) ) {
                inity -= 90;
            }
        }
        if (tower.getReturnCost() != 0) {
            inity -= 90;
        }
        if (tower.getTowerNo() == 0) {
            if (Player.getSkillCount000() > 0) {
                inity -= 90;
            }
            if (Player.getSkillCount001() > 0) {
                inity -= 90;
            }
            if (Player.getSkillCount002() > 0) {
                inity -= 90;
            }
        }
        
        if (gameOnTouch.getCanvasX() + tower.getPositionX() < gameOnTouch.displayX / 2) {
            offx = 120;
        } else {
            offx = -550;
        }
        if (gameOnTouch.getCanvasY() + tower.getPositionY() < gameOnTouch.displayY / 2) {
            inity = 0;
            offy = 0;
        } else {
            offy = 0;
        }
        
        if (tower.getLevelupCost() != 0) {
            gameOnTouch.setSubMenulist(new SubMenuObject(0, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                    ""+context.getResources().getString(R.string.submenu_levelup)+" -$"+tower.getLevelupCost()));
            offy += 90;
        }
        if (tower.getChangeCost() != 0) {
            if ( (tower.getTowerNo() == 1 && Player.getRelease(2) == true) ||
                    (tower.getTowerNo() == 11 && Player.getRelease(12) == true) ||
                    (tower.getTowerNo() == 21 && Player.getRelease(22) == true) ||
                (tower.getTowerNo() == 31 && Player.getRelease(32) == true) ) {
                
                gameOnTouch.setSubMenulist(new SubMenuObject(1, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                        ""+tower.getChangeJobName()+" "+context.getResources().getString(R.string.submenu_change)+" -$"+tower.getChangeCost()));
                offy += 90;
            }
        }
        if (tower.getReturnCost() != 0) {
            gameOnTouch.setSubMenulist(new SubMenuObject(2, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                    ""+context.getResources().getString(R.string.submenu_return)+" +$"+tower.getReturnCost()));
        }
        
        if (tower.getTowerNo() == 0) {
            if (Player.getSkillCount000() > 0) {
                gameOnTouch.setSubMenulist(new SubMenuObject(11, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                        ""+context.getResources().getString(R.string.submenu_skillname000)+"("+context.getResources().getString(R.string.submenu_skill000)+")"+" あと"+ Player.getSkillCount000() + "回"));
                offy += 90;
            }
            if (Player.getSkillCount001() > 0) {
                gameOnTouch.setSubMenulist(new SubMenuObject(12, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                        ""+context.getResources().getString(R.string.submenu_skillname001)+"("+context.getResources().getString(R.string.submenu_skill001)+")"+" あと"+ Player.getSkillCount001() + "回"));
                offy += 90;
            }
            if (Player.getSkillCount002() > 0) {
                gameOnTouch.setSubMenulist(new SubMenuObject(13, tower.getPositionX() + offx, tower.getPositionY() - 40 + inity + offy, 
                        ""+context.getResources().getString(R.string.submenu_skillname002)+"("+context.getResources().getString(R.string.submenu_skill002)+")"+" あと"+ Player.getSkillCount002() + "回"));
                offy += 90;
            }
        }
        
        for (int i = 0; i < gameOnTouch.getSubMenulist().size() ; i++) {
            SubMenuObject submenu = gameOnTouch.getSubMenulist().get(i);
            switch (submenu.getSubmenuNo()) {
                case 0:
                    if (Player.getGold() >= tower.getLevelupCost()) {
                        submenu.setPaintFilter(null);
                    } else {
                        submenu.setPaintFilter(Common.redColor);
                    }
                    break;
                case 1:
                    if (Player.getGold() >= tower.getChangeCost()) {
                        submenu.setPaintFilter(null);
                    } else {
                        submenu.setPaintFilter(Common.redColor);
                    }
                    break;
                case 2:
                    break;
            }
        }
        
        gameOnTouch.setTower(tower);
        gameOnTouch.setSubmenuHideFlg(true);
    }
    
    private void towerAssistSet(List<TowerObject> towerlist) {
        TowerObject tower;
        for (int i = 0; i < towerlist.size(); i++) {
            tower = towerlist.get(i);
            tower.setShotCountSpeed(1);
            tower.setShotPowerPlus(0);
        }
        for (int i = 0; i < towerlist.size(); i++) {
            tower = towerlist.get(i);
            if ((tower.getStatus() == 2 || tower.getStatus() == 3)) {
                // アシストの判定
                if (tower.getRangeAssist() != 0) {
                    TowerObject tower2;
                    for (int j = 0 ; j < towerlist.size() ; j++) {
                        tower2 = towerlist.get(j);
                        // 味方のステータスが通常時のとき
                        if ((tower2.getStatus() == 2 || tower2.getStatus() == 3)) {
                            if (tower2.getX() == tower.getX() && tower2.getY() == tower.getY()) {
                                // 自身は除外
                            } else {
                                // 味方が射程内にいたとき
                                if (Common.rangeCheck(tower2.getX(), tower2.getY(), tower.getX(), tower.getY(), tower.getRangeAssist(), tower.isRangeCornerAssist())) {
                                    if (tower.getType() == 3) { // 攻撃速度UP
                                        tower2.setShotCountSpeed(tower.getAssistSpeed());
                                    } else if (tower.getType() == 4) { // 攻撃力UP
                                        tower2.setShotPowerPlus(tower.getAssistPower());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
