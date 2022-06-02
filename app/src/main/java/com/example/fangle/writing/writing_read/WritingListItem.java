package com.example.fangle.writing.writing_read;

public class WritingListItem {
    private String writing;
    private String nickname;
    private String date_created;

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

    WritingListItem(String writing,String nickname,String date_created){
        this.nickname = nickname;
        this.writing = writing;
        this.date_created = date_created;
    }
}
