package com.example.defense_souls;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SignObject {

    private int signNo;
    private int status;
    private Bitmap image;
    private int type;

    private float positionX;
    private float positionY;
    
    private int sizeX;
    private int sizeY;
    private int dispX;
    private int dispY;
    private float dispSizeX;
    private float dispSizeY;
    private float dispHarfSizeX;
    private float dispHarfSizeY;

    private int actionCount;
    private int actionChange;

    private int animationCount;
    private int animationChange;
    private int animationChangeCount;
    private int animationChangeCountMax;
    
    public SignObject(Resources resources, float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        
        this.image = BitmapFactory.decodeResource(resources, R.drawable.cutin000)
;
        this.type = 3;
        this.status = 1;
        this.actionCount = 0;
        this.actionChange = 60;
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth();
        this.sizeY = image.getHeight();
        this.dispSizeX = sizeX;
        this.dispSizeY = sizeY;
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
    }
    public void imageDispSet() {
        this.dispX = sizeX * animationChangeCount;
        this.dispY = 0;
    }

    public int getSignNo() {
        return signNo;
    }

    public void setSignNo(int signNo) {
        this.signNo = signNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public int getActionChange() {
        return actionChange;
    }

    public void setActionChange(int actionChange) {
        this.actionChange = actionChange;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
