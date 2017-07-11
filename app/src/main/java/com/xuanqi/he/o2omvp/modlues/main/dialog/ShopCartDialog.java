package com.xuanqi.he.o2omvp.modlues.main.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.adapter.ShoppingCartDialogAdapter;
import com.xuanqi.he.o2omvp.modlues.main.bean.ShopCart;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.DividerItemDecoration;


/**
 * Created by He on 17-6-12.
 */
public class ShopCartDialog extends Dialog implements View.OnClickListener, ShoppingCartDialogAdapter.OnDialogShoppingAdapterClickListener {

    private LinearLayout llAnimation;
    private RelativeLayout bottomLayout;
    private TextView tvDelete;
    private FrameLayout mFlCartLayout;
    private ShopCart shopCart;
    private TextView mTvCartTotal;
    private TextView mTvCartTotalNum;
    private RecyclerView recyclerView;
    private ShoppingCartDialogAdapter adapter;
    private OnDialogDismissListener mListener;
    private TextView mTvSettleAccounts;
    private int count = 5;
    private int height = 255;

    public ShopCartDialog(Context context, ShopCart shopCart, int themeResId) {
        super(context, themeResId);
        this.shopCart = shopCart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_shopping_cart);
        llAnimation = (LinearLayout) findViewById(R.id.ll_animation);
        tvDelete = (TextView) findViewById(R.id.tv_delete);
        mTvSettleAccounts = (TextView) findViewById(R.id.tv_settle_accounts);
        mFlCartLayout = (FrameLayout) findViewById(R.id.fl_cart_layout);
        bottomLayout = (RelativeLayout) findViewById(R.id.rl_cart_bottom);
        mTvCartTotal = (TextView) findViewById(R.id.tv_cart_total);
        mTvCartTotalNum = (TextView) findViewById(R.id.tv_cart_total_num);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mFlCartLayout.setOnClickListener(this);
        bottomLayout.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        mTvSettleAccounts.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int size = shopCart.getShoppingSingleMap().keySet().size();
        if (size > count) {
            recyclerView.getLayoutParams().height = DeviceUtils.dip2px(height);
        } else {
            recyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        adapter = new ShoppingCartDialogAdapter(shopCart);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.setOnDialogShoppingAdapterClickListener(this);
        showTotalPrice();
    }

    @Override
    public void show() {
        super.show();
        animationShow(300);
    }

    @Override
    public void dismiss() {
        animationHide(300);
    }

    private void showTotalPrice() {
        GradientDrawable drawable = (GradientDrawable) mFlCartLayout.getBackground();
        if (shopCart != null && shopCart.getShoppingTotalPrice() > 0) {
            mTvCartTotal.setText("¥ " + shopCart.getShoppingTotalPrice());
            if (mTvCartTotalNum.getVisibility() != View.VISIBLE) {
                mTvCartTotalNum.setVisibility(View.VISIBLE);
            }
            mTvCartTotalNum.setText("" + shopCart.getShoppingAccount());
            drawable.setColor(ContextCompat.getColor(getContext(), R.color.blue));
            mTvSettleAccounts.setText("去结算");
            mTvSettleAccounts.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue));
        } else {
            mTvCartTotal.setText("未选购");
            if (mTvCartTotalNum.getVisibility() != View.GONE) {
                mTvCartTotalNum.setVisibility(View.GONE);
            }
            drawable.setColor(ContextCompat.getColor(getContext(), R.color.black));
            mTvSettleAccounts.setText("20元起送");
            mTvSettleAccounts.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void animationShow(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(llAnimation, "translationY", 1000, 0).setDuration(mDuration)
        );
        animatorSet.start();
    }

    private void animationHide(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(llAnimation, "translationY", 0, 1000).setDuration(mDuration)
        );
        animatorSet.start();

        if (mListener != null) {
            mListener.dialogDismiss();
        }

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ShopCartDialog.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_cart_bottom:
            case R.id.fl_cart_layout:
                this.dismiss();
                break;
            case R.id.tv_delete:
                clear();
                break;
            case R.id.tv_settle_accounts:
                break;
        }
    }

    public OnDialogDismissListener getListener() {
        return mListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onAddClick(int num, String name) {
        showTotalPrice();
        if (listener != null) {
            listener.onAddClick(num, name);
        }
    }

    @Override
    public void onDeleteClick(int num, String name) {
        showTotalPrice();
        int size = shopCart.getShoppingSingleMap().keySet().size();
        if (size > count) {
            recyclerView.getLayoutParams().height = DeviceUtils.dip2px(height);
        } else {
            recyclerView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        if (listener != null) {
            listener.onDeleteClick(num, name);
        }

        if (shopCart.getShoppingAccount() == 0) {
            this.dismiss();
        }
    }

    public interface OnDialogDismissListener {
        void dialogDismiss();
    }

    public void clear() {
        shopCart.clear();
        showTotalPrice();
        if (listener != null) {
            listener.clear();
        }
        if (shopCart.getShoppingAccount() == 0) {
            this.dismiss();
        }
    }

    private OnShoppingDialogClickListener listener;

    public interface OnShoppingDialogClickListener {
        void onAddClick(int num, String name);

        void onDeleteClick(int num, String name);

        void clear();
    }

    public void setOnShoppingDialogClickListener(OnShoppingDialogClickListener listener) {
        this.listener = listener;
    }
}
