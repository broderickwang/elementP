package com.xuanqi.he.o2omvp.modlues.main;

import com.xuanqi.he.o2omvp.modlues.main.bean.MainBean;
import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;

import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseView {
        void onRequestSuccess(List<MainBean> list);

        void onRequestFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestData(int count);
    }
}
