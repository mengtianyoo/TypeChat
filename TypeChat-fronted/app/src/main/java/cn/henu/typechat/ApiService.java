package cn.henu.typechat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/get_answer")  // 你的服务器端聊天接口
    Call<ChatResponse> sendMessage(@Body ChatRequest request);
}




