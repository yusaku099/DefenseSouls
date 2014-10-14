package com.example.defense_souls;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CustomDialogFragment extends DialogFragment {
    private TextView title;
    private TextView message;
    private int startCount;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        // タイトルのスタートアップガイド
        if (this.getTag() == "StartGuide") {
            
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            startCount = 0;
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.app_start_title));
            message.setText(getActivity().getResources().getString(R.string.app_name) + getActivity().getResources().getString(R.string.app_start_description01));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (startCount == 0) {
                        message.setText(getActivity().getResources().getString(R.string.app_start_description02));
                        startCount++;
                    } else if (startCount == 1) {
                        message.setText(getActivity().getResources().getString(R.string.app_start_description03));
                        startCount++;
                    } else {
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor prefEditor = pref.edit();
                        prefEditor.putBoolean(Common.PREF_INIT_STARTGUIDE, true);
                        dismiss();
                        prefEditor.commit();
                    }
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_STARTGUIDE, true);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            return content;
            
            // ゲームのスタートアップガイド
        } else if (this.getTag() == "GameGuide") {
                
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            player.setGameSpeed(0);
            startCount = 0;
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.app_game_title));
            message.setText(getActivity().getResources().getString(R.string.app_game_description01));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (startCount == 0) {
                        message.setText(getActivity().getResources().getString(R.string.app_game_description02));
                        startCount++;
                    } else if (startCount == 1) {
                        message.setText(getActivity().getResources().getString(R.string.app_game_description03));
                        startCount++;
                    } else if (startCount == 2) {
                        message.setText(getActivity().getResources().getString(R.string.app_game_description04));
                        startCount++;
                    } else if (startCount == 3) {
                        message.setText(getActivity().getResources().getString(R.string.app_game_description05));
                        startCount++;
                    } else if (startCount == 4) {
                        message.setText(getActivity().getResources().getString(R.string.app_game_description06));
                        startCount++;
                    } else {
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor prefEditor = pref.edit();
                        prefEditor.putBoolean(Common.PREF_INIT_GAMEGUIDE, true);
                        player.setGameSpeed(1);
                        dismiss();
                        prefEditor.commit();
                    }
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_GAMEGUIDE, true);
                    player.setGameSpeed(1);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            return content;
            
            // ストアのスタートアップガイド
        } else if (this.getTag() == "StoreGuide") {
                
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            startCount = 0;
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.app_store_title));
            message.setText(getActivity().getResources().getString(R.string.app_store_description01));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (startCount == 0) {
                        message.setText(getActivity().getResources().getString(R.string.app_store_description02));
                        startCount++;
                    } else {
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor prefEditor = pref.edit();
                        prefEditor.putBoolean(Common.PREF_INIT_STOREGUIDE, true);
                        dismiss();
                        prefEditor.commit();
                    }
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_STOREGUIDE, true);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            return content;

            // ステータスのスタートアップガイド
        } else if (this.getTag() == "StatusGuide") {
                
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            startCount = 0;
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.app_status_title));
            message.setText(getActivity().getResources().getString(R.string.app_status_description01));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (startCount == 0) {
                        message.setText(getActivity().getResources().getString(R.string.app_status_description02));
                        startCount++;
                    } else {
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor prefEditor = pref.edit();
                        prefEditor.putBoolean(Common.PREF_INIT_STATUSGUIDE, true);
                        dismiss();
                        prefEditor.commit();
                    }
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_STATUSGUIDE, true);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            return content;
            
            // 記録のスタートアップガイド
        } else if (this.getTag() == "HistoryGuide") {
                
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            startCount = 0;
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.app_history_title));
            message.setText( getActivity().getResources().getString(R.string.app_history_description01));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_HISTORYGUIDE, true);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    prefEditor.putBoolean(Common.PREF_INIT_HISTORYGUIDE, true);
                    dismiss();
                    prefEditor.commit();
                }
                
            });
            return content;
            
            // ストアの買い物
        } else if (this.getTag() == "StoreBuy") {
            
            View content = inflater.inflate(R.layout.custom_dialog_store, null); 
            
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.store_title));
            message.setText(getActivity().getResources().getString(R.string.message_Strengthening));
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor prefEditor = pref.edit();
                    
                    switch (listid) {
                        case 0:
                            prefEditor.putInt(Common.PREF_LIFE_LEVEL, pref.getInt(Common.PREF_LIFE_LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_LIFE_MAX, pref.getInt(Common.PREF_LIFE_MAX, 5) + pref.getInt(Common.PREF_STORE_LIFEUP, 1));
                            break;
                        case 1:
                            prefEditor.putInt(Common.PREF_FUNDS_LEVEL, pref.getInt(Common.PREF_FUNDS_LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_INITIALFOUNDS, pref.getInt(Common.PREF_INITIALFOUNDS, 10) + pref.getInt(Common.PREF_STORE_FUNDSUP, 5));
                            break;
                        case 100:
                            prefEditor.putInt(Common.PREF_STORE_TOWER000LEVEL, pref.getInt(Common.PREF_STORE_TOWER000LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER000BONUS, pref.getInt(Common.PREF_STORE_TOWER000BONUS, 0) + 5);
                            break;
                        case 101:
                            prefEditor.putInt(Common.PREF_STORE_TOWER001LEVEL, pref.getInt(Common.PREF_STORE_TOWER001LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER001BONUS, pref.getInt(Common.PREF_STORE_TOWER001BONUS, 0) + 5);
                            break;
                        case 102:
                            prefEditor.putInt(Common.PREF_STORE_TOWER002LEVEL, pref.getInt(Common.PREF_STORE_TOWER002LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER002BONUS, pref.getInt(Common.PREF_STORE_TOWER002BONUS, 0) + 5);
                            break;
                        case 111:
                            prefEditor.putInt(Common.PREF_STORE_TOWER011LEVEL, pref.getInt(Common.PREF_STORE_TOWER011LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER011BONUS, pref.getInt(Common.PREF_STORE_TOWER011BONUS, 0) + 5);
                            break;
                        case 112:
                            prefEditor.putInt(Common.PREF_STORE_TOWER012LEVEL, pref.getInt(Common.PREF_STORE_TOWER012LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER012BONUS, pref.getInt(Common.PREF_STORE_TOWER012BONUS, 0) + 5);
                            break;
                        case 121:
                            prefEditor.putInt(Common.PREF_STORE_TOWER021LEVEL, pref.getInt(Common.PREF_STORE_TOWER021LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER021BONUS, pref.getInt(Common.PREF_STORE_TOWER021BONUS, 0) + 5);
                            break;
                        case 122:
                            prefEditor.putInt(Common.PREF_STORE_TOWER022LEVEL, pref.getInt(Common.PREF_STORE_TOWER022LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER022BONUS, pref.getInt(Common.PREF_STORE_TOWER022BONUS, 0) + 5);
                            break;
                        case 131:
                            prefEditor.putInt(Common.PREF_STORE_TOWER031LEVEL, pref.getInt(Common.PREF_STORE_TOWER031LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER031BONUS, pref.getInt(Common.PREF_STORE_TOWER031BONUS, 0) + 5);
                            break;
                        case 132:
                            prefEditor.putInt(Common.PREF_STORE_TOWER032LEVEL, pref.getInt(Common.PREF_STORE_TOWER032LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_TOWER032BONUS, pref.getInt(Common.PREF_STORE_TOWER032BONUS, 0) + 5);
                            break;
                            
                        case 200:
                            prefEditor.putInt(Common.PREF_STORE_SKILL000LEVEL, pref.getInt(Common.PREF_STORE_SKILL000LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_SKILL000COUNT, pref.getInt(Common.PREF_STORE_SKILL000COUNT, 1) + 1);
                            break;
                        case 201:
                            prefEditor.putInt(Common.PREF_STORE_SKILL001LEVEL, pref.getInt(Common.PREF_STORE_SKILL001LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_SKILL001COUNT, pref.getInt(Common.PREF_STORE_SKILL001COUNT, 1) + 1);
                            break;
                        case 202:
                            prefEditor.putInt(Common.PREF_STORE_SKILL002LEVEL, pref.getInt(Common.PREF_STORE_SKILL002LEVEL, 1) + 1);
                            prefEditor.putInt(Common.PREF_STORE_SKILL002COUNT, pref.getInt(Common.PREF_STORE_SKILL002COUNT, 1) + 1);
                            break;
                    }
                    
                    
                    prefEditor.putLong(Common.PREF_ASSET, pref.getLong(Common.PREF_ASSET, 0) - listcost);
                    prefEditor.commit();
                    
                    TextView textAsset = (TextView)getActivity().findViewById( R.id.text_storeasset );
                    textAsset.setText(String.valueOf(pref.getLong(Common.PREF_ASSET,0)));
                    ImageView imageStand = (ImageView)getActivity().findViewById( R.id.image_storestand );
                    imageStand.setBackgroundResource(R.drawable.stand002);
                    storeFragment.setDataList();
                    storeFragment.getStorelistAdapter().notifyDataSetChanged();
                    storeFragment.playSound("buy");

                    dismiss();
                }
                
            });
            
            content.findViewById(R.id.dialog_no_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
                
            });
            return content;
            
            // ストアの資産がたりない
        } else if (this.getTag() == "StoreNot") {
            
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);
            
            title.setText(getActivity().getResources().getString(R.string.store_title));
            message.setText(getActivity().getResources().getString(R.string.message_NotFunds));
            
            ImageView imageStand = (ImageView)getActivity().findViewById( R.id.image_storestand );
            imageStand.setBackgroundResource(R.drawable.stand003);
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
                
            });
            return content;
            
            // ストアのすでにMAX
        } else if (this.getTag() == "StoreMAX") {
            
            View content = inflater.inflate(R.layout.custom_dialog, null); 
            
            title = (TextView) content.findViewById(R.id.dialog_title);
            message = (TextView) content.findViewById(R.id.dialog_description);

            title.setText(getActivity().getResources().getString(R.string.store_title));
            message.setText(getActivity().getResources().getString(R.string.message_Maximum));
            
            ImageView imageStand = (ImageView)getActivity().findViewById( R.id.image_storestand );
            imageStand.setBackgroundResource(R.drawable.stand003);
            
            content.findViewById(R.id.dialog_ok_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
                
            });
            
            content.findViewById(R.id.dialog_close_button).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dismiss();
                }
                
            });
            return content;
        }
        
        return null;

    }

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        
        // ダイアログのタイトルバーを消す
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        
        // ダイアログの画面がいタッチで消えない
        dialog.setCanceledOnTouchOutside(false);
        
        return dialog;
    }
    
    private int listid;
    private int listcost;
    public void setBuy(int id, int cost) {
        this.listid = id;
        this.listcost = cost;
    }

    private StoreFragment storeFragment;
    public void setArguments(StoreFragment storeFragment) {
        // TODO Auto-generated method stub
        this.storeFragment = storeFragment;

    }
    
    private Player player;
    public void setArguments(com.example.defense_souls.Player player) {
        // TODO Auto-generated method stub
        this.player = player;
    }
    
    
}
