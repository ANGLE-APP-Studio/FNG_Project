package com.example.fangle.bulletinboard.bulletinboard_read;

public class BulletinborardListItem {

    private String board_name = "";
    private String rng = "";

    public BulletinborardListItem(){}

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getRng() {
        return rng;
    }

    public void setRng(String rng) {
        this.rng = rng;
    }

    public BulletinborardListItem(String board_name){
        this.board_name = board_name;
    }
    public BulletinborardListItem(String board_name, String rng){
        this.board_name = board_name;
        this.rng = rng;
    }
}