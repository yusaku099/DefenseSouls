package com.example.defense_souls;

import java.util.ArrayList;
import java.util.List;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("NewApi")
public class HistoryFragment extends Fragment implements OnItemClickListener, OnTouchListener {
    private List<HistoryListItem> dataList = new ArrayList<HistoryListItem>();
    private HistoryListAdapter HistorylistAdapter;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	     return inflater.inflate(R.layout.history_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        setDataList();

        HistorylistAdapter = new HistoryListAdapter(getActivity(), dataList);
        ListView History = (ListView)getActivity().findViewById(R.id.list_history);
        History.setAdapter(HistorylistAdapter);
        History.setOnItemClickListener(this);
        History.setOnTouchListener(this);
        
        // 初めて起動した場合のスタートアップガイド表示
        if (pref.getBoolean(Common.PREF_INIT_HISTORYGUIDE, false) == false) {
            CustomDialogFragment customDialogFragment = new CustomDialogFragment();
            customDialogFragment.show(getFragmentManager(), "HistoryGuide");
        }
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		
	}

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        playSound("select");
        // TODO Auto-generated method stub
        ListView listView = (ListView) parent;
        HistoryListItem listItem = (HistoryListItem) listView.getItemAtPosition(position);
        
        MyDBHelper mHelper = new MyDBHelper(getActivity());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String[] selection = {listItem.getDate()};
        db.delete("score", "colDate LIKE ?", selection);
        setDataList();
        HistorylistAdapter.notifyDataSetChanged();
    }
    
    private void setDataList() {
        dataList.clear();
        
        String result_level = getResources().getString(R.string.text_historyresult_level);
        String result_score = getResources().getString(R.string.text_historyresult_score);

        MyDBHelper mHelper = new MyDBHelper(getActivity());
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cs = db.query("score", null, null, null, null, null, "colScore DESC");
        if (cs.moveToFirst()) {
            do {
                dataList.add(new HistoryListItem(0, cs.getString(1), cs.getInt(2), result_level+":"+cs.getInt(3)+"   "+result_score+":"+cs.getInt(4), 0, cs.getString(5)));
            } while (cs.moveToNext());

        }
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
