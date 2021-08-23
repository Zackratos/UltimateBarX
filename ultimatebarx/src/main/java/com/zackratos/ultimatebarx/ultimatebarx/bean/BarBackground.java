package com.zackratos.ultimatebarx.ultimatebarx.bean;

import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

/**
 * @Author : Zackratos
 * @Date : 2021/8/24 2:15
 * @email : 869649338@qq.com
 * @Describe :
 */
public class BarBackground {
    @ColorInt
    private int color;
    @ColorRes
    private int colorRes;
    @DrawableRes
    private int drawableRes;

    public static BarBackground newInstance() {
        BarBackground bg = new BarBackground();
        bg.defaultBg();
        return bg;
    }

    @ColorInt
    public int getColor() {
        return color;
    }

    @ColorRes
    public int getColorRes() {
        return colorRes;
    }

    @DrawableRes
    public int getDrawableRes() {
        return drawableRes;
    }

    public void setColor(@ColorInt  int color) {
        colorRes = -1;
        drawableRes = -1;
        this.color = color;
    }

    public void setColorRes(@ColorRes int colorRes) {
        color = Integer.MIN_VALUE;
        drawableRes = -1;
        this.colorRes = colorRes;
    }

    public void setDrawableRes(@DrawableRes int drawableRes) {
        color = Integer.MIN_VALUE;
        colorRes = -1;
        this.drawableRes = drawableRes;
    }

    public void transparent() {
        color = Color.TRANSPARENT;
        colorRes = -1;
        drawableRes = -1;
    }

    public void defaultBg() {
        color = Integer.MIN_VALUE;
        colorRes = -1;
        drawableRes = -1;
    }

    public void update(BarBackground target) {
        if (this.equals(target)) return;
        this.color = target.color;
        this.colorRes = target.colorRes;
        this.drawableRes = target.drawableRes;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof BarBackground)) {
            return false;
        }
        BarBackground target = (BarBackground) obj;
        return color == target.color
                && colorRes == target.colorRes
                && drawableRes == target.drawableRes;
    }
}
