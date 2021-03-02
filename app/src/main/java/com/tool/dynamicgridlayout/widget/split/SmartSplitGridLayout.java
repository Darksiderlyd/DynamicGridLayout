package com.tool.dynamicgridlayout.widget.split;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.tool.dynamicgridlayout.R;
import com.tool.dynamicgridlayout.base.BaseAdapter;
import com.tool.dynamicgridlayout.base.BaseGridData;
import com.tool.dynamicgridlayout.base.BaseViewHolder;
import com.tool.dynamicgridlayout.utils.DimenUtils;

import java.util.List;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class SmartSplitGridLayout<D extends BaseGridData, A extends BaseAdapter<D, BaseViewHolder<D>>> extends GridLayout {
    private static final String TAG = "DynamicGridLayout";
    private final int mSpace;
    private final int mHalfSpace;

    private A mAdapter;

    private static final float BORDER = 0.25f;
    public static final int H = 0;
    public static final int V = 1;
    private static int mOriginWidth;
    private static int mOriginHeight;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAdapter(A mAdapter) {
        this.mAdapter = mAdapter;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadData() {
        loadView();
    }

    public SmartSplitGridLayout(Context context) {
        this(context, null);
    }

    public SmartSplitGridLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartSplitGridLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DynamicGridLayout);
        mSpace = (int) ta.getDimension(R.styleable.DynamicGridLayout_mdgl_space, 0f);
        mHalfSpace = mSpace / 2;
        ta.recycle();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onGlobalLayout() {
                mOriginWidth = (int) (getMeasuredWidth() * 0.2);
                mOriginHeight = (int) (getMeasuredHeight() * 0.2);
                setOrientation(mOriginWidth > mOriginHeight ? VERTICAL : HORIZONTAL);
                setColumnCount(mOriginWidth);
                setRowCount(mOriginHeight);
                Log.e(TAG, mOriginWidth + "   " + mOriginHeight);
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadView() {
        if (mAdapter == null) {
            Log.e(TAG, "Adapter can not be null!");
            return;
        }

        removeAllViews();

        float sumRatio = 0;

        for (D b : mAdapter.getDataList()) {
            sumRatio += b.getRatio();
        }

        Dynamic(mOriginWidth, mOriginHeight, 0, mOriginWidth * mOriginHeight, sumRatio, (List<BaseGridData>) mAdapter.getDataList());

        for (D b : mAdapter.getDataList()) {
            Log.d("Dynamic", "W: " + b.getWidth() + ", H: " + b.getHeight());
            Log.d("Dynamic", "Start(x,y): " + b.getStartX() + "," + b.getStartY() + ", End(x,y): " + b.getEndX() + "," + b.getEndY());
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


    public static void Dynamic(float width, float height, int index, float area, float sumRatio, List<BaseGridData> ratios) {
        Log.d("DynamicLayout", "width: " + width + ", " + "height: " + height + ", " + "index: " + index + ", " + "area: " + area + ", " + "sumRatio: " + sumRatio + ", ");
        float minSide = Math.min(width, height);
        int orientation = width > height ? H : V;
        int i = index;
        float usedRatio = 0;

        while (i < ratios.size()) {
            usedRatio = usedRatio + ratios.get(i).getRatio();
            float usedRatioRatio = usedRatio / sumRatio;
            float usedArea = usedRatioRatio * area;
            float value = usedArea / minSide;
            if (usedRatioRatio < BORDER) {
                i++;
                if (i == ratios.size() - 1) {
                    Log.d("DynamicLayout", usedRatioRatio + " 小于" + BORDER + " i: " + i);
                    Log.d("DynamicLayout", "剩下的全部分配掉");
                    if (i == index) {
                        ratios.get(i).setWidth(orientation == H ? value : width);
                        ratios.get(i).setHeight(orientation == H ? height : value);

                        ratios.get(i).setStartX((int) (mOriginWidth - width));
                        ratios.get(i).setStartY((int) (mOriginHeight - height));

                        ratios.get(i).setEndX((int) (mOriginWidth - width + ratios.get(i).getWidth()));
                        ratios.get(i).setEndY((int) (mOriginHeight - height + ratios.get(i).getHeight()));
                        Log.d("DynamicLayout", "只有一个直接分配");
                    } else {

                        float lastStartX = mOriginWidth - width;
                        float lastStartY = mOriginHeight - height;

                        float lastEndX = mOriginWidth - width;
                        float lastEndY = mOriginHeight - height;


                        for (int j = index; j <= i; j++) {
                            float segValue = ratios.get(j).getRatio() / usedRatio * minSide;
                            ratios.get(j).setWidth(orientation == H ? value : segValue);
                            ratios.get(j).setHeight(orientation == H ? segValue : value);

                            ratios.get(j).setStartX((int) lastStartX);
                            ratios.get(j).setStartY((int) lastStartY);

                            lastStartX = orientation == H ? mOriginWidth - width : lastStartX + ratios.get(j).getWidth();
                            lastStartY = orientation == H ? lastStartY + ratios.get(j).getHeight() : mOriginHeight - height;

                            lastEndX = orientation == H ? mOriginWidth - width + ratios.get(j).getWidth() : lastEndX + ratios.get(j).getWidth();
                            lastEndY = orientation == H ? lastEndY + ratios.get(j).getHeight() : mOriginHeight - height + ratios.get(j).getHeight();
                            ratios.get(j).setEndX((int) lastEndX);
                            ratios.get(j).setEndY((int) lastEndY);
                            Log.d("DynamicLayout", "多个循环分配" + j);
                        }
                    }
                }
            } else {
                Log.d("DynamicLayout", usedRatioRatio + " 大于" + BORDER);

                if (i == index) {
                    ratios.get(i).setWidth(orientation == H ? value : width);
                    ratios.get(i).setHeight(orientation == H ? height : value);

                    ratios.get(i).setStartX((int) (mOriginWidth - width));
                    ratios.get(i).setStartY((int) (mOriginHeight - height));

                    ratios.get(i).setEndX((int) (mOriginWidth - width + ratios.get(i).getWidth()));
                    ratios.get(i).setEndY((int) (mOriginHeight - height + ratios.get(i).getHeight()));
                    Log.d("DynamicLayout", "只有一个直接分配");
                } else {

                    float lastStartX = mOriginWidth - width;
                    float lastStartY = mOriginHeight - height;

                    float lastEndX = mOriginWidth - width;
                    float lastEndY = mOriginHeight - height;


                    for (int j = index; j <= i; j++) {
                        float segValue = ratios.get(j).getRatio() / usedRatio * minSide;
                        ratios.get(j).setWidth(orientation == H ? value : segValue);
                        ratios.get(j).setHeight(orientation == H ? segValue : value);

                        ratios.get(j).setStartX((int) lastStartX);
                        ratios.get(j).setStartY((int) lastStartY);

                        lastStartX = orientation == H ? mOriginWidth - width : lastStartX + ratios.get(j).getWidth();
                        lastStartY = orientation == H ? lastStartY + ratios.get(j).getHeight() : mOriginHeight - height;

                        lastEndX = orientation == H ? mOriginWidth - width + ratios.get(j).getWidth() : lastEndX + ratios.get(j).getWidth();
                        lastEndY = orientation == H ? lastEndY + ratios.get(j).getHeight() : mOriginHeight - height + ratios.get(j).getHeight();
                        ratios.get(j).setEndX((int) lastEndX);
                        ratios.get(j).setEndY((int) lastEndY);
                        Log.d("DynamicLayout", "循环：（" + (int) lastEndX + "，" + (int) lastEndY + "）");
                        Log.d("DynamicLayout", "多个循环分配" + j);
                    }
                }

                area = area - usedArea;
                sumRatio = sumRatio - usedRatio;
                i++;
                if (i < ratios.size()) {
                    Dynamic(orientation == H ? width - value : width, orientation == H ? height : height - value, i, area, sumRatio, ratios);
                }
                Log.d("DynamicLayout", "一轮完成");
                break;
            }
        }
    }

}
