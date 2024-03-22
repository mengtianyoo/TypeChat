package cn.henu.typechat.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.henu.typechat.model.UploadResponse;
import cn.henu.typechat.service.ApiClient;
import cn.henu.typechat.service.ApiInterface;
import cn.henu.typechat.viewmodel.UpdateUserInfoActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImageUploadUtil {

    private static final String TAG = "ImageUploadUtil";

    private static final String MEDIA_TYPE_IMAGE = "image/*";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private Context mContext;
    private Retrofit mRetrofit;
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    public void uploadImage(String filePath, int userId, UpdateUserInfoActivity activity) {
        // 检查是否已经具有权限
        final String imageurl = null;
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        RequestBody requestFile = RequestBody.create(MediaType.parse(MEDIA_TYPE_IMAGE), file);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        RequestBody userIdPart = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(userId));

        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("userId", userIdPart);

        Call<UploadResponse> call = apiService.uploadImage(imagePart, requestBodyMap);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadResponse> call, @NonNull Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    UploadResponse uploadResponse = response.body();
                    if (uploadResponse != null && uploadResponse.isSuccess()) {
                        String imageUrl = uploadResponse.getData().getCompressUrl();
                        Log.d(TAG, "Upload successful. Image URL: " + uploadResponse.getData().getCompressUrl());
                        activity.setImageURL(imageUrl);
                    }
                } else {
                    Log.e(TAG, "Upload failed. Code: " + response.code());
                }

            }

            @Override
            public void onFailure(@NonNull Call<UploadResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "Upload failed. Error: " + t.getMessage());
            }
        });

    }
}

