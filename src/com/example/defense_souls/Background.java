package com.example.defense_souls;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {

    private int backNo;
    private int status;
    private Bitmap image;

    private float positionX;
    private float positionY;
    
    private float sizeX;
    private float sizeY;
    private float dispSizeX;
    private float dispSizeY;
    private float dispHarfSizeX;
    private float dispHarfSizeY;

    public Background(Resources resources, int backNo, float positionX, float positionY) {
        this.backNo = backNo;
        this.status = 1;
        this.positionX = positionX;
        this.positionY = positionY;
        
        switch (backNo) {
            case 1:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage001);
                break;
            case 2:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage002);
                break;
            case 3:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage003);
                break;
            case 4:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage004);
                break;
            case 5:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage005);
                break;
            case 6:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage006);
                break;
            case 7:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage007);
                break;
            case 8:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage008);
                break;
            case 9:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage009);
                break;
            case 10:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage010);
                break;
            case 11:
                this.image = BitmapFactory.decodeResource(resources, R.drawable.stage011);
                break;
        }
    }
    
    public void imageSizeSet(Bitmap image) {
        this.sizeX = image.getWidth();
        this.sizeY = image.getHeight();
        this.dispSizeX = sizeX;
        this.dispSizeY = sizeY;
        this.dispHarfSizeX = sizeX/2;
        this.dispHarfSizeY = sizeY/2;
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

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public int getBackNo() {
        return backNo;
    }

    public void setBackNo(int backNo) {
        this.backNo = backNo;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
}
