package cn.henu.typechat.viewmodel;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cn.henu.typechat.R;

public class TypeChatContact extends Fragment {

    private TypeChatContactViewModel mViewModel;

    public static TypeChatContact newInstance() {
        return new TypeChatContact();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.typechat_contact, container, false);

        // 获取 FragmentManager
        final FragmentManager fragmentManager = getChildFragmentManager();

        // 添加联系人列表 Fragment
        final ContactListFragment contactListFragment = new ContactListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.container, contactListFragment)
                .commit();
        // 添加群聊列表 Fragment
        final GroupChatListFragment groupChatListFragment = new GroupChatListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.container, groupChatListFragment)
                .hide(groupChatListFragment) // 隐藏群聊列表 Fragment
                .commit();

        // 设置按钮点击事件
        FloatingActionButton switchButton = rootView.findViewById(R.id.switch_button);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // 判断当前显示的 Fragment 是哪个，然后切换显示和隐藏
                if (contactListFragment.isVisible()) {
                    fragmentTransaction.hide(contactListFragment)
                            .show(groupChatListFragment);
                } else {
                    fragmentTransaction.hide(groupChatListFragment)
                            .show(contactListFragment);
                }
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TypeChatContactViewModel.class);
        // TODO: Use the ViewModel
    }
}

