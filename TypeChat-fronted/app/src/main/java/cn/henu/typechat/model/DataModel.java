package cn.henu.typechat.model;

import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("success")
    private boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private Datasub data;


    public Datasub getLogindata() {
        return data;
    }

    public void setLogindata(Datasub logindata) {
        this.data = logindata;
    }



    public Datasub getData() {
        return data;
    }

    public void setData(Datasub data) {
        this.data = data;
    }

    // Constructor
    public DataModel() {}

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}