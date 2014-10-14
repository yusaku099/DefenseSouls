package com.example.defense_souls;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class TowerObject {

    private int status;
    private int towerNo;
    private int imageNo;
    private String jobName;
    private String changeJobName;

    private int type;
    private int level;
    private int rank;
    private int cost;
    private int levelupCost;
    private int changeCost;
    private int returnCost;
    private int shotType;
    
    private int x;
    private int y;
    private int z;
    
    private float positionX;
    private float positionY;
    private int range;
    private boolean rangeCorner;
    private boolean speedupFlg = false;;
    
    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;
    private int dir;

    private float shotCount;
    private float shotChange;
    private float shotCountSpeed;
    private int shotPower;
    private int shotPowerPlus;
    private int rangeAssist;
    private boolean rangeCornerAssist;
    private int assistPower;
    private float assistSpeed;
    private boolean shotAppear;
    private int extraSkill;
    private int assistSkillPower;
    private int assistSkillTime;
    
    private int damageCount;
    private int damageChange;
    
    private int selectCount;
    private int selectChange;
    
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
    
    private Timer shotTimer;
    private TimerTask shotTimerTask;
    private Paint rangePaint = new Paint();
    private Paint rangeAssistPaint = new Paint();
    
    private Paint paint = new Paint();

    public TowerObject(Context context, Player player, int towerNo, int level, int rank, int x, int y) {
        this.status = 1;
        this.towerNo = towerNo;
        this.level = level;
        this.rank = rank;
        
        this.x = x;
        this.y = y;
        this.dir = 12;
        this.paint.setFilterBitmap(true);
        this.rangePaint.setColor(Color.BLUE);
        this.rangePaint.setAlpha(70);
        this.rangeAssistPaint.setColor(Color.GREEN);
        this.rangeAssistPaint.setAlpha(70);
        this.animationCount = 0;
        this.animationChange = 8;
        this.animationChangeCount = 0;
        this.animationChangeCountMax = 2;
        this.shotCount = 0;
        this.shotCountSpeed = 1;
        this.shotPowerPlus = 0;
        this.selectCount = 0;
        this.selectChange = 0;
        this.damageCount = 0;
        this.shotAppear = true;
        this.extraSkill = 0;
        this.setPositionX((x * Common.CELLSIZE_X));
        this.setPositionY((y * Common.CELLSIZE_Y));
        this.rangeCorner = false;
        this.rangeCornerAssist = false;
        
        int shot;
        int power;
        float speed;
        int assist;
        int range;
        int assistRange;
        float assistSpeed;
        int bonus;
        switch (towerNo) {
            case 0: // プレイヤー
                this.status = 11;
                this.damageChange = 60;
                this.shotAppear = false;
                setMainData(context.getResources().getString(R.string.tower000_name), "", Common.IMAGE_TOWER000, 1);
                shot = 1;
                power = Common.INIT_TOWER000POWER;
                speed = Common.INIT_TOWER000SPEED;
                assist = Common.INIT_TOWER000ASSISTPOWER;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER000RANGE;
                if (level == 1) {
                    setCostData(0, 30, 0, 0);
                } else if (level == 2) {
                    power += 10;
                    assist += 5;
                    setCostData(0, 50, 0, 0);
                } else if (level == 3) {
                    power += 20;
                    assist += 10;
                    setCostData(0, 0, 0, 0);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, true);
                setAssistData(Common.setAssistPowerup(assist, bonus), 0, 0, false);
                break;
                
            case 1: // 剣士
                this.shotAppear = false;
                setMainData(context.getResources().getString(R.string.tower001_name), context.getResources().getString(R.string.tower002_name), Common.IMAGE_TOWER001, 1);
                shot = 2;
                power = Common.INIT_TOWER001POWER;
                speed = Common.INIT_TOWER001SPEED;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER001RANGE;
                if (level == 1) {
                    setCostData(10, 10, 30, 5);
                } else if (level == 2) {
                    power += 5;
                    speed += 2.0f;
                    setCostData(0, 20, 20, 10);
                } else if (level == 3) {
                    power += 10;
                    speed += 4.0f;
                    setCostData(0, 0, 10, 20);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, true);
                break;
                
            case 2: // 勇士
                this.shotAppear = false;
                setMainData(context.getResources().getString(R.string.tower002_name), "", Common.IMAGE_TOWER002, 4);
                shot = 4;
                power = Common.INIT_TOWER002POWER;
                speed = Common.INIT_TOWER002SPEED;
                assist = Common.INIT_TOWER002ASSISTPOWER;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER002RANGE;
                if (level == 1) {
                    assistRange = 1;
                    setCostData(0, 50, 0, 30);
                } else if (level == 2) {
                    power += 10;
                    assistRange = 2;
                    setCostData(0, 100, 0, 60);
                } else {
                    power += 20;
                    assistRange = 3;
                    setCostData(0, 0, 0, 100);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, true);
                setAssistData(Common.setAssistPowerup(assist, bonus), 0, assistRange, false);
                break;
                
            case 11: // 炎の魔術師
                power = Common.INIT_TOWER011POWER;
                speed = Common.INIT_TOWER011SPEED;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER011RANGE;
                setMainData(context.getResources().getString(R.string.tower011_name), context.getResources().getString(R.string.tower012_name), Common.IMAGE_TOWER011, 2);
                if (level == 1) {
                    shot = 11;
                    setCostData(15, 30, 30, 10);
                } else if (level == 2) {
                    shot = 12;
                    power += 10;
                    setCostData(0, 50, 20, 20);
                } else {
                    shot = 13;
                    power += 20;
                    setCostData(0, 0, 10, 30);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, false);
                break;
                
            case 12: // 時の術師
                assistSpeed = Common.INIT_TOWER012ASSISTSPEED;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER002RANGE;
                setMainData(context.getResources().getString(R.string.tower012_name), "", Common.IMAGE_TOWER012, 3);
                if (level == 1) {
                    assistRange = 1;
                    setCostData(10, 50, 0, 30);
                } else if (level == 2) {
                    assistRange = 2;
                    setCostData(0, 100, 0, 60);
                } else {
                    assistRange = 3;
                    setCostData(0, 0, 0, 100);
                }
                setAssistData(0, Common.setAssistSpeedup(assistSpeed, bonus), assistRange, false);
                break;
                
            case 21: // 氷の魔術師
                shot = 21;
                power = Common.INIT_TOWER021POWER;
                speed = Common.INIT_TOWER021SPEED;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER021RANGE;
                setMainData(context.getResources().getString(R.string.tower021_name), context.getResources().getString(R.string.tower022_name), Common.IMAGE_TOWER021, 1);
                if (level == 1) {
                    setCostData(10, 20, 30, 5);
                } else if (level == 2) {
                    speed += 3.0f;
                    setCostData(0, 30, 20, 15);
                } else if (level == 3) {
                    speed += 6.0f;
                    setCostData(0, 0, 10, 30);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, false);
                break;
                
            case 22: // 光の術師
                this.shotAppear = false;
                power = Common.INIT_TOWER022POWER;
                speed = Common.INIT_TOWER022SPEED;
                bonus = player.getStatusBonus(towerNo);
                range = Common.INIT_TOWER022RANGE;
                setMainData(context.getResources().getString(R.string.tower022_name), "", Common.IMAGE_TOWER022, 2);
                if (level == 1) {
                    shot = 31;
                    setCostData(0, 30, 0, 15);
                } else if (level == 2) {
                    shot = 32;
                    power += 10;
                    setCostData(0, 50, 0, 30);
                } else {
                    shot = 33;
                    power += 20;
                    setCostData(0, 0, 0, 50);
                }
                setShotData(shot, Common.setPowerup(power, bonus), Common.setSpeedup(speed, bonus), range, true);
                break;
                
            case 31: // 槍のトラップ
                this.status = 16;
                this.dir = 5;
                this.shotAppear = false;
                this.animationChangeCountMax = 3;
                this.animationChange = 2;
                power = Common.INIT_TOWER031POWER;
                bonus = player.getStatusBonus(towerNo);
                setMainData(context.getResources().getString(R.string.tower031_name), context.getResources().getString(R.string.tower032_name), Common.IMAGE_TOWER031, 9);
                setShotData(0, Common.setPowerup(power, bonus), 0, 0, false);
                setCostData(5, 0, 30, 3);
                break;
                
            case 32: // 地雷トラップ
                this.status = 16;
                this.dir = 5;
                this.shotAppear = false;
                power = Common.INIT_TOWER032POWER;
                bonus = player.getStatusBonus(towerNo);
                setMainData(context.getResources().getString(R.string.tower032_name), "", Common.IMAGE_TOWER032, 9);
                setShotData(0, Common.setPowerup(power, bonus), 0, 0, false);
                setCostData(0, 0, 0, 15);
                break;
        }
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth()/(animationChangeCountMax+1);
        if (towerNo == 0) {
            this.sizeY = image.getHeight()/10;
        } else if (towerNo == 31 || towerNo == 32) {
                this.sizeY = 32;
                if (towerNo == 32) {
                    this.sizeX = 32;
                }
        } else {
            this.sizeY = image.getHeight()/9;
        }
        this.dispSizeX = sizeX*2;
        this.dispSizeY = sizeY*2;
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
        this.dispOffX = 0;
        this.dispOffY = -(dispHarfSizeY / 2);
    }
    
    public void imageDispSet() {
        switch (dir) {
        case 1: // 上
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 3;
            break;
        case 2: // 右上
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 7;
            break;
        case 3: // 右
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 2;
            break;
        case 4: // 右下
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 6;
            break;
        case 5: // 下
            this.dispX = sizeX * animationChangeCount;
            this.dispY = 0;
            break;
        case 6: // 左下
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 4;
            break;
        case 7: // 左
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY;
            break;
        case 8: // 左上
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 5;
            break;
        case 11: // アシスト
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 7;
            break;
        case 12: // ポーズ
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 8;
            break;
        case 13: // ダメージ中
            this.dispX = sizeX * animationChangeCount;
            this.dispY = sizeY * 9;
            break;
        }
    }
    
    private void setMainData(String name, String changeJobName, int imageNo, int type) {
        this.jobName = name;                    // 名前
        this.changeJobName = changeJobName;     // チェンジ後の名前
        this.imageNo = imageNo;                 // イメージNo
        this.type = type;                       // 戦闘タイプ
    }
        
    private void setShotData(int shotType, int shotPower, float shotChange, int range, boolean corner) {
        this.shotType = shotType;           // 弾タイプ
        this.shotPower = shotPower;         // 弾の攻撃力
        this.shotChange = shotChange;       // 攻撃速度
        this.shotCount = shotChange;       // 攻撃速度
        this.range = range;                 // 射程
        this.rangeCorner = corner;
    }
    
    private void setAssistData(int assistPower, float assistSpeed, int rangeAssist, boolean corner) {
        this.assistPower = assistPower;     // 補助攻撃力
        this.assistSpeed = assistSpeed;     // 補助速度
        this.rangeAssist = rangeAssist;
        this.rangeCornerAssist = corner;
    }
    
    private void setCostData(int cost, int levelupCost, int changeCost, int returnCost) {
        this.cost = cost;                   // 招聘コスト
        this.levelupCost = levelupCost;     // レベルアップコスト
        this.changeCost = changeCost;       // チェンジコスト
        this.returnCost = returnCost;       // リターンコスト
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTowerNo() {
        return towerNo;
    }

    public void setTowerNo(int towerNo) {
        this.towerNo = towerNo;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
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

    public Paint getRangePaint() {
        return rangePaint;
    }

    public void setRangePaint(Paint rangePaint) {
        this.rangePaint = rangePaint;
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

    public int getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(int selectCount) {
        this.selectCount = selectCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelupCost() {
        return levelupCost;
    }

    public void setLevelupCost(int levelupCost) {
        this.levelupCost = levelupCost;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getChangeJobName() {
        return changeJobName;
    }

    public void setChangeJobName(String changeJobName) {
        this.changeJobName = changeJobName;
    }

    public int getReturnCost() {
        return returnCost;
    }

    public void setReturnCost(int returnCost) {
        this.returnCost = returnCost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getShotType() {
        return shotType;
    }

    public void setShotType(int shotType) {
        this.shotType = shotType;
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

    public Timer getShotTimer() {
        return shotTimer;
    }

    public void setShotTimer(Timer shotTimer) {
        this.shotTimer = shotTimer;
    }

    public TimerTask getShotTimerTask() {
        return shotTimerTask;
    }

    public void setShotTimerTask(TimerTask shotTimerTask) {
        this.shotTimerTask = shotTimerTask;
    }

    public boolean isSpeedupFlg() {
        return speedupFlg;
    }

    public void setSpeedupFlg(boolean speedupFlg) {
        this.speedupFlg = speedupFlg;
    }

    public int getSelectChange() {
        return selectChange;
    }

    public void setSelectChange(int selectChange) {
        this.selectChange = selectChange;
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

    public Paint getRangeAssistPaint() {
        return rangeAssistPaint;
    }

    public void setRangeAssistPaint(Paint rangeAssistPaint) {
        this.rangeAssistPaint = rangeAssistPaint;
    }

    public int getShotPower() {
        return shotPower;
    }

    public void setShotPower(int shotPower) {
        this.shotPower = shotPower;
    }

    public int getAssistPower() {
        return assistPower;
    }

    public void setAssistPower(int assistPower) {
        this.assistPower = assistPower;
    }

    public int getChangeCost() {
        return changeCost;
    }

    public void setChangeCost(int changeCost) {
        this.changeCost = changeCost;
    }

    public int getShotPowerPlus() {
        return shotPowerPlus;
    }

    public void setShotPowerPlus(int shotPowerPlus) {
        this.shotPowerPlus = shotPowerPlus;
    }

    public int getRangeAssist() {
        return rangeAssist;
    }

    public void setRangeAssist(int rangeAssist) {
        this.rangeAssist = rangeAssist;
    }

    public float getShotCount() {
        return shotCount;
    }

    public void setShotCount(float shotCount) {
        this.shotCount = shotCount;
    }

    public float getShotChange() {
        return shotChange;
    }

    public void setShotChange(float shotChange) {
        this.shotChange = shotChange;
    }

    public float getShotCountSpeed() {
        return shotCountSpeed;
    }

    public void setShotCountSpeed(float shotCountSpeed) {
        this.shotCountSpeed = shotCountSpeed;
    }

    public float getAssistSpeed() {
        return assistSpeed;
    }

    public void setAssistSpeed(float assistSpeed) {
        this.assistSpeed = assistSpeed;
    }

    public boolean isShotAppear() {
        return shotAppear;
    }

    public void setShotAppear(boolean shotAppear) {
        this.shotAppear = shotAppear;
    }

    public int getExtraSkill() {
        return extraSkill;
    }

    public void setExtraSkill(int extraSkill) {
        this.extraSkill = extraSkill;
    }

    public int getAssistSkillPower() {
        return assistSkillPower;
    }

    public void setAssistSkillPower(int assistSkillPower) {
        this.assistSkillPower = assistSkillPower;
    }

    public int getAssistSkillTime() {
        return assistSkillTime;
    }

    public void setAssistSkillTime(int assistSkillTime) {
        this.assistSkillTime = assistSkillTime;
    }

    public boolean isRangeCorner() {
        return rangeCorner;
    }

    public void setRangeCorner(boolean rangeCorner) {
        this.rangeCorner = rangeCorner;
    }

    public boolean isRangeCornerAssist() {
        return rangeCornerAssist;
    }

    public void setRangeCornerAssist(boolean rangeCornerAssist) {
        this.rangeCornerAssist = rangeCornerAssist;
    }

}
