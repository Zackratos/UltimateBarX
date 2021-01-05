package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.zackratos.ultimatebarx.library.UltimateBarX;

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
        UltimateBarX.with(this)
                .fitWindow(true)
                .colorRes(R.color.deepSkyBlue)
                .light(false)
                .applyNavigationBar();
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
                UltimateBarX.with(this)
                        .transparent()
                        .light(false)
                        .applyStatusBar();
                break;
            case 1:
                UltimateBarX.with(this)
                        .fitWindow(false)
                        .colorRes(R.color.alphaWhite)
                        .light(true)
                        .applyStatusBar();
                break;
            case 2:
                UltimateBarX.with(this)
                        .fitWindow(false)
                        .colorRes(R.color.alphaBlack)
                        .light(false)
                        .applyStatusBar();
                break;
            case 3:
                UltimateBarX.with(this)
                        .transparent()
                        .light(true)
                        .applyStatusBar();
                break;
        }
    }
}
