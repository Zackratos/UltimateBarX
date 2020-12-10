package com.zackratos.ultimatebarx.sample;


/**
 * @Author : zhangwenchao
 * @Date : 2020/7/8  9:25 PM
 * @email : 869649338@qq.com
 * @Describe :
 */
public class SwitchFragmentActivity extends SingleFragmentActivity {

    @Override
    protected InnerFragment createFragment(){
        return SwitchFragment.Companion.newInstance();
    }
}
