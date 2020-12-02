package com.zackratos.ultimatebarx.sample;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:40 PM
 * @email : zhangwenchao@soulapp.cn
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
