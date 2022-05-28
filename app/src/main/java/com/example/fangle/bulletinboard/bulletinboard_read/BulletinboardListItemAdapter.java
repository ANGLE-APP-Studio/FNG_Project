package com.example.fangle.bulletinboard.bulletinboard_read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.writing.writing_read.WritingListItem;

import java.util.ArrayList;

public class BulletinboardListItemAdapter extends BaseAdapter {

    ArrayList<BulletinborardListItem> items = new ArrayList<BulletinborardListItem>();
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
        BulletinborardListItem listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bulletinborard_list_item, parent, false);
        }

        TextView board_name = convertView.findViewById(R.id.board_name);

        board_name.setText(listItem.getBoard_name());

        return convertView;
    }

    public void addItem(BulletinborardListItem item){
        items.add(item);
    }

}
