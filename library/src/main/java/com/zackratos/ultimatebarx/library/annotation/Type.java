package com.zackratos.ultimatebarx.library.annotation;

import androidx.annotation.IntDef;

import com.zackratos.ultimatebarx.library.UltimateBarX;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({UltimateBarX.STATUS_BAR, UltimateBarX.NAVIGATION_BAR})
@Retention(RetentionPolicy.SOURCE)
public @interface Type {
}
