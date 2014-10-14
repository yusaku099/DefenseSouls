package com.example.defense_souls;

import java.util.List;
import java.util.Random;

import android.util.Log;

public class EnemyAction {
    
    public void enemyAction(Player Player, Image image, 
            List<TowerObject> towerlist, List<EnemyObject> enemylist, List<EffectObject> effectlist, 
            GameOnTouch gameOnTouch, StageDataObject stageData, EnemyDataObject enemyData, Sound sound, int gameCount) {
        stageData.setEnemyDataClear(stageData.getEnemyData());
        EffectObject effect;
        
        for (int i = 0; i < enemylist.size(); i++) {
            EnemyObject enemy = enemylist.get(i);
            switch( enemy.getStatus() ){
                // 初期化
                case 1:
                    if (gameCount > enemy.getAppear()) {
                        enemy.imageSizeSet(image.getImagelist().get(enemy.getImageNo()));
                        enemy.setStatus(2);
                        enemy.setPositionX((enemy.getX() * Common.CELLSIZE_X));
                        enemy.setPositionY((enemy.getY() * Common.CELLSIZE_Y));
                        if (enemy.getType() == 3) { // 水棲タイプの出現
                            effect = new EffectObject(102, enemy.getPositionX(), enemy.getPositionY() - 20, 0, 0, null);
                            effectlist.add(effect);
                            
                        } else if (enemy.getType() == 6) { // 悪魔タイプの出現
                            effect = new EffectObject(101, enemy.getPositionX(), enemy.getPositionY() - 20, 0, 0, null);
                            effectlist.add(effect);
                            
                        }
                    }
                    break;
                // 移動方向決定
                case 2:
                    enemy.setStatus(3);
                    setDir(enemy, stageData);
                    
                // 移動中
                case 3:
                    enemy.setHide(false);
                    setSpeed(enemy);
                    
                    // 反転処理
                    if (stageData.getPathData(enemy.getMoveX(), enemy.getMoveY()) == Common.AREA_NO) {
                        enemy.setTurnFlg(-1);
                        enemy.setMoveX(enemy.getX());
                        enemy.setMoveY(enemy.getY());
                    }
                    
                    // アニメーション処理
                    enemy.setAnimationCount(enemy.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (enemy.getAnimationCount() > enemy.getAnimationChange()) {
                        enemy.setAnimationChangeCount(enemy.getAnimationChangeCount() + 1);
                        if (enemy.getAnimationChangeCount() > enemy.getAnimationChangeCountMax()) {
                            enemy.setAnimationChangeCount(0);
                        }
                        enemy.setAnimationCount(0);
                    }
                    
                    // ダメージ処理
                    if (enemy.getDamageType() != 0) {
                        enemy.setDamageCount(enemy.getDamageCount() + (1 * Player.getGameSpeed()));
                        if (enemy.getDamageCount() > enemy.getDamageChange()) {
                            enemy.setDamageType(0);
                            enemy.setDamageCount(0);
                            enemy.setPaintFilter(null);
                        }
                    }
                    
                    // 再生処理
                    if (enemy.getType() == 5) {
                        if ((int)(enemy.getLife()+enemy.getRegeneration()) > enemy.getLifeMax()) {
                            enemy.setLife(enemy.getLifeMax());
                        } else {
                            enemy.setLife((int)(enemy.getLife()+enemy.getRegeneration()));
                        }
                    }
                    
                    // スピードアップ処理
                    if (enemy.isSpeedup() == true) {
                        enemy.setSpeedupCount(enemy.getSpeedupCount() + (1 * Player.getGameSpeed()));
                        if (enemy.getSpeedupCount() > enemy.getSpeedupChange()) {
                            enemy.setSpeedup(false);
                            enemy.setSpeedupCount(0);
                        }
                    }
                    
                    // スロウ処理
                    if (enemy.getSlowChange() != 0) {
                        enemy.setSlowCount(enemy.getSlowCount() + (1 * Player.getGameSpeed()));
                        enemy.setPaintFilter(Common.cyanColor);
                        if (enemy.getSlowCount() > enemy.getSlowChange()) {
                            enemy.setSlowCount(0);
                            enemy.setSlowChange(0);
                            enemy.setPaintFilter(null);
                        }
                        enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getDownspeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                        if (enemy.isSpeedup() == true) {
                            enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getDownspeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                        }
                        
                        // スロウエリアに接触したとき(飛行型は無効)
                    } else if (stageData.getAreaData(gameOnTouch.getTargetX(2, enemy.getPositionX(), stageData.getCellx()),
                            gameOnTouch.getTargetX(2, enemy.getPositionY(), stageData.getCelly())) == Common.AREA_SLOW && enemy.getType() != 2) {
                        
                            enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getDownspeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                            if (enemy.isSpeedup() == true) {
                                enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getDownspeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                            }
                        
                    } else {
                        enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getSpeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                        if (enemy.isSpeedup() == true) {
                            enemy.setMoveCount(enemy.getMoveCount() + ((enemy.getSpeed() * Player.getGameSpeed()) * enemy.getTurnFlg()));
                        }
                    }
                    
                    enemy.setPositionX((enemy.getX() * Common.CELLSIZE_X) + (enemy.getSpeedX() * enemy.getMoveCount()));
                    enemy.setPositionY((enemy.getY() * Common.CELLSIZE_Y) + (enemy.getSpeedY() * enemy.getMoveCount()));
                    
                    // 移動位置到着処理
                    if ((enemy.getMoveCount() > Common.CELLSIZE_X) || (enemy.getMoveCount() < 0)) {
                        enemy.setX(enemy.getMoveX());
                        enemy.setY(enemy.getMoveY());
                        enemy.setMoveCount(0);
                        enemy.setTurnFlg(1);
                        enemy.setStatus(2); // 移動方向決定へ
                        
                        // 拠点に到着した場合
                        if (enemy.getX() == stageData.getMybaseX() && enemy.getY() == stageData.getMybaseY()) {
                            if (Player.isNoDamage() == false) {
                                enemy.setStatus(9);
                            } else {
                                // 無敵状態の場合
                                if (enemy.isBoss() == false) {
                                    enemy.setStatus(10);
                                } else {
                                    // Bossクラスの場合
                                    enemy.setStatus(9);
                                }
                            }
                        } else {
                            
                            // イベントスイッチにのったとき
                            if (stageData.getStatusData(enemy.getX(), enemy.getY()) == 3) {
                                for (int x = 0 ; x < stageData.getCellx() ; x++) {
                                    for (int y = 0 ; y < stageData.getCelly() ; y++) {
                                        // 鉱山の爆発岩1
                                        if (stageData.getStatusData(x, y) == 4) {
                                            stageData.setStatusData(5, x, y);
                                        }
                                    }
                                }

                            }
                            
                            // ダメージゾーンにのったとき
                            if (stageData.getAreaData(enemy.getX(), enemy.getY()) == Common.AREA_DAMAGE && enemy.getType() != 2) {
                                enemy.setLife(Common.setDamage(enemy.getLife(), enemy.getDefence(), enemy.getType(), 10, 0));
                                if (enemy.getLife() <= 0) {
                                    enemy.setLife(0);
                                    enemy.setDamageCount(0);
                                    enemy.setDamageChange(30);
                                    enemy.setStatus(4);
                                    break;
                                } else {
                                    enemy.setDamageType(1);
                                    enemy.setDamageCount(0);
                                    enemy.setDamageChange(30);
                                    enemy.setPaintFilter(Common.whiteColor);
                                }
                                
                            }
                            
                            // 槍の罠にのったとき
                            if (stageData.getAreaData(enemy.getX(), enemy.getY()) == Common.AREA_SPEAR && enemy.getType() != 2) {
                                stageData.setAreaData(Common.AREA_FREE, enemy.getMoveX(), enemy.getMoveY());
                                int power = 0;
                                for (int j = 0; j < towerlist.size(); j++) {
                                    TowerObject tower = towerlist.get(j);
                                    if (tower.getX() == enemy.getX() && tower.getY() == enemy.getY()) {
                                        if (tower.getType() == 9) { // トラップタイプのとき
                                            tower.setStatus(18); // トラップ起動
                                            power = tower.getShotPower();
                                            break;
                                        }
                                    }
                                }
                                sound.playSound(Common.SOUND_ATTACK001);
                                enemy.setLife(Common.setDamage(enemy.getLife(), enemy.getDefence(), enemy.getType(), power, 0));
                                if (enemy.getLife() <= 0) {
                                    enemy.setLife(0);
                                    enemy.setDamageCount(0);
                                    enemy.setDamageChange(30);
                                    enemy.setStatus(4);
                                    break;
                                } else {
                                    enemy.setDamageType(1);
                                    enemy.setDamageCount(0);
                                    enemy.setDamageChange(30);
                                    enemy.setPaintFilter(Common.whiteColor);
                                }
                                
                            }
                            
                            // 地雷にのったとき
                            if (stageData.getAreaData(enemy.getX(), enemy.getY()) == Common.AREA_MINE && enemy.getType() != 2) {
                                stageData.setAreaData(Common.AREA_FREE, enemy.getMoveX(), enemy.getMoveY());
                                int power = 0;
                                for (int j = 0; j < towerlist.size(); j++) {
                                    TowerObject tower = towerlist.get(j);
                                    if (tower.getX() == enemy.getX() && tower.getY() == enemy.getY()) {
                                        if (tower.getType() == 9) { // トラップタイプのとき
                                            tower.setStatus(18); // トラップ起動
                                            power = tower.getShotPower();
                                            break;
                                        }
                                    }
                                }
                                effect = new EffectObject(13, enemy.getPositionX(), enemy.getPositionY(), power, 50, Common.redColor);
                                effectlist.add(effect);
                            }
                            
                            // スピードアップまたは瞬間移動スキル持ちの場合
                            if (enemy.getType() == 1 || enemy.getType() == 6) {
                                if (enemy.getSkillCount() > 0) {
                                    Random rnd = new Random();
                                    int ran = rnd.nextInt(100);
                                    if (ran > 70) { // 発動確率は30%
                                        if (enemy.getType() == 1) {
                                            enemy.setSpeedup(true);
                                            enemy.setSkillCount(enemy.getSkillCount()-1);
                                            
                                        } else if (enemy.getType() == 6) {
                                            for (int x = 0 ; x < stageData.getCellx() ; x++) {
                                                for (int y = 0 ; y < stageData.getCelly() ; y++) {
                                                    if (stageData.getMoveData(x, y) != 0) {
                                                        // ステージデータから移動距離を引いて相対距離が近くなる場所がある場合
                                                        if (stageData.getMoveData(x, y) < stageData.getMoveData(enemy.getX(), enemy.getY()) - 4) {
                                                            // その場所がホームではない場合
                                                            if (stageData.getAreaData(x, y) != Common.AREA_MYBASE && stageData.getPathData(x, y) == Common.AREA_OK) {
                                                                Log.v("teleport", "search3");
                                                                // 射程内である場合
                                                                if (Common.rangeCheck(enemy.getX(), enemy.getY(), x, y, 5, false)) {
                                                                    rnd = new Random();
                                                                    ran = rnd.nextInt(100);
                                                                    if (ran > 70) { // 決定確率は30%
                                                                        // 瞬間移動可能と判断
                                                                        enemy.setTeleport(true);
                                                                        enemy.setSkillCount(enemy.getSkillCount()-1);
                                                                        // 移動地点を予約
                                                                        enemy.setTeleportX(x);
                                                                        enemy.setTeleportY(y);
                                                                        enemy.setStatus(6); // 瞬間移動中へ
                                                                        effect = new EffectObject(101, enemy.getPositionX(), enemy.getPositionY()-20, 0, 0, null);
                                                                        effectlist.add(effect);
                                                                        break;
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
                            }
                        }
                    }
                    stageData.setEnemyData(Common.AREA_ENEMY, 
                            gameOnTouch.getTargetX(2, enemy.getPositionX(), stageData.getCellx()), 
                            gameOnTouch.getTargetY(2, enemy.getPositionY(), stageData.getCelly()));
                    break;
                    
                // 倒された時
                case 4:
                    if (enemy.isHide() == false) {
                        enemy.setHide(true);
                    } else {
                        enemy.setHide(false);
                    }
                    enemy.setDamageCount(enemy.getDamageCount() + (1 * Player.getGameSpeed()));
                    if (enemy.getDamageCount() > enemy.getDamageChange()) {
                        
                        // 不死型の蘇生能力
                        if (enemy.getType() == 4) {
                            if (enemy.getSkillCount() > 0) {
                                if (enemy.getKillType() != 4) {
                                    enemy.setLife(enemy.getLifeMax());
                                    enemy.setHide(false);
                                    enemy.setSkillCount(enemy.getSkillCount()-1);
                                    enemy.setStatus(2);
                                    break;
                                }
                            }
                        }
                        Player.addEnemyKillCount(enemy.getEnemyNo());
                        Player.setGold(Player.getGold() + enemy.getGold());
                        Player.setScore(Player.getScore() + enemy.getGold());
                        enemylist.remove(i);
                    }
                    break;
                    
                // 瞬間移動スキル発動時
                case 6:
                    stageData.setEnemyData(Common.AREA_ENEMY, enemy.getTeleportX(), enemy.getTeleportY());

                    if (enemy.isHide() == false) {
                        enemy.setHide(true);
                    } else {
                        enemy.setHide(false);
                    }
                    enemy.setTeleportCount(enemy.getTeleportCount() + (1 * Player.getGameSpeed()));
                    if (enemy.getTeleportCount() > enemy.getTeleportChange()) {
                        enemy.setPositionX((enemy.getTeleportX() * Common.CELLSIZE_X));
                        enemy.setPositionY((enemy.getTeleportY() * Common.CELLSIZE_Y));
                        enemy.setX(enemy.getTeleportX());
                        enemy.setY(enemy.getTeleportY());
                        enemy.setTeleportCount(0);
                        enemy.setHide(false);
                        enemy.setStatus(2);
                    }
                    break;
                        
                // 拠点到着ダメージ判定
                case 9:
                    towerlist.get(0).setStatus(10);
                    towerlist.get(0).setDir(13);
                    towerlist.get(0).setAnimationCount(0);
                    towerlist.get(0).setAnimationChange(15);
                    towerlist.get(0).setAnimationChangeCount(0);
                    towerlist.get(0).setAnimationChangeCountMax(2);
                    towerlist.get(0).imageSizeSet(image.getImagelist().get(towerlist.get(0).getImageNo()));
                    Player.setLife(Player.getLife()-1);
                    enemylist.remove(i);
                    break;
                    
                // 拠点到着ノーダメージ判定
                case 10:
                    double x,y,r;
                    Random rnd = new Random();
                    int ran;
                    ran = rnd.nextInt(30);
                    x = enemy.getPositionX() - towerlist.get(0).getPositionX() + (ran - 15);
                    y = enemy.getPositionY() - towerlist.get(0).getPositionY() + (ran - 15);
                    r = Common.getRadians(x,y);
                    
                    enemy.setSpeedX((float)(10.0f * Math.cos(r / 180 * Math.PI) * -1.0));
                    enemy.setSpeedY((float)(10.0f * Math.sin(r / 180 * Math.PI) * -1.0));
                    
                    enemy.setDamageCount(0);
                    enemy.setDamageChange(100);
                    enemy.setStatus(11);
                    break;
                    
                case 11:
                    enemy.setPositionX(enemy.getPositionX() + enemy.getSpeedX());
                    enemy.setPositionY(enemy.getPositionY() + enemy.getSpeedY());
                    enemy.setRotate(enemy.getRotate() + 15.0f);
                    if (enemy.getRotate() >= 360) {
                        enemy.setRotate(0);
                    }
                    // 画面外に移動したとき
                    if (enemy.getPositionX() < -10000 || enemy.getPositionX() > 10000 ||
                            enemy.getPositionY() < -10000 || enemy.getPositionY() > 10000) {
                        enemylist.remove(i);
                    } else {
                        enemy.setDamageCount(enemy.getDamageCount() + (1 * Player.getGameSpeed()));
                        if (enemy.getDamageCount() > enemy.getDamageChange()) {
                            enemylist.remove(i);
                        }
                    }
                    break;
            }
            
        }
    }
    
    private void setDir(EnemyObject enemy, StageDataObject stageData) {
        int dir;
        int x;
        int y;
        int moveBase;
        int moveLocation[] = new int[4];
        
        dir = -1;
        x = enemy.getX();
        y = enemy.getY();
        moveBase = stageData.getMoveData(enemy.getX(), enemy.getY());
        moveLocation[0] = stageData.getMoveData(x-1, y);
        moveLocation[1] = stageData.getMoveData(x, y-1);
        moveLocation[2] = stageData.getMoveData(x+1, y);
        moveLocation[3] = stageData.getMoveData(x, y+1);
        
        enemy.setSpeedX(0);
        enemy.setSpeedY(0);
        
        Random rnd = new Random();
        int ran = rnd.nextInt(2);
        if (ran == 0) { // ランダムにより次の方向決定を変える
            for (int j = 0 ; j < 4 ; j++) {
                if (moveLocation[j] != 0) {
                    if (moveBase > moveLocation[j]) {
                        moveBase = moveLocation[j];
                        dir = j;
                    }
                }
            }
        } else if (ran == 1) {
            for (int j = 3 ; j >= 0 ; j--) {
                if (moveLocation[j] != 0) {
                    if (moveBase > moveLocation[j]) {
                        moveBase = moveLocation[j];
                        dir = j;
                    }
                }
            }
        }
        
        enemy.setMoveDir(dir);
        switch (dir) {
            case 0:
                enemy.setMoveX(x-1);
                enemy.setMoveY(y);
                break;
            case 1:
                enemy.setMoveX(x);
                enemy.setMoveY(y-1);
                break;
            case 2:
                enemy.setMoveX(x+1);
                enemy.setMoveY(y);
                break;
            case 3:
                enemy.setMoveX(x);
                enemy.setMoveY(y+1);
                break;
        }
    }
    
    private void setSpeed(EnemyObject enemy) {
        switch (enemy.getMoveDir()) {
        case 0:
            enemy.setSpeedX(-1);
            break;
        case 1:
            enemy.setSpeedY(-1);
            break;
        case 2:
            enemy.setSpeedX(1);
            break;
        case 3:
            enemy.setSpeedY(1);
            break;
        }
    }
}
