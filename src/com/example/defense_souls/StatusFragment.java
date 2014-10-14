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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

@SuppressLint("NewApi")
public class StatusFragment extends Fragment implements OnItemClickListener {
    private TextView statusView;
    private ImageView imageView;
    private List<StatusListItem> dataList = new ArrayList<StatusListItem>();
    private SharedPreferences pref;
    private StatusListAdapter StatuslistAdapter;
    private AnimationDrawable frameAnimation;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	     return inflater.inflate(R.layout.status_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);

		setDataList();
        
        statusView = (TextView)getActivity().findViewById( R.id.text_statusTextView);
        statusView.setWidth((pref.getInt(Common.PREF_STAGE_SIZEX, 0) / 2));
        imageView = (ImageView)getActivity().findViewById( R.id.image_statusView);
        imageView.setScaleX(4.0f);
        imageView.setScaleY(4.0f);
        
        StatuslistAdapter = new StatusListAdapter(getActivity(), dataList);
        ListView Status = (ListView)getActivity().findViewById(R.id.list_status);
        Status.setAdapter(StatuslistAdapter);
        Status.setOnItemClickListener(this);
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        // 初めて起動した場合のスタートアップガイド表示
        if (pref.getBoolean(Common.PREF_INIT_STATUSGUIDE, false) == false) {
            CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getFragmentManager(), "StatusGuide");
        }
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
        StatusListItem listItem = (StatusListItem) listView.getItemAtPosition(position);
        String crlf = Common.crlf;
        switch (listItem.getListId()) {
            case 1:
                String skill01 = "";
                String skill02 = "";
                String skill03 = "";
                if (pref.getBoolean(Common.PREF_STORE_SKILL000RELEASE, false) == true) {
                    skill01 = getResources().getString(R.string.submenu_skillname000) + " : " + getResources().getString(R.string.submenu_skilldescription000) + crlf;
                }
                if (pref.getBoolean(Common.PREF_STORE_SKILL001RELEASE, false) == true) {
                    skill02 = getResources().getString(R.string.submenu_skillname001) + " : " + getResources().getString(R.string.submenu_skilldescription001) + crlf;
                }
                if (pref.getBoolean(Common.PREF_STORE_SKILL002RELEASE, false) == true) {
                    skill03 = getResources().getString(R.string.submenu_skillname002) + " : " + getResources().getString(R.string.submenu_skilldescription002) + crlf;
                }
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER000RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER000POWER, pref.getInt(Common.PREF_STORE_TOWER000BONUS, 0))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER000SPEED, pref.getInt(Common.PREF_STORE_TOWER000BONUS, 0))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower000_description) + crlf + crlf + skill01 + skill02 + skill03
                                );
                setFrameAnimation(0);
                break;
            case 2:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER001RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER001POWER, pref.getInt(Common.PREF_STORE_TOWER001BONUS, 0))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER001SPEED, pref.getInt(Common.PREF_STORE_TOWER001BONUS, 0))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower001_description) + crlf
                                );
                setFrameAnimation(1);
                break;
            case 3:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER002RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER002POWER, pref.getInt(Common.PREF_STORE_TOWER002BONUS, 0))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER002SPEED, pref.getInt(Common.PREF_STORE_TOWER002BONUS, 0))) + crlf +
                        getResources().getString(R.string.text_assistpower) + " : " + String.valueOf(Common.setAssistPowerup(Common.INIT_TOWER002ASSISTPOWER, pref.getInt(Common.PREF_STORE_TOWER002BONUS, 0))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower002_description) + crlf
                                );
                setFrameAnimation(2);
                break;
            case 4:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER011RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER011POWER, pref.getInt(Common.PREF_STORE_TOWER011BONUS, 0))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER011SPEED, pref.getInt(Common.PREF_STORE_TOWER011BONUS, 0))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower011_description) + crlf
                                );
                setFrameAnimation(3);
                break;
            case 5:
                DecimalFormat decimalFormat = new DecimalFormat("##.##");
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_assistspeed) + " : " + String.valueOf(decimalFormat.format(Common.setAssistSpeedup(Common.INIT_TOWER012ASSISTSPEED, pref.getInt(Common.PREF_STORE_TOWER012BONUS, 0)))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower012_description) + crlf
                                );
                setFrameAnimation(4);
                break;
            case 6:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER021RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER021POWER, pref.getInt(Common.PREF_STORE_TOWER021BONUS, 1))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER021SPEED, pref.getInt(Common.PREF_STORE_TOWER021BONUS, 1))) + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower021_description) + crlf
                                );
                setFrameAnimation(5);
                break;
            case 7:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_range) + " : " + Common.INIT_TOWER022RANGE + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER022POWER, pref.getInt(Common.PREF_STORE_TOWER022BONUS, 1))) + crlf +
                        getResources().getString(R.string.text_speed) + " : " + String.valueOf(Common.setSpeedupPoint(Common.INIT_TOWER022SPEED, pref.getInt(Common.PREF_STORE_TOWER022BONUS, 1))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower022_description) + crlf
                                );
                setFrameAnimation(6);
                break;
            case 8:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER031POWER, pref.getInt(Common.PREF_STORE_TOWER031BONUS, 1))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower031_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 9:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_power) + " : " + String.valueOf(Common.setPowerup(Common.INIT_TOWER032POWER, pref.getInt(Common.PREF_STORE_TOWER032BONUS, 1))) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.tower032_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 101:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY001_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy001_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 102:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY002_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy002_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 103:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY003_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy003_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 104:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY004_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy004_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 111:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY011_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype003_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy011_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 112:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "D" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY012_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype003_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy012_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 113:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY013_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype003_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy013_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 114:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY014_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype003_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy014_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 121:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY021_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype001_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy021_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 122:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY022_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype001_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy022_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 123:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY023_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype001_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy023_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 131:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY031_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy031_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 132:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY032_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy032_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 133:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY033_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy033_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 134:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY034_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy034_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 141:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY041_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy041_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 142:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY042_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy042_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 143:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY043_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype002_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy043_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 151:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY051_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy051_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 152:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY052_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype005_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy052_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 153:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY053_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype005_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy053_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 154:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "E" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY054_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy054_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 161:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY061_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype004_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy061_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 162:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY062_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype004_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy062_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 163:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "B" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY063_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype004_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy063_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 164:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "S" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY064_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype004_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy064_description) + crlf
                                );
                imageView.setBackground(null);
                break;
                
            case 171:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "F" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY071_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype006_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy071_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 172:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY072_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype006_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy072_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 173:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "A" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY073_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype006_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy073_description) + crlf
                                );
                imageView.setBackground(null);
                break;
            case 181:
                statusView.setText("【" + listItem.getTitle() + "】" + crlf + crlf +
                        getResources().getString(R.string.text_life) + " : " + "SS" + crlf +
                        getResources().getString(R.string.text_defense) + " : " + "SS" + crlf +
                        getResources().getString(R.string.text_movespeed) + " : " + "C" + crlf +
                        getResources().getString(R.string.text_killcount) + " : " + pref.getLong(Common.PREF_ENEMY081_COUNT, 0) + crlf + crlf +
                        getResources().getString(R.string.text_description) + " : " + crlf + getResources().getString(R.string.enemytype000_description) + crlf + crlf + 
                        getResources().getString(R.string.enemy081_description) + crlf
                                );
                imageView.setBackground(null);
                break;
        }
    }
    
    private void setDataList() {
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dataList.clear();
        
        // 各タワーのステータス
        dataList.add(new StatusListItem(1, getResources().getString(R.string.tower000_name), R.drawable.face000));
        dataList.add(new StatusListItem(2, getResources().getString(R.string.tower001_name), R.drawable.face001));
        if (pref.getBoolean(Common.PREF_STORE_TOWER002RELEASE, false) == true) {
            dataList.add(new StatusListItem(3, getResources().getString(R.string.tower002_name), R.drawable.face002));
        }
        dataList.add(new StatusListItem(4, getResources().getString(R.string.tower011_name), R.drawable.face011));
        if (pref.getBoolean(Common.PREF_STORE_TOWER012RELEASE, false) == true) {
            dataList.add(new StatusListItem(5, getResources().getString(R.string.tower012_name), R.drawable.face012));
        }
        dataList.add(new StatusListItem(6, getResources().getString(R.string.tower021_name), R.drawable.face021));
        if (pref.getBoolean(Common.PREF_STORE_TOWER022RELEASE, false) == true) {
            dataList.add(new StatusListItem(7, getResources().getString(R.string.tower022_name), R.drawable.face022));
        }
        dataList.add(new StatusListItem(8, getResources().getString(R.string.tower031_name), R.drawable.face031));
        if (pref.getBoolean(Common.PREF_STORE_TOWER032RELEASE, false) == true) {
            dataList.add(new StatusListItem(9, getResources().getString(R.string.tower032_name), R.drawable.face032));
        }
        
        
        // 各エネミーのステータス
        if (pref.getLong(Common.PREF_ENEMY001_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(101, getResources().getString(R.string.enemy001_name), R.drawable.face_enemy001));
        }
        if (pref.getLong(Common.PREF_ENEMY002_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(102, getResources().getString(R.string.enemy002_name), R.drawable.face_enemy002));
        }
        if (pref.getLong(Common.PREF_ENEMY003_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(103, getResources().getString(R.string.enemy003_name), R.drawable.face_enemy003));
        }
        if (pref.getLong(Common.PREF_ENEMY004_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(104, getResources().getString(R.string.enemy004_name), R.drawable.face_enemy004));
        }
        
        if (pref.getLong(Common.PREF_ENEMY011_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(111, getResources().getString(R.string.enemy011_name), R.drawable.face_enemy011));
        }
        if (pref.getLong(Common.PREF_ENEMY012_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(112, getResources().getString(R.string.enemy012_name), R.drawable.face_enemy012));
        }
        if (pref.getLong(Common.PREF_ENEMY013_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(113, getResources().getString(R.string.enemy013_name), R.drawable.face_enemy013));
        }
        if (pref.getLong(Common.PREF_ENEMY014_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(114, getResources().getString(R.string.enemy014_name), R.drawable.face_enemy014));
        }
        
        if (pref.getLong(Common.PREF_ENEMY021_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(121, getResources().getString(R.string.enemy021_name), R.drawable.face_enemy021));
        }
        if (pref.getLong(Common.PREF_ENEMY022_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(122, getResources().getString(R.string.enemy022_name), R.drawable.face_enemy022));
        }
        if (pref.getLong(Common.PREF_ENEMY023_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(123, getResources().getString(R.string.enemy023_name), R.drawable.face_enemy023));
        }
        
        if (pref.getLong(Common.PREF_ENEMY031_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(131, getResources().getString(R.string.enemy031_name), R.drawable.face_enemy031));
        }
        if (pref.getLong(Common.PREF_ENEMY032_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(132, getResources().getString(R.string.enemy032_name), R.drawable.face_enemy032));
        }
        if (pref.getLong(Common.PREF_ENEMY033_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(133, getResources().getString(R.string.enemy033_name), R.drawable.face_enemy033));
        }
        if (pref.getLong(Common.PREF_ENEMY034_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(134, getResources().getString(R.string.enemy034_name), R.drawable.face_enemy034));
        }

        if (pref.getLong(Common.PREF_ENEMY041_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(141, getResources().getString(R.string.enemy041_name), R.drawable.face_enemy041));
        }
        if (pref.getLong(Common.PREF_ENEMY042_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(142, getResources().getString(R.string.enemy042_name), R.drawable.face_enemy042));
        }
        if (pref.getLong(Common.PREF_ENEMY043_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(143, getResources().getString(R.string.enemy043_name), R.drawable.face_enemy043));
        }
        
        if (pref.getLong(Common.PREF_ENEMY051_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(151, getResources().getString(R.string.enemy051_name), R.drawable.face_enemy051));
        }
        if (pref.getLong(Common.PREF_ENEMY052_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(152, getResources().getString(R.string.enemy052_name), R.drawable.face_enemy052));
        }
        if (pref.getLong(Common.PREF_ENEMY053_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(153, getResources().getString(R.string.enemy053_name), R.drawable.face_enemy053));
        }
        if (pref.getLong(Common.PREF_ENEMY054_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(154, getResources().getString(R.string.enemy054_name), R.drawable.face_enemy054));
        }

        if (pref.getLong(Common.PREF_ENEMY061_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(161, getResources().getString(R.string.enemy061_name), R.drawable.face_enemy061));
        }
        if (pref.getLong(Common.PREF_ENEMY062_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(162, getResources().getString(R.string.enemy062_name), R.drawable.face_enemy062));
        }
        if (pref.getLong(Common.PREF_ENEMY063_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(163, getResources().getString(R.string.enemy063_name), R.drawable.face_enemy063));
        }
        if (pref.getLong(Common.PREF_ENEMY064_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(164, getResources().getString(R.string.enemy064_name), R.drawable.face_enemy064));
        }

        if (pref.getLong(Common.PREF_ENEMY071_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(171, getResources().getString(R.string.enemy071_name), R.drawable.face_enemy071));
        }
        if (pref.getLong(Common.PREF_ENEMY072_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(172, getResources().getString(R.string.enemy072_name), R.drawable.face_enemy072));
        }
        if (pref.getLong(Common.PREF_ENEMY073_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(173, getResources().getString(R.string.enemy073_name), R.drawable.face_enemy073));
        }
        
        if (pref.getLong(Common.PREF_ENEMY081_COUNT, 0) > 0) {
            dataList.add(new StatusListItem(181, getResources().getString(R.string.enemy081_name), R.drawable.face_enemy081));
        }

    }
    
    
    private void setFrameAnimation(int id) {
        frameAnimation = new AnimationDrawable();
        Drawable[] frame = new Drawable[4];
        int[] duration = new int[4];
        switch (id) {
        case 0:
            frame[0] = getResources().getDrawable(R.drawable.tower000_1);
            duration[0] = 300;
            frame[1] = getResources().getDrawable(R.drawable.tower000_2);
            duration[1] = 600;
            break;
            
        case 1:
            frame[0] = getResources().getDrawable(R.drawable.tower001_1);
            duration[0] = 150;
            frame[1] = getResources().getDrawable(R.drawable.tower001_2);
            duration[1] = 150;
            frame[2] = getResources().getDrawable(R.drawable.tower001_3);
            duration[2] = 150;
            frame[3] = getResources().getDrawable(R.drawable.tower001_2);
            duration[3] = 150;
            break;
        case 2:
            frame[0] = getResources().getDrawable(R.drawable.tower002_1);
            duration[0] = 300;
            frame[1] = getResources().getDrawable(R.drawable.tower002_2);
            duration[1] = 300;
            break;
        case 3:
            frame[0] = getResources().getDrawable(R.drawable.tower011_1);
            duration[0] = 300;
            frame[1] = getResources().getDrawable(R.drawable.tower011_3);
            duration[1] = 600;
            break;
        case 4:
            frame[0] = getResources().getDrawable(R.drawable.tower012_1);
            duration[0] = 300;
            frame[1] = getResources().getDrawable(R.drawable.tower012_2);
            duration[1] = 200;
            frame[2] = getResources().getDrawable(R.drawable.tower012_3);
            duration[2] = 600;
            break;
        case 5:
            frame[0] = getResources().getDrawable(R.drawable.tower021_1);
            duration[0] = 400;
            frame[1] = getResources().getDrawable(R.drawable.tower021_2);
            duration[1] = 200;
            frame[2] = getResources().getDrawable(R.drawable.tower021_3);
            duration[2] = 600;
            break;
        case 6:
            frame[0] = getResources().getDrawable(R.drawable.tower022_1);
            duration[0] = 400;
            frame[1] = getResources().getDrawable(R.drawable.tower022_2);
            duration[1] = 200;
            frame[2] = getResources().getDrawable(R.drawable.tower022_3);
            duration[2] = 600;
            break;
        }
        for (int i = 0 ; i < frame.length ; i++) {
            if (frame[i] != null) {
                frameAnimation.addFrame(frame[i], duration[i]);
            }
        }
        frameAnimation.setOneShot(false);
        imageView.setBackground(frameAnimation);
        frameAnimation.start();

    }
	
    private SoundPool mSoundPool;
    private int soundId_Select;
    public void playSound(String tag) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (pref.getBoolean(Common.PREF_SOUND, true) == true) {
            if (tag == "select") {
                mSoundPool.play(soundId_Select, 1.0F, 1.0F, 0, 0, 1.0F);
            }
        }
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId_Select = mSoundPool.load(getActivity().getApplicationContext(), R.raw.system002, 0);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        // リリース
        mSoundPool.release();
    }

}
