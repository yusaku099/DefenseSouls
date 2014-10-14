package com.example.defense_souls;

import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

public class SubMenuObject {

    private int submenuNo;
    private int imageNo;
    private int status;

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
    
    private String menuText;
    private Paint paint = new Paint();

    public SubMenuObject(int submenuNo, float positionX, float positionY, String text) {
        this.submenuNo = submenuNo;
        this.status = 1;
        this.positionX = positionX;
        this.positionY = positionY;
        this.paint.setFilterBitmap(true);
        this.imageNo = Common.IMAGE_WINDOW001;
        this.menuText = text.intern();
        
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth();
        this.sizeY = image.getHeight();
    }

    public int getSubmenuNo() {
        return submenuNo;
    }

    public void setSubmenuNo(int submenuNo) {
        this.submenuNo = submenuNo;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
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

}
