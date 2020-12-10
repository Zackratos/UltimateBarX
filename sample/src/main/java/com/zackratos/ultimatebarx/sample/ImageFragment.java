package com.zackratos.ultimatebarx.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/4  9:36 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class ImageFragment extends Fragment {

    private static final String DRAWABLE = "drawable";

    public ImageFragment() {
        super(R.layout.fragment_image);
    }

    public static ImageFragment newInstance(@DrawableRes int drawable) {
        ImageFragment fragment = new ImageFragment();
        Bundle arg = new Bundle();
        arg.putInt(DRAWABLE, drawable);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int drawable = getArguments().getInt(DRAWABLE);
        ImageView ivYurisa = view.findViewById(R.id.ivYurisa);
        ivYurisa.setImageResource(drawable);
        if (callback != null) {
            callback.initUltimateBarX(this);
        }
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        void initUltimateBarX(Fragment f);
    }
}
