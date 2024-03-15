package cn.henu.typechat.viewmodel;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import cn.henu.typechat.R;
import cn.henu.typechat.model.DataModel;
import cn.henu.typechat.model.DataModel2;
import cn.henu.typechat.model.RegisterRequest;
import cn.henu.typechat.model.User;
import cn.henu.typechat.service.ApiClient;
import cn.henu.typechat.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeChatRegister extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText nicknameEditText;
    private RadioGroup gendergroup;
    private TextView tologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typechat_register);

        emailEditText = findViewById(R.id.registereamil);
        passwordEditText = findViewById(R.id.editTextPassword1);
        confirmPasswordEditText = findViewById(R.id.editTextPassword2);
        nicknameEditText = findViewById(R.id.editTextNickname);
        gendergroup = findViewById(R.id.gender);

        Button registerbuttton = findViewById(R.id.buttonRegister);

        tologin = findViewById(R.id.tologin);
        tologin.setOnClickListener(v -> {
            Intent intent = new Intent(TypeChatRegister.this, TypeChatLogin.class);
            startActivity(intent);
        });
        registerbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                // 获取用户输入的电子邮件、密码、昵称和性别
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String nickname = nicknameEditText.getText().toString();
                int selceted = gendergroup.getCheckedRadioButtonId();
                String gender = selceted == R.id.radioButtonMale ? "男" : "女";

                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setEmail(email);
                registerRequest.setNickname(nickname);
                registerRequest.setGender(gender);
                registerRequest.setPassword1(password);
                registerRequest.setPassword2(confirmPassword);

                Call<DataModel2> call = apiService.register(registerRequest);
                call.enqueue(new Callback<DataModel2>() {
                    @Override
                    public void onResponse(Call<DataModel2> call, Response<DataModel2> response) {
                        if (response.isSuccessful()) {
                            DataModel2 data = response.body();
                            if (data.isSuccess()) {
                                // 登录成功，跳转到主界面
                                Toast.makeText(TypeChatRegister.this, data.getData(), Toast.LENGTH_SHORT).show();
                                Log.d("TypeChatregister", "register success: " + new Gson().toJson(data.getData()));
                                navigateTologin();
                            } else {
                                // 登录失败，显示错误消息
                                Toast.makeText(TypeChatRegister.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // 处理请求失败的情况
                            Toast.makeText(TypeChatRegister.this, "Login failed. Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<DataModel2> call, Throwable t) {
                        // 处理网络请求失败的情况
                        Toast.makeText(TypeChatRegister.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void navigateTologin() {
        Intent intent = new Intent(TypeChatRegister.this, TypeChatHome.class);
        startActivity(intent);
        finish();
    }
}