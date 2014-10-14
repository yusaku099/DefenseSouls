package com.example.defense_souls;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class DrawSet {
    private Paint blackPaint = new Paint();
    private Paint grayPaint = new Paint();
    private Paint redPaint = new Paint();
    private Paint systemPaint = new Paint();
    private Paint goldPaint = new Paint();
    private Paint bluePaint = new Paint();
    private Paint locationPaint = new Paint();
    private Paint statusPaint = new Paint();
    private Paint iconPaint = new Paint();
    private Paint miniPaint = new Paint();
    private Paint enemylifePaint = new Paint();
    private Paint enemylifegaugePaint = new Paint();
    private Paint resultPaint = new Paint();
    private Paint objectPaint = new Paint();
    private Paint glidPaint = new Paint();
    private Paint alphaPaint = new Paint();
    
    private List<DrawObject> drawlist = new ArrayList<DrawObject>();
    private List<DrawObject> drawlistAlpha = new ArrayList<DrawObject>();
    private List<DrawObject> drawlistShadow = new ArrayList<DrawObject>();

    int posix;
    int posiy;
    int posiw;
    int posih;
    int targetX;
    int targetY;
    int nowOffX;
    int nowOffY;
    Rect src = new Rect();
    Rect dst = new Rect();
    Context context;
    
    public DrawSet(Context context) {
        this.context = context;
        systemPaint.setColor(Color.WHITE);
        systemPaint.setStrokeWidth(2);
        systemPaint.setTextSize(24.0f);
        goldPaint.setColor(Color.rgb(0xFF, 0xD7, 0x00));
        goldPaint.setStrokeWidth(2);
        goldPaint.setTextSize(24.0f);
        bluePaint.setColor(Color.rgb(0xBF, 0xEF, 0xFF));
        bluePaint.setStrokeWidth(2);
        bluePaint.setTextSize(24.0f);
        locationPaint.setColor(Color.rgb(0xFF, 0x22, 0x22));
        locationPaint.setStrokeWidth(3);        
        statusPaint.setStrokeWidth(2);
        statusPaint.setTextSize(24.0f);
        iconPaint.setColor(Color.WHITE);
        iconPaint.setAlpha(70);
        miniPaint.setColor(Color.WHITE);
        miniPaint.setTextSize(16.0f);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setColorFilter(Common.blackColor);
        blackPaint.setAlpha(70);
        grayPaint.setColor(Color.GRAY);
        grayPaint.setAlpha(70);
        redPaint.setColor(Color.RED);
        redPaint.setAlpha(70);
        enemylifePaint.setColor(Color.RED);
        enemylifegaugePaint.setColor(Color.BLACK);
        resultPaint.setColor(Color.BLACK);
        resultPaint.setAlpha(70);
        objectPaint.setAlpha(90);
        glidPaint.setStyle(Paint.Style.STROKE);
        glidPaint.setColor(Color.DKGRAY);
//        glidPaint.setColor(Color.RED);
    }
    
    // 背景の描画
    public void backgroundDrawSet(Canvas canvas, Background background, StageDataObject stageData, GameOnTouch gameOnTouch, Image image) {
        background.imageSizeSet(background.getImage());

        posix = (int)((gameOnTouch.getCanvasX() + background.getPositionX() + stageData.getDispOffSizeX()) - background.getDispSizeX());
        posiy = (int)((gameOnTouch.getCanvasY() + background.getPositionY() + stageData.getDispOffSizeY()) - background.getDispSizeY());
        posiw = (int)((gameOnTouch.getCanvasX() + background.getPositionX() + stageData.getDispOffSizeX()) + background.getDispSizeX());
        posih = (int)((gameOnTouch.getCanvasY() + background.getPositionY() + stageData.getDispOffSizeY()) + background.getDispSizeY());
        targetX = gameOnTouch.getTargetX(1, 0, stageData.getCellx());
        targetY = gameOnTouch.getTargetY(1, 0, stageData.getCelly());

        src.set(0, 0, (int)background.getSizeX(), (int)background.getSizeY());
        dst.set(posix, posiy, posiw, posih);
        canvas.drawBitmap(background.getImage(), src, dst, null);
    }
    
    // 射程等の描画
    public void controlDrawSet(Canvas canvas, StageDataObject stageData, GameOnTouch gameOnTouch) {
        for (int x = 0 ; x < stageData.getCellx() ; x++) {
            for (int y = 0 ; y < stageData.getCelly() ; y++) {
                
                if (gameOnTouch.getTower() != null) {
                    if (Common.rangeCheck(x, y, gameOnTouch.getTower().getX(), gameOnTouch.getTower().getY(), gameOnTouch.getTower().getRange(), gameOnTouch.getTower().isRangeCorner())) {
                        posix = (int)((nowOffX + (x * Common.CELLSIZE_X)) - Common.CELLHARFSIZE_X);
                        posiy = (int)((nowOffY + (y * Common.CELLSIZE_Y)) - Common.CELLHARFSIZE_Y);
                        posiw = (int)((nowOffX + (x * Common.CELLSIZE_X)) + Common.CELLHARFSIZE_X);
                        posih = (int)((nowOffY + (y * Common.CELLSIZE_Y)) + Common.CELLHARFSIZE_Y);
                        
                        dst.set(posix, posiy, posiw, posih);
                        canvas.drawRect(dst, gameOnTouch.getTower().getRangePaint());
                    }
                    if (Common.rangeCheck(x, y, gameOnTouch.getTower().getX(), gameOnTouch.getTower().getY(), gameOnTouch.getTower().getRangeAssist(), gameOnTouch.getTower().isRangeCornerAssist())) {
                        posix = (int)((nowOffX + (x * Common.CELLSIZE_X)) - Common.CELLHARFSIZE_X);
                        posiy = (int)((nowOffY + (y * Common.CELLSIZE_Y)) - Common.CELLHARFSIZE_Y);
                        posiw = (int)((nowOffX + (x * Common.CELLSIZE_X)) + Common.CELLHARFSIZE_X);
                        posih = (int)((nowOffY + (y * Common.CELLSIZE_Y)) + Common.CELLHARFSIZE_Y);
                        
                        dst.set(posix, posiy, posiw, posih);
                        canvas.drawRect(dst, gameOnTouch.getTower().getRangeAssistPaint());
                    }
                }
            }
        }
    }
    
    // グリッドの描画
    public void glidSet(Canvas canvas, StageDataObject stageData, boolean glid) {
        if (glid == true) {
            // グリッド表示
            for (int x = 0 ; x < stageData.getCellx() ; x++) {
                for (int y = 0 ; y < stageData.getCelly() ; y++) {
                    if (stageData.getPathData(x, y) == Common.AREA_OK && 
                        stageData.getAreaData(x, y) != Common.AREA_NOGROUND) {
                        posix = (int)((nowOffX + (x * Common.CELLSIZE_X)) - (Common.CELLSIZE_X / 2));
                        posiy = (int)((nowOffY + (y * Common.CELLSIZE_Y)) - (Common.CELLSIZE_Y / 2));
                        posiw = (int)((nowOffX + (x * Common.CELLSIZE_X)) + (Common.CELLSIZE_X / 2));
                        posih = (int)((nowOffY + (y * Common.CELLSIZE_Y)) + (Common.CELLSIZE_Y / 2));
                        dst.set(posix, posiy, posiw, posih);
                        canvas.drawRect(dst, glidPaint);
                    }
                }
            }
        }
        
    }
    
    // ゲームオブジェクトの描画
    public void gameDrawSet(Canvas canvas, StageDataObject stageData, 
            List<TowerObject> towerlist, List<EnemyObject> enemylist, List<ShotObject> shotlist, List<EffectObject> effectlist, 
            GameOnTouch gameOnTouch, Image image) {
        
        drawlist.removeAll(drawlist);
        drawlistAlpha.removeAll(drawlistAlpha);
        drawlistShadow.removeAll(drawlistShadow);
        
        for (int i = 0; i < towerlist.size(); i++) {    // 自軍タワーの描画
            TowerObject tower = towerlist.get(i);
            int z = 0;
            if (tower.getStatus() != 1) {
                tower.imageDispSet();
                
                if (tower.getType() == 9) { // トラップタイプのとき
                    z = 0;
                    tower.setPositionY((tower.getY() * Common.CELLSIZE_Y)+10);
                } else {
                    z = (int)tower.getPositionY();
                }
                
                drawlist.add(new DrawObject(tower.getImageNo(), 
                        tower.getDispX(), tower.getDispY(), tower.getDispX() + tower.getSizeX(), tower.getDispY() + tower.getSizeY(), 
                        (int)((nowOffX + tower.getPositionX()) - tower.getDispHarfSizeX() + tower.getDispOffX()), 
                        (int)((nowOffY + tower.getPositionY()) - tower.getDispHarfSizeY() + tower.getDispOffY()), 
                        (int)((nowOffX + tower.getPositionX()) + tower.getDispHarfSizeX() + tower.getDispOffX()), 
                        (int)((nowOffY + tower.getPositionY()) + tower.getDispHarfSizeY() + tower.getDispOffY()), 
                        z, 0.0f, tower.getPaint()));
                
                if (tower.getType() != 9) {
                    drawlistShadow.add(new DrawObject(tower.getImageNo(), 
                            tower.getDispX(), tower.getDispY(), tower.getDispX() + tower.getSizeX(), tower.getDispY() + tower.getSizeY(), 
                            (int)((nowOffX + tower.getPositionX() + 10) - tower.getDispHarfSizeX() + tower.getDispOffX()), 
                            (int)((nowOffY + tower.getPositionY() + 20) - tower.getDispHarfSizeY() + tower.getDispOffY()), 
                            (int)((nowOffX + tower.getPositionX() + 10) + tower.getDispHarfSizeX() + tower.getDispOffX()), 
                            (int)((nowOffY + tower.getPositionY()) + tower.getDispHarfSizeY() + tower.getDispOffY()), 
                            z, 0.0f, blackPaint));
                }
                
                // 1マス下が影ありのオブジェクトの場合、透過オブジェクトとして登録
                if (stageData.getAreaData(tower.getX(), tower.getY() + 1) == Common.AREA_SHADOW) {
                    drawlistAlpha.add(new DrawObject(tower.getImageNo(), 
                            tower.getDispX(), tower.getDispY(), tower.getDispX() + tower.getSizeX(), tower.getDispY() + tower.getSizeY(), 
                            (int)((nowOffX + tower.getPositionX()) - tower.getDispHarfSizeX() + tower.getDispOffX()), 
                            (int)((nowOffY + tower.getPositionY()) - tower.getDispHarfSizeY() + tower.getDispOffY()), 
                            (int)((nowOffX + tower.getPositionX()) + tower.getDispHarfSizeX() + tower.getDispOffX()), 
                            (int)((nowOffY + tower.getPositionY()) + tower.getDispHarfSizeY() + tower.getDispOffY()), 
                            z, 0.0f, objectPaint));
                }

            }
        }
        
        for (int i = 0; i < enemylist.size(); i++) {    // 敵軍エネミーの描画
            EnemyObject enemy = enemylist.get(i);
            if (enemy.isHide() == false) {
                enemy.imageDispSet();
                drawlist.add(new DrawObject(enemy.getImageNo(), 
                        enemy.getDispX(), enemy.getDispY(), enemy.getDispX() + enemy.getSizeX(), enemy.getDispY() + enemy.getSizeY(), 
                        (int)((nowOffX + enemy.getPositionX()) - enemy.getDispHarfSizeX() + enemy.getDispOffX()), 
                        (int)((nowOffY + enemy.getPositionY()) - enemy.getDispHarfSizeY() + enemy.getDispOffY()), 
                        (int)((nowOffX + enemy.getPositionX()) + enemy.getDispHarfSizeX() + enemy.getDispOffX()), 
                        (int)((nowOffY + enemy.getPositionY()) + enemy.getDispHarfSizeY() + enemy.getDispOffY()), 
                        (int)enemy.getPositionY(), enemy.getRotate(), enemy.getPaint()));
                
                drawlistShadow.add(new DrawObject(enemy.getImageNo(), 
                        enemy.getDispX(), enemy.getDispY(), enemy.getDispX() + enemy.getSizeX(), enemy.getDispY() + enemy.getSizeY(), 
                        (int)((nowOffX + enemy.getPositionX() + 10) - enemy.getDispHarfSizeX() + enemy.getDispOffX()), 
                        (int)((nowOffY + enemy.getPositionY() + 20) - enemy.getDispHarfSizeY() + enemy.getDispOffY()), 
                        (int)((nowOffX + enemy.getPositionX() + 10) + enemy.getDispHarfSizeX() + enemy.getDispOffX()), 
                        (int)((nowOffY + enemy.getPositionY()) + enemy.getDispHarfSizeY() + enemy.getDispOffY()), 
                        (int)enemy.getPositionY(), enemy.getRotate(), blackPaint));
            }
        }
        
        for (int i = 0; i < shotlist.size(); i++) {    // ショットの描画
            ShotObject shot = shotlist.get(i);
            if (shot.getStatus() != 1) {
                shot.imageDispSet();
                drawlist.add(new DrawObject(shot.getImageNo(), 
                        shot.getDispX(), shot.getDispY(), shot.getDispX() + shot.getSizeX(), shot.getDispY() + shot.getSizeY(), 
                        (int)((nowOffX + shot.getPositionX()) - shot.getDispHarfSizeX()), (int)((nowOffY + shot.getPositionY()) - shot.getDispHarfSizeY()), 
                        (int)((nowOffX + shot.getPositionX()) + shot.getDispHarfSizeX()), (int)((nowOffY + shot.getPositionY()) + shot.getDispHarfSizeY()), 
                        (int)shot.getPositionY(), 0.0f, null));
            }
        }
        
        for (int i = 0; i < effectlist.size(); i++) {    // エフェクトの描画
            EffectObject effect = effectlist.get(i);
            if (effect.getStatus() != 1) {
                effect.imageDispSet();
                drawlist.add(new DrawObject(effect.getImageNo(), 
                        effect.getDispX(), effect.getDispY(), effect.getDispX() + effect.getSizeX(), effect.getDispY() + effect.getSizeY(), 
                        (int)((nowOffX + effect.getPositionX()) - effect.getDispHarfSizeX() + effect.getDispOffX()), 
                        (int)((nowOffY + effect.getPositionY()) - effect.getDispHarfSizeY() + effect.getDispOffY()), 
                        (int)((nowOffX + effect.getPositionX()) + effect.getDispHarfSizeX() + effect.getDispOffX()), 
                        (int)((nowOffY + effect.getPositionY()) + effect.getDispHarfSizeY() + effect.getDispOffY()), 
                        (int)effect.getPositionY()+20, 0.0f, null));
            }
        }

        // ステージオブジェクトの描画（前面）
        for (int y = 0 ; y < stageData.getCelly() ; y++) {
            for (int x = 0 ; x < stageData.getCellx() ; x++) {
                
                if (stageData.getImageData(x, y) != 0) {
                    stageData.imageSizeSet(image.getImagelist().get(stageData.getImageData(x, y)), x, y);
                    drawlist.add(new DrawObject(
                            stageData.getImageData(x, y), 
                            stageData.getDispX(), stageData.getDispY(), stageData.getDispX() + stageData.getSizeX(), stageData.getDispY() + stageData.getSizeY(), 
                            (int)(nowOffX + (x * Common.CELLSIZE_X) - stageData.getDispHarfSizeX()), 
                            (int)(nowOffY + (y * Common.CELLSIZE_Y) - stageData.getDispSizeY() + Common.CELLHARFSIZE_Y), 
                            (int)(nowOffX + (x * Common.CELLSIZE_X) + stageData.getDispHarfSizeX()), 
                            (int)(nowOffY + (y * Common.CELLSIZE_Y) + Common.CELLHARFSIZE_Y), 
                            (y * Common.CELLSIZE_Y)-5, 0.0f, null)
                            );
                }
            }
        }
        
        // 影の描画
        for (int i = 0; i < drawlistShadow.size(); i++) {
            DrawObject draw = drawlistShadow.get(i);
            canvas.save();
            canvas.rotate(draw.getRotate(), draw.getDst().centerX(), draw.getDst().centerY());
            canvas.drawBitmap(image.getImagelist().get(draw.getImageNo()), draw.getSrc(), draw.getDst(), draw.getPaint());
            canvas.restore();
        }
        
        // 描画順をソート
        Common.quickSort(drawlist, 0, drawlist.size()-1);
        // オブジェクトの描画
        for (int i = 0; i < drawlist.size(); i++) {
            DrawObject draw = drawlist.get(i);
            canvas.save();
            canvas.rotate(draw.getRotate(), draw.getDst().centerX(), draw.getDst().centerY());
            canvas.drawBitmap(image.getImagelist().get(draw.getImageNo()), draw.getSrc(), draw.getDst(), draw.getPaint());
            canvas.restore();
        }
        
        // 透過オブジェクトの描画
        for (int i = 0; i < drawlistAlpha.size(); i++) {
            DrawObject draw = drawlistAlpha.get(i);
            canvas.save();
            canvas.rotate(draw.getRotate(), draw.getDst().centerX(), draw.getDst().centerY());
            canvas.drawBitmap(image.getImagelist().get(draw.getImageNo()), draw.getSrc(), draw.getDst(), draw.getPaint());
            canvas.restore();
        }
    }
    
    // エフェクトの描画
    public void effectDrawSet(Canvas canvas, StageDataObject stageData, List<EffectObject> effectlist, GameOnTouch gameOnTouch, Image image) {
        for (int i = 0; i < effectlist.size(); i++) {    // エフェクトの描画
            EffectObject effect = effectlist.get(i);
            if (effect.getStatus() != 1) {
                effect.imageDispSet();
                posix = (int)((nowOffX + effect.getPositionX()) - effect.getDispHarfSizeX() + effect.getDispOffX());
                posiy = (int)((nowOffY + effect.getPositionY()) - effect.getDispHarfSizeY() + effect.getDispOffY());
                posiw = (int)((nowOffX + effect.getPositionX()) + effect.getDispHarfSizeX() + effect.getDispOffX());
                posih = (int)((nowOffY + effect.getPositionY()) + effect.getDispHarfSizeY() + effect.getDispOffY());
    
                src.set(effect.getDispX(), effect.getDispY(), effect.getDispX() + effect.getSizeX(), effect.getDispY() + effect.getSizeY());
                dst.set(posix, posiy, posiw, posih);
                canvas.drawBitmap(image.getImagelist().get(effect.getImageNo()), src, dst, null);
            }
        }
    }
    
    // ステージエフェクトの描画
    public void stageEffectDrawSet(Canvas canvas, StageDataObject stageData, GameOnTouch gameOnTouch, Image image) {
        for (int i = 0; i < stageData.getStageEffectlist().size(); i++) {    // エフェクトの描画
            StageEffectObject stageEffect = stageData.getStageEffectlist().get(i);
            if (stageEffect.getStatus() != 1) {
                stageEffect.imageDispSet();
                posix = (int)((nowOffX + stageEffect.getPositionX()) - stageEffect.getDispHarfSizeX() + stageEffect.getDispOffX());
                posiy = (int)((nowOffY + stageEffect.getPositionY()) - stageEffect.getDispHarfSizeY() + stageEffect.getDispOffY());
                posiw = (int)((nowOffX + stageEffect.getPositionX()) + stageEffect.getDispHarfSizeX() + stageEffect.getDispOffX());
                posih = (int)((nowOffY + stageEffect.getPositionY()) + stageEffect.getDispHarfSizeY() + stageEffect.getDispOffY());
    
                src.set(stageEffect.getDispX(), stageEffect.getDispY(), stageEffect.getDispX() + stageEffect.getSizeX(), stageEffect.getDispY() + stageEffect.getSizeY());
                dst.set(posix, posiy, posiw, posih);
                canvas.drawBitmap(image.getImagelist().get(stageEffect.getImageNo()), src, dst, null);
            }
        }
    }
    
    // 自軍のステータス描画
    public void towerStatusDrawSet(Canvas canvas, List<TowerObject> towerlist, Image image) {
        for (int i = 0; i < towerlist.size(); i++) {
            TowerObject tower = towerlist.get(i);
            Bitmap bitmap = null;
            // 通常時およびトラップタイプではないとき
            if (tower.getStatus() != 1 && tower.getType() != 9) {
                if (tower.getLevel() == 1) {
                    // Lv1アイコン
                    bitmap = image.getImagelist().get(Common.IMAGE_LEVEL001);
                } else if (tower.getLevel() == 2) {
                    // Lv2アイコン
                    bitmap = image.getImagelist().get(Common.IMAGE_LEVEL002);
                } else if (tower.getLevel() == 3) {
                    // Lv3アイコン
                    bitmap = image.getImagelist().get(Common.IMAGE_LEVEL003);
                }
                src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
                dst.set((int)((nowOffX + (tower.getX() * Common.CELLSIZE_X))),
                        (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY() - bitmap.getHeight()),
                        (int)((nowOffX + (tower.getX() * Common.CELLSIZE_X)) + bitmap.getWidth()),
                        (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY()));
                canvas.drawBitmap(bitmap, src, dst, null);
                
                if (tower.getShotCountSpeed() > 1) {
                    // スピードアップアイコン
                    bitmap = image.getImagelist().get(Common.IMAGE_ICON011);
                    src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    dst.set((int)((nowOffX + (tower.getX() * Common.CELLSIZE_X)) - tower.getDispHarfSizeX()),
                            (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY() - 22),
                            (int)((nowOffX + (tower.getX() * Common.CELLSIZE_X)) - tower.getDispHarfSizeX() + 22),
                            (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY()));
                    canvas.drawBitmap(bitmap, src, dst, null);
                }
                if (tower.getShotPowerPlus() > 0 || tower.getAssistSkillPower() > 0) {
                    // パワーアップアイコン
                    bitmap = image.getImagelist().get(Common.IMAGE_ICON012);
                    src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    dst.set((int)((nowOffX + (tower.getX() * Common.CELLSIZE_X)) - tower.getDispHarfSizeX()),
                            (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY() - 44),
                            (int)((nowOffX + (tower.getX() * Common.CELLSIZE_X)) - tower.getDispHarfSizeX() + 22),
                            (int)((nowOffY + (tower.getY() * Common.CELLSIZE_Y)) + tower.getDispHarfSizeY() - 22));
                    canvas.drawBitmap(bitmap, src, dst, null);
                }
            }
        }
    }
    
    // 敵軍のステータス描画
    public void enemyStatusDrawSet(Canvas canvas, List<EnemyObject> enemylist) {
        for (int i = 0; i < enemylist.size(); i++) {
            EnemyObject enemy = enemylist.get(i);
            if (enemy.isHide() == false) {
                if (enemy.getLife() != 0) {
                    posix = (int)(nowOffX + enemy.getPositionX() - Common.CELLHARFSIZE_X) + 10;
                    posiy = (int)(nowOffY + enemy.getPositionY() + enemy.getDispHarfSizeY()) - 20;
                    posiw = (int)(nowOffX + enemy.getPositionX() - Common.CELLHARFSIZE_X + Common.ENEMY_LIFEGAUGE_WIDTH + 10);
                    posih = (int)(nowOffY + enemy.getPositionY() + enemy.getDispHarfSizeY() - 25);
                    
                    dst.set(posix, posiy, posiw, posih);
                    canvas.drawRect(dst, enemylifegaugePaint);
                    
                    posix = (int)(nowOffX + enemy.getPositionX() - Common.CELLHARFSIZE_X + 11);
                    posiy = (int)(nowOffY + enemy.getPositionY() + enemy.getDispHarfSizeY() - 22);
                    posiw = (int)(nowOffX + enemy.getPositionX() - Common.CELLHARFSIZE_X + (((float)enemy.getLife() / (float)enemy.getLifeMax()) * Common.ENEMY_LIFEGAUGE_WIDTH) + 9);
                    posih = (int)(nowOffY + enemy.getPositionY() + enemy.getDispHarfSizeY() - 24);
                    
                    dst.set(posix, posiy, posiw, posih);
                    canvas.drawRect(dst, enemylifePaint);
                }
            }
            if (enemy.getStatus() == 4) {
                canvas.drawText("+"+enemy.getGold(), nowOffX + enemy.getPositionX()-2, nowOffY + enemy.getPositionY() - enemy.getDamageCount(), systemPaint);
            }
        }
    }
    
    // ボタンの描画
    public void buttonDrawSet(Canvas canvas, StageDataObject stageData, GameOnTouch gameOnTouch, Image image) {
        for (int i = 0; i < gameOnTouch.getButtonlist().size(); i++) {    // ボタンの描画
            ButtonObject button = gameOnTouch.getButtonlist().get(i);
    
            if (button.getStatus() != 0 && button.isHide() == true) {
                src.set(button.getDispX(), button.getDispY(), button.getDispX() + button.getSizeX(), button.getDispY() + button.getSizeY());
                dst.set((int)(button.getPositionX() - button.getDispHarfSizeX()), (int)(button.getPositionY() - button.getDispHarfSizeY()), 
                        (int)(button.getPositionX() + button.getDispHarfSizeX()), (int)(button.getPositionY() + button.getDispHarfSizeY()));
                canvas.drawBitmap(image.getImagelist().get(button.getImageNo()), src, dst, button.getPaint());
                
                if (button.getType() == 1) {
                    src.set(176, 0, 192, 32);
                    dst.set((int)(button.getPositionX() + button.getDispHarfSizeX() - 48),
                            (int)(button.getPositionY() + button.getDispHarfSizeY()), 
                            (int)(button.getPositionX() + button.getDispHarfSizeX() - 32), 
                            (int)(button.getPositionY() + button.getDispHarfSizeY() + 32));
                    canvas.drawBitmap(image.getImagelist().get(stageData.getNumberImageNo()), src, dst, null);
                    src.set(132, 0, 144, 24);
                    dst.set((int)(button.getPositionX() + button.getDispHarfSizeX()) - 48, (int)(button.getPositionY() + button.getDispHarfSizeY()), 
                            (int)(button.getPositionX() + button.getDispHarfSizeX()) - 36, (int)(button.getPositionY() + button.getDispHarfSizeY() + 24));
                    canvas.drawBitmap(image.getImagelist().get(stageData.getNumberImageNo()), src, dst, null);
                    numberDrawSet(canvas, image, stageData, button.getGold(), (int)(button.getPositionX() + button.getDispHarfSizeX()), (int)(button.getPositionY() + button.getDispHarfSizeY()), 0);
                    
                    if (button.getStatus() == 3) {
                        // 配置用のアイコン動作中の時
                        if (gameOnTouch.getTower() != null) {
                            // トラップタイプではないとき
                            if (gameOnTouch.getTower().getType() != 9) {
                                // 配置可能なエリアの時
                                if (stageData.getPathData(targetX, targetY) == Common.AREA_OK && 
                                        stageData.getAreaData(targetX, targetY) != Common.AREA_SPEAR && 
                                        stageData.getAreaData(targetX, targetY) != Common.AREA_MINE &&
                                        stageData.getAreaData(targetX, targetY) != Common.AREA_NOGROUND &&
                                        stageData.getAreaData(targetX, targetY) != Common.AREA_DAMAGE
                                        ) {
                                        stageData.setVirtualData(stageData.getMoveData(), stageData.getPathData());
                                        stageData.setVirtualPathData(Common.AREA_NO, targetX, targetY);
                                        // タワー配置時を仮想し、配置チェックを行う
                                        if (stageData.updateMoveData(stageData.getVirtualMoveData(), stageData.getVirtualPathData())) {
                                            gameOnTouch.getTower().imageSizeSet(image.getImagelist().get(gameOnTouch.getTower().getImageNo()));
                                            
                                            posix = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) - gameOnTouch.getTower().getDispHarfSizeX() + gameOnTouch.getTower().getDispOffX());
                                            posiy = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) - gameOnTouch.getTower().getDispHarfSizeY() + gameOnTouch.getTower().getDispOffY());
                                            posiw = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) + gameOnTouch.getTower().getDispHarfSizeX() + gameOnTouch.getTower().getDispOffX());
                                            posih = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) + gameOnTouch.getTower().getDispHarfSizeY() + gameOnTouch.getTower().getDispOffY());
                                            
                                            src.set(gameOnTouch.getTower().getDispX(),
                                                    gameOnTouch.getTower().getDispY(), 
                                                    gameOnTouch.getTower().getDispX() + gameOnTouch.getTower().getSizeX(), 
                                                    gameOnTouch.getTower().getDispY() + gameOnTouch.getTower().getSizeY());
                                            dst.set(posix, posiy, posiw, posih);
                                            canvas.drawBitmap(image.getImagelist().get(gameOnTouch.getTower().getImageNo()), src, dst, null);
                                            
                                        } else {
                                            posix = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) - (Common.CELLSIZE_X / 2));
                                            posiy = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) - (Common.CELLSIZE_Y / 2));
                                            posiw = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) + (Common.CELLSIZE_X / 2));
                                            posih = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) + (Common.CELLSIZE_Y / 2));
                                            dst.set(posix, posiy, posiw, posih);
                                            canvas.drawRect(dst, redPaint);
                                        }
                                        
                                } else {
                                    posix = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) - (Common.CELLSIZE_X / 2));
                                    posiy = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) - (Common.CELLSIZE_Y / 2));
                                    posiw = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) + (Common.CELLSIZE_X / 2));
                                    posih = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) + (Common.CELLSIZE_Y / 2));
                                    dst.set(posix, posiy, posiw, posih);
                                    canvas.drawRect(dst, redPaint);
                                }
                            } else {
                                // 配置可能なエリアの時
                                if (stageData.getPathData(targetX, targetY) == Common.AREA_OK && stageData.getAreaData(targetX, targetY) == Common.AREA_FREE) {
                                    gameOnTouch.getTower().imageSizeSet(image.getImagelist().get(gameOnTouch.getTower().getImageNo()));
                                    
                                    posix = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) - gameOnTouch.getTower().getDispHarfSizeX() + gameOnTouch.getTower().getDispOffX());
                                    posiy = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)+10) - gameOnTouch.getTower().getDispHarfSizeY() + gameOnTouch.getTower().getDispOffY());
                                    posiw = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) + gameOnTouch.getTower().getDispHarfSizeX() + gameOnTouch.getTower().getDispOffX());
                                    posih = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)+10) + gameOnTouch.getTower().getDispHarfSizeY() + gameOnTouch.getTower().getDispOffY());
                                    
                                    src.set(gameOnTouch.getTower().getDispX(), gameOnTouch.getTower().getDispY(), 
                                            gameOnTouch.getTower().getDispX() + gameOnTouch.getTower().getSizeX(), 
                                            gameOnTouch.getTower().getDispY() + gameOnTouch.getTower().getSizeY());
                                    dst.set(posix, posiy, posiw, posih);
                                    canvas.drawBitmap(image.getImagelist().get(gameOnTouch.getTower().getImageNo()), src, dst, null);
                                    
                                } else {
                                    posix = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) - (Common.CELLSIZE_X / 2));
                                    posiy = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) - (Common.CELLSIZE_Y / 2));
                                    posiw = (int)((nowOffX + (targetX * Common.CELLSIZE_X)) + (Common.CELLSIZE_X / 2));
                                    posih = (int)((nowOffY + (targetY * Common.CELLSIZE_Y)) + (Common.CELLSIZE_Y / 2));
                                    dst.set(posix, posiy, posiw, posih);
                                    canvas.drawRect(dst, redPaint);
                                }
                                
                            }
                        }
                    }
                }
            }
        }
    }
    
    // タッチ箇所の描画
    public void locationDrawSet(Canvas canvas, Image image) {
        posix = (int)(nowOffX + (targetX * Common.CELLSIZE_X)-Common.CELLHARFSIZE_X);
        posiy = (int)(nowOffY + (targetY * Common.CELLSIZE_Y)-Common.CELLHARFSIZE_Y);
        posiw = (int)(nowOffX + (targetX * Common.CELLSIZE_X)+Common.CELLHARFSIZE_X);
        posih = (int)(nowOffY + (targetY * Common.CELLSIZE_Y)+Common.CELLHARFSIZE_Y);
        canvas.drawLine(posix, posiy, posiw, posiy, locationPaint);
        canvas.drawLine(posix, posiy, posix, posih, locationPaint);
        canvas.drawLine(posiw, posiy, posiw, posih, locationPaint);
        canvas.drawLine(posix, posih, posiw, posih, locationPaint);
    }
    
    // ストップ・再生・早送りの描画
    public void playDrawSet(Canvas canvas, GameOnTouch gameOnTouch, Image image) {
        for (int i = 0; i < gameOnTouch.getPlaylist().size(); i++) {    // ボタンの描画
            PlayObject play = gameOnTouch.getPlaylist().get(i);
    
            if (play.isHide() == true) {
                src.set(play.getDispX(), play.getDispY(), play.getDispX() + play.getSizeX(), play.getDispY() + play.getSizeY());
                dst.set((int)(play.getPositionX() - play.getDispHarfSizeX()), (int)(play.getPositionY() - play.getDispHarfSizeY()), 
                        (int)(play.getPositionX() + play.getDispHarfSizeX()), (int)(play.getPositionY() + play.getDispHarfSizeY()));
                canvas.drawBitmap(image.getImagelist().get(play.getImageNo()), src, dst, play.getPaint());
            }
        }
    }
    
    // 各アイコンの描画
    public void iconStatusDrawSet(Canvas canvas, Player player, Image image, StageDataObject stageData, int waveNo) {
        int sizex = 64;
        int sizey = 64;
        int xoff;
        int yoff;
        
        // ライフ
        src.set(0, 0, 48, 48);
        xoff = player.getStageSizeX() - 32;
        yoff = 32;
        dst.set(xoff - sizex, yoff, xoff, sizey + yoff);
        canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON001), src, dst, null);
        numberDrawSet(canvas, image, stageData, player.getLife(), xoff - 32 + 12, 16 + 8 + yoff, 0);
        
        // ゴールド
        src.set(0, 0, 64, 64);
        xoff = player.getStageSizeX() - 32;
        yoff = 96;
        dst.set(xoff - sizex, yoff, xoff, sizey + yoff);
        canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON002), src, dst, null);
        numberDrawSet(canvas, image, stageData, player.getGold(), xoff - 32 + 12, 16 + 8 + yoff, 0);
        
        // スコア
        src.set(0, 0, 64, 64);
        xoff = 32;
        yoff = 32;
        dst.set(xoff, yoff, sizex + xoff, sizey + yoff);
        canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON003), src, dst, null);
        numberDrawSet(canvas, image, stageData, player.getScore(), xoff + 32, 16 + 8 + yoff, 1);
        
        // ステージレベル＆ウェーブ
        sizex = 79;
        sizey = 27;
        src.set(0, 0, sizex, sizey);
        xoff = (player.getStageSizeX()/2) - 30 - sizex;
        yoff = 32;
        dst.set(xoff, yoff, xoff + sizex, yoff + sizey);
        canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_LEVEL), src, dst, null);
        int wave = waveNo;
        if (wave == 0) {
            wave = 1;
        }
        src.set(132, 0, 144, 24);
        xoff = (player.getStageSizeX()/2);
        yoff = 70;
        dst.set(xoff, yoff, xoff + 12, yoff + 24);
        canvas.drawBitmap(image.getImagelist().get(stageData.getNumberImageNo()), src, dst, null);
        numberDrawSet(canvas, image, stageData, player.getStageLevel(), xoff - 32, yoff, 0);
        numberDrawSet(canvas, image, stageData, wave, xoff + 12 + 32, yoff, 1);

        sizex = 80;
        sizey = 26;
        src.set(0, 0, sizex, sizey);
        xoff = (player.getStageSizeX()/2) + 30;
        yoff = 32;
        dst.set(xoff, yoff, xoff + sizex, yoff + sizey);
        canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_WAVE), src, dst, null);
    }
    
    private void numberDrawSet(Canvas canvas, Image image, StageDataObject stageData, int num, int x, int y, int dir) {
        int len = String.valueOf(num).length();
        int n;
        int j = 0;
        
        if (dir == 0) {
            // 右から左へ
            for (int i = len - 1 ; i >= 0 ; i--) {
                n = Integer.parseInt(String.valueOf((String.valueOf(num).charAt(i))));
                src.set(12 * n, 0, 12 * (n + 1), 24);
                dst.set(x - (12 * (j + 1)), y, x - (12 * j), y + 24);
                canvas.drawBitmap(image.getImagelist().get(stageData.getNumberImageNo()), src, dst, null);
                j++;
            }
        } else if (dir == 1) {
            // 左から右へ
            for (int i = 0 ; i < len ; i++) {
                n = Integer.parseInt(String.valueOf((String.valueOf(num).charAt(i))));
                src.set(12 * n, 0, 12 * (n + 1), 24);
                dst.set(x + (12 * j), y, x + (12 * (j + 1)), y + 24);
                canvas.drawBitmap(image.getImagelist().get(stageData.getNumberImageNo()), src, dst, null);
                j++;
            }
        }
        
    }
    
    // サブメニューの描画
    public void submenuDrawSet(Canvas canvas, GameOnTouch gameOnTouch, Image image) {
        if (gameOnTouch.isSubmenuHideFlg() == true) {
            for (int i = 0; i < gameOnTouch.getSubMenulist().size(); i++) {    // サブメニューの描画
                SubMenuObject submenu = gameOnTouch.getSubMenulist().get(i);
                
                canvas.save();
                
                posix = (int)((nowOffX + submenu.getPositionX()));
                posiy = (int)((nowOffY + submenu.getPositionY()));
    
                src.set(0, 0, 15, 30);  // ウィンドウ左
                dst.set(posix, posiy, posix + 30, posiy + 90);
                canvas.drawBitmap(image.getImagelist().get(submenu.getImageNo()), src, dst, submenu.getPaint());
    
                src.set(10, 0, 20, 30);  // ウィンドウ中央
                dst.set(posix + 30, posiy, posix + 400, posiy + 90);
                canvas.drawBitmap(image.getImagelist().get(submenu.getImageNo()), src, dst, submenu.getPaint());
                
                src.set(15, 0, 30, 30);  // ウィンドウ右
                dst.set(posix + 400, posiy, posix + 430, posiy + 90);
                canvas.drawBitmap(image.getImagelist().get(submenu.getImageNo()), src, dst, submenu.getPaint());
                
                canvas.drawText(submenu.getMenuText(), posix + 20, posiy + 40, systemPaint);
            }
        }
        
    }

    // カットインの描画
    public void startSignDrawSet(Canvas canvas, SignObject Sign, GameOnTouch gameOnTouch, Image image) {
        if (Sign.getStatus() == 2 || Sign.getStatus() == 3) {
            canvas.save();
            canvas.scale(1.0f + (float)(Sign.getActionCount() * 0.1f), 
                    1.0f + (float)(Sign.getActionCount() * 0.1f), 
                    gameOnTouch.getDisplayX()/2, gameOnTouch.getDisplayY()/2);
            src.set(0, 0, Sign.getSizeX(), Sign.getSizeY());
            posix = (int)(Sign.getPositionX() - (Sign.getActionCount()*2) - (gameOnTouch.getDisplayX()/2));
            posiy = (int)(Sign.getPositionY() - (gameOnTouch.getDisplayY()/2));
            posiw = (int)(Sign.getPositionX() - (Sign.getActionCount()*2)  + (gameOnTouch.getDisplayX()/2));
            posih = (int)(Sign.getPositionY() + (gameOnTouch.getDisplayY()/2));
            dst.set(posix, posiy, posiw, posih);
            alphaPaint.setAlpha(255-(Sign.getActionCount()*2));
            canvas.drawBitmap(Sign.getImage(), src, dst, alphaPaint);
            canvas.restore();
        }
        if (Sign.getStatus() == 2) {
            canvas.drawText(context.getResources().getString(R.string.cutin_gamestart), 20, 150, systemPaint);
        }
    }
    
    // リザルトの描画
    public void resultDrawSet(Canvas canvas, Player player, GameOnTouch gameOnTouch, Bitmap resultBmp, Image image, StageDataObject stageData, int gameStatus) {
        int centerx = gameOnTouch.getDisplayX() / 2;
        int centery = gameOnTouch.getDisplayY() / 2;
        int w = resultBmp.getWidth();
        int h = resultBmp.getHeight();
        int yoff = -100;
        
        // リザルトメッセージ
        dst.set(0, 0, gameOnTouch.getDisplayX(), gameOnTouch.getDisplayY());
        canvas.drawRect(dst, resultPaint);
        
        src.set(0, 0, w, h);
        posix = (int)((centerx) - w / 2);
        posiy = (int)((150) - h / 2);
        posiw = (int)((centerx) + w / 2);
        posih = (int)((150) + h / 2);

        dst.set(posix, posiy, posiw, posih);
        canvas.drawBitmap(resultBmp, src, dst, null);
        
        if (gameStatus == Common.GAMESTATUS_GAMESUCCESS || gameStatus == Common.GAMESTATUS_GAMEFAILURE) {
            DecimalFormat df = new DecimalFormat("###,###,###");
            
            // スコア
            src.set(0, 0, 64, 64);
            dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
            canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON003), src, dst, null);
            canvas.drawText(context.getResources().getString(R.string.get_score), centerx - 170, centery + yoff + 24, systemPaint);
            canvas.drawText(":", centerx + 150, centery + yoff + 24, systemPaint);
            canvas.drawText(""+df.format(player.getScore()).toString(), centerx + 200, centery + yoff + 24, systemPaint);
            yoff += 50;

            // ステージボーナス
            src.set(0, 0, 64, 64);
            dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
            canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON003), src, dst, null);
            canvas.drawText(context.getResources().getString(R.string.get_stagebonus), centerx - 170, centery + yoff + 24, systemPaint);
            canvas.drawText(":", centerx + 150, centery + yoff + 24, systemPaint);
            canvas.drawText(""+df.format(player.getStageBonus()).toString(), centerx + 200, centery + yoff + 24, systemPaint);
            yoff += 50;
            
            // パーフェクトボーナス
            if (player.getNodamageBonus() > 0) {
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON003), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.get_perfectbonus), centerx - 170, centery + yoff + 24, goldPaint);
                canvas.drawText(":", centerx + 150, centery + yoff + 24, systemPaint);
                canvas.drawText(""+df.format(player.getNodamageBonus()).toString(), centerx + 200, centery + yoff + 24, goldPaint);
                yoff += 50;
            }
            
            // 報酬の合計
            src.set(0, 0, 64, 64);
            dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
            canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON004), src, dst, null);
            canvas.drawText(context.getResources().getString(R.string.get_total_reward), centerx - 170, centery + yoff + 24, systemPaint);
            canvas.drawText(":", centerx + 150, centery + yoff + 24, systemPaint);
            canvas.drawText(""+df.format(player.getTotalCount()).toString(), centerx + 200, centery + yoff + 24, systemPaint);
            yoff += 50;
            
        } else if (gameStatus == Common.GAMESTATUS_GAMERELEASE) {
         // ステージレベルアップ
            if (player.isStageLevelup() == true) {
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.stage_levelup), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            }
            
            // ステージ解放
            String[] location = context.getResources().getStringArray(R.array.list_stagelocation);
            if (player.getStageRelease() == 1) {
                // 三つの道標解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[1] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 2) {
                // 最前線解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[2] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
                // 略奪されし鉱山解放
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[3] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
                // 魔の森解放
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[5] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 3) {
                // 死者の都解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[7] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 4) {
                // 結晶洞窟解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[4] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 5) {
                // 守護者解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[6] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 6) {
                // 亡国の王解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[8] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 7) {
                // 魔境解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[9] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getStageRelease() == 8) {
                // 竜の巣解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(location[10] + context.getResources().getString(R.string.stage_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            }
            
            // 戦力強化項目の追加
            if (player.isStageLevelup() == true) {
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.shop_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            }
            if (player.getShopTowerRelease() == 1) { // 勇士の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.tower002_name) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 2) { // 時の術師の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.tower012_name) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 3) { // 聖女の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.tower022_name) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 4) { // 地雷の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.tower032_name) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 11) { // スキル1の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.submenu_skillname000) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 12) { // スキル2の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.submenu_skillname001) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            } else if (player.getShopTowerRelease() == 13) { // スキル3の項目解放
                src.set(0, 0, 64, 64);
                dst.set(centerx - 200, centery + yoff, centerx - 200 + 24, centery + yoff + 24);
                canvas.drawBitmap(image.getImagelist().get(Common.IMAGE_ICON005), src, dst, null);
                canvas.drawText(context.getResources().getString(R.string.submenu_skillname002) + context.getResources().getString(R.string.result_release), centerx - 170, centery + yoff + 24, systemPaint);
                yoff += 50;
            }
        }
    }

    public int getNowOffX() {
        return nowOffX;
    }

    public void setNowOffX(int nowOffX) {
        this.nowOffX = nowOffX;
    }

    public int getNowOffY() {
        return nowOffY;
    }

    public void setNowOffY(int nowOffY) {
        this.nowOffY = nowOffY;
    }
}
