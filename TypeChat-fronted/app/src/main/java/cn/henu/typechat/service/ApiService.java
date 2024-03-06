package cn.henu.typechat.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @POST
    Call<ResponseBody> performRequest(@Url String url, @Body Map<String, String> data);
}

