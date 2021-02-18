package com.tool.dynamicgridlayout.markuse;

import android.view.ViewGroup;

import com.tool.dynamicgridlayout.base.BaseViewHolder;

public class MarkViewHolder extends BaseViewHolder<MarkBean> {

    public MarkViewHolder(ViewGroup parent, int resId) {
        super(parent, resId);
    }

    @Override
    public void setData(MarkBean data) {
        this.mData = data;
    }
}
