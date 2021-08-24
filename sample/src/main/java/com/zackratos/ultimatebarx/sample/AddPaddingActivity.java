package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;


/**
 * @Author : zhangwenchao
 * @Date : 2021/1/5  9:10 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class AddPaddingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_padding);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("九阴真经");
        TextView tvBottom = findViewById(R.id.tvBottom);
        UltimateBarXKt.statusBar(this, barConfig -> {
            barConfig.transparent();
            return null;
        });
        UltimateBarXKt.navigationBar(this, barConfig -> {
            barConfig.transparent();
            return null;
        });
        UltimateBarXKt.addStatusBarTopPadding(toolbar);
        UltimateBarXKt.addNavigationBarBottomPadding(tvBottom);
    }
}
