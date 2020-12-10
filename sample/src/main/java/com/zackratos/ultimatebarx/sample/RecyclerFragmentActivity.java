package com.zackratos.ultimatebarx.sample;

/**
 * @Author : zhangwenchao
 * @Date : 2020/12/2  8:51 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class RecyclerFragmentActivity extends SingleFragmentActivity {

    @Override
    protected InnerFragment createFragment() {
        return RecyclerFragment.newInstance();
    }
}
