package com.example.defense_souls;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

@SuppressLint("NewApi")
class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    private Canvas canvas;
    private SurfaceHolder holder = null;
    
    private boolean mainLoop;
	private Thread mainThread;
	private boolean actionLoop;
	private boolean gameRelease;
	
    private Timer myTimer;
    private int stageNo;
    private int stageLevel;
    
    private int gameStatus;
    private int gameCount;
    private int waveCount;
    private int waveCountMax;
    
    private List<EnemyObject> Enemylist;
    private List<TowerObject> Towerlist;
    private List<EffectObject> Effectlist;
    private List<ShotObject> Shotlist;
    private SignObject Sign;
    
    private GameOnTouch GameOnTouch;
    private SubMenuAction SubMenuAction;
    private ButtonAction ButtonAction;
    private PlayAction PlayAction;
    private TowerAction TowerAction;
    private EnemyAction EnemyAction;
    private ShotAction ShotAction;
    private EffectAction EffectAction;
    private StageEffectAction StageEffectAction;
    private SignAction SignAction;
    private DrawSet DrawSet;
    private StageDataAction StageDataAction;
    
    private Player Player;
    private Background Background;
    private Image Image;
    private Sound Sound;
    private MediaManager MediaManager;
    private StageDataObject StageData;
    private EnemyDataObject EnemyData;
    private int[] EnemyCount;
    
    private Button endButton;
    private Context context;
    private SharedPreferences pref;
    private FragmentManager fragmentManager;
    private Bitmap resultBmp;
    
    @SuppressLint("NewApi")
    public DrawSurfaceView(Context context, SurfaceView sv, Button button, FragmentManager fragmentManager) {
		super(context);
		this.context = context;
		this.fragmentManager = fragmentManager;

        endButton = button;
        endButton.setVisibility(INVISIBLE);

        GameOnTouch = new GameOnTouch();
        sv.setOnTouchListener(GameOnTouch);
        
        holder = sv.getHolder();
        holder.addCallback(this);
        
        Image = new Image(this.getContext().getResources());        // 画像の読み込み
        
        this.initializeSet();   // 各初期設定
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v("surfaceChanged", "OK");
	}
	 
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
        Log.v("surfaceCreated", "OK");
        actionLoop = false;

        mainLoop = true;
        mainThread = new Thread(this);
        mainThread.start();
        
        myTimer = new Timer();
        myTimer.schedule(new MyTimer(), 1000, 1000);
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v("surfaceDestroyed", "OK");
        finishLoop();
	}
	
    @Override
	public void run() {
		while (mainLoop) {
		    if (actionLoop == true) {
	            gameAction();
	            drawCanvas();   // ゲームの描画
	            
		    }
		}
	}
	
    private void gameAction() {
        switch (gameStatus) {
            // 開始前
        case Common.GAMESTATUS_GAMESTART:
            TowerAction.towerAction(getContext(), Player, Image, Towerlist, Enemylist, Shotlist, Effectlist, GameOnTouch, StageData, gameStatus);
            SignAction.signAction(getContext(), fragmentManager, Sign, GameOnTouch, StageData, Player, MediaManager, this);
            StageDataAction.stageDataAction(StageData, Player, Effectlist, GameOnTouch);
            break;
            
            // ゲーム中
        case Common.GAMESTATUS_GAMEMAIN:
            MediaManager.get().update(context);
            if (Player.getGameSpeed() == 0) {
                PlayAction.playAction(getContext(), Player, GameOnTouch, pref);
                SubMenuAction.submenuAction(context, StageData, Player, GameOnTouch, pref, endButton, Sound, MediaManager, this);
                break;
            }
            SubMenuAction.submenuAction(context, StageData, Player, GameOnTouch, pref, endButton, Sound, MediaManager, this);
            ButtonAction.buttonAction(getContext(), Player, Towerlist, GameOnTouch, StageData);
            PlayAction.playAction(getContext(), Player, GameOnTouch, pref);
            TowerAction.towerAction(getContext(), Player, Image, Towerlist, Enemylist, Shotlist, Effectlist, GameOnTouch, StageData, gameStatus);
            EnemyAction.enemyAction(Player, Image, Towerlist, Enemylist, Effectlist, GameOnTouch, StageData, EnemyData, Sound, gameCount);
            ShotAction.shotAction(Player, Image, StageData, Shotlist, Enemylist, Effectlist, GameOnTouch, Sound);
            EffectAction.effectAction(Player, Image, StageData, Enemylist, Effectlist, Sound);
            StageEffectAction.stageEffectAction(Player, Image, StageData, Sound);
            StageDataAction.stageDataAction(StageData, Player, Effectlist, GameOnTouch);
            StageData.updateMoveData(StageData.getMoveData(), StageData.getPathData());
            
            // ゲームオーバー処理
            if (Player.getLife() <= 0) {
                FailureResultSet();
                break;
                
            } else if (Enemylist.size() == 0) {
                if (waveCount >= waveCountMax) {
                    SuccessResultSet();
                    break;
                }
            }
            break;
            
            // リザルト開始
        case Common.GAMESTATUS_GAMESUCCESS:
        case Common.GAMESTATUS_GAMEFAILURE:
            MediaManager.get().update(context);
            if (GameOnTouch.isEndFlg() == true) {
                if (gameRelease == false) {
                    gameStatus = Common.GAMESTATUS_GAMEEND;
                } else {
                    GameOnTouch.setEndFlg(false);
                    gameStatus = Common.GAMESTATUS_GAMERELEASE;
                }
            } else {
                if (Player.getTotalCount() >= Player.getTotal()) {
                    Player.setTotalCount(Player.getTotal());
                    GameOnTouch.setEndStatus(1);
                } else {
                    if (GameOnTouch.getEndStatus() == 0) {
                        if (Player.getTotal() < 1000) {
                            Player.setTotalCount(Player.getTotalCount() + 1);
                        } else if (Player.getTotal() < 10000) {
                            Player.setTotalCount(Player.getTotalCount() + 10);
                        } else if (Player.getTotal() < 100000) {
                            Player.setTotalCount(Player.getTotalCount() + 100);
                        } else if (Player.getTotal() < 1000000) {
                            Player.setTotalCount(Player.getTotalCount() + 1000);
                        }
                    } else if (GameOnTouch.getEndStatus() == 1) {
                        Player.setTotalCount(Player.getTotal());
                    }
                }
            }
            break;
            
            // 解放開始
        case Common.GAMESTATUS_GAMERELEASE:
            if (GameOnTouch.isEndFlg() == true) {
                gameStatus = Common.GAMESTATUS_GAMEEND;
            }
            break;
            
        case Common.GAMESTATUS_GAMEEND:
            endButton.callOnClick();
            memoryRelease();
            break;
        }
    }
    
	private void drawCanvas() {
        canvas = holder.lockCanvas();
		if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
	        switch (gameStatus) {
                // 開始前
            case Common.GAMESTATUS_GAMESTART:
                DrawSet.setNowOffX((int)GameOnTouch.getCanvasX());
                DrawSet.setNowOffY((int)GameOnTouch.getCanvasY());
                DrawSet.backgroundDrawSet(canvas, Background, StageData, GameOnTouch, Image);
                DrawSet.glidSet(canvas, StageData, pref.getBoolean(Common.PREF_GLID, true));
                
                DrawSet.gameDrawSet(canvas, StageData, Towerlist, Enemylist, Shotlist, Effectlist, GameOnTouch, Image);
                DrawSet.stageEffectDrawSet(canvas, StageData, GameOnTouch, Image);
                
                DrawSet.buttonDrawSet(canvas, StageData, GameOnTouch, Image);
                DrawSet.playDrawSet(canvas, GameOnTouch, Image);
                DrawSet.iconStatusDrawSet(canvas, Player, Image, StageData, waveCount);
                DrawSet.startSignDrawSet(canvas, Sign, GameOnTouch, Image);
                break;
                // ゲーム中
            case Common.GAMESTATUS_GAMEMAIN:
                DrawSet.setNowOffX((int)GameOnTouch.getCanvasX());
                DrawSet.setNowOffY((int)GameOnTouch.getCanvasY());
                DrawSet.backgroundDrawSet(canvas, Background, StageData, GameOnTouch, Image);
                
                DrawSet.controlDrawSet(canvas, StageData, GameOnTouch);
                
                DrawSet.glidSet(canvas, StageData, pref.getBoolean(Common.PREF_GLID, true));
                DrawSet.locationDrawSet(canvas, Image);
                
                DrawSet.gameDrawSet(canvas, StageData, Towerlist, Enemylist, Shotlist, Effectlist, GameOnTouch, Image);
                
//                DrawSet.effectDrawSet(canvas, StageData, Effectlist, GameOnTouch, Image);
                DrawSet.stageEffectDrawSet(canvas, StageData, GameOnTouch, Image);
                
                DrawSet.towerStatusDrawSet(canvas, Towerlist, Image);
                DrawSet.enemyStatusDrawSet(canvas, Enemylist);
                
                DrawSet.buttonDrawSet(canvas, StageData, GameOnTouch, Image);
                DrawSet.playDrawSet(canvas, GameOnTouch, Image);
                DrawSet.iconStatusDrawSet(canvas, Player, Image, StageData, waveCount);
                DrawSet.submenuDrawSet(canvas, GameOnTouch, Image);
                
                break;
                
                // リザルト開始
            case Common.GAMESTATUS_GAMESUCCESS:
            case Common.GAMESTATUS_GAMEFAILURE:
            case Common.GAMESTATUS_GAMERELEASE:
                DrawSet.setNowOffX((int)GameOnTouch.getCanvasX());
                DrawSet.setNowOffY((int)GameOnTouch.getCanvasY());
                DrawSet.backgroundDrawSet(canvas, Background, StageData, GameOnTouch, Image);
                
                DrawSet.gameDrawSet(canvas, StageData, Towerlist, Enemylist, Shotlist, Effectlist, GameOnTouch, Image);
                DrawSet.stageEffectDrawSet(canvas, StageData, GameOnTouch, Image);
                
                DrawSet.buttonDrawSet(canvas, StageData, GameOnTouch, Image);
                DrawSet.resultDrawSet(canvas, Player, GameOnTouch, resultBmp, Image, StageData, gameStatus);
                break;
            }
			holder.unlockCanvasAndPost(canvas);
		}
		
	}
	
	private void FailureResultSet() {
        myTimer = null;
        gameStatus = Common.GAMESTATUS_GAMEFAILURE;
        resultBmp = Image.getImagelist().get(Common.IMAGE_RESULT002);
        MediaManager.get().play(R.raw.defeat001, context);
        GameOnTouch.setResultFlg(true);
        Player.setLife(0);
        Player.setGold(0);
        
        Player.setTotal(Player.getScore()); // 防衛失敗時　スコアだけ入手
        
        // 資産に追加
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = pref.edit();
        if ((long)(pref.getLong(Common.PREF_ASSET, 0) + Player.getTotal()) > pref.getLong(Common.PREF_ASSETMAX, 9999999)) { // MAXを超えていた場合
            prefEditor.putLong(Common.PREF_ASSET, pref.getLong(Common.PREF_ASSETMAX, 9999999));
        } else {
            prefEditor.putLong(Common.PREF_ASSET, (long)(pref.getLong(Common.PREF_ASSET, 0) + Player.getTotal()));
        }
        
        // エネミー撃破数の追加
        DecimalFormat decimalFormat = new DecimalFormat("000");
        for (int i = 0 ; i < Common.ENEMY_TYPE_MAX ; i++) {
            if (Player.getEnemyKillCount(i) > 0) {
                prefEditor.putLong("Enemy"+decimalFormat.format(i)+"_Count", (long)(pref.getLong("Enemy"+decimalFormat.format(i)+"_Count", 0) + Player.getEnemyKillCount(i)));
            }
        }
        
        prefEditor.commit();
	}
	
	private void SuccessResultSet() {
        myTimer = null;

        gameStatus = Common.GAMESTATUS_GAMESUCCESS;
        resultBmp = Image.getImagelist().get(Common.IMAGE_RESULT001);
        MediaManager.get().play(R.raw.victory001, context);
	    GameOnTouch.setResultFlg(true);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = pref.edit();
        // 挑戦ステージのレベルがMAXであれば、制限値をアップさせる
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String stageNo = String.valueOf(decimalFormat.format(pref.getInt(Common.PREF_STAGE_NO, 0)));
        if (pref.getInt("Stage" + stageNo + "LevelMax", 5) == pref.getInt("Stage" + stageNo + "Level", 1)) {
            
            // ステージレベルの上限アップ
            prefEditor.putInt("Stage" + stageNo + "LevelMax", pref.getInt("Stage" + stageNo + "Level", 1) + 5);
            Player.setStageLevelup(true);
            
            // 戦力強化のレベルアップ
            if (pref.getInt(Common.PREF_STORE_LEVEL, 1) < pref.getInt(Common.PREF_STORE_LEVELMAX, 30)) {
                prefEditor.putInt(Common.PREF_STORE_LEVEL, pref.getInt(Common.PREF_STORE_LEVEL, 1)+1);
                Player.setStoreRelease(true);
                gameRelease = true;
            }
            
            if (Player.getStageNo() == 1) { // 大平原クリア時
                if (pref.getBoolean(Common.PREF_STORE_TOWER002RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_TOWER002RELEASE, true); // 勇士の項目解放
                    Player.setShopTowerRelease(1);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE02_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE02_RELEASE, true);   // 三つの道標解放
                    Player.setStageRelease(1);
                }
                
            } else if (Player.getStageNo() == 2) { // 三つの道標クリア時
                if (pref.getBoolean(Common.PREF_STAGE03_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE03_RELEASE, true);   // 最前線解放
                    Player.setStageRelease(2);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE04_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE04_RELEASE, true);   // 略奪されし鉱山解放
                }
                if (pref.getBoolean(Common.PREF_STAGE06_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE06_RELEASE, true);   // 魔の森解放
                }
                
            } else if (Player.getStageNo() == 3) { // 最前線クリア時
                if (pref.getBoolean(Common.PREF_STORE_TOWER022RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_TOWER022RELEASE, true); // 聖女の項目解放
                    Player.setShopTowerRelease(3);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE08_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE08_RELEASE, true);   // 死者の都解放
                    Player.setStageRelease(3);
                }
                
            } else if (Player.getStageNo() == 4) { // 略奪されし鉱山クリア時
                if (pref.getBoolean(Common.PREF_STORE_SKILL000RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_SKILL000RELEASE, true); // スキル１の項目解放
                    Player.setShopTowerRelease(11);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE05_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE05_RELEASE, true);   // 結晶洞窟解放
                    Player.setStageRelease(4);
                    gameRelease = true;
                }
                
            } else if (Player.getStageNo() == 5) { // 結晶洞窟クリア時
                if (pref.getBoolean(Common.PREF_STORE_TOWER032RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_TOWER032RELEASE, true); // 地雷の項目解放
                    Player.setShopTowerRelease(4);
                    gameRelease = true;
                }
                
            } else if (Player.getStageNo() == 6) { // 魔の森クリア時
                if (pref.getBoolean(Common.PREF_STORE_SKILL001RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_SKILL001RELEASE, true); // スキル2の項目解放
                    Player.setShopTowerRelease(12);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE07_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE07_RELEASE, true);   // 守護者解放
                    Player.setStageRelease(5);
                    gameRelease = true;
                }

            } else if (Player.getStageNo() == 7) { // 守護者クリア時
                if (pref.getBoolean(Common.PREF_STORE_TOWER012RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_TOWER012RELEASE, true); // 時の術師の項目解放
                    Player.setShopTowerRelease(2);
                    gameRelease = true;
                }
                
            } else if (Player.getStageNo() == 8) { // 死者の都クリア時
                if (pref.getBoolean(Common.PREF_STORE_SKILL002RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STORE_SKILL002RELEASE, true); // スキル3の項目解放
                    Player.setShopTowerRelease(13);
                    gameRelease = true;
                }
                if (pref.getBoolean(Common.PREF_STAGE09_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE09_RELEASE, true);   // 亡国の王解放
                    Player.setStageRelease(6);
                    gameRelease = true;
                }
                
            } else if (Player.getStageNo() == 9) { // 亡国の王クリア時
                
            } else if (Player.getStageNo() == 10) { // 魔境クリア時
                if (pref.getBoolean(Common.PREF_STAGE11_RELEASE, false) == false) {
                    prefEditor.putBoolean(Common.PREF_STAGE11_RELEASE, true);   // 竜の巣解放
                    Player.setStageRelease(8);
                    gameRelease = true;
                }
                
            }
            if (pref.getBoolean(Common.PREF_STAGE05_RELEASE, false) == true && 
                pref.getBoolean(Common.PREF_STAGE07_RELEASE, false) == true && 
                pref.getBoolean(Common.PREF_STAGE09_RELEASE, false) == true) {
                if (pref.getBoolean(Common.PREF_STAGE10_RELEASE, false) == false) {
                    // 結晶洞窟・守護者・亡国の王クリア時
                    prefEditor.putBoolean(Common.PREF_STAGE10_RELEASE, true);   // 魔境解放
                    Player.setStageRelease(7);
                    gameRelease = true;
                }
            }
        }
        
        // ステージボーナス
        int lifebonus = Player.getLife() * 10;
        int goldbonus = Player.getGold();
        Player.setStageBonus(Common.setScoreup(lifebonus + goldbonus, StageData.getDifficulty() + waveCount));
        
        // ノーダメージの場合、パーフェクトボーナス取得
        if (pref.getInt(Common.PREF_LIFE_MAX, 5) == Player.getLife()) {
            Player.setNodamageBonus(StageData.getDifficulty() * Player.getStageLevel());
        }
        
        // 資産に追加
        Player.setTotal(Player.getScore() + Player.getStageBonus() + Player.getNodamageBonus());
        if ((long)(pref.getLong(Common.PREF_ASSET, 0) + Player.getTotal()) > pref.getLong(Common.PREF_ASSETMAX, 9999999)) { // MAXを超えていた場合
            prefEditor.putLong(Common.PREF_ASSET, pref.getLong(Common.PREF_ASSETMAX, 9999999));
        } else {
            prefEditor.putLong(Common.PREF_ASSET, (long)(pref.getLong(Common.PREF_ASSET, 0) + Player.getTotal()));
        }
        
        // エネミー撃破数の追加
        decimalFormat = new DecimalFormat("000");
        for (int i = 0 ; i < Common.ENEMY_TYPE_MAX ; i++) {
            if (Player.getEnemyKillCount(i) > 0) {
                prefEditor.putLong("Enemy"+decimalFormat.format(i)+"_Count", (long)(pref.getLong("Enemy"+decimalFormat.format(i)+"_Count", 0) + Player.getEnemyKillCount(i)));
            }
        }
        prefEditor.commit();
        
        String[] location = getResources().getStringArray(R.array.list_stagelocation);
        
        // データベースに新しいリザルトを追加する
        MyDBHelper mHelper = new MyDBHelper(context);
        ContentValues cv = new ContentValues(5);
        cv.put(MyDBHelper.COL_STAGENAME, location[Player.getStageNo()-1]);
        cv.put(MyDBHelper.COL_STAGENO, StageData.getIconImageNo());
        cv.put(MyDBHelper.COL_STAGELEVEL, Player.getStageLevel());

        // SQLの日付フォーマット用のフォーマットを作成
        String text = (String) DateFormat.format("yyyy/MM/dd, E, kk:mm:ss", new Date());
        cv.put(MyDBHelper.COL_DATE, text);

        cv.put(MyDBHelper.COL_SCORE, Player.getTotal());

        // データベースの接続をオープンする
        SQLiteDatabase mDb;
        mDb = mHelper.getWritableDatabase();
        mDb.insert(MyDBHelper.TABLE_NAME, null, cv);
    }
	
	private class MyTimer extends TimerTask {
	    public void run() {
	        // ゲーム中のとき
            if (gameStatus == Common.GAMESTATUS_GAMEMAIN) {
                
                if (Enemylist.size() == 0) {
                    if (waveCount < waveCountMax) {
                        EnemyData = new EnemyDataObject(waveCount, Enemylist, EnemyCount, StageData);
                        waveCount++;
                        gameCount = 0;
                        if (waveCount >= waveCountMax) {
                            if (StageData.getBgmBoss() != 0) {
                                MediaManager.get().play(StageData.getBgmBoss(), context);
                            }
                        }
                    }
                }
                gameCount = gameCount + (1 * Player.getGameSpeed());
            }
	        actionLoop =true;

	    }
	}

	public void onGameStopDown() {
        // ゲーム中のとき
        if (gameStatus == Common.GAMESTATUS_GAMEMAIN && GameOnTouch.isSubmenuHideFlg() == false) {
            GameOnTouch.subMenuClear();
            Player.setGameSpeed(0);
            GameOnTouch.getPlaylist().get(0).setHide(false);
            GameOnTouch.getPlaylist().get(1).setHide(true);
            GameOnTouch.getPlaylist().get(2).setHide(true);
            PlayAction.playSubMenuSet(context, GameOnTouch, pref);
            
        } else if (gameStatus == Common.GAMESTATUS_GAMEMAIN && GameOnTouch.isSubmenuHideFlg() == true) {
            GameOnTouch.subMenuClear();
            GameOnTouch.setSubmenuHideFlg(false);
            Player.setGameSpeed(1);
            GameOnTouch.getPlaylist().get(0).setHide(true);
            GameOnTouch.getPlaylist().get(1).setHide(false);
            GameOnTouch.getPlaylist().get(2).setHide(true);
        }
	}
 
    public void finishLoop() {
        synchronized (mainThread) {
            // ループを抜ける
            mainLoop = false;
            MediaManager.get().stop();
            if (myTimer != null) {
                myTimer.cancel();
            }
        } try {
            // スレッドの終了を待つ
            mainThread.join();
        } catch( InterruptedException ex ){
            Thread.currentThread().interrupt();
        }
    }
    
    public void initializeSet() {
        myTimer = new Timer();
        Enemylist = new ArrayList<EnemyObject>();
        Towerlist = new ArrayList<TowerObject>();
        Effectlist = new ArrayList<EffectObject>();
        Shotlist = new ArrayList<ShotObject>();
        
        SubMenuAction = new SubMenuAction();
        ButtonAction = new ButtonAction();
        PlayAction = new PlayAction();
        TowerAction = new TowerAction();
        EnemyAction = new EnemyAction();
        ShotAction = new ShotAction();
        EffectAction = new EffectAction();
        StageEffectAction = new StageEffectAction();
        SignAction = new SignAction();
        StageDataAction = new StageDataAction();
        
        Sound = new Sound(context);
        
        GameOnTouch.initSet();

        pref = PreferenceManager.getDefaultSharedPreferences(context);
        Player = new Player(pref);
        
        stageNo = Player.getStageNo();
        stageLevel = Player.getStageLevel();
        
        gameCount = 0;
        waveCount = 0;
        waveCountMax = 9 + stageLevel;
        gameStatus = 0;
        gameRelease = false;
        DrawSet = new DrawSet(context);
        
        Background = new Background(context.getResources(), stageNo, Player.getStageSizeX() / 2, Player.getStageSizeY() / 2);
        Background.imageSizeSet(Background.getImage());
        
        StageData = new StageDataObject(Background, stageNo, stageLevel);
        StageData.updateMoveData(StageData.getMoveData(), StageData.getPathData());

        GameOnTouch.setBaseSize(1.0f);
        GameOnTouch.setDisplayX(Player.getStageSizeX());
        GameOnTouch.setDisplayY(Player.getStageSizeY());
        GameOnTouch.setCanvasXAdjustMIN(StageData.getCanvasXAdjustMIN());
        GameOnTouch.setCanvasXAdjustMAX(StageData.getCanvasXAdjustMAX());
        GameOnTouch.setCanvasYAdjustMIN(StageData.getCanvasYAdjustMIN());
        GameOnTouch.setCanvasYAdjustMAX(StageData.getCanvasYAdjustMAX());
        GameOnTouch.setCanvasX(StageData.getCanvasXAdjustMAX());
        GameOnTouch.setCanvasY(StageData.getCanvasYAdjustMAX());
        GameOnTouch.setKeepCanvasX(GameOnTouch.getCanvasX());
        GameOnTouch.setKeepCanvasY(GameOnTouch.getCanvasY());
        
        Towerlist.add(new TowerObject(context, Player, 0, 1, 1, StageData.getMybaseX(), StageData.getMybaseY()));
        
        // ボタン設定
        for (int i = 0; i < 4; i++) {
            GameOnTouch.setButtonlist(new ButtonObject(i, GameOnTouch.getDisplayX() - (600 - (i * 150)), GameOnTouch.getDisplayY() - 150));
        }
        for (int i = 0; i < 3; i++) {
            GameOnTouch.setPlaylist(new PlayObject(i, 80 + (i * 120), GameOnTouch.getDisplayY() - 150));
        }
        
        Sign = new SignObject(context.getResources(), GameOnTouch.getDisplayX() / 2, GameOnTouch.getDisplayY() / 2);
        
        // Waveごとの敵の出現量を決定
        EnemyCount = new int[waveCountMax];
        for (int i = 0 ; i < waveCountMax ; i++) {
            EnemyCount[i] = (int)(Common.setEnemyAppear(3 + i, StageData.getDifficulty()));
        }
    }
    
    private void memoryRelease() {
        GameOnTouch = null;
        Image = null;
        Sound = null;
        
        Enemylist = null;
        Towerlist = null;
        Effectlist = null;
        Shotlist = null;
        
        SubMenuAction = null;
        ButtonAction =null;
        PlayAction = null;
        TowerAction = null;
        EnemyAction = null;
        ShotAction = null;
        EffectAction = null;
        SignAction = null;
        
        Player = null;
        DrawSet = null;
        StageData = null;
        Background = null;
        resultBmp = null;
        Sign = null;
        actionLoop = false;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

}
