package com.tool.dynamicgridlayout.mygrid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    protected M mData;

    public BaseViewHolder(ViewGroup parent, @LayoutRes int resId) {
        super(LayoutInflater.from(parent.getContext()).inflate(resId, parent, false));
    }

    /**
     * 获取布局中的View
     *
     * @param viewId view的Id
     * @param <T>    View的类型
     * @return view
     */
    protected <T extends View> T getView(@IdRes int viewId) {
        return (T) (itemView.findViewById(viewId));
    }

    /**
     * 获取Context实例
     *
     * @return context
     */
    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 设置数据
     *
     * @param data 要显示的数据对象
     */
    public abstract void setData(M data);

    public M getData() {
        return mData;
    }
}
