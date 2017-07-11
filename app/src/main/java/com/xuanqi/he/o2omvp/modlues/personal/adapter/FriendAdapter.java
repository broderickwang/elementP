package com.xuanqi.he.o2omvp.modlues.personal.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;

import com.xuanqi.he.o2omvp.R;
import com.xuanqi.he.o2omvp.modlues.personal.bean.FriendBean;
import com.xuanqi.he.o2omvp.widget.recycler.CommonRecyclerAdapter;
import com.xuanqi.he.o2omvp.widget.recycler.RecyclerViewHolder;
import com.xuanqi.he.o2omvp.widget.recycler.decoration.SectionBean;

import java.util.List;

/**
 * @author Created by He on 2017/6/9.
 * @description
 */

public class FriendAdapter extends CommonRecyclerAdapter<FriendBean> {

    private List<FriendBean> mDatas;

    public FriendAdapter(Context context, List<FriendBean> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
        this.mDatas = data;
    }

    @Override
    public void convert(final RecyclerViewHolder holder, int position, final FriendBean item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_signature, item.getSignature());

        generateTag(holder, holder.getAdapterPosition());
    }

    private void generateTag(RecyclerViewHolder holder, int position) {
        SectionBean tag;
        // 没有tag的话 new 一个, 有的话 复用
        if (holder.itemView.getTag() == null) {
            tag = new SectionBean();
        } else {
            tag = (SectionBean) holder.itemView.getTag();
        }
        // 判断当前position的开始结束状态
        if (position == 0) {
            tag.setGroupStart(true);
            tag.setGroupEnd(!mDatas.get(position).getCategory().equals(mDatas.get(position + 1).getCategory()));
        } else if (position == mDatas.size() - 1) {
            tag.setGroupStart(!mDatas.get(position).getCategory().equals(mDatas.get(position - 1).getCategory()));
            tag.setGroupEnd(true);
        } else {
            tag.setGroupStart(!mDatas.get(position).getCategory().equals(mDatas.get(position - 1).getCategory()));
            tag.setGroupEnd(!mDatas.get(position).getCategory().equals(mDatas.get(position + 1).getCategory()));
        }
        holder.itemView.setTag(tag);
    }
}
