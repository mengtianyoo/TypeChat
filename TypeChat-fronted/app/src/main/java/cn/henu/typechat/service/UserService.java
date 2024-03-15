package cn.henu.typechat.service;

import org.json.JSONObject;

import java.util.Map;

import cn.henu.typechat.dto.ResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("/typechat/index/login")
    Call<ResponseDto<JSONObject>> login(@Body Map<String, String> params);
    @POST("/typechat/index/register")
    Call<ResponseDto<JSONObject>> register(@Body Map<String, String> params);
}

