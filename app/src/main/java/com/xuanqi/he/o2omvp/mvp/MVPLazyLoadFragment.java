package com.xuanqi.he.o2omvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public abstract class MVPLazyLoadFragment<V extends BaseView, T extends BasePresenterImpl<V>> extends Fragment implements BaseView {

    public T mPresenter;
    private View mView;
    private Unbinder unbinder;
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            //设置已经不是可见的
            isVisible = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);

        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        unbinder = ButterKnife.bind(this, mView);

        isInitView = true;
        lazyLoad();
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            //如果不是第一次加载、不是可见的、不是初始化view，则不加载数据
            return;
        }
        initEvent();
        initData();
//        requestNetData();
        //设置已经不是第一次加载
        isFirstLoad = false;
    }


    protected abstract int getLayoutId();

    /**
     * 监听事件
     */
    public void initEvent() {
    }

    /**
     * 初始化
     */
    protected abstract void initData();

    /**
     * 请求网络数据
     */
//    public void requestNetData() {}

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
