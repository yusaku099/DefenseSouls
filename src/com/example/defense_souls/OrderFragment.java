package com.example.defense_souls;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.SharedPreferences;

@SuppressLint("NewApi")
public class OrderFragment extends Fragment implements OnItemClickListener, OnClickListener {
    private ImageView stand;
    private Button GameStart;
    private Button StageLevelup;
    private Button StageLeveldown;
    private TextView StageLevelText;
    private List<OrderListItem> dataList = new ArrayList<OrderListItem>();
    private SharedPreferences pref;
    private OrderListAdapter OrderlistAdapter;
    
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // アクションバーを表示
        ActionBar mActionBar = getActivity().getActionBar();
        mActionBar.show();
	    return inflater.inflate(R.layout.order_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

        setDataList();
        
        OrderlistAdapter = new OrderListAdapter(getActivity(), dataList);
        ListView Order = (ListView)getActivity().findViewById(R.id.list_stagelocation);
        Order.setAdapter(OrderlistAdapter);
        Order.setOnItemClickListener(this);
        GameStart = (Button)getActivity().findViewById(R.id.button_gamestart);
        GameStart.setCompoundDrawablesWithIntrinsicBounds(getActivity().getResources().getDrawable(R.drawable.battle), null, null, null);

        StageLevelup = (Button)getActivity().findViewById(R.id.button_levelup);
        StageLevelup.setOnClickListener(this);

        StageLeveldown = (Button)getActivity().findViewById(R.id.button_leveldown);
        StageLeveldown.setOnClickListener(this);
        
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String stageNo = String.valueOf(""+decimalFormat.format(pref.getInt(Common.PREF_STAGE_NO, 1)));
        
        StageLevelText = (TextView)getActivity().findViewById(R.id.text_stageleveltext);
        StageLevelText.setText(String.valueOf("Level " + pref.getInt("Stage" + stageNo + "Level", 1) + "/" + pref.getInt("Stage" + stageNo + "LevelMax", 5)));
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		
	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound("select");
        // TODO Auto-generated method stub
        ListView listView = (ListView) parent;
        OrderListItem listItem = (OrderListItem) listView.getItemAtPosition(position);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putInt(Common.PREF_STAGE_NO, listItem.getListId());
        prefEditor.commit();

        setDataList();
        OrderlistAdapter.notifyDataSetChanged();
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String stageNo = String.valueOf(decimalFormat.format(pref.getInt(Common.PREF_STAGE_NO, 0)));
        StageLevelText.setText(String.valueOf("Level " + pref.getInt("Stage" + stageNo + "Level", 1) + "/" + pref.getInt("Stage" + stageNo + "LevelMax", 5)));
    }

    @Override
    public void onClick(View arg0) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor prefEditor = pref.edit();
        int level;
        
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String stageNo = String.valueOf(""+decimalFormat.format(pref.getInt(Common.PREF_STAGE_NO, 1)));
        
        switch (arg0.getId()) {
        case R.id.button_levelup:
            playSound("up");
            level = pref.getInt("Stage" + stageNo + "Level", 1) + 1;
            if (level > pref.getInt("Stage" + stageNo + "LevelMax", 5)) {
                prefEditor.putInt("Stage" + stageNo + "Level", pref.getInt("Stage" + stageNo + "LevelMax", 5));
            } else {
                prefEditor.putInt("Stage" + stageNo + "Level", level);
            }
            prefEditor.commit();
            break;
        case R.id.button_leveldown:
            playSound("down");
            level = pref.getInt("Stage" + stageNo + "Level", 1) - 1;
            if (level < pref.getInt("Stage" + stageNo + "LevelMin", 1)) {
                prefEditor.putInt("Stage" + stageNo + "Level", pref.getInt("Stage" + stageNo + "LevelMin", 1));
            } else {
                prefEditor.putInt("Stage" + stageNo + "Level", level);
            }
            prefEditor.commit();
            break;
        }
        
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        setDataList();
        OrderlistAdapter.notifyDataSetChanged();
        StageLevelText.setText(String.valueOf("Level " + pref.getInt("Stage" + stageNo + "Level", 1) + "/" + pref.getInt("Stage" + stageNo + "LevelMax", 5)));
    }
    
    private void setDataList() {
        dataList.clear();
        String[] location = getResources().getStringArray(R.array.list_stagelocation);
        String[] description = getResources().getStringArray(R.array.list_stagedescription);
        int[] draw = {R.drawable.stageicon001,R.drawable.stageicon002,R.drawable.stageicon003,
                R.drawable.stageicon004,R.drawable.stageicon005,R.drawable.stageicon006,
                R.drawable.stageicon007,R.drawable.stageicon008,R.drawable.stageicon009,
                R.drawable.stageicon010,R.drawable.stageicon011,R.drawable.stageicon001};
        
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        DecimalFormat decimalFormat = new DecimalFormat("00");
        int level;
        int levelmax;
        for (int i = 1 ; i < 12 ; i++) {
            if (pref.getBoolean("Stage" + decimalFormat.format(i) + "Release", false) == true) {
                level = pref.getInt("Stage" + decimalFormat.format(i) + "Level", 1);
                levelmax = pref.getInt("Stage" + decimalFormat.format(i) + "LevelMax", 5);
                dataList.add(new OrderListItem(i, location[i-1], draw[i-1], description[i-1], String.valueOf(level + "/" + levelmax)));
            }
        }
    }
    
    private SoundPool mSoundPool;
    private int soundId_Select;
    private int soundId_levelup;
    private int soundId_leveldown;
    public void playSound(String tag) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (tag == "select") {
                mSoundPool.play(soundId_Select, 1.0F, 1.0F, 0, 0, 1.0F);
            }
            if (tag == "up") {
                mSoundPool.play(soundId_levelup, 1.0F, 1.0F, 0, 0, 1.0F);
            }
            if (tag == "down") {
                mSoundPool.play(soundId_leveldown, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId_Select = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system002, 0);
        soundId_levelup = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system003, 0);
        soundId_leveldown = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system004, 0);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        // リリース
        mSoundPool.release();
    }
    
}
