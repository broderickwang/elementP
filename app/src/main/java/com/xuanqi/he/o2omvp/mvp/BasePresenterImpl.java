package com.xuanqi.he.o2omvp.mvp;

import java.lang.ref.WeakReference;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V mView;
    private WeakReference<V> mReference;

    @Override
    public void attachView(V view) {
        mReference = new WeakReference<>(view);
        mView = mReference.get();
    }

    @Override
    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mView = null;
        }
    }
}
