package cn.henu.typechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.henu.typechat.mode.User;

public class login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://typechat-1c3d4-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化视图
        editTextEmail = findViewById(R.id.editTextEmails);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取用户输入的邮箱和密码
                final String email = editTextEmail.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

                // 查询Firebase Realtime Database中是否存在对应的用户信息
                mDatabase.child("users").child(email.replace(".", "_")).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // 用户存在，检查密码是否正确
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null && user.getPassword().equals(password)) {
                                // 密码正确，登录成功，跳转到聊天界面
                                Intent intent = new Intent(login.this, ChatActivity.class);
                                startActivity(intent);
                                finish(); // 结束当前登录页面
                            } else {
                                // 密码错误
                                Toast.makeText(login.this, "密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // 用户不存在
                            Toast.makeText(login.this, "用户不存在", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 读取数据失败
                        Toast.makeText(login.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // 跳转到注册页面
        TextView toRegister = findViewById(R.id.toregister);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
