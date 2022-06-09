package com.example.fangle.announcement.announcement_read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.bulletinboard.bulletinboard_read.BulletinborardListItem;
import com.example.fangle.writing.writing_read.WritingListItem;

import java.util.ArrayList;

public class AnnounCementListItemAdapter extends BaseAdapter {

    ArrayList<AnnounCementListItem> items = new ArrayList<AnnounCementListItem>();
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
        AnnounCementListItem listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.announcement_list_item, parent, false);
        }

        TextView announcement_title = convertView.findViewById(R.id.announcement_title);
        TextView date_created = convertView.findViewById(R.id.Date_Created);
        ImageView announcement_image = convertView.findViewById(R.id.announcement_image);

        announcement_title.setText(listItem.getAnnouncement_title());
        date_created.setText(listItem.getDate_Created());
        announcement_image.setImageResource(listItem.getAnnouncement_image());

        return convertView;
    }

    public void addItem(AnnounCementListItem item){
        items.add(item);
    }
}
