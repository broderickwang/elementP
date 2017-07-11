package com.xuanqi.he.o2omvp.modlues.main;

import android.os.Handler;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.MainBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    @Override
    public void requestData(final int count) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<MainBean> mList = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    MainBean bean = new MainBean();
                    bean.setIcon(R.mipmap.test_home_img01);
                    bean.setCount(1000 + i);
                    bean.setName("肯德基宅急送" + i);
                    Random random = new Random();
                    bean.setScore(random.nextInt(6));
                    mList.add(bean);
                }

                mView.onRequestSuccess(mList);
            }
        }, 2000);
    }
}
