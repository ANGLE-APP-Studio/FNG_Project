package com.example.fangle.comment.comment_read;

public class CommentListItem {
    private String userID;
    private String comment;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentListItem(String userID, String comment) {
        this.userID = userID;
        this.comment = comment;
    }
}
