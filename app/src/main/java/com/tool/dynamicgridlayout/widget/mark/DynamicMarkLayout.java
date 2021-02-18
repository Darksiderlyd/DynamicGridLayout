package com.tool.dynamicgridlayout.widget.mark;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tool.dynamicgridlayout.base.BaseAdapter;
import com.tool.dynamicgridlayout.base.BaseViewHolder;
import com.tool.dynamicgridlayout.base.BaseMarkData;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class DynamicMarkLayout<D extends BaseMarkData, A extends BaseAdapter<D, BaseViewHolder<D>>> extends FrameLayout {
    private static final String TAG = "DynamicGridLayout";

    private A mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAdapter(A mAdapter) {
        this.mAdapter = mAdapter;
        loadView();
    }

    public DynamicMarkLayout(Context context) {
        this(context, null);
    }

    public DynamicMarkLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicMarkLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadView() {
        if (mAdapter == null) {
            Log.e(TAG, "Adapter can not be null!");
            return;
        }
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            D data = mAdapter.getItem(i);

            //创建ViewHolder
            BaseViewHolder<D> viewHolder = mAdapter.createViewHolder(this, data.isFlagTop() ? 1 : 0);

            //绑定ViewHolder
            mAdapter.onBindViewHolder(viewHolder, i);

            viewHolder.itemView.setTag(data);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            params.leftMargin = data.getLeft();
            params.topMargin = data.getTop();

            addView(viewHolder.itemView, params);
        }
    }
}
