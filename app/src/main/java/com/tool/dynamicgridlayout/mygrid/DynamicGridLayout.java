package com.tool.dynamicgridlayout.mygrid;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;

import androidx.annotation.Nullable;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class DynamicGridLayout<D extends GridData, A extends BaseAdapter<D, BaseViewHolder<D>>> extends GridLayout {
    private static final String TAG = "DynamicGridLayout";

    private A mAdapter;
    private int mMargin = 10;
    private int mHalfMargin = mMargin / 2;

    public void setAdapter(A mAdapter) {
        this.mAdapter = mAdapter;
        loadView();
    }

    public DynamicGridLayout(Context context) {
        this(context, null);
    }

    public DynamicGridLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicGridLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setColumnCount(20);
        setRowCount(20);
    }

    private void loadView() {
        if (mAdapter == null) {
            Log.e(TAG, "Adapter can not be null!");
            return;
        }
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            //创建ViewHolder
            BaseViewHolder<D> viewHolder = mAdapter.createViewHolder(this, 0);
            //绑定ViewHolder
            mAdapter.onBindViewHolder(viewHolder, i);

            D data = mAdapter.getItem(i);

            viewHolder.itemView.setTag(data);
            LayoutParams params = new LayoutParams();

            params.width = 0;
            params.height = 0;

            int leftMargin = data.getStartX() == 0 ? mMargin : mHalfMargin;
            int topMargin = data.getStartY() == 0 ? mMargin : mHalfMargin;
            int rightMargin = data.getEndX() % getColumnCount() == 0 ? mMargin : mHalfMargin;
            int bottomMargin = data.getEndY() % getRowCount() == 0 ? mMargin : mHalfMargin;

            params.setMargins(dp2px(this.getContext(), leftMargin), dp2px(this.getContext(), topMargin), dp2px(this.getContext(), dp2px(this.getContext(), rightMargin)), dp2px(this.getContext(), bottomMargin));
            params.columnSpec = GridLayout.spec(data.getStartX(), data.getEndX() - data.getStartX(), (float) (data.getEndX() - data.getStartX()));
            params.rowSpec = GridLayout.spec(data.getStartY(), data.getEndY() - data.getStartY(), (float) (data.getEndY() - data.getStartY()));
            addView(viewHolder.itemView, params);
        }
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
