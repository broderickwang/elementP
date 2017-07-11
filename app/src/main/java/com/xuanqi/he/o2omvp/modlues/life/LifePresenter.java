package com.xuanqi.he.o2omvp.modlues.life;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.life.bean.LifeBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class LifePresenter extends BasePresenterImpl<LifeContract.View> implements LifeContract.Presenter{

    private int[] arr = new int[]{R.mipmap.test_img_ad01, R.mipmap.test_img_ad02, R.mipmap.test_img_ad03};

    @Override
    public void requestData() {
        List<LifeBean> mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LifeBean bean = new LifeBean();
            bean.setTitle("回家看书的护肤开始的恢复" + i);
            bean.setName("诸葛小清风");
            bean.setTime("2016-05-04");
            bean.setPic(arr[i % 3]);
            mList.add(bean);
        }
        mView.onRequestSuccess(mList);
    }
}
