package cn.henu.typechat.viewmodel;

import static android.content.Context.MODE_PRIVATE;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Calendar;

import cn.henu.typechat.R;
import cn.henu.typechat.model.DataModelInfo;
import cn.henu.typechat.model.User;
import cn.henu.typechat.service.ApiClient;
import cn.henu.typechat.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeChatInfo extends Fragment {
    private TextView nicknameView;
    private TextView idView;
    private TextView instructionView;
    private TextView nickname2View;
    private TextView genderView;
    private TextView ageView;
    private TextView dobView;
    private TextView mailView;
    private ImageView avatarView;
    private TypeChatInfoViewModel mViewModel;

    public static TypeChatInfo newInstance() {
        return new TypeChatInfo();
    }
    @Override
    public void onResume() {
        super.onResume();
        refreshUserInfo();
    }

    private void refreshUserInfo() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Bundle arguments = getArguments();
        if (arguments != null) {
            int userId = arguments.getInt("userId");
            User user = new User();
            user.setId(userId);
            Call<DataModelInfo> call =apiService.getInfo(user);

            call.enqueue(new Callback<DataModelInfo>() {

                @Override
                public void onResponse(Call<DataModelInfo> call, Response<DataModelInfo> response) {
                    DataModelInfo dataModelInfo = response.body();
                    Log.d("TypeChatInfo", "Get user info success: " + dataModelInfo.getData().getNickname());
                    if (dataModelInfo.isSuccess()) {
                        // Update views with user info
                        Log.d("TypeChatInfo", "Get user info success: " + dataModelInfo.getData().getNickname() + isAdded());
                        String birthday = dataModelInfo.getData().getBirthday(); // Assuming the format is "yyyy-MM-dd"
                        int birthYear = Integer.parseInt(birthday.substring(0, 4)); // Extract the year from the birthday
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR); // Get the current year
                        int age = currentYear - birthYear; // Calculate the age
                        String avatarUrl = dataModelInfo.getData().getAvatar();
                        Log.d("TypeChatInfo", "Avatar URL: " + avatarUrl);
                        ageView.setText(String.valueOf(age)); // Set the age to the TextView
                        nicknameView.setText(dataModelInfo.getData().getNickname());
                        idView.setText(String.valueOf(dataModelInfo.getData().getId()));
                        instructionView.setText(dataModelInfo.getData().getIntroduction());
                        nickname2View.setText(dataModelInfo.getData().getNickname());
                        if(dataModelInfo.getData().getGender() == 1 )genderView.setText("男");
                        else genderView.setText("男");
                        ageView.setText(String.valueOf(age));
                        dobView.setText(dataModelInfo.getData().getBirthday());
                        mailView.setText(dataModelInfo.getData().getEmail());
                        if (isAdded()) {
                            Glide.with(TypeChatInfo.this)
                                    .load(avatarUrl)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)  // 跳过磁盘缓存
                                    .skipMemoryCache(true)  // 跳过内存缓存
                                    .into(avatarView);
                        }


                    } else {
                        Log.e("TypeChatInfo", "Get user info failed: " + dataModelInfo.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<DataModelInfo> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.typechat_info, container, false);
        nicknameView = view.findViewById(R.id.nickname);
        idView = view.findViewById(R.id.id);
        instructionView = view.findViewById(R.id.instruction);
        nickname2View = view.findViewById(R.id.nickname2);
        genderView = view.findViewById(R.id.gender);
        ageView = view.findViewById(R.id.age);
        dobView = view.findViewById(R.id.dob);
        mailView = view.findViewById(R.id.mail);
        avatarView = view.findViewById(R.id.avatr);
        Bundle arguments1 = getArguments();
        int userId = arguments1.getInt("userId");
        Button logoutButton = view.findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        Button updateButton = view.findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateUserInfoActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        return view;
    }
    private void logout() {
        // Clear user info from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Navigate back to LoginActivity
        Intent intent = new Intent(getActivity(), TypeChatLogin.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TypeChatInfoViewModel.class);
        // TODO: Use the ViewModel

    }

}