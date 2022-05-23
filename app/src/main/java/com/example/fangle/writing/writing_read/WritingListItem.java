package com.example.fangle.writing.writing_read;

public class WritingListItem {
    private String writing;
    private String nickname;

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
    WritingListItem(String writing,String nickname){
        this.nickname = nickname;
        this.writing = writing;
    }
}
