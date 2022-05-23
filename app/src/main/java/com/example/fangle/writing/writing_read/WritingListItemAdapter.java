package com.example.fangle.writing.writing_read;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fangle.R;

import java.util.ArrayList;

public class WritingListItemAdapter extends BaseAdapter {
    ArrayList<WritingListItem> items = new ArrayList<WritingListItem>();
    Context context;
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        WritingListItem listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.writing_list_item, parent, false);
        }

        TextView writing = convertView.findViewById(R.id.writing);
        TextView nickname = convertView.findViewById(R.id.nickname);

        nickname.setText(listItem.getNickname());
        writing.setText(listItem.getWriting());
        return convertView;
    }

    public void addItem(WritingListItem item){
        items.add(item);
    }
}