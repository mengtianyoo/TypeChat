package cn.henu.typechat.viewmodel;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.henu.typechat.R;

public class TypeChatMessage extends Fragment {

    private TypeChatMessageViewModel mViewModel;

    public static TypeChatMessage newInstance() {
        return new TypeChatMessage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.typechat_message, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TypeChatMessageViewModel.class);
        // TODO: Use the ViewModel
    }

}