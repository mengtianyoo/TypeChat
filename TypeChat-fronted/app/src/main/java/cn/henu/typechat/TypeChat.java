package cn.henu.typechat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import cn.henu.typechat.viewmodel.TypeChatLogin;
import cn.henu.typechat.viewmodel.TypeChatHome;

public class TypeChat extends AppCompatActivity {
    private static final int DELAY_TIME = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typechat);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if user info exists in SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
                int userId = sharedPreferences.getInt("userId", -1);
                Intent intent;
                if (userId != -1) {
                    // User info exists, navigate to MainActivity
                    intent = new Intent(TypeChat.this, TypeChatHome.class);
                    intent.putExtra("userId", userId);
                } else {
                    // User info does not exist, navigate to LoginActivity
                    intent = new Intent(TypeChat.this, TypeChatLogin.class);
                }
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);
    }
}