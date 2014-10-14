package com.example.defense_souls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;

@SuppressLint("NewApi")
public class GameFragment extends Fragment {
    private DrawSurfaceView drawSurfaceView;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // アクションバーを非表示
        ActionBar mActionBar = getActivity().getActionBar();
        mActionBar.hide();
	    return inflater.inflate(R.layout.game_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		drawSurfaceView = new DrawSurfaceView( getActivity(), (SurfaceView)getActivity().findViewById( R.id.SV ), (Button)getActivity().findViewById( R.id.button_endgame ), getActivity().getFragmentManager() );
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
    @Override
	public void onResume() {
	    super.onResume();
	}

    public void onGameStop() {
        if (drawSurfaceView != null) {
            drawSurfaceView.onGameStopDown();
        }
    }

}
