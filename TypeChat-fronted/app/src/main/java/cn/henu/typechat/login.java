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

import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.henu.typechat.dto.ResponseDto;
import cn.henu.typechat.service.ApiManager;
import cn.henu.typechat.service.ApiResponseCallback;
import cn.henu.typechat.service.UserService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

            }
        });

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
