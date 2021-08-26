package com.zackratos.ultimatebarx.ultimatebarx.java;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXKt;
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 8:06 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class StatusBarOperator extends BaseOperator {

    public StatusBarOperator(LifecycleOwner owner) {
        this(owner, BarConfig.Companion.newInstance());
    }

    public StatusBarOperator(LifecycleOwner owner, BarConfig config) {
        super(owner, config);
    }


    @Override
    protected void applyActivity(FragmentActivity activity) {
        UltimateBarXKt.statusBar(activity, config, null);
    }

    @Override
    protected void applyFragment(Fragment fragment) {
        UltimateBarXKt.statusBar(fragment, config, null);
    }
}
