package com.example.defense_souls;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameOnTouch implements View.OnTouchListener {
    
    private boolean checkFlg;
    private boolean onFlg;
    private boolean moveFlg;
    private boolean submenuOnFlg;
    private boolean submenuHideFlg;
    private boolean buttonOnFlg;
    private boolean playOnFlg;
    private boolean resultFlg;
    private boolean endFlg;
    private int endStatus;
    private int flickFlg;
    private int flickOtherFlg;
    
    // 最後にタッチされた座標
    private float startTouchX;
    private float startTouchY;
    private float startTouchOtherX;
    private float startTouchOtherY;
    
    private float canvasX;
    private float canvasY;
    private float keepCanvasX;
    private float keepCanvasY;
 
    // 現在タッチ中の座標
    private float nowTouchedX;
    private float nowTouchedY;
    private float nowTouchedOtherX;
    private float nowTouchedOtherY;
    private float baseSize;
    
    public int displayX;
    public int displayY;
    
    private int canvasXAdjustMIN;
    private int canvasXAdjustMAX;
    private int canvasYAdjustMIN;
    private int canvasYAdjustMAX;

    private List<ButtonObject> buttonlist;
    private List<PlayObject> playlist;
    private List<SubMenuObject> submenulist;
    private TowerObject Tower;

    @Override
    public boolean onTouch( View view, MotionEvent motionEvent )
    {
        if (checkFlg == false) {
            return true;
        }
              // タッチされている座標
//      Log.v( "Y", ""+motionEvent.getY() );
//      Log.v( "X", ""+motionEvent.getX() );
        Log.v( "on", ""+onFlg );
        switch( motionEvent.getAction() ){
        // タッチ
        case MotionEvent.ACTION_DOWN:
            startTouchX = motionEvent.getX();
            startTouchY = motionEvent.getY();
            startTouchOtherX = startTouchX;
            startTouchOtherY = startTouchY;
            onFlg = true;
            
            if (resultFlg == true) {
                if (endStatus == 0) {
                    endStatus = 1;
                } else if (endStatus == 1) {
                    endFlg = true;
                }
            }
            break;
            
        // タッチ中に追加でタッチした場合
        case MotionEvent.ACTION_POINTER_DOWN:
            break;
     
        // スライド
        case MotionEvent.ACTION_MOVE:
            moveFlg = true;
            if (resultFlg == false) {
                startTouchX = 0;
                startTouchY = 0;
                nowTouchedX = motionEvent.getX();
                nowTouchedY = motionEvent.getY();
                // ボタンアイコンをタッチしていないとき
                if (submenuHideFlg == false && buttonOnFlg == false) {
                    canvasX = keepCanvasX + nowTouchedX - startTouchOtherX;
                    canvasY = keepCanvasY + nowTouchedY - startTouchOtherY;
                    if (canvasX < canvasXAdjustMIN) {
                        canvasX = canvasXAdjustMIN;
                    } else if (canvasX > canvasXAdjustMAX) {
                        canvasX = canvasXAdjustMAX;
                    }
                    if (canvasY < canvasYAdjustMIN) {
                        canvasY = canvasYAdjustMIN;
                    } else if (canvasY > canvasYAdjustMAX) {
                        canvasY = canvasYAdjustMAX;
                    }
                }
            }
            break;
     
        // タッチが離れた
        case MotionEvent.ACTION_UP:
            if (resultFlg == false) {
                nowTouchedX = motionEvent.getX();
                nowTouchedY = motionEvent.getY();
                // ボタンアイコンをタッチしていないとき
                if (submenuOnFlg == false && buttonOnFlg == false) {
                    keepCanvasX = canvasX;
                    keepCanvasY = canvasY;
                }
                onFlg = false;
                moveFlg = false;
                buttonOnFlg = false;
                playOnFlg = false;
                submenuOnFlg = false;
                
                startTouchX = 0;
                startTouchY = 0;
                startTouchOtherX = 0;
                startTouchOtherY = 0;
                nowTouchedOtherX = 0;
                nowTouchedOtherY = 0;
            }
            break;
     
            // アップ後にほかの指がタッチ中の場合
           case MotionEvent.ACTION_POINTER_UP:
               break;
     
               // UP+DOWNの同時発生(タッチのキャンセル）
           case MotionEvent.ACTION_CANCEL:
               Log.v( "motionEvent", "--ACTION_CANCEL" );
     
               // ターゲットとするUIの範囲外を押下
           case MotionEvent.ACTION_OUTSIDE:
               Log.v( "motionEvent", "--ACTION_OUTSIDE" );
               break;
        }
        return true;
    }
    
    public int getTargetX(int type, float positionX, int max) {
        int x = 0;
        if (type == 0) {
            x = (int)((startTouchX - canvasX + positionX) / Common.CELLSIZE_X);
        } else if (type == 1) {
            x = (int)((nowTouchedX - canvasX + positionX) / Common.CELLSIZE_X);
        } else if (type == 2) {
            x = (int)((positionX +  Common.CELLHARFSIZE_X) / Common.CELLSIZE_X);
        }
        
        if (x <= 0) {
            x = 0;
        } else if (x >= max) {
            x = max;
        }
        
        return x;
    }

    public int getTargetY(int type, float positionY, int max) {
        int y = 0;
        if (type == 0) {
            y = (int)((startTouchY - canvasY + positionY) / Common.CELLSIZE_Y);
        } else if (type == 1) {
            y = (int)((nowTouchedY - canvasY + positionY) / Common.CELLSIZE_Y);
        } else if (type == 2) {
            y = (int)((positionY +  Common.CELLHARFSIZE_Y) / Common.CELLSIZE_Y);
        }
        
        if (y <= 0) {
            y = 0;
        } else if (y >= max) {
            y = max;
        }
        
        return y;
    }
    
    public void initSet() {
        buttonlist = new ArrayList<ButtonObject>();
        playlist = new ArrayList<PlayObject>();
        submenulist = new ArrayList<SubMenuObject>();
        
        checkFlg = false;
        onFlg = false;
        moveFlg = false;
        submenuOnFlg = false;
        resultFlg = false;
        endFlg = false;
        endStatus = 0;
              
        canvasX = 0;
        canvasY = 0;
        keepCanvasX = 0;
        keepCanvasY = 0;
    }

    public boolean isOnFlg() {
        return onFlg;
    }

    public void setOnFlg(boolean onFlg) {
        this.onFlg = onFlg;
    }

    public int getFlickFlg() {
        return flickFlg;
    }

    public void setFlickFlg(int flickFlg) {
        this.flickFlg = flickFlg;
    }

    public int getFlickOtherFlg() {
        return flickOtherFlg;
    }

    public void setFlickOtherFlg(int flickOtherFlg) {
        this.flickOtherFlg = flickOtherFlg;
    }

    public float getStartTouchX() {
        return startTouchX;
    }

    public void setStartTouchX(float startTouchX) {
        this.startTouchX = startTouchX;
    }

    public float getStartTouchY() {
        return startTouchY;
    }

    public void setStartTouchY(float startTouchY) {
        this.startTouchY = startTouchY;
    }

    public float getStartTouchOtherX() {
        return startTouchOtherX;
    }

    public void setStartTouchOtherX(float startTouchOtherX) {
        this.startTouchOtherX = startTouchOtherX;
    }

    public float getStartTouchOtherY() {
        return startTouchOtherY;
    }

    public void setStartTouchOtherY(float startTouchOtherY) {
        this.startTouchOtherY = startTouchOtherY;
    }

    public float getCanvasX() {
        return canvasX;
    }

    public void setCanvasX(float canvasX) {
        this.canvasX = canvasX;
    }

    public float getCanvasY() {
        return canvasY;
    }

    public void setCanvasY(float canvasY) {
        this.canvasY = canvasY;
    }

    public float getKeepCanvasX() {
        return keepCanvasX;
    }

    public void setKeepCanvasX(float keepCanvasX) {
        this.keepCanvasX = keepCanvasX;
    }

    public float getKeepCanvasY() {
        return keepCanvasY;
    }

    public void setKeepCanvasY(float keepCanvasY) {
        this.keepCanvasY = keepCanvasY;
    }

    public float getNowTouchedX() {
        return nowTouchedX;
    }

    public void setNowTouchedX(float nowTouchedX) {
        this.nowTouchedX = nowTouchedX;
    }

    public float getNowTouchedY() {
        return nowTouchedY;
    }

    public void setNowTouchedY(float nowTouchedY) {
        this.nowTouchedY = nowTouchedY;
    }

    public float getNowTouchedOtherX() {
        return nowTouchedOtherX;
    }

    public void setNowTouchedOtherX(float nowTouchedOtherX) {
        this.nowTouchedOtherX = nowTouchedOtherX;
    }

    public float getNowTouchedOtherY() {
        return nowTouchedOtherY;
    }

    public void setNowTouchedOtherY(float nowTouchedOtherY) {
        this.nowTouchedOtherY = nowTouchedOtherY;
    }

    public int getCanvasXAdjustMIN() {
        return canvasXAdjustMIN;
    }

    public void setCanvasXAdjustMIN(int canvasXAdjustMIN) {
        this.canvasXAdjustMIN = canvasXAdjustMIN;
    }

    public int getCanvasXAdjustMAX() {
        return canvasXAdjustMAX;
    }

    public void setCanvasXAdjustMAX(int canvasXAdjustMAX) {
        this.canvasXAdjustMAX = canvasXAdjustMAX;
    }

    public int getCanvasYAdjustMIN() {
        return canvasYAdjustMIN;
    }

    public void setCanvasYAdjustMIN(int canvasYAdjustMIN) {
        this.canvasYAdjustMIN = canvasYAdjustMIN;
    }

    public int getCanvasYAdjustMAX() {
        return canvasYAdjustMAX;
    }

    public void setCanvasYAdjustMAX(int canvasYAdjustMAX) {
        this.canvasYAdjustMAX = canvasYAdjustMAX;
    }


    public List<ButtonObject> getButtonlist() {
        return buttonlist;
    }

    public void setPlaylist(PlayObject playObject) {
        this.playlist.add(playObject);
    }

    public List<PlayObject> getPlaylist() {
        return playlist;
    }

    public void setButtonlist(ButtonObject buttonObject) {
        this.buttonlist.add(buttonObject);
    }
    
    public List<SubMenuObject> getSubMenulist() {
        return submenulist;
    }

    public void setSubMenulist(SubMenuObject SubMenuObject) {
        this.submenulist.add(SubMenuObject);
    }
    
    public void subMenuClear() {
        submenulist.removeAll(submenulist);
    }

    public TowerObject getTower() {
        return Tower;
    }

    public void setTower(TowerObject Tower) {
        this.Tower = Tower;
    }

    public float getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(float baseSize) {
        this.baseSize = baseSize;
    }

    public int getDisplayX() {
        return displayX;
    }

    public void setDisplayX(int displayX) {
        this.displayX = displayX;
    }

    public int getDisplayY() {
        return displayY;
    }

    public void setDisplayY(int displayY) {
        this.displayY = displayY;
    }

    public boolean isSubmenuOnFlg() {
        return submenuOnFlg;
    }

    public void setSubmenuOnFlg(boolean submenuOnFlg) {
        this.submenuOnFlg = submenuOnFlg;
    }

    public boolean isResultFlg() {
        return resultFlg;
    }

    public void setResultFlg(boolean resultFlg) {
        this.resultFlg = resultFlg;
    }

    public boolean isEndFlg() {
        return endFlg;
    }

    public void setEndFlg(boolean endFlg) {
        this.endFlg = endFlg;
    }

    public int getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(int endStatus) {
        this.endStatus = endStatus;
    }

    public boolean isMoveFlg() {
        return moveFlg;
    }

    public void setMoveFlg(boolean moveFlg) {
        this.moveFlg = moveFlg;
    }

    public boolean isCheckFlg() {
        return checkFlg;
    }

    public void setCheckFlg(boolean checkFlg) {
        this.checkFlg = checkFlg;
    }

    public boolean isPlayOnFlg() {
        return playOnFlg;
    }

    public void setPlayOnFlg(boolean playOnFlg) {
        this.playOnFlg = playOnFlg;
    }

    public boolean isSubmenuHideFlg() {
        return submenuHideFlg;
    }

    public void setSubmenuHideFlg(boolean submenuHideFlg) {
        this.submenuHideFlg = submenuHideFlg;
    }

    public boolean isButtonOnFlg() {
        return buttonOnFlg;
    }

    public void setButtonOnFlg(boolean buttonOnFlg) {
        this.buttonOnFlg = buttonOnFlg;
    }

}
