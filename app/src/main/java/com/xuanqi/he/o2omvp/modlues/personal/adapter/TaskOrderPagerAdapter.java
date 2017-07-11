package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.xuanqi.he.o2omvp.adapter.CommonPagerAdapter;
import com.xuanqi.he.o2omvp.modlues.personal.fragment.receiveorders.ReceiveOrdersFragment;

import java.util.List;

/**
 * @author Created by He on 2017/7/5.
 * @description
 */

public class TaskOrderPagerAdapter extends CommonPagerAdapter<CharSequence> {

    private List<CharSequence> mDatas;

    public TaskOrderPagerAdapter(FragmentManager manager, List<CharSequence> mDatas) {
        super(manager, mDatas);
        this.mDatas = mDatas;
    }

    @Override
    protected Fragment getFragment(int position) {
//        Fragment fragment;
//        if (position == 0) {
//            fragment = new ReceiveOrdersFragment();
//        } else {
//            fragment = new ReleaseFragment();
//        }
        return new ReceiveOrdersFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
