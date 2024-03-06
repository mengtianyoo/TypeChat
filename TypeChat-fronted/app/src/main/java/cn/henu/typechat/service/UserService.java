package cn.henu.typechat.service;

import org.json.JSONObject;

import java.util.Map;

import cn.henu.typechat.dto.ResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/ddchat/index/login")
    Call<ResponseDto<JSONObject>> login(@Body Map<String, String> params);

    @POST("/ddchat/index/register")
    Call<ResponseDto<?>> register(@Body Map<String, String> params);
}

