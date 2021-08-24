package com.zackratos.ultimatebarx.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;

/**
 * @Author : zhangwenchao
 * @Date : 2020/7/8  2:45 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class ViewPagerActivity2 extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private FrameLayout flAndroid, flAlbum, flCamera, flGames;
    private ImageView ivAndroid, ivAlbum, ivCamera, ivGames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_2);
        initView();
        setStatusBar(0);
        UltimateBarXKt.navigationBar(this, barConfig -> {
            barConfig.setFitWindow(true);
            barConfig.setColorRes(R.color.deepSkyBlue);
            barConfig.setLight(false);
            return null;
        });
    }

    private void initView() {
        flAndroid = findViewById(R.id.flAndroid);
        flAlbum = findViewById(R.id.flAlbum);
        flCamera = findViewById(R.id.flCamera);
        flGames = findViewById(R.id.flGames);
        ivAndroid = findViewById(R.id.ivAndroid);
        ivAlbum = findViewById(R.id.ivAlbum);
        ivCamera = findViewById(R.id.ivCamera);
        ivGames = findViewById(R.id.ivGames);
        viewPager2 = findViewById(R.id.viewPager);
        flAndroid.setOnClickListener(v -> viewPager2.setCurrentItem(0));
        flAlbum.setOnClickListener(v -> viewPager2.setCurrentItem(1));
        flCamera.setOnClickListener(v -> viewPager2.setCurrentItem(2));
        flGames.setOnClickListener(v -> viewPager2.setCurrentItem(3));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                setStatusBar(position);
                setTabSelect(position);
            }
        });
        viewPager2.setAdapter(new ViewPagerAdapter2(this));
    }

    private void setTabSelect(int index) {
        ivAndroid.setImageResource(R.drawable.ic_android_sliver_24dp);
        ivAlbum.setImageResource(R.drawable.ic_album_sliver_24dp);
        ivCamera.setImageResource(R.drawable.ic_camera_sliver_24dp);
        ivGames.setImageResource(R.drawable.ic_games_sliver_24dp);
        switch (index) {
            case 0:
                ivAndroid.setImageResource(R.drawable.ic_android_deep_sky_blue_24dp);
                break;
            case 1:
                ivAlbum.setImageResource(R.drawable.ic_album_deep_sky_blue_24dp);
                break;
            case 2:
                ivCamera.setImageResource(R.drawable.ic_camera_deep_sky_blue_24dp);
                break;
            case 3:
                ivGames.setImageResource(R.drawable.ic_games_deep_sky_blue_24dp);
                break;
        }
    }

    private void setStatusBar(int index) {
        switch (index) {
            case 0:
                UltimateBarXKt.statusBar(this, barConfig -> {
                    barConfig.transparent();
                    barConfig.setLight(false);
                    return null;
                });
                break;
            case 1:
                UltimateBarXKt.statusBar(this, barConfig -> {
                    barConfig.setFitWindow(false);
                    barConfig.setColorRes(R.color.alphaWhite);
                    barConfig.setLight(true);
                    barConfig.setLvlColor(Color.TRANSPARENT);
                    return null;
                });
                break;
            case 2:
                UltimateBarXKt.statusBar(this, barConfig -> {
                    barConfig.setFitWindow(false);
                    barConfig.setColorRes(R.color.alphaBlack);
                    barConfig.setLight(false);
                    return null;
                });
                break;
            case 3:
                UltimateBarXKt.statusBar(this, barConfig -> {
                    barConfig.transparent();
                    barConfig.setLight(true);
                    barConfig.setLvlColorRes(R.color.alphaGreen);
                    return null;
                });
                break;
        }
    }
}
