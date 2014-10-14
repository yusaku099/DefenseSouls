package com.example.defense_souls;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class EnemyObject {

    private int status;
    private int enemyNo;
    private int imageNo;
    private boolean hide;
    private int appear;
    private boolean boss;
    
    private int x;
    private int y;
    private int z;
    private int life;
    private int lifeMax;
    private int gold;
    private int type;
    private int defence;
    private float speed;
    private float downspeed;
    private float speedX;
    private float speedY;
    private float moveCount;
    private int skillCount;
    
    private boolean speedup;
    private int speedupCount;
    private int speedupChange;
    
    private boolean teleport;
    private int teleportCount;
    private int teleportChange;
    private int teleportX;
    private int teleportY;
    
    private float regeneration;
    
    private int killType;
    
    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;
    
    private int damageType;
    private int damageCount;
    private int damageChange;

    private int slowCount;
    private int slowChange;
    
    private float positionX;
    private float positionY;
    private float offX;
    private float offY;
    private int moveX;
    private int moveY;
    private int moveDir;
    private int turnFlg;

    private int sizeX;
    private int sizeY;
    private int dispX;
    private int dispY;
    private float dispSizeX;
    private float dispSizeY;
    private float dispHarfSizeX;
    private float dispHarfSizeY;
    private float dispOffX;
    private float dispOffY;
    private float rotate;

    private Paint paint = new Paint();

    public EnemyObject(int enemyNo, int waveNo, int difficulty, int appear, int x, int y) {
        this.enemyNo = enemyNo;
        this.status = 1;
        this.hide = true;
   
        this.x = x;
        this.y = y;
        this.appear = appear;
        this.paint.setFilterBitmap(true);
        this.animationCount = 0;
        this.animationChange = 8;
        this.animationChangeCount = 0;
        this.animationChangeCountMax = 3;
        this.speedupCount = 0;
        this.speedupChange = 100;
        this.teleportCount = 0;
        this.teleportChange = 50;
        this.regeneration = 0.1f;
        this.turnFlg = 1;
        this.rotate = 0.0f;
        
        switch (enemyNo) {
        case 1:enemyStatusSet(Common.IMAGE_ENEMY001,0,30,1.6f,0.8f,5,1,false); break;//コボルト
        case 2:enemyStatusSet(Common.IMAGE_ENEMY002,0,50,1.6f,0.8f,10,3,false); break;//ゴブリン
        case 3:enemyStatusSet(Common.IMAGE_ENEMY003,0,80,1.6f,0.8f,15,10,false); break;//オーガ
        case 4:enemyStatusSet(Common.IMAGE_ENEMY004,0,300,0.8f,0.8f,15,300,true); break;//トロール
        case 11:enemyStatusSet(Common.IMAGE_ENEMY011,3,30,1.4f,1.3f,6,1,false); break;//サハギン
        case 12:enemyStatusSet(Common.IMAGE_ENEMY012,3,50,1.4f,1.3f,12,3,false); break;//リザードマン
        case 13:enemyStatusSet(Common.IMAGE_ENEMY013,3,90,1.4f,1.3f,15,10,false); break;//大王イカ
        case 14:enemyStatusSet(Common.IMAGE_ENEMY014,3,300,0.8f,1.3f,15,300,true); break;//サーペント
        case 21:enemyStatusSet(Common.IMAGE_ENEMY021,1,30,2.5f,1.0f,4,1,false); break;//ヘルハウンド
        case 22:enemyStatusSet(Common.IMAGE_ENEMY022,1,50,2.5f,1.0f,8,4,false); break;//ケルベロス
        case 23:enemyStatusSet(Common.IMAGE_ENEMY023,1,80,2f,1.0f,13,12,false); break;//ゴート
        case 31:enemyStatusSet(Common.IMAGE_ENEMY031,2,25,2.5f,1.0f,4,1,false); break;//大コウモリ
        case 32:enemyStatusSet(Common.IMAGE_ENEMY032,2,50,2.2f,1.0f,8,3,false); break;//ハーピィ
        case 33:enemyStatusSet(Common.IMAGE_ENEMY033,2,80,2.2f,1.0f,13,10,false); break;//ワイバーン
        case 34:enemyStatusSet(Common.IMAGE_ENEMY034,2,250,1.0f,1.0f,15,300,true); break;//ルフ
        case 41:enemyStatusSet(Common.IMAGE_ENEMY041,2,25,1.5f,1.2f,4,1,false); break;//キラービー
        case 42:enemyStatusSet(Common.IMAGE_ENEMY042,0,45,1.6f,1.2f,12,4,false); break;//大サソリ
        case 43:enemyStatusSet(Common.IMAGE_ENEMY043,2,80,1.6f,1.2f,18,12,false); break;//大ムカデ
        case 51:enemyStatusSet(Common.IMAGE_ENEMY051,0,25,1.2f,1.0f,5,1,false); break;//スライム
        case 52:enemyStatusSet(Common.IMAGE_ENEMY052,5,60,1.2f,1.0f,10,4,false); break;//マタンゴ
        case 53:enemyStatusSet(Common.IMAGE_ENEMY053,5,80,1.2f,1.0f,15,12,false); break;//食人樹
        case 54:enemyStatusSet(Common.IMAGE_ENEMY054,0,250,0.5f,1.0f,20,500,true); break;//ゴーレム
        case 61:enemyStatusSet(Common.IMAGE_ENEMY061,4,25,1.6f,1.0f,4,1,false); break;//ゴースト
        case 62:enemyStatusSet(Common.IMAGE_ENEMY062,4,45,1.4f,0.8f,9,4,false); break;//ゾンビ
        case 63:enemyStatusSet(Common.IMAGE_ENEMY063,4,75,1.6f,0.8f,14,12,false); break;//スケルトン
        case 64:enemyStatusSet(Common.IMAGE_ENEMY064,4,250,0.5f,0.8f,15,500,true); break;//死神
        case 71:enemyStatusSet(Common.IMAGE_ENEMY071,6,20,1.6f,0.8f,5,2,false); break;//ミニデーモン
        case 72:enemyStatusSet(Common.IMAGE_ENEMY072,6,50,1.6f,0.8f,10,6,false); break;//デーモン
        case 73:enemyStatusSet(Common.IMAGE_ENEMY073,6,100,1.6f,0.8f,15,14,false); break;//アークデーモン
        case 81:enemyStatusSet(Common.IMAGE_ENEMY081,0,500,0.5f,0.8f,20,800,true); break;//ドラゴン
        }
        
        // 難易度による強化
        this.lifeMax = Common.setLifeup(life, waveNo + difficulty);
        this.life = lifeMax;
        this.defence = Common.setDefenceup(defence, waveNo + difficulty);
        
    }
    
    private void enemyStatusSet(int imageNo, int type, int life, float speed, float downspeed, int def, int gold, boolean boss) {
        this.imageNo = imageNo;
        this.type = type;
        this.life = life;
        this.speed = speed;
        this.downspeed = downspeed;
        this.defence = def;
        this.gold = gold;
        this.boss = boss;
        this.skillCount = 1;    // テストにより全員に１カウント
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth()/4;
        this.sizeY = image.getHeight()/4;
        this.dispSizeX = sizeX * 2;
        this.dispSizeY = sizeY * 2;
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
        this.dispOffX = 0;
        this.dispOffY = -(dispHarfSizeY / 2);
    }
    
    public void imageDispSet() {
        switch (moveDir) {
        case 0: // 左
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY;
            break;
        case 1: // 上
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 3;
            break;
        case 2: // 右
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 2;
            break;
        case 3: // 下
            this.dispX = sizeX * animationChangeCount;
            this.dispY = 0;
            break;
        }
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEnemyNo() {
        return enemyNo;
    }

    public void setEnemyNo(int enemyNo) {
        this.enemyNo = enemyNo;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getAnimationCount() {
        return animationCount;
    }

    public void setAnimationCount(int animationCount) {
        this.animationCount = animationCount;
    }

    public int getAnimationChange() {
        return animationChange;
    }

    public void setAnimationChange(int animationChange) {
        this.animationChange = animationChange;
    }

    public int getAnimationChangeCount() {
        return animationChangeCount;
    }

    public void setAnimationChangeCount(int animationChangeCount) {
        this.animationChangeCount = animationChangeCount;
    }

    public int getAnimationChangeCountMax() {
        return animationChangeCountMax;
    }

    public void setAnimationChangeCountMax(int animationChangeCountMax) {
        this.animationChangeCountMax = animationChangeCountMax;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public float getOffX() {
        return offX;
    }

    public void setOffX(float offX) {
        this.offX = offX;
    }

    public float getOffY() {
        return offY;
    }

    public void setOffY(float offY) {
        this.offY = offY;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    public int getMoveDir() {
        return moveDir;
    }

    public void setMoveDir(int moveDir) {
        this.moveDir = moveDir;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getDispX() {
        return dispX;
    }

    public void setDispX(int dispX) {
        this.dispX = dispX;
    }

    public int getDispY() {
        return dispY;
    }

    public void setDispY(int dispY) {
        this.dispY = dispY;
    }

    public float getDispSizeX() {
        return dispSizeX;
    }

    public void setDispSizeX(float dispSizeX) {
        this.dispSizeX = dispSizeX;
    }

    public float getDispSizeY() {
        return dispSizeY;
    }

    public void setDispSizeY(float dispSizeY) {
        this.dispSizeY = dispSizeY;
    }

    public float getDispHarfSizeX() {
        return dispHarfSizeX;
    }

    public void setDispHarfSizeX(float dispHarfSizeX) {
        this.dispHarfSizeX = dispHarfSizeX;
    }

    public float getDispHarfSizeY() {
        return dispHarfSizeY;
    }

    public void setDispHarfSizeY(float dispHarfSizeY) {
        this.dispHarfSizeY = dispHarfSizeY;
    }

    public int getDamageType() {
        return damageType;
    }

    public void setDamageType(int damageType) {
        this.damageType = damageType;
    }

    public int getDamageCount() {
        return damageCount;
    }

    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }

    public int getDamageChange() {
        return damageChange;
    }

    public void setDamageChange(int damageChange) {
        this.damageChange = damageChange;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    
    public void setPaintFilter(LightingColorFilter Filter) {
        this.paint.setColorFilter(Filter);
    }

    public float getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(float moveCount) {
        this.moveCount = moveCount;
    }

    public float getDownspeed() {
        return downspeed;
    }

    public void setDownspeed(float downspeed) {
        this.downspeed = downspeed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public int getLifeMax() {
        return lifeMax;
    }

    public void setLifeMax(int lifeMax) {
        this.lifeMax = lifeMax;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSlowCount() {
        return slowCount;
    }

    public void setSlowCount(int slowCount) {
        this.slowCount = slowCount;
    }

    public int getSlowChange() {
        return slowChange;
    }

    public void setSlowChange(int slowChange) {
        this.slowChange = slowChange;
    }

    public float getDispOffX() {
        return dispOffX;
    }

    public void setDispOffX(float dispOffX) {
        this.dispOffX = dispOffX;
    }

    public float getDispOffY() {
        return dispOffY;
    }

    public void setDispOffY(float dispOffY) {
        this.dispOffY = dispOffY;
    }

    public int getAppear() {
        return appear;
    }

    public void setAppear(int appear) {
        this.appear = appear;
    }

    public int getTurnFlg() {
        return turnFlg;
    }

    public void setTurnFlg(int turnFlg) {
        this.turnFlg = turnFlg;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public int getSkillCount() {
        return skillCount;
    }

    public void setSkillCount(int skillCount) {
        this.skillCount = skillCount;
    }

    public int getSpeedupCount() {
        return speedupCount;
    }

    public void setSpeedupCount(int speedupCount) {
        this.speedupCount = speedupCount;
    }

    public int getSpeedupChange() {
        return speedupChange;
    }

    public void setSpeedupChange(int speedupChange) {
        this.speedupChange = speedupChange;
    }

    public boolean isSpeedup() {
        return speedup;
    }

    public void setSpeedup(boolean speedup) {
        this.speedup = speedup;
    }

    public boolean isTeleport() {
        return teleport;
    }

    public void setTeleport(boolean teleport) {
        this.teleport = teleport;
    }

    public int getTeleportCount() {
        return teleportCount;
    }

    public void setTeleportCount(int teleportCount) {
        this.teleportCount = teleportCount;
    }

    public int getTeleportChange() {
        return teleportChange;
    }

    public void setTeleportChange(int teleportChange) {
        this.teleportChange = teleportChange;
    }

    public int getTeleportX() {
        return teleportX;
    }

    public void setTeleportX(int teleportX) {
        this.teleportX = teleportX;
    }

    public int getTeleportY() {
        return teleportY;
    }

    public void setTeleportY(int teleportY) {
        this.teleportY = teleportY;
    }

    public float getRegeneration() {
        return regeneration;
    }

    public void setRegeneration(float regeneration) {
        this.regeneration = regeneration;
    }

    public int getKillType() {
        return killType;
    }

    public void setKillType(int killType) {
        this.killType = killType;
    }

}
