package com.example.defense_souls;

import java.util.List;
import java.util.Random;

import android.util.Log;

public class EnemyDataObject {
    
    public EnemyDataObject(int waveNo, List<EnemyObject> enemylist, int[] enemyCount, StageDataObject stageData) {
        
        Random rnd;
        int apran = 0;
        int ap = 0;
        
        ap = 10 + 3;
        
        int basernd;
        int enemyrnd;
        int x = 0;
        int y = 0;

        // waveのエネミーカウントがコストに引かれて0以下になるまでループ
        while (enemyCount[waveNo] > 0) {
            
            rnd = new Random();
            basernd = rnd.nextInt(stageData.getEnemybaseMax());
            EnemyList enemy = new EnemyList(stageData.getEnemybaseType(basernd));
            x = stageData.getEnemybaseX(basernd);
            y = stageData.getEnemybaseY(basernd);
            rnd = new Random();
            enemyrnd = rnd.nextInt(enemy.getTypeMax());
            // waveの出現条件に合致したとき
            if (enemy.getAppear()[enemyrnd] <= waveNo) {
                apran = rnd.nextInt(ap) + 3;
                enemylist.add(new EnemyObject(enemy.getNo()[enemyrnd], waveNo, stageData.getDifficulty(), apran, x, y));
                enemyCount[waveNo] -= enemy.getCost()[enemyrnd];
            }
            
        }
        if (waveNo >= enemyCount.length - 1) {
            if (stageData.getBossNo() != 0) {
                // waveの最後にboss出現
                // 出現ポイントは固定
                enemylist.add(new EnemyObject(stageData.getBossNo(), waveNo, stageData.getDifficulty(), apran, 
                        stageData.getBossbaseX(), stageData.getBossbaseY()));
                
            }
        }
        
    }
}
