package com.xuanqi.he.o2omvp.modlues.personal.fragment.receiveorders;


import com.xuanqi.he.o2omvp.modlues.personal.bean.ReceiveOrdersBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ReceiveOrdersContract {
    interface View extends BaseView {
        void onRequestSuccess(List<ReceiveOrdersBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData(int count);

        void requestFailed();
    }
}
