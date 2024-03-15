package cn.henu.typechat.model;

import com.google.gson.annotations.SerializedName;

public class Datasub {
    @SerializedName("userInfo")
    private User userinfo;
    @SerializedName("token")
    private String token;


    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUserinfo() {
        return userinfo;
    }

}
