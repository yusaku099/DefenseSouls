package com.example.defense_souls;

import java.util.ArrayList;
import java.util.List;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.SharedPreferences;

@SuppressLint({ "NewApi", "ValidFragment" })
public class StoreFragment extends Fragment implements OnItemClickListener, OnTouchListener {
    private List<StoreListItem> dataList = new ArrayList<StoreListItem>();
    private SharedPreferences pref;
    private StoreListAdapter StorelistAdapter;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	     return inflater.inflate(R.layout.store_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		Log.v("storelevel",""+pref.getInt(Common.PREF_STORE_LEVEL, 1));
		
        TextView textAsset = (TextView)getActivity().findViewById( R.id.text_storeasset );
        textAsset.setText(String.valueOf(pref.getLong(Common.PREF_ASSET,0)));
		
        setDataList();
        
        StorelistAdapter = new StoreListAdapter(getActivity(), dataList);
        ListView Store = (ListView)getActivity().findViewById(R.id.list_store);
        Store.setAdapter(StorelistAdapter);
        Store.setOnItemClickListener(this);
        Store.setOnTouchListener(this);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        // 初めて起動した場合のスタートアップガイド表示
        if (pref.getBoolean(Common.PREF_INIT_STOREGUIDE, false) == false) {
            CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getFragmentManager(), "StoreGuide");
        }
	}

    
    @Override
	public void onDestroy(){
		super.onDestroy();
		
	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound("select");
        ListView listView = (ListView) parent;
        StoreListItem listItem = (StoreListItem) listView.getItemAtPosition(position);
        if (listItem.getCost() != 0) {
            if (pref.getLong(Common.PREF_ASSET, 0) >= listItem.getCost()) {
                CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                customDialogFragment.setBuy(listItem.getListId(), listItem.getCost());
                customDialogFragment.setArguments(this);
                customDialogFragment.show(getFragmentManager(), "StoreBuy");
                
            } else {
                CustomDialogFragment customDialogFragment = new CustomDialogFragment();
                customDialogFragment.show(getFragmentManager(), "StoreNot");
                
           }
        } else {
            CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getFragmentManager(), "StoreMAX");
        }
    }
    
    public void setDataList() {
        dataList.clear();
        String crlf = Common.crlf;

        // ライフアップ
        setData(0, pref.getInt(Common.PREF_LIFE_LEVEL, 1), getResources().getString(R.string.title_Lifeup), 
                R.drawable.icon021, getResources().getString(R.string.description_Lifeup), 
                ""+pref.getInt(Common.PREF_LIFE_MAX, 1) + " ⇒ " + (pref.getInt(Common.PREF_LIFE_MAX, 1) + pref.getInt(Common.PREF_STORE_LIFEUP, 1)));
        
        // 初期資金アップ
        setData(1, pref.getInt(Common.PREF_FUNDS_LEVEL, 1), getResources().getString(R.string.title_InitialFunds), 
                R.drawable.icon022, getResources().getString(R.string.description_InitialFunds), 
                ""+pref.getInt(Common.PREF_INITIALFOUNDS, 1) + " ⇒ " + (pref.getInt(Common.PREF_INITIALFOUNDS, 1) + pref.getInt(Common.PREF_STORE_FUNDSUP, 10)));

        // 隊長強化
        setData(100, pref.getInt(Common.PREF_STORE_TOWER000LEVEL, 1), 
                getResources().getString(R.string.tower000_name) + " " + getResources().getString(R.string.title_levelup),
                R.drawable.face000, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + getResources().getString(R.string.description_plus), 
                ""+pref.getInt(Common.PREF_STORE_TOWER000BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER000BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        
        // ガンナー強化
        setData(101, pref.getInt(Common.PREF_STORE_TOWER001LEVEL, 1), 
                getResources().getString(R.string.tower001_name) + " " + getResources().getString(R.string.title_levelup),
                R.drawable.face001, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + getResources().getString(R.string.description_plus),
                ""+pref.getInt(Common.PREF_STORE_TOWER001BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER001BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        
        if (pref.getBoolean(Common.PREF_STORE_TOWER002RELEASE, false) == true) {
            // 勇士強化
            setData(102, pref.getInt(Common.PREF_STORE_TOWER002LEVEL, 1), 
                    getResources().getString(R.string.tower002_name) + " " + getResources().getString(R.string.title_levelup),
                    R.drawable.face002, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + "," + getResources().getString(R.string.text_assistpower) + getResources().getString(R.string.description_plus), 
                    ""+pref.getInt(Common.PREF_STORE_TOWER002BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER002BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        }
        // 炎の術師強化
        setData(111, pref.getInt(Common.PREF_STORE_TOWER011LEVEL, 1), 
                getResources().getString(R.string.tower011_name) + " " + getResources().getString(R.string.title_levelup),
                R.drawable.face011, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + getResources().getString(R.string.description_plus),
                ""+pref.getInt(Common.PREF_STORE_TOWER011BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER011BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        if (pref.getBoolean(Common.PREF_STORE_TOWER012RELEASE, false) == true) {
            // 時の術師強化
            setData(112, pref.getInt(Common.PREF_STORE_TOWER012LEVEL, 1), 
                    getResources().getString(R.string.tower012_name) + " " + getResources().getString(R.string.title_levelup),
                    R.drawable.face012, getResources().getString(R.string.text_assistspeed) + getResources().getString(R.string.description_plus),
                    ""+pref.getInt(Common.PREF_STORE_TOWER012BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER012BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        }
        // 凍結の術師強化
        setData(121, pref.getInt(Common.PREF_STORE_TOWER021LEVEL, 1), 
                getResources().getString(R.string.tower021_name) + " " + getResources().getString(R.string.title_levelup),
                R.drawable.face021, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + getResources().getString(R.string.description_plus),
                ""+pref.getInt(Common.PREF_STORE_TOWER021BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER021BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        if (pref.getBoolean(Common.PREF_STORE_TOWER022RELEASE, false) == true) {
            // 聖女強化
            setData(122, pref.getInt(Common.PREF_STORE_TOWER022LEVEL, 1), 
                    getResources().getString(R.string.tower022_name) + " " + getResources().getString(R.string.title_levelup),
                    R.drawable.face022, getResources().getString(R.string.text_power) + "," + getResources().getString(R.string.text_speed) + getResources().getString(R.string.description_plus),
                    ""+pref.getInt(Common.PREF_STORE_TOWER022BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER022BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        }
        // 槍の罠強化
        setData(131, pref.getInt(Common.PREF_STORE_TOWER031LEVEL, 1), 
                getResources().getString(R.string.tower031_name) + " " + getResources().getString(R.string.title_levelup),
                R.drawable.face031, getResources().getString(R.string.text_power) + getResources().getString(R.string.description_plus),
                ""+pref.getInt(Common.PREF_STORE_TOWER031BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER031BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        if (pref.getBoolean(Common.PREF_STORE_TOWER032RELEASE, false) == true) {
            // 地雷強化
            setData(132, pref.getInt(Common.PREF_STORE_TOWER032LEVEL, 1), 
                    getResources().getString(R.string.tower032_name) + " " + getResources().getString(R.string.title_levelup),
                    R.drawable.face032, getResources().getString(R.string.text_power) + getResources().getString(R.string.description_plus),
                    ""+pref.getInt(Common.PREF_STORE_TOWER032BONUS, 1) + "% ⇒ " + (pref.getInt(Common.PREF_STORE_TOWER032BONUS, 1) + pref.getInt(Common.PREF_STORE_BONUSUP, 5)) + "%");
        }
        
        if (pref.getBoolean(Common.PREF_STORE_SKILL000RELEASE, false) == true) {
            // スキルの使用回数アップ
            setData(200, pref.getInt(Common.PREF_STORE_SKILL000LEVEL, 1), getResources().getString(R.string.submenu_skillname000) + " " + getResources().getString(R.string.title_Skillup),
                    R.drawable.face000, getResources().getString(R.string.submenu_skill000) + " " + getResources().getString(R.string.description_Skillup),
                    ""+pref.getInt(Common.PREF_STORE_SKILL000COUNT, 1) + " ⇒ " + (pref.getInt(Common.PREF_STORE_SKILL000COUNT, 1) + 1));
        }
        if (pref.getBoolean(Common.PREF_STORE_SKILL001RELEASE, false) == true) {
            // スキルの使用回数アップ
            setData(201, pref.getInt(Common.PREF_STORE_SKILL001LEVEL, 1), getResources().getString(R.string.submenu_skillname001) + " " + getResources().getString(R.string.title_Skillup),
                    R.drawable.face000, getResources().getString(R.string.submenu_skill001) + " " + getResources().getString(R.string.description_Skillup),
                    ""+pref.getInt(Common.PREF_STORE_SKILL001COUNT, 1) + " ⇒ " + (pref.getInt(Common.PREF_STORE_SKILL001COUNT, 1) + 1));
        }
        if (pref.getBoolean(Common.PREF_STORE_SKILL002RELEASE, false) == true) {
            // スキルの使用回数アップ
            setData(202, pref.getInt(Common.PREF_STORE_SKILL002LEVEL, 1), getResources().getString(R.string.submenu_skillname002) + " " + getResources().getString(R.string.title_Skillup),
                    R.drawable.face000, getResources().getString(R.string.submenu_skill002) + " " + getResources().getString(R.string.description_Skillup),
                    ""+pref.getInt(Common.PREF_STORE_SKILL002COUNT, 1) + " ⇒ " + (pref.getInt(Common.PREF_STORE_SKILL002COUNT, 1) + 1));
        }
        
    }
    
    private void setData(int id, int level, String title, int imageNo, String description, String result) {
        int[] cost = setCost(id);
        for (int i = 0 ; i < cost.length ; i++) {
            if (level == (i + 1)) {
                if (level < pref.getInt(Common.PREF_STORE_LEVEL, 1) + 1) {
                    dataList.add(new StoreListItem(id, title, imageNo, description, result, cost[i]));
                    return;
                }
                
            } else if (cost.length == (i + 1)) {
                dataList.add(new StoreListItem(id, title, imageNo, description, "", 0));
                return;
                
            } else if (level > pref.getInt(Common.PREF_STORE_LEVEL, 1)) {
                dataList.add(new StoreListItem(id, title, imageNo, description, "", 0));
                return;
            }
        }
    }
    
    private int[] setCost(int costType) {
        
        switch (costType) {
        case 0 : return new int[] {500,2000,5000,10000,20000};
        case 1 : return new int[] {200,500,1000,1500,3000,5000,7000,10000,15000,20000,30000,45000,60000,80000,100000};
        case 100 : return new int[] {100,300,400,600,900,1300,2000,3000,4500,6000,8000,10000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000,700000,900000};
        case 101 : return new int[] {50,200,300,450,600,800,1000,1500,2000,3000,4000,6000,8000,12000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000};
        case 102 : return new int[] {100,300,400,600,900,1300,2000,3000,4500,6000,8000,10000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000,700000,900000};
        case 111 : return new int[] {50,200,300,450,600,800,1000,1500,2000,3000,4000,6000,8000,12000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000};
        case 112 : return new int[] {100,300,400,600,900,1300,2000,3000,4500,6000,8000,10000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000,700000,900000};
        case 121 : return new int[] {50,200,300,450,600,800,1000,1500,2000,3000,4000,6000,8000,12000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000};
        case 122 : return new int[] {100,300,400,600,900,1300,2000,3000,4500,6000,8000,10000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000,700000,900000};
        case 131 : return new int[] {50,200,300,450,600,800,1000,1500,2000,3000,4000,6000,8000,12000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000};
        case 132 : return new int[] {100,300,400,600,900,1300,2000,3000,4500,6000,8000,10000,14000,18000,22000,28000,34000,40000,50000,62000,75000,90000,120000,150000,200000,300000,400000,500000,700000,900000};
        case 200 : return new int[] {500,1500,3000,5000};
        case 201 : return new int[] {3000,5000,8000,10000};
        case 202 : return new int[] {5000,15000};
        }
        return null;
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        ImageView imageStand = (ImageView)getActivity().findViewById( R.id.image_storestand );
        imageStand.setBackgroundResource(R.drawable.stand001);
        return false;
    }

    public StoreListAdapter getStorelistAdapter() {
        return StorelistAdapter;
    }

    public void setStorelistAdapter(StoreListAdapter storelistAdapter) {
        StorelistAdapter = storelistAdapter;
    }
    
    private SoundPool mSoundPool;
    private int soundId_Buy;
    private int soundId_Select;
    public void playSound(String tag) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (tag == "buy") {
                mSoundPool.play(soundId_Buy, 1.0F, 1.0F, 0, 0, 1.0F);
            }
            if (tag == "select") {
                mSoundPool.play(soundId_Select, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId_Buy = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system001, 0);
        soundId_Select = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system002, 0);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        // リリース
        mSoundPool.release();
    }
    
}
