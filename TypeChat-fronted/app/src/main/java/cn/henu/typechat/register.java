package cn.henu.typechat;

// RegisterActivity.java
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;


public class register extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextNickname;
    private EditText editTextGender;
    private EditText editTextPassword1;
    private EditText editTextPassword2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 初始化视图
        editTextEmail = findViewById(R.id.editrigEmail);
        editTextNickname = findViewById(R.id.editTextNickname);
        editTextGender = findViewById(R.id.editTextGender);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        // 设置注册按钮点击事件
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的注册信息
                String email = editTextEmail.getText().toString().trim();
                String nickname = editTextNickname.getText().toString().trim();
                String gender = editTextGender.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();

                // 构造请求参数
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("nickname", nickname);
                params.put("gender", gender);
                params.put("password1", password1);
                params.put("password2", password2);

                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);

            }
        });

    }
}
