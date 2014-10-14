package com.example.defense_souls;

import android.graphics.Paint;
import android.graphics.Rect;

public class DrawObject {

    private int imageNo;
    private int z;
    private float rotate;
    
    private Rect src = new Rect();
    private Rect dst = new Rect();
    
    private Paint paint = new Paint();

    public DrawObject(int imageNo, int left, int top, int right, int bottom, int xposi, int yposi, int width, int height, int z, float rotate, Paint paint) {
        this.imageNo = imageNo;
        this.src.set(left, top, right, bottom);
        this.dst.set(xposi, yposi, width, height);
        this.z = z;
        this.rotate = rotate;
        this.paint = paint;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Rect getSrc() {
        return src;
    }

    public void setSrc(Rect src) {
        this.src = src;
    }

    public Rect getDst() {
        return dst;
    }

    public void setDst(Rect dst) {
        this.dst = dst;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float rotate) {
        this.rotate = rotate;
    }
}
