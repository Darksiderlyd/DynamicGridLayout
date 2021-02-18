package com.tool.dynamicgridlayout.base;

import android.view.View;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public interface OnItemClickListener<T> {
    /**
     * 点击时回调
     *
     * @param view     点击的View
     * @param position 点击的位置
     */
    void onItemClick(View view, int position);

    /**
     * 重复点击时回调
     *
     * @param view     重复点击的View
     * @param position 重复点击的位置
     */
    void onItemReClick(View view, int position);

    /**
     * 点击时回调
     *
     * @param view     点击的View
     * @param position 点击的位置
     */
//    void onItemClickWithData(View view, int position, T data);

    /**
     * 长点击时回调
     *
     * @param view     点击的View
     * @param position 点击的位置
     */
    void onItemLongClick(View view, int position);

    /**
     * 点击时回调
     *
     * @param view     点击的View
     * @param position 点击的位置
     */
//    void onItemLongClickWithData(View view, int position, T data);
}