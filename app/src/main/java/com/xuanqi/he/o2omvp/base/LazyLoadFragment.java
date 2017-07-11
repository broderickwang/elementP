package com.xuanqi.he.o2omvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/8.
 */

public abstract class LazyLoadFragment extends Fragment {

    private View mView;
    private Unbinder unbinder;
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;
            lazyLoad();
        }else {
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
        if (!isFirstLoad || !isVisible || !isInitView){
            //如果不是第一次加载、不是可见的、不是初始化view，则不加载数据
            return;
        }
        initEvent();
        initData();
        //设置已经不是第一次加载
        isFirstLoad = false;
    }

    protected abstract int getLayoutId();

    /**
     * 监听事件
     */
    public void initEvent() {}

    /**
     * 初始化
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
