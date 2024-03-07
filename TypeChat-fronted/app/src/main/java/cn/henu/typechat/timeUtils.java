package cn.henu.typechat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class timeUtils {
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}

