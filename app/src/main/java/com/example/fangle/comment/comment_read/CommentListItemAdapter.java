package com.example.fangle.comment.comment_read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fangle.R;
import com.example.fangle.writing.writing_read.WritingListItem;

import java.util.ArrayList;

public class CommentListItemAdapter extends BaseAdapter {
    ArrayList<CommentListItem> items = new ArrayList<CommentListItem>();
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
        CommentListItem listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment_list_item, parent, false);
        }

        TextView userId = convertView.findViewById(R.id.userID);
        TextView comment = convertView.findViewById(R.id.comment);

        userId.setText(listItem.getUserID());
        comment.setText(listItem.getComment());

        return convertView;
    }

    public void addItem(CommentListItem item){
        items.add(item);
    }
}
