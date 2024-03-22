package cn.henu.typechat.service;

import java.util.Map;

import cn.henu.typechat.model.DataModel;
import cn.henu.typechat.model.DataModel2;
import cn.henu.typechat.model.DataModelInfo;
import cn.henu.typechat.model.LoginRequest;
import cn.henu.typechat.model.RegisterRequest;
import cn.henu.typechat.model.UploadResponse;
import cn.henu.typechat.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("/typechat/index/register")
    Call<DataModel2> register(@Body RegisterRequest registerRequest);
    @POST("/typechat/index/login")
    Call<DataModel> login(@Body LoginRequest loginRequest);

    @POST("/typechat/user/info")
    Call<DataModelInfo> getInfo(@Body User user);

    @Multipart
    @POST("/typechat/image/uploadAvatar")
    Call<UploadResponse> uploadImage(@Part MultipartBody.Part image, @PartMap Map<String, RequestBody> userId);

    @POST("/typechat/user/update")
    Call<User> updateUser(@Body User user);
}