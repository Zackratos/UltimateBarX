package com.zackratos.ultimatebarx.ultimatebarx.java;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground;
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 8:02 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
abstract class BaseOperator implements Operator {

    protected BarConfig config;

    private final LifecycleOwner owner;

    public BaseOperator(LifecycleOwner owner, BarConfig config) {
        this.owner = owner;
        this.config = config;
    }

    @Override
    public Operator fitWindow(boolean fitWindow) {
        config.setFitWindow(fitWindow);
        return this;
    }

    @Override
    public Operator background(BarBackground background) {
        config.setBackground(background);
        return this;
    }

    @Override
    public Operator color(int color) {
        config.setColor(color);
        return this;
    }

    @Override
    public Operator colorRes(int colorRes) {
        config.setColorRes(colorRes);
        return this;
    }

    @Override
    public Operator drawableRes(int drawableRes) {
        config.setDrawableRes(drawableRes);
        return this;
    }

    @Override
    public Operator transparent() {
        config.transparent();
        return this;
    }

    @Override
    public Operator light(boolean light) {
        config.setLight(light);
        return this;
    }

    @Override
    public Operator lvlBackground(BarBackground background) {
        config.setLvlBackground(background);
        return this;
    }

    @Override
    public Operator lvlColor(int color) {
        config.setLvlColor(color);
        return this;
    }

    @Override
    public Operator lvlColorRes(int colorRes) {
        config.setLvlColorRes(colorRes);
        return this;
    }

    @Override
    public Operator lvlDrawableRes(int drawableRes) {
        config.setLvlDrawableRes(drawableRes);
        return this;
    }

    @Override
    public void apply() {
        if (owner instanceof FragmentActivity) {
            applyActivity((FragmentActivity) owner);
        } else if (owner instanceof Fragment) {
            applyFragment((Fragment) owner);
        }
    }

    abstract protected void applyActivity(FragmentActivity activity);

    abstract protected void applyFragment(Fragment fragment);
}
