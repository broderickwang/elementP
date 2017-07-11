package com.xuanqi.he.o2omvp.modlues.personal.fragment.receiveorders;

import android.os.Handler;

import com.xuanqi.he.o2omvp.modlues.personal.bean.ReceiveOrdersBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReceiveOrdersPresenter extends BasePresenterImpl<ReceiveOrdersContract.View> implements ReceiveOrdersContract.Presenter {

    @Override
    public void requestData(final int count) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ReceiveOrdersBean> list = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    ReceiveOrdersBean bean = new ReceiveOrdersBean();
                    bean.setName("诸葛小前锋" + i);
                    bean.setTitle("家政搬家！急！服务号的速来！" + i);
                    int random = new Random().nextInt(100);
                    bean.setMoney(random * 1.0f);
                    list.add(bean);
                }
                mView.onRequestSuccess(list);
            }
        }, 2000);
    }

    @Override
    public void requestFailed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.onRequestFailed("请求失败！！！！");
            }
        }, 2000);
    }

}
