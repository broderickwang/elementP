package com.xuanqi.he.o2omvp.modlues.service;

import com.xuanqi.he.o2omvp.mvp.BasePresenter;
import com.xuanqi.he.o2omvp.mvp.BaseView;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ServiceContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
