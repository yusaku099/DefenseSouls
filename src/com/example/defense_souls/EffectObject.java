package com.example.defense_souls;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;

public class EffectObject {

    private int status;
    private int effectNo;
    private int effectType;
    private int imageNo;
    private int soundNo;
    private float positionX;
    private float positionY;

    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;
    private boolean turn;
    
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

    private float hitSizeX;
    private float hitSizeY;
    private float hitHarfSizeX;
    private float hitHarfSizeY;
    
    private int power;
    private boolean damage;
    private int damageCount;
    private LightingColorFilter damageFilter;
    
    public EffectObject(int effectNo, float positionX, float positionY, int power, int damageCount, LightingColorFilter damageFilter) {
        this.effectNo = effectNo;
        this.effectType = 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.status = 1;
        this.animationCount = 0;
        this.animationChangeCount = 0;
        this.hitSizeX = 2;
        this.hitSizeY = 2;
        this.power = power;
        this.damage = false;
        this.damageCount = damageCount;
        this.damageFilter = damageFilter;
        this.soundNo = 0;

        switch (effectNo) {
            case 1:
                shotAnimationSet(Common.IMAGE_EFFECT000, 1, 8);
                this.turn = true;
                this.soundNo = Common.SOUND_ATTACK001;
                break;
            case 2:
                shotAnimationSet(Common.IMAGE_EFFECT001, 1, 8);
                this.turn = true;
                this.soundNo = Common.SOUND_ATTACK002;
                break;
            case 3:
                shotAnimationSet(Common.IMAGE_EFFECT002, 1, 8);
                this.turn = true;
                this.soundNo = Common.SOUND_ATTACK002;
                break;
            case 4:
                shotAnimationSet(Common.IMAGE_EFFECT003, 1, 11);
                this.turn = true;
                this.soundNo = Common.SOUND_ATTACK003;
                break;
                
            case 11:
            case 12:
            case 13:
                shotAnimationSet(Common.IMAGE_EFFECT011, 3, 6);
                this.turn = true;
                this.effectType = 3;
                this.damage = true;
                this.soundNo = Common.SOUND_FIRE001;
                break;
                
            case 21:
                shotAnimationSet(Common.IMAGE_EFFECT021, 3, 7);
                this.turn = true;
                this.soundNo = Common.SOUND_ICE001;
                break;
                
            case 31:
            case 32:
            case 33:
                shotAnimationSet(Common.IMAGE_EFFECT022, 5, 6);
                this.turn = false;
                this.effectType = 4;
                this.damage = true;
                this.soundNo = Common.SOUND_LIGHT001;
                break;
                
            case 41:
                shotAnimationSet(Common.IMAGE_EFFECT031, 5, 4);
                this.turn = false;
                this.effectType = 3;
                this.damage = true;
                this.soundNo = Common.SOUND_EXPLOSION001;
                break;
            case 42:
                shotAnimationSet(Common.IMAGE_EFFECT032, 5, 5);
                this.turn = false;
                this.soundNo = Common.SOUND_SUPPORT001;
                break;
            case 43:
                shotAnimationSet(Common.IMAGE_EFFECT033, 5, 11);
                this.turn = true;
                this.soundNo = Common.SOUND_SUPPORT002;
                break;
                
            case 101:
                shotAnimationSet(Common.IMAGE_EFFECT042, 5, 8);
                this.turn = true;
                this.soundNo = Common.SOUND_TELEPORT001;
                break;
                
            case 102:
                shotAnimationSet(Common.IMAGE_EFFECT041, 5, 7);
                this.turn = true;
                this.soundNo = Common.SOUND_WATER001;
                break;
                
            case 201:
                shotAnimationSet(Common.IMAGE_EFFECT011, 3, 6);
                this.turn = true;
                this.effectType = 3;
                this.soundNo = Common.SOUND_FIRE001;
                break;
        }
    }
    
    private void shotAnimationSet(int imageNo, int change, int changeMax) {
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
                this.dispSizeX = 240;
                this.dispSizeY = 240;
                break;
            case 2:
            case 3:
            case 4:
                this.dispSizeX = 120;
                this.dispSizeY = 120;
                break;
                
            case 11:
                this.dispSizeX = 100;
                this.dispSizeY = 100;
                this.hitSizeX = 60;
                this.hitSizeY = 60;
                break;
            case 12:
                this.dispSizeX = 140;
                this.dispSizeY = 140;
                this.hitSizeX = 100;
                this.hitSizeY = 100;
                break;
            case 13:
                this.dispSizeX = 180;
                this.dispSizeY = 180;
                this.hitSizeX = 140;
                this.hitSizeY = 140;
                break;

            case 21:
                this.dispSizeX = 160;
                this.dispSizeY = 160;
                break;
                
            case 31:
                this.dispSizeX = 200;
                this.dispSizeY = 200;
                this.hitSizeX = 180;
                this.hitSizeY = 180;
                break;
            case 32:
                this.dispSizeX = 250;
                this.dispSizeY = 250;
                this.hitSizeX = 230;
                this.hitSizeY = 230;
                break;
            case 33:
                this.dispSizeX = 300;
                this.dispSizeY = 300;
                this.hitSizeX = 280;
                this.hitSizeY = 280;
                break;
            case 41:
            case 42:
            case 43:
                this.dispSizeX = player.getStageSizeX()*2;
                this.dispSizeY = player.getStageSizeY()*2;
                this.hitSizeX = player.getStageSizeX()*2;
                this.hitSizeY = player.getStageSizeY()*2;
                break;
                
            case 101:
            case 102:
                this.dispSizeX = 160;
                this.dispSizeY = 160;
                break;
                
            case 201:
                this.dispSizeX = 180;
                this.dispSizeY = 180;
                this.hitSizeX = 140;
                this.hitSizeY = 140;
                break;
        }
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
        this.hitHarfSizeX = hitSizeX / 2;
        this.hitHarfSizeY = hitSizeY / 2;

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

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
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

    public float getHitSizeX() {
        return hitSizeX;
    }

    public void setHitSizeX(float hitSizeX) {
        this.hitSizeX = hitSizeX;
    }

    public float getHitSizeY() {
        return hitSizeY;
    }

    public void setHitSizeY(float hitSizeY) {
        this.hitSizeY = hitSizeY;
    }

    public float getHitHarfSizeX() {
        return hitHarfSizeX;
    }

    public void setHitHarfSizeX(float hitHarfSizeX) {
        this.hitHarfSizeX = hitHarfSizeX;
    }

    public float getHitHarfSizeY() {
        return hitHarfSizeY;
    }

    public void setHitHarfSizeY(float hitHarfSizeY) {
        this.hitHarfSizeY = hitHarfSizeY;
    }

    public int getDamageCount() {
        return damageCount;
    }

    public void setDamageCount(int damageCount) {
        this.damageCount = damageCount;
    }

    public LightingColorFilter getDamageFilter() {
        return damageFilter;
    }

    public void setDamageFilter(LightingColorFilter damageFilter) {
        this.damageFilter = damageFilter;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getEffectNo() {
        return effectNo;
    }

    public void setEffectNo(int effectNo) {
        this.effectNo = effectNo;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public int getEffectType() {
        return effectType;
    }

    public void setEffectType(int effectType) {
        this.effectType = effectType;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getSoundNo() {
        return soundNo;
    }

    public void setSoundNo(int soundNo) {
        this.soundNo = soundNo;
    }

}
