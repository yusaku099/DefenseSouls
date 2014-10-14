package com.example.defense_souls;

import java.util.List;

public class StageEffectAction {

    public void stageEffectAction(Player Player, Image image, StageDataObject stageData, Sound sound) {
        for (int i = 0; i < stageData.getStageEffectlist().size(); i++) {
            StageEffectObject stageEffect = stageData.getStageEffectlist().get(i);
            switch( stageEffect.getStatus() ){
                // 初期化
                case 1:
                    stageEffect.imageSizeSet(image.getImagelist().get(stageEffect.getImageNo()), Player);
                    stageEffect.setStatus(2);
                    if (stageEffect.getSoundNo() != 0) {
                        sound.playSound(stageEffect.getSoundNo());
                    }
                    break;
                // 通常時
                case 2:
                    stageEffect.setPositionX(stageEffect.getPositionX()+(stageEffect.getSpeedX() * Player.getGameSpeed()));
                    if (stageEffect.getPositionX() > (stageData.getCanvasX()*2)+stageEffect.getDispSizeX()) {
                        stageEffect.setPositionX(-stageEffect.getDispSizeX());
                    }
                    stageEffect.setPositionY(stageEffect.getPositionY()+(stageEffect.getSpeedY() * Player.getGameSpeed()));
                    if (stageEffect.getPositionY() > (stageData.getCanvasY()*2)+stageEffect.getDispSizeY()) {
                        stageEffect.setPositionY(-stageEffect.getDispSizeY());
                    }
                    stageEffect.setAnimationCount(stageEffect.getAnimationCount() + (1 * Player.getGameSpeed()));
                    if (stageEffect.getAnimationCount() > stageEffect.getAnimationChange()) {
                        stageEffect.setAnimationChangeCount(stageEffect.getAnimationChangeCount() + 1);
                        if (stageEffect.getAnimationChangeCount() > stageEffect.getAnimationChangeCountMax()) {
                            stageEffect.setAnimationChangeCount(0);
                        }
                        stageEffect.setAnimationCount(0);
                    }
                    
                    break;
            }
        }
    }
}
