package com.zackratos.ultimatebarx.sample;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:40 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class InnerFragment extends Fragment {

    public InnerFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }

    protected boolean onBackPressed() {
        return true;
    }
}
