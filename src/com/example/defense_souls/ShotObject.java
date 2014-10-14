package com.example.defense_souls;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class ShotObject {

    private int status;
    private int shotNo;
    private int imageNo;
    private int power;
    private float speed;
    
    private float positionX;
    private float positionY;
    private float speedX;
    private float speedY;
    private float angle;

    private int shotCount;
    private int shotChange;
    
    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;

    private int damageType;
    private boolean slow;
    private int damageCount;
    private LightingColorFilter damageFilter;
    private int shotType;
    
    private int sizeX;
    private int sizeY;
    private int dispX;
    private int dispY;
    private float dispSizeX;
    private float dispSizeY;
    private float dispHarfSizeX;
    private float dispHarfSizeY;
    
    private Paint rangePaint = new Paint();
    
    EnemyObject TargetEnemy;
    
    public ShotObject(int shotNo, float positionX, float positionY, double rotate, int shotPower) {
        this.status = 1;
        this.shotNo = shotNo;
        this.shotType = 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.slow = false;

        this.animationCount = 0;
        this.animationChangeCount = 0;
        this.shotCount = 0;

        float angle;
        angle = (float)rotate + 90;
        if (angle > 360) {
            angle -= 360;
        }
        this.angle = angle;
        
        switch (shotNo) {
        case 1:
            shotAnimationSet(Common.IMAGE_SHOT000, 1, 0);
            shotStatusSet(shotPower, 8.0f, 30, 1, 20, Common.whiteColor);
            this.shotType = 2;
            break;
            
        case 11:
        case 12:
        case 13:
            shotAnimationSet(Common.IMAGE_SHOT001, 1, 1);
            shotStatusSet(shotPower, 5.0f, 100, 2, 50, Common.redColor);
            this.shotType = 3;
            break;
            
        case 21:
            shotAnimationSet(Common.IMAGE_SHOT002, 1, 2);
            shotStatusSet(shotPower, 7.0f, 30, 3, 100, Common.cyanColor);
            this.slow = true;
            break;
            
        }

        this.speedX = (float)(speed * Math.cos(rotate / 180 * Math.PI));
        this.speedY = (float)(speed * Math.sin(rotate / 180 * Math.PI));
    }
    
    private void shotAnimationSet(int imageNo, int change, int changeMax) {
        this.imageNo = imageNo;
        this.animationChange = change;
        this.animationChangeCountMax = changeMax;
    }
    
    private void shotStatusSet(int power, float speed, int shotChange, int damageType, int damageCount, LightingColorFilter damageFilter) {
        this.power = power;
        this.speed = speed;
        this.shotChange = shotChange;
        this.damageType = damageType;
        this.damageCount = damageCount;
        this.damageFilter = damageFilter;
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth() / (animationChangeCountMax + 1);
        this.sizeY = image.getHeight();
        switch (shotNo) {
        case 1:
        case 2:
            this.dispSizeX = 8;
            this.dispSizeY = 8;
            break;
            
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 21:
            this.dispSizeX = 60;
            this.dispSizeY = 60;
            break;
        }
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
    }
    
    public void imageDispSet() {
        this.dispX = sizeX * animationChangeCount;
        this.dispY = 0;
    }

    public LightingColorFilter getDamageFilter() {
        return damageFilter;
    }

    public void setDamageFilter(LightingColorFilter damageFilter) {
        this.damageFilter = damageFilter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
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

    public EnemyObject getTargetEnemy() {
        return TargetEnemy;
    }

    public void setTargetEnemy(EnemyObject targetEnemy) {
        TargetEnemy = targetEnemy;
    }

    public int getDamageCount() {
        return damageCount;
    }

    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }

    public int getDamageType() {
        return damageType;
    }

    public void setDamageType(int damageType) {
        this.damageType = damageType;
    }

    public int getShotNo() {
        return shotNo;
    }

    public void setShotNo(int shotNo) {
        this.shotNo = shotNo;
    }

    public int getShotType() {
        return shotType;
    }

    public void setShotType(int shotType) {
        this.shotType = shotType;
    }

    public boolean isSlow() {
        return slow;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    public int getShotCount() {
        return shotCount;
    }

    public void setShotCount(int shotCount) {
        this.shotCount = shotCount;
    }

    public int getShotChange() {
        return shotChange;
    }

    public void setShotChange(int shotChange) {
        this.shotChange = shotChange;
    }

}
