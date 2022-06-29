package com.example.fangle.account.user_data;

import android.app.Application;

public class UserData  {
    public String userID;
    public String userPW;
    public int sign=0; // 0 이면 이메일 로그인  1이면 구글 로그인

    public UserData(){}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String reset(){
        userID = "";
        userPW = "";
        return "";
    }

    public UserData(String userID,String userPW , int sign){
        this.userID = userID;
        this.userPW = userPW;
        this.sign = 0;
    }

    private static UserData instance = null;

    public static synchronized UserData getInstance(){
        if(null == instance){
            instance = new UserData();
        }
        return instance;
    }

}
