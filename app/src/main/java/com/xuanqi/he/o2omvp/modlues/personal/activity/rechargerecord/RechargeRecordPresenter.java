package com.xuanqi.he.o2omvp.modlues.personal.activity.rechargerecord;

import android.os.Handler;

import com.xuanqi.he.o2omvp.modlues.personal.bean.RechargeRecordBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;
import com.xuanqi.he.o2omvp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RechargeRecordPresenter extends BasePresenterImpl<RechargeRecordContract.View> implements RechargeRecordContract.Presenter {

    @Override
    public void requestData(final int count) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<RechargeRecordBean> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    RechargeRecordBean bean = new RechargeRecordBean();
                    Random random = new Random();
                    bean.setMoney("¥" + (random.nextInt(100) + 1));
                    bean.setStatus(random.nextInt(2) + "");
                    bean.setTime(DateUtils.getCurrentTime());
                    list.add(bean);
                }
                mView.onRequestSuccess(list);
            }
        }, 2000);
    }
}
