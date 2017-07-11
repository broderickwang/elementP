package com.xuanqi.he.o2omvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public abstract class CommonPagerAdapter<T> extends FragmentPagerAdapter {

    private List<T> mDatas;

    public CommonPagerAdapter(FragmentManager manager, List<T> mDatas) {
        super(manager);
        this.mDatas = mDatas;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    protected abstract Fragment getFragment(int position);

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return getTabTitle(mDatas, position);
    }*/

    /*@Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }*/

//    protected abstract CharSequence getTabTitle(List<T> list, int position);
}
