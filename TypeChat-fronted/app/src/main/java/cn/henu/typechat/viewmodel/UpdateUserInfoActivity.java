package cn.henu.typechat.viewmodel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.henu.typechat.R;
import cn.henu.typechat.model.UploadResponse;
import cn.henu.typechat.model.User;
import cn.henu.typechat.service.ApiClient;
import cn.henu.typechat.service.ApiInterface;
import cn.henu.typechat.util.ImageUploadUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserInfoActivity extends AppCompatActivity  {
    private static final int PICK_IMAGE_REQUEST_CODE = 200;
    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    private EditText nicknameEditText;
    private EditText introductionEditText;
    private RadioGroup gendergroup;
    private EditText birthdayEditText;
    private Button submitButton;
    private ImageView avatarImageView;
    private Button changeAvatarButton;
    private String imageurl;

    private static final String TAG = "ImageUploadUtil";

    private static final String MEDIA_TYPE_IMAGE = "image/*";

    ImageUploadUtil imageUploadUtil = new ImageUploadUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        nicknameEditText = findViewById(R.id.nicknameEditText);
        introductionEditText = findViewById(R.id.introductionEditText);
        gendergroup = findViewById(R.id.genderEditText);
        birthdayEditText = findViewById(R.id.birthdayEditText);
        birthdayEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        avatarImageView = findViewById(R.id.avatarImageView);
        changeAvatarButton = findViewById(R.id.changeAvatarButton);
        submitButton = findViewById(R.id.submitButton);



        changeAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Permission is not granted
                if (ContextCompat.checkSelfPermission(UpdateUserInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(UpdateUserInfoActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                else {
                        // Permission has already been granted

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
                    }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    // 请求存储权限

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Log.d("UpdateUserInfoActivity", "Permission has already been granted");
            Uri selectedImageUri = data.getData();
            avatarImageView.setImageURI(selectedImageUri);
            int userId = getIntent().getIntExtra("userId", -1);
            imageUploadUtil.uploadImage(getRealPathFromUri(selectedImageUri), userId, this);

            // TODO: Upload the new avatar to the server
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
    }

    private void showDatePickerDialog() {
        // Get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Display the selected date in birthdayEditText
                        birthdayEditText.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        // Show the dialog
        datePickerDialog.show();
    }

    private void updateUser() {
        String nickname = nicknameEditText.getText().toString().trim();
        if (nickname.isEmpty()) {
            // Display error message if username is empty
            Toast.makeText(UpdateUserInfoActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return; // Exit the method
        }
        String introduction = introductionEditText.getText().toString();
        int gender = gendergroup.getCheckedRadioButtonId();
        int userId = getIntent().getIntExtra("userId", -1);
        String birthday = birthdayEditText.getText().toString();
        User user = new User();
        user.setAvatar(imageurl);
        user.setId(userId);
        user.setNickname(nickname);
        user.setIntroduction(introduction);
        user.setGender(gender);
        user.setBirthday(birthday);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiService.updateUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateUserInfoActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateUserInfoActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(UpdateUserInfoActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setImageURL(String imageUrl) {
        String baseUrl = ApiClient.getBaseUrl();
        String imageUrl1 = imageUrl;
        String xiegang = "/";
        imageurl = baseUrl +xiegang+imageUrl1;

    }
}
