package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/4  9:30 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class FragmentStackActivity extends AppCompatActivity {

    @DrawableRes
    private int[] images = new int[]{
            R.drawable.yurisa__001,
            R.drawable.yurisa__004,
            R.drawable.yurisa__003,
            R.drawable.yurisa__005,
            R.drawable.yurisa__002,
            R.drawable.yurisa__006
    };

    private int cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_stack);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> replaceFragment());
//        replaceFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flContainer, nextFragment())
                .commit();
        cursor++;
    }

    private void replaceFragment() {
        if (cursor > 5) return;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, nextFragment())
                .addToBackStack(null)
                .commit();
        cursor++;
    }

    private Fragment nextFragment() {
        ImageFragment fragment = ImageFragment.newInstance(images[cursor]);
        fragment.setCallback(getCallback());
        return fragment;
    }

    private ImageFragment.Callback getCallback() {
        switch (cursor) {
            case 0:
                return f ->
                        UltimateBarXKt.statusBar(f, barConfig -> {
                            barConfig.setFitWindow(false);
                            barConfig.setColorRes(R.color.alphaBlack);
                            barConfig.setLight(false);
                            return null;
                        });
            case 1:
            case 5:
                return f ->
                        UltimateBarXKt.statusBar(f, barConfig -> {
                            barConfig.transparent();
                            barConfig.setLight(true);
                            barConfig.setLvlColorRes(R.color.alphaBlack);
                            return null;
                        });
            case 2:
                return f ->
                        UltimateBarXKt.statusBar(f, barConfig -> {
                            barConfig.setFitWindow(false);
                            barConfig.setColorRes(R.color.alphaGreen);
                            barConfig.setLight(false);
                            return null;
                        });
            case 3:
                return f ->
                        UltimateBarXKt.statusBar(f, barConfig -> {
                            barConfig.transparent();
                            barConfig.setLight(false);
                            return null;
                        });
            case 4:
                return f ->
                        UltimateBarXKt.statusBar(f, barConfig -> {
                            barConfig.setFitWindow(false);
                            barConfig.setColorRes(R.color.alphaWhite);
                            barConfig.setLight(true);
                            barConfig.setLvlColorRes(R.color.alphaGreen);
                            return null;
                        });
            default:
                return f -> {
                };
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cursor--;
    }
}
