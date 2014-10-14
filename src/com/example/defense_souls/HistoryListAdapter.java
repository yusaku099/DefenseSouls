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

public class HistoryListAdapter extends BaseAdapter {
    private Context context;
    private List<?> items;
    
    public HistoryListAdapter(Context context, List<?> items){
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
             customItemView = inflater.inflate(R.layout.history_list, parent, false);
             Log.d(this.getClass().getName(), "created");
        } else {
             customItemView = convertView;
             Log.d(this.getClass().getName(), "recycled");
        }
        
        ImageView iconImageView = (ImageView)customItemView.findViewById(R.id.image_locationicon);
        TextView titleTextView = (TextView)customItemView.findViewById(R.id.text_locationName);
        TextView dateTextView = (TextView)customItemView.findViewById(R.id.text_resultdate);
        TextView resultTextView = (TextView)customItemView.findViewById(R.id.text_scoreresult);

        HistoryListItem item = (HistoryListItem) items.get(position);
        iconImageView.setBackgroundResource(item.getImageNo());
        titleTextView.setText(item.getTitle());
        
        dateTextView.setText(String.valueOf(item.getDate()));
        resultTextView.setText(item.getResult());
        customItemView.setBackgroundResource(R.drawable.historylist_template);
        
        return customItemView;
    }

}
