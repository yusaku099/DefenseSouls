package com.example.defense_souls;

import java.util.List;

public class ShotAction {

    public void shotAction(Player Player, Image image, StageDataObject stageData,
            List<ShotObject> shotlist, List<EnemyObject> enemylist, List<EffectObject> effectlist, GameOnTouch gameOnTouch, Sound sound) {
        ShotObject shot;
        
        for (int i = 0; i < shotlist.size(); i++) {
            shot = shotlist.get(i);
            switch( shot.getStatus() ){
                // 初期化
                case 1:
                    shot.imageSizeSet(image.getImagelist().get(shot.getImageNo()));
                    shot.setStatus(2);
                    if (shot.getImageNo() == Common.IMAGE_SHOT000) {
                        sound.playSound(Common.SOUND_SHOT001);
                    }
                    break;
                // 通常時
                case 2:
                    shot.setShotCount(shot.getShotCount() + (1 * Player.getGameSpeed()));
                    if (shot.getShotCount() > shot.getShotChange()) {
                        shot.setShotCount(0);
                        shotlist.remove(i);
                        break;
                    }
                    
                    shot.setAnimationCount(shot.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (shot.getAnimationCount() > shot.getAnimationChange()) {
                        shot.setAnimationChangeCount(shot.getAnimationChangeCount() + 1);
                        if (shot.getAnimationChangeCount() > shot.getAnimationChangeCountMax()) {
                            shot.setAnimationChangeCount(0);
                        }
                        shot.setAnimationCount(0);
                    }
                    
                    shot.setPositionX(shot.getPositionX() + (shot.getSpeedX() * Player.getGameSpeed()));
                    shot.setPositionY(shot.getPositionY() + (shot.getSpeedY() * Player.getGameSpeed()));
                    int nowx;
                    int nowy;
                    nowx = gameOnTouch.getTargetX(2, shot.getPositionX(), stageData.getCellx());
                    nowy = gameOnTouch.getTargetY(2, shot.getPositionY(), stageData.getCelly());
                    
                    // 画面外に移動したとき
                    if (shot.getPositionX() < -10000 || shot.getPositionX() > 10000 ||
                            shot.getPositionY() < -10000 || shot.getPositionY() > 10000) {
                        shotlist.remove(i);
                    } else if (stageData.getImageData(nowx, nowy) != 0 && stageData.getAreaData(nowx, nowy) == Common.AREA_SHADOW) {    // 影付きの高さのあるオブジェクトに接した時
                        effectlist.add(new EffectObject(shot.getShotNo(), shot.getPositionX(), shot.getPositionY(), shot.getPower(), shot.getDamageCount(), shot.getDamageFilter()));
                        shotlist.remove(i);
                    } else {
                        for (int j = 0; j < enemylist.size(); j++) {
                            EnemyObject enemy = enemylist.get(j);
                            
                            if (enemy.getStatus() == 2 || enemy.getStatus() == 3) {
                                // 敵と接触したとき
                                if (Common.hitCheck(shot.getPositionX(), shot.getPositionY(), 1, 1,
                                        enemy.getPositionX() - enemy.getDispHarfSizeX(), 
                                        enemy.getPositionY() - enemy.getDispHarfSizeY(), 
                                        enemy.getDispSizeX(), enemy.getDispSizeY())) {
                                    enemy.setLife(Common.setDamage(enemy.getLife(), enemy.getDefence(), enemy.getType(), shot.getPower(), shot.getShotType()));
                                    // 敵の体力が0以下のとき
                                    if (enemy.getLife() <= 0) {
                                        enemy.setLife(0);
                                        enemy.setDamageCount(0);
                                        enemy.setDamageChange(30);
                                        enemy.setStatus(4);
                                    } else {
                                        // スロウ効果のあるショットのとき
                                        if (shot.isSlow() == true) {
                                            enemy.setSlowCount(0);
                                            if (enemy.getSlowChange() < shot.getDamageCount()) {
                                                enemy.setSlowChange(shot.getDamageCount());
                                            }
                                            
                                            // 通常ダメージのとき
                                        } else {
                                            enemy.setDamageType(1);
                                            enemy.setDamageCount(0);
                                            enemy.setDamageChange(shot.getDamageCount());
                                            enemy.setPaintFilter(shot.getDamageFilter());
                                        }
                                    }
                                    
                                    effectlist.add(new EffectObject(shot.getShotNo(), shot.getPositionX(), shot.getPositionY(), shot.getPower(), shot.getDamageCount(), shot.getDamageFilter()));
                                    shotlist.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                // 消滅時
                case 4:
                    shotlist.remove(i);
                    break;
            }
        }
    }
    
}
