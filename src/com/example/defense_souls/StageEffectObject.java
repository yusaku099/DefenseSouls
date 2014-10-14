package com.example.defense_souls;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;

public class StageEffectObject {

    private int status;
    private int effectNo;
    private int effectType;
    private int imageNo;
    private int soundNo;
    private float positionX;
    private float positionY;
    private float speedX;
    private float speedY;

    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;
    private boolean turn;
    
    private int sizeX;
    private int sizeY;
    private int dispX;
    private int dispY;
    private int dispSizeX;
    private int dispSizeY;
    private int dispHarfSizeX;
    private int dispHarfSizeY;
    private int dispOffX;
    private int dispOffY;
    
    public StageEffectObject(int effectNo, int positionX, int positionY) {
        this.effectNo = effectNo;
        this.effectType = 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.status = 1;
        this.animationCount = 0;
        this.animationChangeCount = 0;
        this.soundNo = 0;
        this.speedX = 0;
        this.speedY = 0;

        switch (effectNo) {
            case 1:
                animationSet(Common.IMAGE_STAGEEFFECT001, 0, 0);
                this.turn = true;
                this.speedX = 0.2f;
                this.speedY = 0.2f;
                break;
            case 2:
                animationSet(Common.IMAGE_STAGEEFFECT002, 0, 0);
                this.turn = true;
                this.speedX = 0.2f;
                this.speedY = 0.2f;
                break;
            case 3:
                animationSet(Common.IMAGE_STAGEEFFECT003, 0, 0);
                this.turn = true;
                this.speedX = 0.2f;
                this.speedY = 0.2f;
                break;
                
        }
    }
    
    private void animationSet(int imageNo, int change, int changeMax) {
        this.imageNo = imageNo;
        this.animationChange = change;
        this.animationChangeCountMax = changeMax;
    }
    
    public void imageSizeSet(Bitmap image, Player player) {
        if (turn == true) {
            this.sizeX = image.getWidth() / (animationChangeCountMax + 1);
            this.sizeY = image.getHeight();
        } else {
            this.sizeX = image.getWidth();
            this.sizeY = image.getHeight() / (animationChangeCountMax + 1);
        }
        switch (effectNo) {
            case 1:
            case 2:
            case 3:
                this.dispSizeX = sizeX*4;
                this.dispSizeY = sizeY*4;
                break;
                
        }
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;

    }

    public void imageDispSet() {
        if (turn == true) {
            this.dispX = sizeX * animationChangeCount;
            this.dispY = 0;
        } else {
            this.dispX = 0;
            this.dispY = sizeY * animationChangeCount;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEffectNo() {
        return effectNo;
    }

    public void setEffectNo(int effectNo) {
        this.effectNo = effectNo;
    }

    public int getEffectType() {
        return effectType;
    }

    public void setEffectType(int effectType) {
        this.effectType = effectType;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getSoundNo() {
        return soundNo;
    }

    public void setSoundNo(int soundNo) {
        this.soundNo = soundNo;
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

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
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

    public int getDispSizeX() {
        return dispSizeX;
    }

    public void setDispSizeX(int dispSizeX) {
        this.dispSizeX = dispSizeX;
    }

    public int getDispSizeY() {
        return dispSizeY;
    }

    public void setDispSizeY(int dispSizeY) {
        this.dispSizeY = dispSizeY;
    }

    public int getDispHarfSizeX() {
        return dispHarfSizeX;
    }

    public void setDispHarfSizeX(int dispHarfSizeX) {
        this.dispHarfSizeX = dispHarfSizeX;
    }

    public int getDispHarfSizeY() {
        return dispHarfSizeY;
    }

    public void setDispHarfSizeY(int dispHarfSizeY) {
        this.dispHarfSizeY = dispHarfSizeY;
    }

    public int getDispOffX() {
        return dispOffX;
    }

    public void setDispOffX(int dispOffX) {
        this.dispOffX = dispOffX;
    }

    public int getDispOffY() {
        return dispOffY;
    }

    public void setDispOffY(int dispOffY) {
        this.dispOffY = dispOffY;
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

}
