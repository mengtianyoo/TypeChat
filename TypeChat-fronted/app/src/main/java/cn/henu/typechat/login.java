package cn.henu.typechat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView toregister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图
        editTextEmail = findViewById(R.id.editTextEmails);
        editTextPassword = findViewById(R.id.editTextPassword);
        toregister = findViewById(R.id.toregister);

        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,ChatActivity.class);
                startActivity(intent);
            }
        });

//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                String email = editTextEmail.getText().toString().trim();
////                String password = editTextPassword.getText().toString().trim();
////
////                Map<String, String> params = new HashMap<>();
////                params.put("email", email);
////                params.put("password", password);
//                Intent intent = new Intent(login.this, ChatActivity.class);
//                startActivity(intent);
//
//            }
//        });

        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图以启动注册页面
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

    }
}
