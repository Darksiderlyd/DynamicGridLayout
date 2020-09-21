package com.tool.dynamicgridlayout.mygrid;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public abstract class BaseAdapter<M, H extends BaseViewHolder<M>> extends RecyclerView.Adapter<H> {

    protected List<M> dataList;
    protected OnItemClickListener<H> listener;

    public BaseAdapter() {
    }

    /**
     * 设置数据,并设置点击回调接口
     *
     * @param list 数据集合
     */
    public BaseAdapter(@Nullable List<M> list) {
        this.dataList = list;
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
    }

    public void setDataList(List<M> dataList) {
        this.dataList = dataList;
        if (this.dataList == null) {
            this.dataList = new ArrayList<>();
        }
    }

    /**
     * 设置点击监听,并设置点击回调接口
     *
     * @param listener 数据集合
     */
    public void setListener(OnItemClickListener<H> listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final H holder, final int position) {
        holder.setData(dataList.get(position));
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * 填充数据,此方法会清空以前的数据
     *
     * @param list 需要显示的数据
     */
    public void fillList(List<M> list) {
        dataList.clear();
        dataList.addAll(list);
    }

    /**
     * 更新数据
     *
     * @param holder item对应的holder
     * @param data   item的数据
     */
    public void updateItem(H holder, M data) {
        dataList.set(holder.getLayoutPosition(), data);
    }

    /**
     * 获取一条数据
     *
     * @param holder item对应的holder
     * @return 该item对应的数据
     */
    public M getItem(H holder) {
        return dataList.get(holder.getLayoutPosition());
    }

    /**
     * 获取一条数据
     *
     * @param position item的位置
     * @return item对应的数据
     */
    public M getItem(int position) {
        return dataList.get(position);
    }

    /**
     * 追加一条数据
     *
     * @param data 追加的数据
     */
    public void appendItem(M data) {
        dataList.add(data);
    }

    /**
     * 追加一个集合数据
     *
     * @param list 要追加的数据集合
     */
    public void appendList(List<M> list) {
        dataList.addAll(list);
    }

    /**
     * 在最顶部前置数据
     *
     * @param data 要前置的数据
     */
    public void preposeItem(M data) {
        dataList.add(0, data);
    }

    /**
     * 在顶部前置数据集合
     *
     * @param list 要前置的数据集合
     */
    public void preposeList(List<M> list) {
        dataList.addAll(0, list);
    }
}
