package com.example.fangle.community.community_data;

public class CommunityData {

    private String community_name = "";

    public CommunityData(){
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    private static CommunityData instance = null;

    public static synchronized CommunityData getInstance(){
        if(null == instance){
            instance = new CommunityData();
        }
        return instance;
    }

}
