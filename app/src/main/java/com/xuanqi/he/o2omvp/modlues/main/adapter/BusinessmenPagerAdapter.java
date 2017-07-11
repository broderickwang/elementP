package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.SpannableString;

import com.xuanqi.he.o2omvp.adapter.CommonPagerAdapter;
import com.xuanqi.he.o2omvp.modlues.main.fragment.commodity.CommodityFragment;
import com.xuanqi.he.o2omvp.modlues.main.fragment.evaluation.EvaluationFragment;

import java.util.List;

/**
 * @author Created by He on 2017/6/15.
 * @description
 */

public class BusinessmenPagerAdapter extends CommonPagerAdapter<SpannableString> {

    private List<SpannableString> mDatas;

    public BusinessmenPagerAdapter(FragmentManager manager, List<SpannableString> mDatas) {
        super(manager, mDatas);
        this.mDatas = mDatas;
    }

    @Override
    protected Fragment getFragment(int position) {
        Fragment fragment;
        if (position == 0){
            fragment = new CommodityFragment();
        }else {
            fragment = new EvaluationFragment();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }
}
