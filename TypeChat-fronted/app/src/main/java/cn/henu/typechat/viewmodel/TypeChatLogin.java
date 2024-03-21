package cn.henu.typechat.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import cn.henu.typechat.login;
import cn.henu.typechat.model.DataModel;
import cn.henu.typechat.model.LoginRequest;
import cn.henu.typechat.register;
import cn.henu.typechat.service.ApiClient;
import cn.henu.typechat.R;
import cn.henu.typechat.model.User;
import cn.henu.typechat.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeChatLogin extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private User userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typechat_login);

        emailEditText = findViewById(R.id.loginemail);
        passwordEditText = findViewById(R.id.editTextPassword);

        Button loginButton = findViewById(R.id.buttonLogin);

        TextView toregister = findViewById(R.id.toregister);
        toregister.setOnClickListener(v -> {
            Intent intent = new Intent(TypeChatLogin.this, TypeChatRegister.class);
            startActivity(intent);
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的电子邮件和密码
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                // 创建登录请求数据
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(email);
                loginRequest.setPassword(password);

                // 执行登录请求
                Call<DataModel> call = apiService.login(loginRequest);
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        if (response.isSuccessful()) {
                            DataModel data = response.body();
                            if (data != null && data.isSuccess()) {
                                Log.d("TypeChatLogin", "Login success: " + new Gson().toJson(data.getData().getUserinfo().getEmail()));
                                navigateToMainActivity(data);
                            } else {
                                // 登录失败，显示错误消息
                                Toast.makeText(TypeChatLogin.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // 处理请求失败的情况
                            Toast.makeText(TypeChatLogin.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        // 处理网络请求失败的情况
                        Toast.makeText(TypeChatLogin.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    private void navigateToMainActivity(DataModel data) {
        // 示例代码：跳转到主界面
        Intent intent = new Intent(TypeChatLogin.this, TypeChatHome.class);
        // 如果需要传递用户信息或令牌到 MainActivity，可以使用 Intent.putExtra() 方法
        intent.putExtra("userId", data.getData().getUserinfo().getId());
        startActivity(intent);
        // 关闭当前登录界面
        finish();
    }
}
