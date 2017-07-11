package com.xuanqi.he.o2omvp.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.se7en.utils.DeviceUtils;
import com.xuanqi.he.o2omvp.R;


/**
 * @author Created by He on 2017/6/5.
 * @description 对话框工具类
 */

public class PromptDialogUtils {
    private final Context mContext;
    private final int width;
    private View contentView;
    private String title, content, cancel, confirm;
    private boolean isHideCancel;
    private AlertDialog dialog;
    private OnDialogButtonClickListener listener;

    public interface OnDialogButtonClickListener {
        void onClick(boolean cancelOrConfirm);
    }

    private PromptDialogUtils(Builder builder) {
        this.contentView = builder.contentView;
        this.title = builder.title;
        this.mContext = builder.parentView.getContext();
        this.width = builder.width;
        this.content = builder.content;
        this.cancel = builder.cancel;
        this.confirm = builder.confirm;
        this.listener = builder.listener;
        this.isHideCancel = builder.isHideCancel;
    }

//    public static PromptDialogUtils getInstance() {
//        if (instance == null) {
//            synchronized (PromptDialogUtils.class) {
//                if (instance == null) {
//                    instance = new PromptDialogUtils();
//                }
//            }
//        }
//        return instance;
//    }

    public void showDialog() {
        if (dialog == null) {
            dialog = new AlertDialog.Builder(mContext, R.style.PromptDialog)
//                    .setView(contentView)
                    .setCancelable(false)
                    .create();
        }
        TextView tvTitle = (TextView) contentView.findViewById(R.id.dialog_title);
        TextView tvMsg = (TextView) contentView.findViewById(R.id.dialog_msg);
        TextView tvCancel = (TextView) contentView.findViewById(R.id.dialog_btn_cancel);
        TextView tvConfirm = (TextView) contentView.findViewById(R.id.dialog_btn_confirm);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(content)) {
            tvMsg.setText(content);
        }
        if (!TextUtils.isEmpty(cancel)) {
            tvCancel.setText(cancel);
        }
        if (!TextUtils.isEmpty(confirm)) {
            tvConfirm.setText(confirm);
        }
        if (isHideCancel) {
            ((ViewGroup) tvCancel.getParent()).setVisibility(View.GONE);
        }

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog = null;
                if (listener != null) {
                    listener.onClick(false);
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog = null;
                if (listener != null) {
                    listener.onClick(true);
                }
            }
        });

        dialog.show();

        //获得dialog所在的窗体对象
        Window window = dialog.getWindow();
        //得到参数
        WindowManager.LayoutParams params = window.getAttributes();
        //动态设置dialog的宽度为屏幕宽度的6/7
        if (width != 0) {
            params.width = width;
        } else {
            params.width = DeviceUtils.getScreenWdith() * 6 / 7;
        }

        //把设置好的参数重新设置给dialog
        window.setAttributes(params);
        //最后才添加布局
        window.setContentView(contentView);

    }


    public static final class Builder {
        //        Context context;
        private ViewGroup parentView;
        private View contentView;
        private String title, content, cancel, confirm;
        private int width;
        private boolean isHideCancel;
        private OnDialogButtonClickListener listener;

        public Builder(ViewGroup parentView, OnDialogButtonClickListener listener) {
            this.parentView = parentView;
            this.listener = listener;
            contentView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.prompt_dialog_layout, parentView, false);
        }

//        public Builder setContentView(View view){
//            this.contentView = view;
//            return this;
//        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public Builder setConfirm(String confirm) {
            this.confirm = confirm;
            return this;
        }

        public Builder setHideCancel(boolean isHideCancel) {
            this.isHideCancel = isHideCancel;
            return this;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

//        public Builder setViewClick(final View.OnClickListener listener, int... ids){
//            if (contentView == null){
//                throw new NullPointerException("还没有设置view，必须先调用setContenView方法");
//            }
//
//            for (int id:
//                    ids) {
//                contentView.findViewById(id).setOnClickListener(listener);
//            }
//
//            return this;
//        }


        public PromptDialogUtils build() {
            return new PromptDialogUtils(this);
        }
    }
}
