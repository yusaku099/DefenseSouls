package com.example.defense_souls;

import java.text.DecimalFormat;

import android.content.SharedPreferences;

public class Player {
    
    private long asset;
    private int stageNo;
    private int StageLevel;
    private int StageSizeX;
    private int StageSizeY;
    private int life;
    private int gold;
    private int score;
    private int total;
    private int totalCount;
    private boolean stageLevelup;
    private int stageRelease;
    private boolean storeRelease;
    private int shopTowerRelease;
    private int nodamageBonus;
    private int stageBonus;
    
    private int[] enemyKillCount = new int[Common.ENEMY_TYPE_MAX];

    private int gameSpeed;
    private boolean noDamage;
    private int skillCount000;
    private int skillCount001;
    private int skillCount002;
    
    private boolean glid;
    
    private boolean[] Release = new boolean[Common.TOWER_TYPE_MAX];
    private int[] StatusBonus = new int[Common.TOWER_TYPE_MAX];
    
    public Player(SharedPreferences pref) {
        
        this.asset = pref.getLong(Common.PREF_ASSET, 0);
        
        this.stageNo = pref.getInt(Common.PREF_STAGE_NO, 0);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String stageNo = String.valueOf(""+decimalFormat.format(pref.getInt(Common.PREF_STAGE_NO, 1)));
        
        this.StageLevel = pref.getInt("Stage" + stageNo + "Level", 1);
        this.StageSizeX = pref.getInt(Common.PREF_STAGE_SIZEX, 1);
        this.StageSizeY = pref.getInt(Common.PREF_STAGE_SIZEY, 1);
        this.life = pref.getInt(Common.PREF_LIFE_MAX, 10);
        this.gold = pref.getInt(Common.PREF_INITIALFOUNDS, 50);
        this.score = 0;
        this.total = 0;
        this.totalCount = 0;
        this.stageLevelup = false;
        this.nodamageBonus = 0;
        this.gameSpeed = 1;
        this.noDamage = false;
        this.skillCount000 = pref.getInt(Common.PREF_STORE_SKILL000COUNT, 0);
        this.skillCount001 = pref.getInt(Common.PREF_STORE_SKILL001COUNT, 0);
        this.skillCount002 = pref.getInt(Common.PREF_STORE_SKILL002COUNT, 0);
        this.glid = false;
        initEnemyKillCount();
        
        this.Release[2] = pref.getBoolean(Common.PREF_STORE_TOWER002RELEASE, false);
        this.Release[12] = pref.getBoolean(Common.PREF_STORE_TOWER012RELEASE, false);
        this.Release[22] = pref.getBoolean(Common.PREF_STORE_TOWER022RELEASE, false);
        this.Release[32] = pref.getBoolean(Common.PREF_STORE_TOWER032RELEASE, false);
        this.StatusBonus[0] = pref.getInt(Common.PREF_STORE_TOWER000BONUS, 0);
        this.StatusBonus[1] = pref.getInt(Common.PREF_STORE_TOWER001BONUS, 0);
        this.StatusBonus[2] = pref.getInt(Common.PREF_STORE_TOWER002BONUS, 0);
        this.StatusBonus[11] = pref.getInt(Common.PREF_STORE_TOWER011BONUS, 0);
        this.StatusBonus[12] = pref.getInt(Common.PREF_STORE_TOWER012BONUS, 0);
        this.StatusBonus[21] = pref.getInt(Common.PREF_STORE_TOWER021BONUS, 0);
        this.StatusBonus[22] = pref.getInt(Common.PREF_STORE_TOWER022BONUS, 0);
        this.StatusBonus[31] = pref.getInt(Common.PREF_STORE_TOWER031BONUS, 0);
        this.StatusBonus[32] = pref.getInt(Common.PREF_STORE_TOWER032BONUS, 0);
    }
    
    public void initEnemyKillCount() {
        for (int i = 0 ; i< Common.ENEMY_TYPE_MAX ; i++) {
            enemyKillCount[i] = 0;
        }
    }
    
    public int getEnemyKillCount(int id) {
        return enemyKillCount[id];
    }
    public void addEnemyKillCount(int id) {
        this.enemyKillCount[id]++;
    }

    public long getAsset() {
        return asset;
    }

    public void setAsset(long asset) {
        this.asset = asset;
    }

    public int getStageNo() {
        return stageNo;
    }

    public void setStageNo(int stageNo) {
        this.stageNo = stageNo;
    }

    public int getStageLevel() {
        return StageLevel;
    }

    public void setStageLevel(int stageLevel) {
        StageLevel = stageLevel;
    }

    public int getStageSizeX() {
        return StageSizeX;
    }

    public void setStageSizeX(int stageSizeX) {
        StageSizeX = stageSizeX;
    }

    public int getStageSizeY() {
        return StageSizeY;
    }

    public void setStageSizeY(int stageSizeY) {
        StageSizeY = stageSizeY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isStageLevelup() {
        return stageLevelup;
    }

    public void setStageLevelup(boolean stageLevelup) {
        this.stageLevelup = stageLevelup;
    }

    public int getStageRelease() {
        return stageRelease;
    }

    public void setStageRelease(int stageRelease) {
        this.stageRelease = stageRelease;
    }

    public boolean isStoreRelease() {
        return storeRelease;
    }

    public void setStoreRelease(boolean storeRelease) {
        this.storeRelease = storeRelease;
    }

    public int getShopTowerRelease() {
        return shopTowerRelease;
    }

    public void setShopTowerRelease(int shopTowerRelease) {
        this.shopTowerRelease = shopTowerRelease;
    }

    public int getNodamageBonus() {
        return nodamageBonus;
    }

    public void setNodamageBonus(int nodamageBonus) {
        this.nodamageBonus = nodamageBonus;
    }

    public int getStageBonus() {
        return stageBonus;
    }

    public void setStageBonus(int stageBonus) {
        this.stageBonus = stageBonus;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public boolean isNoDamage() {
        return noDamage;
    }

    public void setNoDamage(boolean noDamage) {
        this.noDamage = noDamage;
    }

    public int getSkillCount000() {
        return skillCount000;
    }

    public void setSkillCount000(int skillCount000) {
        this.skillCount000 = skillCount000;
    }

    public int getSkillCount001() {
        return skillCount001;
    }

    public void setSkillCount001(int skillCount001) {
        this.skillCount001 = skillCount001;
    }

    public int getSkillCount002() {
        return skillCount002;
    }

    public void setSkillCount002(int skillCount002) {
        this.skillCount002 = skillCount002;
    }

    public boolean isGlid() {
        return glid;
    }

    public void setGlid(boolean glid) {
        this.glid = glid;
    }

    public boolean getRelease(int id) {
        return Release[id];
    }

    public void setRelease(int id, boolean release) {
        Release[id] = release;
    }

    public int getStatusBonus(int id) {
        return StatusBonus[id];
    }

    public void setStatusBonus(int id, int statusBonus) {
        StatusBonus[id] = statusBonus;
    }

}
