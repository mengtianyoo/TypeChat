package cn.henu.typechat.model;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private UploadData data;

    @SerializedName("success")
    private boolean success;

    public String getCode() {
        return code;
    }

    public UploadData getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public static class UploadData {
        @SerializedName("compressUrl")
        private String compressUrl;

        @SerializedName("url")
        private String url;

        public String getCompressUrl() {
            return compressUrl;
        }

        public String getUrl() {
            return url;
        }
    }
}
