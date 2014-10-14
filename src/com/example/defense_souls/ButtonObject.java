package com.example.defense_souls;

import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class ButtonObject {

    private int buttonNo;
    private int status;
    private int imageNo;
    private int type;
    private int gold;

    private float positionX;
    private float positionY;
    private float scalemax;
    private float scalemin;
    
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

    public ButtonObject(int buttonNo, float positionX, float positionY) {
        this.buttonNo = buttonNo;
        
        this.positionX = positionX;
        this.positionY = positionY;
        this.paint.setFilterBitmap(true);
        this.hide = true;

        switch (buttonNo) {
            case 0:
                this.status = 1;
                this.imageNo = Common.IMAGE_FACE001;
                this.type = 1;
                this.gold = 10;
                break;
            case 1:
                this.status = 1;
                this.imageNo = Common.IMAGE_FACE011;
                this.type = 1;
                this.gold = 15;
                break;
            case 2:
                this.status = 1;
                this.imageNo = Common.IMAGE_FACE021;
                this.type = 1;
                this.gold = 10;
                break;
            case 3:
                this.status = 1;
                this.imageNo = Common.IMAGE_FACE031;
                this.type = 1;
                this.gold = 5;
                break;
            
        }
        
        imageSizeSet();
    }
    
    public void imageSizeSet() {
        
        switch (this.buttonNo) {
        case 0:
        case 1:
        case 2:
        case 3:
            this.sizeX = 96;
            this.sizeY = 96;
            this.dispSizeX = 96;
            this.dispSizeY = 96;
            this.dispX = 0;
            this.dispY = 0;
            break;
        }
            
        this.dispHarfSizeX = dispSizeX / 2;
        this.dispHarfSizeY = dispSizeY / 2;
    }

    public int getButtonNo() {
        return buttonNo;
    }

    public void setButtonNo(int buttonNo) {
        this.buttonNo = buttonNo;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getScalemax() {
        return scalemax;
    }

    public void setScalemax(float scalemax) {
        this.scalemax = scalemax;
    }

    public float getScalemin() {
        return scalemin;
    }

    public void setScalemin(float scalemin) {
        this.scalemin = scalemin;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }
}
