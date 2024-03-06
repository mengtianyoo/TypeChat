package cn.henu.typechat.service;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import cn.henu.typechat.dto.ResponseDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ApiManager {

    private static final String BASE_URL = "http://192.168.58.132:9999/"; // 你的后端基础URL

    private final ApiService apiService;

    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public <T> void performRequest(String url, Map<String, String> data, final ApiResponseCallback<T> callback) {
        Call<ResponseBody> call = apiService.performRequest(url, data);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        // 解析 responseBody 成为你的 ResponseDto 类
                        // 在这里，你需要根据实际情况使用 JSON 解析库，比如 Gson
                        // 假设使用 Gson 来解析，你可以这样做：
                        // Gson gson = new Gson();
                        // T responseObject = gson.fromJson(responseBody, YourResponseDtoClass.class);
                        // 然后调用回调
                        // callback.onSuccess(responseObject);
                        // 由于没有提供 ResponseDto 的具体定义，这里只是简单的打印响应
                        Log.d("ApiManager", "Response: " + responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onError("Error parsing response");
                    }
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ApiManager", "Network error: " + t.getMessage());
                callback.onError("Network error");
            }
        });
    }
    public void register(Map<String, String> data, final ApiResponseCallback<ResponseDto<?>> callback) {
        Call<ResponseBody> call = apiService.performRequest("ddchat/index/register", data);
        enqueueCall(call, callback);
    }

    public void login(Map<String, String> data, final ApiResponseCallback<ResponseDto<JSONObject>> callback) {
        Call<ResponseBody> call = apiService.performRequest("ddchat/index/login", data);
        enqueueCall(call, callback);
    }

    private <T> void enqueueCall(Call<ResponseBody> call, final ApiResponseCallback<T> callback) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        // 解析 responseBody 成为你的 ResponseDto 类
                        // 在这里，你需要根据实际情况使用 JSON 解析库，比如 Gson
                        // 假设使用 Gson 来解析，你可以这样做：
                        // Gson gson = new Gson();
                        // T responseObject = gson.fromJson(responseBody, YourResponseDtoClass.class);
                        // 然后调用回调
                        // callback.onSuccess(responseObject);

                        // 由于没有提供 ResponseDto 的具体定义，这里只是简单的打印响应
                        Log.d("ApiManager", "Response: " + responseBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                        callback.onError("Error parsing response");
                    }
                } else {
                    callback.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ApiManager", "Network error: " + t.getMessage());
                callback.onError("Network error");
            }
        });
    }

}

