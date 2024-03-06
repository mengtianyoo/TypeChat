package cn.henu.typechat.service;

public interface ApiResponseCallback<T> {
    void onSuccess(T response);
    void onError(String errorMessage);
}

