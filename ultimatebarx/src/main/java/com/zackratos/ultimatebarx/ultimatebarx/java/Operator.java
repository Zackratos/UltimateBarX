package com.zackratos.ultimatebarx.ultimatebarx.java;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground;

/**
 * @Author : zackratos
 * @Date : 2021/8/26 7:58 下午
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public interface Operator {

    Operator fitWindow(boolean fitWindow);

    Operator background(BarBackground background);

    Operator color(@ColorInt int color);

    Operator colorRes(@ColorRes int colorRes);

    Operator drawableRes(@DrawableRes int drawableRes);

    Operator transparent();

    Operator light(boolean light);

    Operator lvlBackground(BarBackground background);

    Operator lvlColor(@ColorInt int color);

    Operator lvlColorRes(@ColorRes int colorRes);

    Operator lvlDrawableRes(@DrawableRes int drawableRes);

    void apply();

}
