package com.zackratos.ultimatebarx.ultimatebarx.java;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/11/10 16:48
 * @Describe :
 */
public class StatusBarOnlyOperator extends BaseOperator {

    public StatusBarOnlyOperator(LifecycleOwner owner) {
        this(owner, BarConfig.Companion.newInstance());
    }

    public StatusBarOnlyOperator(LifecycleOwner owner, BarConfig config) {
        super(owner, config);
    }

    @Override
    protected void applyActivity(FragmentActivity activity) {
        UltimateBarXKt.statusBarOnly(activity, config, null);
    }

    @Override
    protected void applyFragment(Fragment fragment) {
        UltimateBarXKt.statusBarOnly(fragment, config, null);
    }
}
