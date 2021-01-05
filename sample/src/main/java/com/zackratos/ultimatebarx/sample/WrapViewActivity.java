package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zackratos.ultimatebarx.library.UltimateBarX;


/**
 * @Author : zhangwenchao
 * @Date : 2021/1/5  9:10 PM
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class WrapViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("九阴真经");
        TextView tvBottom = findViewById(R.id.tvBottom);
        UltimateBarX.with(this).transparent().applyStatusBar();
        UltimateBarX.with(this).transparent().applyNavigationBar();
        FrameLayout statusBarWrapper = UltimateBarX.addStatusBarTopMarginWrapper(toolbar);
        if (statusBarWrapper != null) {
            statusBarWrapper.setBackgroundResource(R.drawable.bg_gradient_2);
        }
        FrameLayout navigationBarWrapper = UltimateBarX.addNavigationBarBottomMarginWrapper(tvBottom);
        if (navigationBarWrapper != null) {
            navigationBarWrapper.setBackgroundResource(R.drawable.bg_gradient_2);
        }
    }
}
