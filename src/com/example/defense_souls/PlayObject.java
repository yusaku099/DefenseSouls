package com.example.defense_souls;

import android.graphics.Paint;

public class PlayObject {

    private int playNo;
    private int status;
    private int imageNo;

    private float positionX;
    private float positionY;
    
    private boolean onTouchFlg = false;
    private TowerObject towerIcon;
    
    private int sizeX;
    private int sizeY;
    private int dispX;
    private int dispY;
    private float dispSizeX;
    private float dispSizeY;
    private float dispHarfSizeX;
    private float dispHarfSizeY;
    private boolean hide;
    
    private Paint paint = new Paint();

    public PlayObject(int playNo, float positionX, float positionY) {
        this.playNo = playNo;
        
        this.positionX = positionX;
        this.positionY = positionY;
        this.paint.setFilterBitmap(true);
        this.hide = true;

        switch (playNo) {
            case 0:
                this.status = 1;
                this.imageNo = Common.IMAGE_BUTTON000;
                break;
            case 1:
                this.status = 1;
                this.imageNo = Common.IMAGE_BUTTON001;
                this.hide = false;
                break;
            case 2:
                this.status = 1;
                this.imageNo = Common.IMAGE_BUTTON002;
                break;
            
        }
        
        imageSizeSet();
    }
    
    public void imageSizeSet() {
        
        switch (this.playNo) {
        case 0:
        case 1:
        case 2:
            this.sizeX = 40;
            this.sizeY = 40;
            this.dispSizeX = 96;
            this.dispSizeY = 96;
            this.dispX = 0;
            this.dispY = 0;
            break;
        }
            
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
    }

    public int getPlayNo() {
        return playNo;
    }

    public void setPlayNo(int playNo) {
        this.playNo = playNo;
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

    public boolean isOnTouchFlg() {
        return onTouchFlg;
    }

    public void setOnTouchFlg(boolean onTouchFlg) {
        this.onTouchFlg = onTouchFlg;
    }

    public TowerObject getTowerIcon() {
        return towerIcon;
    }

    public void setTowerIcon(TowerObject towerIcon) {
        this.towerIcon = towerIcon;
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

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }


}
