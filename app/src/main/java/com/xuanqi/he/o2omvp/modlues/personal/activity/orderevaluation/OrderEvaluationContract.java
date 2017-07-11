package com.xuanqi.he.o2omvp.modlues.personal.activity.orderevaluation;

import com.xuanqi.he.o2omvp.modlues.personal.bean.OrderEvaluationBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class OrderEvaluationContract {
    interface View extends BaseView {
        void onRequestSuccess(List<OrderEvaluationBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData();
    }
}
