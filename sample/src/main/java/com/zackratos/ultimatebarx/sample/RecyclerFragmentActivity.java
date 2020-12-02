package com.zackratos.ultimatebarx.sample;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:51 PM
 * @email : zhangwenchao@soulapp.cn
 * @Describe :
 */
public class RecyclerFragmentActivity extends SingleFragmentActivity {

    @Override
    protected InnerFragment createFragment() {
        return RecyclerFragment.newInstance();
    }
}
