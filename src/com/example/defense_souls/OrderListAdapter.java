package com.example.defense_souls;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderListAdapter extends BaseAdapter {
    private Context context;
    private List<?> items;
    
    public OrderListAdapter(Context context, List<?> items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View customItemView = null; 
        if (convertView == null) {
             LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             customItemView = inflater.inflate(R.layout.order_list, parent, false);
             Log.d(this.getClass().getName(), "created");
        } else {
             customItemView = convertView;
             Log.d(this.getClass().getName(), "recycled");
        }
        
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        ImageView iconImageView = (ImageView)customItemView.findViewById(R.id.image_ordericon);
        TextView titleTextView = (TextView)customItemView.findViewById(R.id.order_stageTextView);
        TextView descriptionTextView = (TextView)customItemView.findViewById(R.id.order_descriptionTextView);
        TextView levelTextView = (TextView)customItemView.findViewById(R.id.order_levelTextView);
     
        OrderListItem item = (OrderListItem) items.get(position);
        iconImageView.setBackgroundResource(item.getImageNo());
        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());
        levelTextView.setText(item.getLevel());
        
        if (item.getListId() == pref.getInt(Common.PREF_STAGE_NO, 1)) {
            customItemView.setBackgroundResource(R.drawable.storelistred_template);
        } else {
            customItemView.setBackgroundResource(R.drawable.orderlist_template);
        }
        
        
        return customItemView;
    }

}
