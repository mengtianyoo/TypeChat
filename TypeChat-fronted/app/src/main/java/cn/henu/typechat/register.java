package cn.henu.typechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.henu.typechat.mode.User;

public class register extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextNickname;
    private EditText editTextGender;
    private EditText editTextPassword1;
    private EditText editTextPassword2;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://typechat-1c3d4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();


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

        // 获取Firebase数据库引用
        mDatabase =  FirebaseDatabase.getInstance("https://typechat-1c3d4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        // 设置注册按钮点击事件
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的注册信息
                final String email = editTextEmail.getText().toString().trim();
                final String nickname = editTextNickname.getText().toString().trim();
                final String gender = editTextGender.getText().toString().trim();
                String password = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();

                // 简单的验证逻辑，确保密码一致
                if (!password.equals(password2)) {
                    Toast.makeText(register.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase.child("users").orderByChild("nickname").equalTo(nickname)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Toast.makeText(register.this, "该昵称已被使用，请选择其他昵称", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Check if the email is already in use
                                    mDatabase.child("users").orderByChild("email").equalTo(email)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.exists()) {
                                                        Toast.makeText(register.this, "该邮箱已被注册，请选择其他邮箱", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        // Create user object
                                                        User user = new User(email, nickname, gender, password);

                                                        // Store user information in Firebase Realtime Database
                                                        mDatabase.child("users").child(email.replace(".", "_")).setValue(user);

                                                        // Registration successful, navigate to the chat activity
                                                        Intent intent = new Intent(register.this, ChatActivity.class);
                                                        startActivity(intent);
                                                        finish(); // End the current registration page
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    // Handle database error
                                                    Toast.makeText(register.this, "数据库错误：" + error.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle database error
                                Toast.makeText(register.this, "数据库错误：" + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
