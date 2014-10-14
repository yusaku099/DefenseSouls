package com.example.defense_souls;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusListAdapter extends BaseAdapter {
    private Context context;
    private List<?> items;
    
    public StatusListAdapter(Context context, List<?> items){
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
             customItemView = inflater.inflate(R.layout.status_list, parent, false);
             Log.d(this.getClass().getName(), "created");
        } else {
             customItemView = convertView;
             Log.d(this.getClass().getName(), "recycled");
        }
        
        ImageView iconImageView = (ImageView)customItemView.findViewById(R.id.image_statusicon);
        TextView titleTextView = (TextView)customItemView.findViewById(R.id.nameTextView);
     
        StatusListItem item = (StatusListItem) items.get(position);
        iconImageView.setBackgroundResource(item.getImageNo());
        titleTextView.setText(item.getTitle());
        customItemView.setBackgroundResource(R.drawable.orderlist_template);
        
        return customItemView;
    }

}
