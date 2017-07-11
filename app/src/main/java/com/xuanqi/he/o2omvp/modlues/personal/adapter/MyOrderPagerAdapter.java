package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xuanqi.he.o2omvp.adapter.CommonPagerAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.fragment.myorder.MyOrderFragment;

import java.util.List;

/**
 * @author Created by He on 2017/7/5.
 * @description
 */

public class MyOrderPagerAdapter extends CommonPagerAdapter<CharSequence> {

    private List<CharSequence> mDatas;

    public MyOrderPagerAdapter(FragmentManager manager, List<CharSequence> mDatas) {
        super(manager, mDatas);
        this.mDatas = mDatas;
    }

    @Override
    protected Fragment getFragment(int position) {
        return new MyOrderFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
