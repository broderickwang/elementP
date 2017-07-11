package com.xuanqi.he.o2omvp.modlues.personal.fragment.myorder;

import com.xuanqi.he.o2omvp.modlues.personal.bean.MyOrderBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyOrderContract {
    interface View extends BaseView {
        void onRequestSuccess(List<MyOrderBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData(int count);
    }
}
