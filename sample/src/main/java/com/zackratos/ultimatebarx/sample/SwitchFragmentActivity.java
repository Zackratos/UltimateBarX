package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author : zhangwenchao
 * @Date : 2020/7/8  9:25 PM
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class SwitchFragmentActivity extends AppCompatActivity {

    private SwitchFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame_layout);
        fragment = SwitchFragment.Companion.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flContainer, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!fragment.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
