package com.example.defense_souls;

import java.util.List;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;

public class Common {
    static final int GAMESTATUS_GAMESTART = 0;
    static final int GAMESTATUS_GAMEMAIN = 1;
    static final int GAMESTATUS_GAMESUCCESS = 2;
    static final int GAMESTATUS_GAMEFAILURE = 3;
    static final int GAMESTATUS_GAMERELEASE = 4;
    static final int GAMESTATUS_GAMEEND = 99;

    static final int CELLSIZE_X = 80;
    static final int CELLSIZE_Y = 80;
    static final int CELLHARFSIZE_X = CELLSIZE_X/2;
    static final int CELLHARFSIZE_Y = CELLSIZE_Y/2;
    static final int ENEMY_LIFEGAUGE_WIDTH = 60;
    static final int ENEMY_LIFEGAUGE_HEIGHT = 5;
    static final int TOWER_TYPE_MAX = 40;
    static final int ENEMY_TYPE_MAX = 100;

    static final boolean AREA_OK = true;
    static final boolean AREA_NO = false;
    static final boolean AREA_ENEMY = true;
    static final int AREA_FREE = 0;
    static final int AREA_MYBASE = 1;
    static final int AREA_ENEMYBASE = 2;
    static final int AREA_SLOW = 5;
    static final int AREA_SHADOW = 6;
    static final int AREA_SPEAR = 11;
    static final int AREA_MINE = 12;
    static final int AREA_NOGROUND = 13;
    static final int AREA_DAMAGE = 14;

    static final String crlf = System.getProperty("line.separator");

    static final int IMAGE_NUMBER001 = 1;
    
    static final int IMAGE_BUTTON000 = 10;
    static final int IMAGE_BUTTON001 = 11;
    static final int IMAGE_BUTTON002 = 12;
    
    static final int IMAGE_WINDOW001 = 21;

    static final int IMAGE_LEVEL001 = 26;
    static final int IMAGE_LEVEL002 = 27;
    static final int IMAGE_LEVEL003 = 28;
    
    static final int IMAGE_ICON001 = 31;
    static final int IMAGE_ICON002 = 32;
    static final int IMAGE_ICON003 = 33;
    static final int IMAGE_ICON004 = 34;
    static final int IMAGE_ICON005 = 35;
    static final int IMAGE_ICON006 = 36;
    static final int IMAGE_ICON011 = 41;
    static final int IMAGE_ICON012 = 42;
    static final int IMAGE_ICON031 = 61;
    static final int IMAGE_ICON032 = 62;
    static final int IMAGE_ICON033 = 63;
    static final int IMAGE_ICON034 = 64;
    static final int IMAGE_ICON035 = 65;
    
    static final int IMAGE_RESULT001 = 71;
    static final int IMAGE_RESULT002 = 72;
    
    static final int IMAGE_LEVEL = 91;
    static final int IMAGE_WAVE = 92;
    
    static final int IMAGE_FACE001 = 101;
    static final int IMAGE_FACE011 = 111;
    static final int IMAGE_FACE021 = 121;
    static final int IMAGE_FACE031 = 131;
    
    static final int IMAGE_OBJECT001 = 251;
    static final int IMAGE_OBJECT002 = 252;
    static final int IMAGE_OBJECT003 = 253;
    static final int IMAGE_OBJECT004 = 254;
    static final int IMAGE_OBJECT005 = 255;
    static final int IMAGE_OBJECT006 = 256;
    static final int IMAGE_OBJECT008 = 258;
    static final int IMAGE_OBJECT009 = 259;
    static final int IMAGE_OBJECT010 = 260;
    static final int IMAGE_OBJECT011 = 261;
    static final int IMAGE_OBJECT012 = 262;
    static final int IMAGE_OBJECT013 = 263;
    static final int IMAGE_OBJECT014 = 264;
    static final int IMAGE_OBJECT016 = 266;
    static final int IMAGE_OBJECT017 = 267;
    static final int IMAGE_OBJECT018 = 268;
    static final int IMAGE_OBJECT021 = 271;
    static final int IMAGE_OBJECT022 = 272;
    static final int IMAGE_OBJECT023 = 273;
    static final int IMAGE_OBJECT024 = 274;
    static final int IMAGE_OBJECT026 = 276;
    static final int IMAGE_OBJECT031 = 281;
    
    static final int IMAGE_TOWER000 = 300;
    static final int IMAGE_TOWER001 = 301;
    static final int IMAGE_TOWER002 = 302;
    static final int IMAGE_TOWER011 = 311;
    static final int IMAGE_TOWER012 = 312;
    static final int IMAGE_TOWER021 = 321;
    static final int IMAGE_TOWER022 = 322;
    static final int IMAGE_TOWER031 = 331;
    static final int IMAGE_TOWER032 = 332;
    static final int IMAGE_TRAP001 = 351;
    static final int IMAGE_TRAP002 = 352;
    
    static final int IMAGE_SHOT000 = 400;
    static final int IMAGE_SHOT001 = 401;
    static final int IMAGE_SHOT002 = 402;
    static final int IMAGE_SHOT003 = 403;
    
    static final int IMAGE_ENEMY001 = 501;
    static final int IMAGE_ENEMY002 = 502;
    static final int IMAGE_ENEMY003 = 503;
    static final int IMAGE_ENEMY004 = 504;
    static final int IMAGE_ENEMY011 = 511;
    static final int IMAGE_ENEMY012 = 512;
    static final int IMAGE_ENEMY013 = 513;
    static final int IMAGE_ENEMY014 = 514;
    static final int IMAGE_ENEMY021 = 521;
    static final int IMAGE_ENEMY022 = 522;
    static final int IMAGE_ENEMY023 = 523;
    static final int IMAGE_ENEMY031 = 531;
    static final int IMAGE_ENEMY032 = 532;
    static final int IMAGE_ENEMY033 = 533;
    static final int IMAGE_ENEMY034 = 534;
    static final int IMAGE_ENEMY041 = 541;
    static final int IMAGE_ENEMY042 = 542;
    static final int IMAGE_ENEMY043 = 543;
    static final int IMAGE_ENEMY051 = 551;
    static final int IMAGE_ENEMY052 = 552;
    static final int IMAGE_ENEMY053 = 553;
    static final int IMAGE_ENEMY054 = 554;
    static final int IMAGE_ENEMY061 = 561;
    static final int IMAGE_ENEMY062 = 562;
    static final int IMAGE_ENEMY063 = 563;
    static final int IMAGE_ENEMY064 = 564;
    static final int IMAGE_ENEMY071 = 571;
    static final int IMAGE_ENEMY072 = 572;
    static final int IMAGE_ENEMY073 = 573;
    static final int IMAGE_ENEMY081 = 581;
    
    static final int IMAGE_EFFECT000 = 600;
    static final int IMAGE_EFFECT001 = 601;
    static final int IMAGE_EFFECT002 = 602;
    static final int IMAGE_EFFECT003 = 603;
    static final int IMAGE_EFFECT011 = 611;
    static final int IMAGE_EFFECT021 = 621;
    static final int IMAGE_EFFECT022 = 622;
    static final int IMAGE_EFFECT031 = 631;
    static final int IMAGE_EFFECT032 = 632;
    static final int IMAGE_EFFECT033 = 633;
    static final int IMAGE_EFFECT041 = 641;
    static final int IMAGE_EFFECT042 = 642;
    
    static final int IMAGE_STAGEEFFECT001 = 701;
    static final int IMAGE_STAGEEFFECT002 = 702;
    static final int IMAGE_STAGEEFFECT003 = 703;
    
    // 効果音
    static final int SOUND_MAX = 100;
    static final int SOUND_SYSTEM001 = 1;
    static final int SOUND_ATTACK001 = 11;
    static final int SOUND_ATTACK002 = 12;
    static final int SOUND_ATTACK003 = 13;
    static final int SOUND_SHOT001 = 15;
    static final int SOUND_FIRE001 = 21;
    static final int SOUND_ICE001 = 25;
    static final int SOUND_LIGHT001 = 31;
    static final int SOUND_EXPLOSION001 = 41;
    static final int SOUND_SUPPORT001 = 45;
    static final int SOUND_SUPPORT002 = 46;
    static final int SOUND_TELEPORT001 = 51;
    static final int SOUND_WATER001 = 61;
    static final int SOUND_WIND001 = 71;
    
    // BGM
    static final int MEDIA_MAX = 20;
    static final int MEDIA_VICTORY001 = 1;
    static final int MEDIA_DEFEAT001 = 2;
    static final int MEDIA_BATTLE = 5;
    static final int MEDIA_BOSS = 6;
    
    // ユニット初期パラメータ
    static final int INIT_TOWER000RANGE = 1;
    static final int INIT_TOWER000POWER = 30;
    static final float INIT_TOWER000SPEED = 9;
    static final int INIT_TOWER000ASSISTPOWER = 15;
    
    static final int INIT_TOWER001RANGE = 1;
    static final int INIT_TOWER001POWER = 15;
    static final float INIT_TOWER001SPEED = 12;
    
    static final int INIT_TOWER002RANGE = 1;
    static final int INIT_TOWER002POWER = 25;
    static final float INIT_TOWER002SPEED = 9;
    static final int INIT_TOWER002ASSISTPOWER = 10;
    static final int INIT_TOWER002ASSISTRANGE = 1;
    
    static final int INIT_TOWER011RANGE = 4;
    static final int INIT_TOWER011POWER = 20;
    static final float INIT_TOWER011SPEED = 7;
    
    static final float INIT_TOWER012ASSISTSPEED = 1.2f;
    static final int INIT_TOWER012ASSISTRANGE = 1;
    
    static final int INIT_TOWER021RANGE = 3;
    static final int INIT_TOWER021POWER = 10;
    static final float INIT_TOWER021SPEED = 9;
    
    static final int INIT_TOWER022RANGE = 1;
    static final int INIT_TOWER022POWER = 25;
    static final float INIT_TOWER022SPEED = 6;
    
    static final int INIT_TOWER031POWER = 20;
    
    static final int INIT_TOWER032POWER = 40;
    
    static final String PREF_INIT = "GameInit";
    static final String PREF_INIT_STARTGUIDE = "StartGuide";
    static final String PREF_INIT_GAMEGUIDE = "GameGuide";
    static final String PREF_INIT_STOREGUIDE = "StoreGuide";
    static final String PREF_INIT_STATUSGUIDE = "StatusGuide";
    static final String PREF_INIT_HISTORYGUIDE = "HistoryGuide";
    
    static final String PREF_GLID = "glid";
    static final String PREF_SOUND = "sound";
    
    static final String PREF_STAGE_NO = "StageNo";
    
    static final String PREF_STAGE01_RELEASE = "Stage01Release";
    static final String PREF_STAGE02_RELEASE = "Stage02Release";
    static final String PREF_STAGE03_RELEASE = "Stage03Release";
    static final String PREF_STAGE04_RELEASE = "Stage04Release";
    static final String PREF_STAGE05_RELEASE = "Stage05Release";
    static final String PREF_STAGE06_RELEASE = "Stage06Release";
    static final String PREF_STAGE07_RELEASE = "Stage07Release";
    static final String PREF_STAGE08_RELEASE = "Stage08Release";
    static final String PREF_STAGE09_RELEASE = "Stage09Release";
    static final String PREF_STAGE10_RELEASE = "Stage10Release";
    static final String PREF_STAGE11_RELEASE = "Stage11Release";
    static final String PREF_STAGE12_RELEASE = "Stage12Release";

    static final String PREF_STAGE01_LEVEL = "Stage01Level";
    static final String PREF_STAGE02_LEVEL = "Stage02Level";
    static final String PREF_STAGE03_LEVEL = "Stage03Level";
    static final String PREF_STAGE04_LEVEL = "Stage04Level";
    static final String PREF_STAGE05_LEVEL = "Stage05Level";
    static final String PREF_STAGE06_LEVEL = "Stage06Level";
    static final String PREF_STAGE07_LEVEL = "Stage07Level";
    static final String PREF_STAGE08_LEVEL = "Stage08Level";
    static final String PREF_STAGE09_LEVEL = "Stage09Level";
    static final String PREF_STAGE10_LEVEL = "Stage10Level";
    static final String PREF_STAGE11_LEVEL = "Stage11Level";
    static final String PREF_STAGE12_LEVEL = "Stage12Level";

    static final String PREF_STAGE01_LEVELMAX = "Stage01LevelMax";
    static final String PREF_STAGE02_LEVELMAX = "Stage02LevelMax";
    static final String PREF_STAGE03_LEVELMAX = "Stage03LevelMax";
    static final String PREF_STAGE04_LEVELMAX = "Stage04LevelMax";
    static final String PREF_STAGE05_LEVELMAX = "Stage05LevelMax";
    static final String PREF_STAGE06_LEVELMAX = "Stage06LevelMax";
    static final String PREF_STAGE07_LEVELMAX = "Stage07LevelMax";
    static final String PREF_STAGE08_LEVELMAX = "Stage08LevelMax";
    static final String PREF_STAGE09_LEVELMAX = "Stage09LevelMax";
    static final String PREF_STAGE10_LEVELMAX = "Stage10LevelMax";
    static final String PREF_STAGE11_LEVELMAX = "Stage11LevelMax";
    static final String PREF_STAGE12_LEVELMAX = "Stage12LevelMax";

    static final String PREF_STAGE01_LEVELMIN = "Stage01LevelMin";
    static final String PREF_STAGE02_LEVELMIN = "Stage02LevelMin";
    static final String PREF_STAGE03_LEVELMIN = "Stage03LevelMin";
    static final String PREF_STAGE04_LEVELMIN = "Stage04LevelMin";
    static final String PREF_STAGE05_LEVELMIN = "Stage05LevelMin";
    static final String PREF_STAGE06_LEVELMIN = "Stage06LevelMin";
    static final String PREF_STAGE07_LEVELMIN = "Stage07LevelMin";
    static final String PREF_STAGE08_LEVELMIN = "Stage08LevelMin";
    static final String PREF_STAGE09_LEVELMIN = "Stage09LevelMin";
    static final String PREF_STAGE10_LEVELMIN = "Stage10LevelMin";
    static final String PREF_STAGE11_LEVELMIN = "Stage11LevelMin";
    static final String PREF_STAGE12_LEVELMIN = "Stage12LevelMin";
    
    static final String PREF_STAGE_SIZEX = "StageSizeX";
    static final String PREF_STAGE_SIZEY = "StageSizeY";
    static final String PREF_ASSET = "GameAsset";
    static final String PREF_ASSETMAX = "GameAssetMax";
    static final String PREF_LIFE_LEVEL = "LifeLevel";
    static final String PREF_LIFE_MAX = "LifeMax";
    static final String PREF_FUNDS_LEVEL = "InitialFundsLevel";
    static final String PREF_INITIALFOUNDS = "InitialFunds";
    static final String PREF_STORE_LEVEL = "ShopLevel";
    static final String PREF_STORE_LEVELMAX = "ShopLevelMax";
    
    static final String PREF_STORE_LIFEUP = "StoreLifeup";
    static final String PREF_STORE_FUNDSUP = "StoreFundsup";
    static final String PREF_STORE_BONUSUP = "StoreBonusup";
    
    static final String PREF_STORE_TOWER000LEVEL = "StoreTower000Level";
    static final String PREF_STORE_TOWER000BONUS = "StoreTower000Bonus";
    static final String PREF_STORE_TOWER001LEVEL = "StoreTower001Level";
    static final String PREF_STORE_TOWER001BONUS = "StoreTower001Bonus";
    static final String PREF_STORE_TOWER002LEVEL = "StoreTower002Level";
    static final String PREF_STORE_TOWER002RELEASE = "StoreTower002Release";
    static final String PREF_STORE_TOWER002BONUS = "StoreTower002Bonus";
    static final String PREF_STORE_TOWER011LEVEL = "StoreTower011Level";
    static final String PREF_STORE_TOWER011BONUS = "StoreTower011Bonus";
    static final String PREF_STORE_TOWER012LEVEL = "StoreTower012Level";
    static final String PREF_STORE_TOWER012RELEASE = "StoreTower012Release";
    static final String PREF_STORE_TOWER012BONUS = "StoreTower012Bonus";
    static final String PREF_STORE_TOWER021LEVEL = "StoreTower021Level";
    static final String PREF_STORE_TOWER021BONUS = "StoreTower021Bonus";
    static final String PREF_STORE_TOWER022LEVEL = "StoreTower022Level";
    static final String PREF_STORE_TOWER022RELEASE = "StoreTower022Release";
    static final String PREF_STORE_TOWER022BONUS = "StoreTower022Bonus";
    static final String PREF_STORE_TOWER031LEVEL = "StoreTower031Level";
    static final String PREF_STORE_TOWER031BONUS = "StoreTower031Bonus";
    static final String PREF_STORE_TOWER032LEVEL = "StoreTower032Level";
    static final String PREF_STORE_TOWER032RELEASE = "StoreTower032Release";
    static final String PREF_STORE_TOWER032BONUS = "StoreTower032Bonus";
    static final String PREF_STORE_SKILL000LEVEL = "StoreSkill000Level";
    static final String PREF_STORE_SKILL000RELEASE = "StoreSkill000Release";
    static final String PREF_STORE_SKILL000COUNT = "StoreSkill000Count";
    static final String PREF_STORE_SKILL001LEVEL = "StoreSkill001Level";
    static final String PREF_STORE_SKILL001RELEASE = "StoreSkill001Release";
    static final String PREF_STORE_SKILL001COUNT = "StoreSkill001Count";
    static final String PREF_STORE_SKILL002LEVEL = "StoreSkill002Level";
    static final String PREF_STORE_SKILL002RELEASE = "StoreSkill002Release";
    static final String PREF_STORE_SKILL002COUNT = "StoreSkill002Count";
   
    static final String PREF_ENEMY001_COUNT = "Enemy001_Count";
    static final String PREF_ENEMY002_COUNT = "Enemy002_Count";
    static final String PREF_ENEMY003_COUNT = "Enemy003_Count";
    static final String PREF_ENEMY004_COUNT = "Enemy004_Count";
    static final String PREF_ENEMY011_COUNT = "Enemy011_Count";
    static final String PREF_ENEMY012_COUNT = "Enemy012_Count";
    static final String PREF_ENEMY013_COUNT = "Enemy013_Count";
    static final String PREF_ENEMY014_COUNT = "Enemy014_Count";
    static final String PREF_ENEMY021_COUNT = "Enemy021_Count";
    static final String PREF_ENEMY022_COUNT = "Enemy022_Count";
    static final String PREF_ENEMY023_COUNT = "Enemy023_Count";
    static final String PREF_ENEMY031_COUNT = "Enemy031_Count";
    static final String PREF_ENEMY032_COUNT = "Enemy032_Count";
    static final String PREF_ENEMY033_COUNT = "Enemy033_Count";
    static final String PREF_ENEMY034_COUNT = "Enemy034_Count";
    static final String PREF_ENEMY041_COUNT = "Enemy041_Count";
    static final String PREF_ENEMY042_COUNT = "Enemy042_Count";
    static final String PREF_ENEMY043_COUNT = "Enemy043_Count";
    static final String PREF_ENEMY051_COUNT = "Enemy051_Count";
    static final String PREF_ENEMY052_COUNT = "Enemy052_Count";
    static final String PREF_ENEMY053_COUNT = "Enemy053_Count";
    static final String PREF_ENEMY054_COUNT = "Enemy054_Count";
    static final String PREF_ENEMY061_COUNT = "Enemy061_Count";
    static final String PREF_ENEMY062_COUNT = "Enemy062_Count";
    static final String PREF_ENEMY063_COUNT = "Enemy063_Count";
    static final String PREF_ENEMY064_COUNT = "Enemy064_Count";
    static final String PREF_ENEMY071_COUNT = "Enemy071_Count";
    static final String PREF_ENEMY072_COUNT = "Enemy072_Count";
    static final String PREF_ENEMY073_COUNT = "Enemy073_Count";
    static final String PREF_ENEMY081_COUNT = "Enemy081_Count";
    static final String PREF_ENEMY091_COUNT = "Enemy091_Count";
    static final String PREF_ENEMY101_COUNT = "Enemy101_Count";
    static final String PREF_ENEMY102_COUNT = "Enemy102_Count";
    
    static final int ENEMYBASE_TYPE001 = 1;
    static final int ENEMYBASE_TYPE011 = 11;
    static final int ENEMYBASE_TYPE012 = 12;
    static final int ENEMYBASE_TYPE013 = 13;
    static final int ENEMYBASE_TYPE021 = 21;
    static final int ENEMYBASE_TYPE022 = 22;
    static final int ENEMYBASE_TYPE023 = 23;
    static final int ENEMYBASE_TYPE024 = 24;
    static final int ENEMYBASE_TYPE031 = 31;
    static final int ENEMYBASE_TYPE032 = 32;
    static final int ENEMYBASE_TYPE041 = 41;
    static final int ENEMYBASE_TYPE042 = 42;
    static final int ENEMYBASE_TYPE043 = 43;
    static final int ENEMYBASE_TYPE044 = 44;
    static final int ENEMYBASE_TYPE051 = 51;
    static final int ENEMYBASE_TYPE052 = 52;
    static final int ENEMYBASE_TYPE053 = 53;
    static final int ENEMYBASE_TYPE054 = 54;
    static final int ENEMYBASE_TYPE061 = 61;
    static final int ENEMYBASE_TYPE062 = 62;
    static final int ENEMYBASE_TYPE063 = 63;
    static final int ENEMYBASE_TYPE071 = 71;
    static final int ENEMYBASE_TYPE072 = 72;
    static final int ENEMYBASE_TYPE073 = 73;
    static final int ENEMYBASE_TYPE081 = 81;
    static final int ENEMYBASE_TYPE082 = 82;
    static final int ENEMYBASE_TYPE091 = 91;
    static final int ENEMYBASE_TYPE092 = 92;
    static final int ENEMYBASE_TYPE101 = 101;
    static final int ENEMYBASE_TYPE102 = 102;
    
    static final int FLICK_NEUTRAL = 0;
    static final int FLICK_LEFT = 1;
    static final int FLICK_TOP = 2;
    static final int FLICK_RIGHT = 3;
    static final int FLICK_BOTTOM = 4;
    static final LightingColorFilter voidColor = new LightingColorFilter(Color.argb(0x00, 0xFF, 0xFF, 0xFF), 0x000000);
    static final LightingColorFilter ironColor = new LightingColorFilter(Color.rgb(0x4A, 0x70, 0x8B), 0x888888);
    static final LightingColorFilter whiteColor = new LightingColorFilter(Color.rgb(0xFF, 0xFF, 0xFF), 0x444444);
    static final LightingColorFilter cyanColor = new LightingColorFilter(Color.rgb(0x00, 0x88, 0xCC), 0x222222);
    static final LightingColorFilter redColor = new LightingColorFilter(Color.rgb(0xCC, 0x33, 0x33), 0x222222);
    static final LightingColorFilter goldColor = new LightingColorFilter(Color.rgb(0xFF, 0xD7, 0x00), 0x222222);
    static final LightingColorFilter blackColor = new LightingColorFilter(Color.rgb(0x00, 0x00, 0x00), 0x000000);
    
    static double getRadians(double x, double y) {
        double s;
        
        s = Math.acos(x / Math.sqrt(x * x + y * y));
        s = (s / Math.PI) * 180.0;
        // マイナスの座標のとき
        if (y < 0) {
            s = 360 -s;
        }
        return s;
    }
    
    static boolean rangeCheck(int targetx, int targety, int positionx, int positiony, int range, boolean corner) {
        int rangex;
        int rangey;
        rangex = positionx - targetx;
        // マイナスのＸ範囲のとき
        if (rangex < 0) {
            rangex *= -1;
        }
        rangey = positiony - targety;
        // マイナスのＹ範囲のとき
        if (rangey < 0) {
            rangey *= -1;
        }
        // ＸとＹのレンジを足して、範囲内であるとき
        if ((rangex + rangey) <= range) {
            return true;
        }
        // 射程の角を判定に加える
        if (corner == true) {
            if (rangex == rangey && rangex == range && rangey == range) {
                return true;
            }
        }
        
        return false;
    }
    
    static boolean hitCheck(float x, float y, float w, float h, float left, float top, float right, float bottom) {
        // 四角の判定範囲にヒットしたとき
        if ( x + w > left && x < left + right && y + h > top && y < top + bottom) {
            return true;
        }
        
        return false;
    }

    static int setDamage(int enemyLife, float enemyDefense, int enemyType, int shotPower, int shotType) {
        int damage;
        float power = (float)shotPower;
        
        switch (enemyType) {
        case 0: // 型なし
            break;
        case 1: // 動物型
            if (shotType == 3) {
                power = power * 1.2f;
            }
            break;
        case 2: // 飛行型
            break;
        case 3: // 水棲型
            break;
        case 4: // 不死型
            if (shotType == 4) {
                power = power * 1.2f;
            }
        case 5: // 植物型
            if (shotType == 3) {
                power = power * 1.2f;
            }
            break;
        case 6: // 悪魔型
            if (shotType == 4) {
                power = power * 1.2f;
            }
            break;
        }
        damage = (int)(power - enemyDefense);
        
        // ダメージがマイナスのとき
        if (damage < 0) {
            damage = 1;
        }
        enemyLife -= damage;
        
        return enemyLife;
    }
    
    // 軸要素の検索
    static int pivot(List<DrawObject> mob, int start, int end){
        // startからendまで順に要素を確認し、異なるindexを検索
        int index = start + 1;
        while(index <= end && mob.get(start).getZ() == mob.get(index).getZ()) index++;

        // 全て同等の要素だった場合は-1を返す
        if(index > end) return -1;

        // startの要素が一番大きい場合はstartを返す
        if(mob.get(start).getZ() >= mob.get(index).getZ()) return start;

        return index;
    }

    // 要素を左右に分割し、check値を軸に要素の入れ替えを実行
    static int partition(List<DrawObject> mob,int start,int end, int check){
        int left = start;
        int right = end;

        // 検索が交差するまで繰り返し
        while(left <= right){

            // 軸要素以上のデータを検索
            while(left <= end && mob.get(left).getZ() < check)  left++;

            // 軸要素未満のデータを検索
            while(right >= start && mob.get(right).getZ() >= check) right--;

            // 分割した左右を比較し、大きい要素を返す
            if(left > right) break;

            // 要素の入れ替え
            DrawObject m;
            m = mob.get(left);
            mob.set(left, mob.get(right));
            mob.set(right, m);
            left++;
            right--;
        }
        return left;
    }

    // クイックソート（再帰用）
    static void quickSort(List<DrawObject> mob,int start,int end){
        if(start == end) return;
        int check = pivot(mob, start, end);
        if(check != -1){
            int index = partition(mob, start, end, mob.get(check).getZ());
            quickSort(mob, start, index-1);
            quickSort(mob, index, end);
        }
    }
    
    static int setScoreup(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static int setEnemyAppear(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static int setLifeup(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static int setDefenceup(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static int setPowerup(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static float setSpeedup(float current, int up) {
        return (float)(300 - ((current + ((current * up) / 100)) * current));
    }

    static float setSpeedupPoint(float current, int up) {
        return (float)(current + ((current * up) / 100));
    }

    static int setAssistPowerup(int current, int up) {
        if(up == 0) return current;
        return (int)(current + ((current * up) / 100));
    }
    
    static float setAssistSpeedup(float current, int up) {
        if(up == 0) return current;
        return (float)(current + ((current * up) / 100));
    }
}
