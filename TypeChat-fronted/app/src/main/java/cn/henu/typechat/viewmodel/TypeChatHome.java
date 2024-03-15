package cn.henu.typechat.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cn.henu.typechat.R;

public class TypeChatHome extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TypeChatMessage messageFragment = new TypeChatMessage();
    TypeChatContact contactsFragment = new TypeChatContact();
    TypeChatInfo profileFragment = new TypeChatInfo();
    private LinearLayout llhome, llcontrast, llline;
    private ImageView ivhome,  ivcontrast, ivline;
    private TextView tthome, ttcontrast, ttline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typechat_home);

        initView();
        initEvent();

        // 获取FragmentManager实例
        fragmentManager = getSupportFragmentManager();

        // 开始Fragment事务
        fragmentTransaction = fragmentManager.beginTransaction();

        // 提交事务
        fragmentTransaction.commit();
    }
    private void initEvent() {
        //添加fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, messageFragment).commit();
        ivhome.setSelected(true);
        tthome.setTextColor(getColor(R.color.greens));
        llhome.setOnClickListener(this);
        llline.setOnClickListener(this);
        llcontrast.setOnClickListener(this);
    }
    private void initView() {
        llhome = findViewById(R.id.llhome);
        llcontrast = findViewById(R.id.llcontrast);
        llline = findViewById(R.id.llline);
        ivhome = findViewById(R.id.home);
        ivcontrast = findViewById(R.id.cotrast);
        ivline = findViewById(R.id.line);
        ttcontrast =findViewById(R.id.ttcontrast);
        tthome = findViewById(R.id.tthome);
        ttline = findViewById(R.id.ttline);
    }
    public void onClick(View view) {
        int id = view.getId();
        resetAllMenuItems();
        if (id == R.id.llhome) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.container, messageFragment).commit();

            ivhome.setSelected(true);
            tthome.setTextColor(getColor(R.color.greens));
        } else if (id == R.id.llline) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, contactsFragment).commit();
            ivline.setSelected(true);
            ttline.setTextColor(getColor(R.color.greens));
        } else if (id == R.id.llcontrast) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, profileFragment).commit();
            ivcontrast.setSelected(true);
            ttcontrast.setTextColor(getColor(R.color.greens));
        }
    }
    private void resetAllMenuItems() {
        ivhome.setSelected(false);
        ivline.setSelected(false);
        ivcontrast.setSelected(false);
        tthome.setTextColor(getColor(R.color.gray));  // 设置文本颜色为灰色
        ttline.setTextColor(getColor(R.color.gray));
        ttcontrast.setTextColor(getColor(R.color.gray));
    }

}
