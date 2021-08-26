package com.zackratos.ultimatebarx.ultimatebarx.java;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 8:46 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
class NavigationBarOperator extends BaseOperator {

    public NavigationBarOperator(LifecycleOwner owner) {
        this(owner, BarConfig.Companion.newInstance());
    }

    public NavigationBarOperator(LifecycleOwner owner, BarConfig config) {
        super(owner, config);
    }

    @Override
    protected void applyActivity(FragmentActivity activity) {
        UltimateBarXKt.navigationBar(activity, config, null);
    }

    @Override
    protected void applyFragment(Fragment fragment) {
        UltimateBarXKt.navigationBar(fragment, config, null);
    }
}
