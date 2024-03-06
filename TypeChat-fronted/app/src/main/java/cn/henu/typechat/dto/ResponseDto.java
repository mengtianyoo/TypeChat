package cn.henu.typechat.dto;

public class ResponseDto<T> {
    private boolean success;
    private String message;
    private String code;
    private T data;

    public ResponseDto(boolean success, String message, String code, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
