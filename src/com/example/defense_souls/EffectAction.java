package com.example.defense_souls;

import java.util.List;

public class EffectAction {

    public void effectAction(Player Player, Image image, StageDataObject stageData, List<EnemyObject> enemylist, List<EffectObject> effectlist, Sound sound) {
        for (int i = 0; i < effectlist.size(); i++) {
            EffectObject effect = effectlist.get(i);
            switch( effect.getStatus() ){
                // 初期化
                case 1:
                    effect.imageSizeSet(image.getImagelist().get(effect.getImageNo()), Player);
                    effect.setStatus(2);
                    if (effect.getSoundNo() != 0) {
                        sound.playSound(effect.getSoundNo());
                    }
                    break;
                // 通常時
                case 2:
                    if (effect.isDamage() == true) {    // 攻撃判定あり
                        for (int j = 0; j < enemylist.size(); j++) {
                            EnemyObject enemy = enemylist.get(j);
                            if (enemy.getStatus() == 2 || enemy.getStatus() == 3) {
                                if (enemy.getStatus() != 1 && enemy.getDamageType() == 0 && enemy.getDamageCount() == 0) {
                                    if (Common.hitCheck(effect.getPositionX() - effect.getHitHarfSizeX(), effect.getPositionY() - effect.getHitHarfSizeY(), 
                                            effect.getHitSizeX(), effect.getHitSizeY(),
                                            enemy.getPositionX() - enemy.getDispHarfSizeX(), 
                                            enemy.getPositionY() - enemy.getDispSizeY(), 
                                            enemy.getDispSizeX(), enemy.getDispSizeY())) {
                                        enemy.setLife(Common.setDamage(enemy.getLife(), enemy.getDefence(), enemy.getType(), effect.getPower(), effect.getEffectType()));
                                        // 敵の体力が0以下のとき
                                        if (enemy.getLife() <= 0) {
                                            enemy.setLife(0);
                                            enemy.setDamageCount(0);
                                            enemy.setDamageChange(30);
                                            enemy.setStatus(4);
                                            enemy.setKillType(effect.getEffectType());
                                        } else {
                                            enemy.setDamageType(1);
                                            enemy.setDamageCount(0);
                                            enemy.setDamageChange(effect.getDamageCount());
                                            enemy.setPaintFilter(effect.getDamageFilter());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    effect.setStatus(3);
                    
                case 3:

                    effect.setAnimationCount(effect.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (effect.getAnimationCount() > effect.getAnimationChange()) {
                        effect.setAnimationChangeCount(effect.getAnimationChangeCount() + 1);
                        if (effect.getAnimationChangeCount() > effect.getAnimationChangeCountMax()) {
                            effect.setAnimationChangeCount(0);
                            effectlist.remove(i);
                        }
                        effect.setAnimationCount(0);
                    }
                    
                    break;
            }
        }
    }
}
