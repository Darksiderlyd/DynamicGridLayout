package com.tool.dynamicgridlayout.widget.multigrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tool.dynamicgridlayout.R;
import com.tool.dynamicgridlayout.base.BaseAdapter;
import com.tool.dynamicgridlayout.base.BaseViewHolder;
import com.tool.dynamicgridlayout.base.BaseGridData;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class MultiDynamicGridLayout<D extends BaseGridData, A extends BaseAdapter<D, BaseViewHolder<D>>> extends GridLayout {
    private static final String TAG = "DynamicGridLayout";
    private final int mSpace;
    private final int mHalfSpace;

    private A mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAdapter(A mAdapter) {
        this.mAdapter = mAdapter;
        loadView();
    }

    public MultiDynamicGridLayout(Context context) {
        this(context, null);
    }

    public MultiDynamicGridLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiDynamicGridLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setColumnCount(20);
        setRowCount(20);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DynamicGridLayout);
        mSpace = (int) ta.getDimension(R.styleable.DynamicGridLayout_mdgl_space, 0f);
        mHalfSpace = mSpace / 2;
        ta.recycle();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

            int leftMargin = data.getStartX() == 0 ? 0 : mHalfSpace;
            int rightMargin = data.getEndX() % getColumnCount() == 0 ? 0 : mHalfSpace;
            int topMargin = data.getStartY() == 0 ? 0 : mHalfSpace;
            int bottomMargin = data.getEndY() % getRowCount() == 0 ? 0 : mHalfSpace;

            params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
            params.columnSpec = GridLayout.spec(data.getStartX(), data.getEndX() - data.getStartX(), (float) (data.getEndX() - data.getStartX()));
            params.rowSpec = GridLayout.spec(data.getStartY(), data.getEndY() - data.getStartY(), (float) (data.getEndY() - data.getStartY()));
            addView(viewHolder.itemView, params);
        }
    }

}
