package com.xuanqi.he.o2omvp.modlues.personal.activity.orderevaluation;

import android.os.Handler;

import com.xuanqi.he.o2omvp.modlues.personal.bean.OrderEvaluationBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderEvaluationPresenter extends BasePresenterImpl<OrderEvaluationContract.View> implements OrderEvaluationContract.Presenter {

    @Override
    public void requestData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<OrderEvaluationBean> list = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    OrderEvaluationBean bean = new OrderEvaluationBean();
                    bean.setName("百姓便利超市" + i);
                    bean.setTitle("可口可乐铁罐" + i);
                    List<String> tagList = new ArrayList<>();
                    tagList.add("干净卫生");
                    tagList.add("包装精美");
                    tagList.add("味道好");
                    if (i > 2) {
                        tagList.add("包装精美");
                        tagList.add("干净卫生");
                    }
                    bean.setList(tagList);
                    list.add(bean);
                }
                mView.onRequestSuccess(list);
            }
        }, 2000);
    }
}
