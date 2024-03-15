package cn.henu.typechat.dto;

public class ResponseDto<T> {
    private final boolean success;
    private final String message;
    private final String code;
    private final T data;

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
