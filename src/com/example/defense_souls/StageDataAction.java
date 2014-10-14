package com.example.defense_souls;

import java.util.List;

public class StageDataAction {

    public void stageDataAction(StageDataObject stageData, Player Player, List<EffectObject> effectlist, GameOnTouch gameOnTouch) {
        
        for (int x = 0 ; x < stageData.getCellx() ; x++) {
            for (int y = 0 ; y < stageData.getCelly() ; y++) {
                
                switch( stageData.getStatusData(x, y) ){
                // 初期化
                case 1:
                    if (stageData.getImageData(x, y) != 0) {
                        stageData.setStatusData(2, x, y);
                    }
                    break;
                    
                case 2:
                    stageData.setAnimationCount(stageData.getAnimationCount(x,y) + (1 * Player.getGameSpeed()),x,y);
                    if (stageData.getAnimationCount(x,y) > stageData.getAnimationChange(x,y)) {
                        stageData.setAnimationChangeCount(stageData.getAnimationChangeCount(x,y) + 1,x,y);
                        if (stageData.getAnimationChangeCount(x,y) > stageData.getAnimationChangeCountMax(x,y)) {
                            stageData.setAnimationChangeCount(0,x,y);
                        }
                        stageData.setAnimationCount(0,x,y);
                    }
                    break;
                    
                // 鉱山１爆発スイッチ初期化
                case 3:
                    break;
                // 鉱山１爆発初期化
                case 4:
                    break;
                    
                case 5:
                    stageData.setAnimationCount(stageData.getAnimationCount(x, y) + (1 * Player.getGameSpeed()), x , y);
                    if (stageData.getAnimationCount(x, y) > stageData.getAnimationChange(x, y)) {
                        stageData.setStatusData(6, x, y);
                        stageData.setAnimationCount(0, x, y);
                        stageData.setAnimationChange(30, x, y);
                        effectlist.add(new EffectObject(201, (x * Common.CELLSIZE_X) - 50, (y * Common.CELLSIZE_Y) - 80, 0, 0, null));
                    }
                    break;
                    
                case 6:
                    stageData.setAnimationCount(stageData.getAnimationCount(x, y) + (1 * Player.getGameSpeed()), x , y);
                    if (stageData.getAnimationCount(x, y) > stageData.getAnimationChange(x, y)) {
                        stageData.setStatusData(7, x, y);
                        stageData.setAnimationCount(0, x, y);
                        stageData.setAnimationChange(30, x, y);
                        effectlist.add(new EffectObject(201, (x * Common.CELLSIZE_X) + 20, (y * Common.CELLSIZE_Y) - 40, 0, 0, null));
                    }
                    break;
                    
                case 7:
                    stageData.setAnimationCount(stageData.getAnimationCount(x, y) + (1 * Player.getGameSpeed()), x , y);
                    if (stageData.getAnimationCount(x, y) > stageData.getAnimationChange(x, y)) {
                        stageData.setStatusData(999, x, y);
                        stageData.setAnimationCount(0, x, y);
                        stageData.setAnimationChange(30, x, y);
                        effectlist.add(new EffectObject(201, (x * Common.CELLSIZE_X), (y * Common.CELLSIZE_Y), 0, 0, null));
                        stageData.objectSet(x,y,Common.AREA_OK,0,0,1);
                        
                        int stage[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,2,231,-1,-1,-1,-1,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,2,-1,-1,-1,-1,0,0,0,0,0,0,0,-1,-1,-1,0,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,2,0,-1,-1,0,0,-1,-1,-1,0,0,0,-1,-1,-1,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,2,0,0,0,0,-1,-1,-1,-1,-1,0,0,102,102,102,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,2,0,0,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,2,2,2,2,231,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,2,2,2,2,2,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,2,2,2,2,2,2,-1,-1,-1,-1,1,-1,-1,-1,-1,-1,-1,-1,-1,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,2,2,2,2,2,0,0,0,-1,-1,-1,-1,0,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,}
                        };
                        stageData.setEnemybaseMax(2);
                        stageData.stageSet(stage);
                    }
                    break;
                }
            }
        }
        
    }
}
