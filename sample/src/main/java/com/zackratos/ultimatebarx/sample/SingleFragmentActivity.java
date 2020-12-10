package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:46 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    private InnerFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame_layout);

        FragmentManager fm = getSupportFragmentManager();

        fragment = (InnerFragment) fm.findFragmentById(R.id.flContainer);

        if (fragment == null) {
            fragment = createFragment();
        }

        fm.beginTransaction()
                .add(R.id.flContainer, fragment)
                .commit();
    }

    protected abstract InnerFragment createFragment();

    @Override
    public void onBackPressed() {
        if (!fragment.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
