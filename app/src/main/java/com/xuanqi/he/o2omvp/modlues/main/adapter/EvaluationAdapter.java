package com.xuanqi.he.o2omvp.modlues.main.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.main.bean.EvaluationBean;
import com.xuanqi.he.o2omvp.utils.GlideUtils;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by He on 2017/7/1.
 * @description
 */

public class EvaluationAdapter extends CommonRecyclerAdapter<EvaluationBean> {

    private Context mContext;

    public EvaluationAdapter(Context context, List<EvaluationBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
        this.mContext = context;
    }

    @Override
    public void convert(RecyclerViewHolder holder, int position, EvaluationBean item) {
        holder.setImageResource(R.id.iv_icon, item.getIcon())
                .setText(R.id.tv_nickname, item.getName())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_content, item.getContent());
        RatingBar ratingBar = holder.getView(R.id.rating_bar);
        ratingBar.setRating(item.getScore());
        LinearLayout imgBox = holder.getView(R.id.ll_img_box);
        ImageView ivOne = holder.getView(R.id.iv_one);
        ImageView ivTwo = holder.getView(R.id.iv_two);
        ImageView ivThree = holder.getView(R.id.iv_three);
        ImageView ivFour = holder.getView(R.id.iv_four);
        List<Integer> urlList = item.getImageList();
        if (urlList.size() == 0) {
            imgBox.setVisibility(View.GONE);
        } else {
            imgBox.setVisibility(View.VISIBLE);
            List<ImageView> list = new ArrayList<>();
            list.add(ivOne);
            list.add(ivTwo);
            list.add(ivThree);
            list.add(ivFour);
            for (int i = 0; i < urlList.size(); i++) {
                GlideUtils.loadImage(mContext, urlList.get(i), list.get(i));
            }
        }
    }
}
