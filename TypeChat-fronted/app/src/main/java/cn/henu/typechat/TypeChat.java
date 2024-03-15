package cn.henu.typechat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import cn.henu.typechat.viewmodel.TypeChatLogin;

public class TypeChat extends AppCompatActivity {
    private static final int DELAY_TIME = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typechat);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TypeChat.this, TypeChatLogin.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME);
    }
}
