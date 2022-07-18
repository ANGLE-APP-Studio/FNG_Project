package com.example.fangle.writing.writing_read;

public class WritingListItem {

    private String writing_title = "";
    private String writing = "";
    private String nickname = "";
    private String date_created = "";

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate_Created() {
        return date_created;
    }

    public void setDate_Created(String date_created) {
        this.date_created = date_created;
    }

    public String getWriting_title() {
        return writing_title;
    }

    public void setWriting_title(String writing_title) {
        this.writing_title = writing_title;
    }

    public WritingListItem(String writing,String nickname,String date_created,String writing_title){
        this.nickname = nickname;
        this.writing = writing;
        this.date_created = date_created;
        this.writing_title = writing_title;
    }

}

