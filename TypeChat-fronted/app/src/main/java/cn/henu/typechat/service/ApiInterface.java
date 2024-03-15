package cn.henu.typechat.service;

import cn.henu.typechat.model.DataModel;
import cn.henu.typechat.model.DataModel2;
import cn.henu.typechat.model.LoginRequest;
import cn.henu.typechat.model.RegisterRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("/typechat/index/register")
    Call<DataModel2> register(@Body RegisterRequest registerRequest);
    @POST("/typechat/index/login")
    Call<DataModel> login(@Body LoginRequest loginRequest);
}