package com.example.defense_souls;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Image {
    private List<Bitmap> imagelist = new ArrayList<Bitmap>();
    
    public Image(Resources Resources) {
        for (int i = 0 ; i < 1000 ; i++) {
            switch( i ){
                case Common.IMAGE_NUMBER001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.number001));
                    break;
                    
                case Common.IMAGE_BUTTON000:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.button000));
                    break;
                case Common.IMAGE_BUTTON001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.button001));
                    break;
                case Common.IMAGE_BUTTON002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.button002));
                    break;
                    
                case Common.IMAGE_WINDOW001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.window001));
                    break;
                case Common.IMAGE_RESULT001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.result001));
                    break;
                case Common.IMAGE_RESULT002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.result002));
                    break;
                    
                case Common.IMAGE_LEVEL:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.level));
                    break;
                case Common.IMAGE_WAVE:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.wave));
                    break;
                    
                case Common.IMAGE_ICON001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon001));
                    break;
                case Common.IMAGE_ICON002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon002));
                    break;
                case Common.IMAGE_ICON003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon003));
                    break;
                case Common.IMAGE_ICON004:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon004));
                    break;
                case Common.IMAGE_ICON005:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon005));
                    break;
                case Common.IMAGE_ICON006:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon006));
                    break;
                case Common.IMAGE_ICON011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon011));
                    break;
                case Common.IMAGE_ICON012:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.icon012));
                    break;
                    
                case Common.IMAGE_LEVEL001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.level001));
                    break;
                case Common.IMAGE_LEVEL002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.level002));
                    break;
                case Common.IMAGE_LEVEL003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.level003));
                    break;
                    
                case Common.IMAGE_FACE001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.face001));
                    break;
                case Common.IMAGE_FACE011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.face011));
                    break;
                case Common.IMAGE_FACE021:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.face021));
                    break;
                case Common.IMAGE_FACE031:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.face031));
                    break;
                    
                case Common.IMAGE_OBJECT001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object001));
                    break;
                case Common.IMAGE_OBJECT002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object006));
                    break;
                    
                case Common.IMAGE_OBJECT004:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object004));
                    break;
                    
                case Common.IMAGE_OBJECT011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object011));
                    break;
                case Common.IMAGE_OBJECT012:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object012));
                    break;
                case Common.IMAGE_OBJECT013:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object013));
                    break;
                case Common.IMAGE_OBJECT014:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object014));
                    break;
                case Common.IMAGE_OBJECT016:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object016));
                    break;
                case Common.IMAGE_OBJECT017:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object017));
                    break;
                case Common.IMAGE_OBJECT018:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object018));
                    break;
                case Common.IMAGE_OBJECT021:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object021));
                    break;
                case Common.IMAGE_OBJECT023:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object023));
                    break;
                case Common.IMAGE_OBJECT024:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object024));
                    break;
                case Common.IMAGE_OBJECT026:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object026));
                    break;
                    
                case Common.IMAGE_OBJECT031:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.object031));
                    break;
                    
                case Common.IMAGE_TOWER000:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower000));
                    break;
                case Common.IMAGE_TOWER001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower001));
                    break;
                case Common.IMAGE_TOWER002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower002));
                    break;
                case Common.IMAGE_TOWER011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower011));
                    break;
                case Common.IMAGE_TOWER012:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower012));
                    break;
                case Common.IMAGE_TOWER021:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower021));
                    break;
                case Common.IMAGE_TOWER022:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower022));
                    break;
                case Common.IMAGE_TOWER031:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower031));
                    break;
                case Common.IMAGE_TOWER032:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.tower032));
                    break;
                    
                case Common.IMAGE_SHOT000:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.shot000));
                    break;
                case Common.IMAGE_SHOT001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.shot001));
                    break;
                case Common.IMAGE_SHOT002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.shot002));
                    break;
                case Common.IMAGE_SHOT003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.shot003));
                    break;
                    
                case Common.IMAGE_ENEMY001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy001));
                    break;
                case Common.IMAGE_ENEMY002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy002));
                    break;
                case Common.IMAGE_ENEMY003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy003));
                    break;
                case Common.IMAGE_ENEMY004:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy004));
                    break;
                case Common.IMAGE_ENEMY011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy011));
                    break;
                case Common.IMAGE_ENEMY012:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy012));
                    break;
                case Common.IMAGE_ENEMY013:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy013));
                    break;
                case Common.IMAGE_ENEMY014:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy014));
                    break;
                case Common.IMAGE_ENEMY021:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy021));
                    break;
                case Common.IMAGE_ENEMY022:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy022));
                    break;
                case Common.IMAGE_ENEMY023:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy023));
                    break;
                case Common.IMAGE_ENEMY031:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy031));
                    break;
                case Common.IMAGE_ENEMY032:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy032));
                    break;
                case Common.IMAGE_ENEMY033:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy033));
                    break;
                case Common.IMAGE_ENEMY034:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy034));
                    break;
                case Common.IMAGE_ENEMY041:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy041));
                    break;
                case Common.IMAGE_ENEMY042:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy042));
                    break;
                case Common.IMAGE_ENEMY043:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy043));
                    break;
                case Common.IMAGE_ENEMY051:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy051));
                    break;
                case Common.IMAGE_ENEMY052:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy052));
                    break;
                case Common.IMAGE_ENEMY053:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy053));
                    break;
                case Common.IMAGE_ENEMY054:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy054));
                    break;
                case Common.IMAGE_ENEMY061:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy061));
                    break;
                case Common.IMAGE_ENEMY062:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy062));
                    break;
                case Common.IMAGE_ENEMY063:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy063));
                    break;
                case Common.IMAGE_ENEMY064:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy064));
                    break;
                case Common.IMAGE_ENEMY071:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy071));
                    break;
                case Common.IMAGE_ENEMY072:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy072));
                    break;
                case Common.IMAGE_ENEMY073:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy073));
                    break;
                case Common.IMAGE_ENEMY081:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.enemy081));
                    break;
                    
                case Common.IMAGE_EFFECT000:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect000));
                    break;
                case Common.IMAGE_EFFECT001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect001));
                    break;
                case Common.IMAGE_EFFECT002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect002));
                    break;
                case Common.IMAGE_EFFECT003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect003));
                    break;
                case Common.IMAGE_EFFECT011:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect011));
                    break;
                case Common.IMAGE_EFFECT021:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect021));
                    break;
                case Common.IMAGE_EFFECT022:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect022));
                    break;
                case Common.IMAGE_EFFECT031:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect031));
                    break;
                case Common.IMAGE_EFFECT032:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect032));
                    break;
                case Common.IMAGE_EFFECT033:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect033));
                    break;
                case Common.IMAGE_EFFECT041:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect041));
                    break;
                case Common.IMAGE_EFFECT042:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.effect042));
                    break;
                    
                case Common.IMAGE_STAGEEFFECT001:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.clouds001));
                    break;
                case Common.IMAGE_STAGEEFFECT002:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.clouds002));
                    break;
                case Common.IMAGE_STAGEEFFECT003:
                    imagelist.add(i,BitmapFactory.decodeResource(Resources, R.drawable.clouds003));
                    break;
                    
                default:
                    imagelist.add(i,null);
                    break;
            }
            
        }
    }

    public List<Bitmap> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<Bitmap> imagelist) {
        this.imagelist = imagelist;
    }
}
