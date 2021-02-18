package com.tool.dynamicgridlayout.mygriduse;

import android.view.ViewGroup;

import com.tool.dynamicgridlayout.base.BaseViewHolder;

public class GridViewHolder extends BaseViewHolder<EntBean> {

    public GridViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setData(EntBean data) {
        this.mData = data;
    }
}
