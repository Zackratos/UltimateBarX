package com.zackratos.ultimatebarx.sample;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zackratos.ultimatebarx.library.UltimateBarX;

/**
 * @Author : zhangwenchao
 * @Date : 2021/1/18  7:08 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        UltimateBarX.with(this).transparent().applyStatusBar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle("射雕英雄传");
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(new PagerAdapter(this));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, true, (tab, position) -> {
            String title = "";
            if (position == 0) {
                title = "九阴真经";
            } else if (position == 1) {
                title = "九阳真经";
            }
            tab.setText(title);
        }).attach();
    }

    private static class PagerAdapter extends FragmentStateAdapter {

        private final SparseArray<Fragment> fragments;

        private Context context;

        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            context = fragmentActivity;
            fragments = new SparseArray<>();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = fragments.get(position);
            if (fragment != null) return fragment;
            if (position == 0) {
                fragment = TextFragment.Companion.newInstance(context.getString(R.string.jiuyin));
            } else if (position == 1) {
                fragment = TextFragment.Companion.newInstance(context.getString(R.string.jiuyang));
            }
            fragments.put(position, fragment);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
