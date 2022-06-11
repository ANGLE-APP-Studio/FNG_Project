package com.example.fangle.announcement.announcement_read;

import android.graphics.Bitmap;

public class AnnounCementListItem {
    private String announcement_title;
    private String Date_Created;
    private int announcement_image;

    public String getAnnouncement_title() {
        return announcement_title;
    }

    public void setAnnouncement_title(String announcement_title) {
        this.announcement_title = announcement_title;
    }

    public String getDate_Created() {
        return Date_Created;
    }

    public void setDate_Created(String date_Created) {
        Date_Created = date_Created;
    }

    public int getAnnouncement_image() {
        return announcement_image;
    }

    public void setAnnouncement_image(int announcement_image) {
        this.announcement_image = announcement_image;
    }

    public AnnounCementListItem(String announcement_title, String Date_Created, int announcement_image){
        this.announcement_title = announcement_title;
        this.Date_Created = Date_Created;
        this.announcement_image = announcement_image;
    }
}
