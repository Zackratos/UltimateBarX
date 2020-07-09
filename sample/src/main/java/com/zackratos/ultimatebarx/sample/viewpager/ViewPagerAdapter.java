package com.zackratos.ultimatebarx.sample.viewpager;

import android.graphics.Color;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zackratos.ultimatebarx.sample.R;

/**
 * @Author : zhangwenchao
 * @Date : 2020/7/8  2:57 PM
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> fragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments = new SparseArray<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragments.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = TextFragment.Companion.newInstance(Color.RED, "Android", Color.WHITE);
                break;
            case 1:
                fragment = ImageTextFragment.Companion.newInstance(R.drawable.yurisa__001, null);
                break;
            case 2:
                fragment = TextFragment.Companion.newInstance(Color.YELLOW, "Camera", Color.BLACK);
                break;
            case 3:
                fragment = ImageTextFragment.Companion.newInstance(R.drawable.yurisa__002, null);
                break;
            default:
                fragment = TextFragment.Companion.newInstance(Color.TRANSPARENT, "", Color.TRANSPARENT);
                break;
        }
        fragments.put(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
