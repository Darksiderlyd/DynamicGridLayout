package com.tool.dynamicgridlayout.widget.split;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;


import com.tool.dynamicgridlayout.base.BaseViewHolder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

////完美实现需要替换Adapter 和 数据
//public class SmartSplitLayout extends FrameLayout {
//
//    private double BORDER = 0.4f;
//    private static final double WH_LOWER = 0.7;
//    private static final double WH_UPPER = 1.4;
//    private static final double RESULT_SCALE = 0.99;
//
//    public static final int H = 0;
//    public static final int V = 1;
//    private static final String TAG = "XSmartSplitLayout";
//
//    //市值排序
//    public static final int CAP = 0;
//    //成交额排序
//    public static final int BALANCE = 1;
//
//    private int mOriginWidth;
//    private int mOriginHeight;
//
//    private int mSortByType;
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        mOriginWidth = getWidth();
//        mOriginHeight = getHeight();
//
//        Log.d(TAG, "Width: " + getWidth() + "Height: " + getHeight());
//    }
//
//    private GlobalHeatMapAdapter mAdapter;
//
//    public SmartSplitLayout(Context context) {
//        this(context, null);
//    }
//
//    public SmartSplitLayout(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SmartSplitLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//
//    public void setBORDER(double BORDER) {
//        this.BORDER = BORDER;
//    }
//
//    public void setAdapter(GlobalHeatMapAdapter adapter) {
//        if (adapter == null) {
//            throw new NullPointerException("FlowBaseAdapter is null");
//        }
//        mAdapter = adapter;
//    }
//
//    public void setSortByType(int sortByType) {
//        this.mSortByType = sortByType;
//    }
//
//    public void loadData(int sortByType) {
//        this.mSortByType = sortByType;
//        loadView();
//    }
//
//    private boolean isAllZero(int type, GlobalHeatMapAdapter adapter) {
//        boolean allIsZero = true;
//
//        for (GlobalIndustryData data : adapter.getData()) {
//            String compareStr = type == CommonConst.INDUSTRY_RANK_TYPE.BALANCE ? data.getBalance() : data.getMarket_cap();
//            if (!TextUtils.isEmpty(compareStr) && ConvertUtils.toDouble(compareStr) != 0.0) {
//                allIsZero = false;
//            }
//        }
//        return allIsZero;
//    }
//
//    private void loadView() {
//        if (mAdapter == null) {
//            Log.e(TAG, "Adapter can not be null!");
//            return;
//        }
//
//        if (mAdapter.getData() == null) {
//            Log.e(TAG, "Data is null!");
//            return;
//        }
//
//        if (mOriginWidth == 0 || mOriginHeight == 0) {
//            Log.e(TAG, "Width And Height is zero!");
//            return;
//        }
//
//        removeAllViews();
//
//        //对所有数据为0处理
//        if (isAllZero(mSortByType, mAdapter)) {
//            for (int i = 0; i < mAdapter.getData().size(); i++) {
//                mAdapter.getData().get(i).setRatio(1);
//            }
//        } else {
//            for (int i = 0; i < mAdapter.getData().size(); i++) {
//                mAdapter.getData().get(i).setRatio(mSortByType == CAP ? ConvertUtils.toDouble(mAdapter.getData().get(i).getMarket_cap())
//                        : ConvertUtils.toDouble(mAdapter.getData().get(i).getBalance()));
//            }
//
//            Collections.sort(mAdapter.getData(), (l1, l2) -> Double.compare(l2.getRatio(), l1.getRatio()));
//        }
//
//        for (int i = 0; i < mAdapter.getData().size(); i++) {
//            Log.e(TAG, mAdapter.getData().get(i).getName() + "  " + mAdapter.getData().get(i).getRatio());
//        }
//
//        double sumRatio = 0;
//
//        int finalCount = 0;
//
//        for (int i = 0; i < mAdapter.getItemCount(); i++) {
//            if (mAdapter.getData().get(i).getRatio() == 0) {
//                break;
//            }
//            finalCount++;
//            sumRatio += mAdapter.getData().get(i).getRatio();
//        }
//
//        Log.e(TAG, "All count is " + mAdapter.getData().size());
//        Log.e(TAG, "Show count is " + mAdapter.getItemCount());
//        Log.e(TAG, "Final count is " + finalCount);
//
//        if (finalCount == 0) {
//            Log.e(TAG, "Invalid ratio data! SortType is " + mSortByType);
//            return;
//        }
//
//        if (finalCount > 0 && finalCount < mAdapter.getItemCount()) {
//            Log.e(TAG, "Some data ratio is invalid! SortType is " + mSortByType);
//        }
//
//        long startTime = System.currentTimeMillis();
//
//        int validDataCount = Math.min(mAdapter.getItemCount(), finalCount);
//
//        Dynamic(mOriginWidth, mOriginHeight, 0, mOriginWidth * mOriginHeight, sumRatio, mAdapter.getData().subList(0, validDataCount));
//
//        for (int i = 0; i < validDataCount; i++) {
//            Log.d("Dynamic", "W: " + mAdapter.getData().get(i).getWidth() + ", H: " + mAdapter.getData().get(i).getHeight());
//            Log.d("Dynamic", "Start(x,y): " + mAdapter.getData().get(i).getStartX() + "," + mAdapter.getData().get(i).getStartY() + ", End(x,y): " + mAdapter.getData().get(i).getEndX() + "," + mAdapter.getData().get(i).getEndY());
//        }
//
//        long l = System.currentTimeMillis();
//        Log.e(TAG, "Dynamic Size: " + mAdapter.getData().size());
//        Log.e(TAG, "TimeSpend Dynamic: " + (System.currentTimeMillis() - startTime));
//
//        for (int i = 0; i < validDataCount; i++) {
//            //创建ViewHolder
//            BaseViewHolder viewHolder = mAdapter.createViewHolder(this, 0);
//            //绑定ViewHolder
//            mAdapter.onBindViewHolder(viewHolder, i);
//            addView(viewHolder.itemView, getLayoutParams(mAdapter.getItem(i)));
//        }
//        Log.e(TAG, "TimeSpend CreateView: " + (System.currentTimeMillis() - l));
//    }
//
//    @NotNull
//    private FrameLayout.LayoutParams getLayoutParams(GlobalIndustryData data) {
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) data.getWidth(), (int) data.getHeight());
//        params.leftMargin = data.getStartX();
//        params.topMargin = data.getStartY();
//        return params;
//    }
//
//    Map<Double, Double> map = new HashMap<>();
//
//    private void Dynamic(double width, double height, int index, double area, double sumRatio, List<GlobalIndustryData> ratios) {
//        Log.d("DynamicLayout", "width: " + width + ", " + "height: " + height + ", " + "index: " + index + ", " + "area: " + area + ", " + "sumRatio: " + sumRatio + ", ");
//        double minSide = Math.min(width, height);
//        int orientation = width > height ? H : V;
//        int i = index;
//        double usedRatio = 0;
//
//        //优化显示矩形接近正方形第一版
////        double preSumRatio = 0;
////        int num = 0;
////        for (int k = 0; k < ratios.size(); k++) {
////            if (preSumRatio / sumRatio > BORDER) {
////                num = k;
////                break;
////            } else {
////                preSumRatio += mAdapter.getItem(k).getRatio();
////            }
////        }
////
////        preSumRatio = 0;
////
////        if (num > 3) {
////            for (int k = 0; k < 3; k++) {
////                preSumRatio += mAdapter.getItem(k).getRatio();
////                BORDER = preSumRatio / sumRatio - 0.01;
////            }
////        }
//
//        //优化显示矩形接近正方形第二版 动态阈值计算
//        double preSumRatio = 0;
//        int j = index;
//        int k = j;
//        Log.d(TAG, "重新计算SumRatio: " + sumRatio);
//        double firstOutUpperRatio = 0.0;
//        double lastOutLowerRatio = 0.0;
//        map.clear();
//        if (ratios.size() - index >= 3) {
//            while (j < ratios.size()) {
//                preSumRatio = preSumRatio + ratios.get(j).getRatio();
//                double usedArea = preSumRatio / sumRatio * area;
//                double valueHeight = usedArea / minSide;//计算出高度
//                double valueWidth = ratios.get(k).getRatio() / preSumRatio * minSide; //计算占比 然后计算第一个宽度
//                double rectRatio = valueHeight / valueWidth;
//                Log.d(TAG, "重新计算: usedArea: " + usedArea + " valueHeight: " + valueHeight + " valueWidth: " + valueWidth + " rectRatio: " + rectRatio);
//
//                map.put(rectRatio, preSumRatio);
//
//                if (rectRatio >= WH_LOWER && rectRatio <= WH_UPPER) {
//                    BORDER = preSumRatio / sumRatio * RESULT_SCALE;
//                    Log.d(TAG, "重新计算BORDER: " + BORDER);
//                    break;
//                } else if (rectRatio > WH_UPPER) {
//                    if (firstOutUpperRatio == 0.0) {
//                        firstOutUpperRatio = rectRatio;
//                    }
//                } else if (rectRatio < WH_LOWER) {
//                    if (rectRatio > lastOutLowerRatio) {
//                        lastOutLowerRatio = rectRatio;
//                    }
//                }
//
//                //利用 rectRatio 一定是递增的
//                if (firstOutUpperRatio != 0) {
//                    Double aDouble;
//                    if (lastOutLowerRatio != 0) {
//                        if (Math.abs(lastOutLowerRatio - WH_LOWER) < Math.abs(firstOutUpperRatio - WH_UPPER)) {
//                            aDouble = map.get(firstOutUpperRatio);
//                        } else {
//                            aDouble = map.get(lastOutLowerRatio);
//                        }
//                    } else {
//                        aDouble = map.get(firstOutUpperRatio);
//                    }
//                    if (aDouble != null) {
//                        BORDER = aDouble / sumRatio * RESULT_SCALE;
//                    }
//                    Log.d(TAG, "重新计算BORDER 没有命中的情况下: " + BORDER);
//                    break;
//                }
//
//                j++;
//            }
//        } else {
//            for (; j < ratios.size(); j++) {
//                preSumRatio += ratios.get(j).getRatio();
//                BORDER = ratios.get(k).getRatio() / preSumRatio * RESULT_SCALE;
//            }
//        }
//
//        while (i < ratios.size()) {
//            usedRatio = usedRatio + ratios.get(i).getRatio();
//            double usedRatioRatio = usedRatio / sumRatio;
//            double usedArea = usedRatioRatio * area;
//            double value = usedArea / minSide;
//            if (usedRatioRatio < BORDER) {
//                i++;
//                if (i == ratios.size() - 1) {
//                    Log.d("DynamicLayout", usedRatioRatio + " 小于" + BORDER + " i: " + i);
//                    Log.d("DynamicLayout", "剩下的全部分配掉");
//                    spreadXY(width, height, index, ratios, minSide, orientation, i, usedRatio, value, true);
//                }
//            } else {
//                Log.d("DynamicLayout", usedRatioRatio + " 大于" + BORDER);
//                spreadXY(width, height, index, ratios, minSide, orientation, i, usedRatio, value, false);
//
//                area = area - usedArea;
//                sumRatio = sumRatio - usedRatio;
//                i++;
//                if (i < ratios.size()) {
//                    Dynamic(orientation == H ? width - value : width, orientation == H ? height : height - value, i, area, sumRatio, ratios);
//                }
//                Log.d("DynamicLayout", "一轮完成");
//                break;
//            }
//        }
//    }
//
//    private void spreadXY(double width, double height, int index, List<GlobalIndustryData> ratios, double minSide, int orientation, int i, double usedRatio, double value, boolean isFinalSpread) {
//        if (i == index) {
//            ratios.get(i).setWidth(orientation == H ? value : width);
//            ratios.get(i).setHeight(orientation == H ? height : value);
//
//            ratios.get(i).setStartX((int) (mOriginWidth - width));
//            ratios.get(i).setStartY((int) (mOriginHeight - height));
//
//            double endX = mOriginWidth - width + ratios.get(i).getWidth();
//            double endY = mOriginHeight - height + ratios.get(i).getHeight();
//
//            if (isFinalSpread) {
//                if (mOriginHeight - endY <= 1 && mOriginHeight - endY > 0) {
//                    endY = mOriginHeight;
//                }
//
//                if (mOriginWidth - endX <= 1 && mOriginWidth - endX > 0) {
//                    endX = mOriginWidth;
//                }
//            } else {
//                if (orientation == H) {
//                    if (mOriginHeight - endY <= 1 && mOriginHeight - endY > 0) {
//                        endY = mOriginHeight;
//                    }
//                } else {
//                    if (mOriginWidth - endX <= 1 && mOriginWidth - endX > 0) {
//                        endX = mOriginWidth;
//                    }
//                }
//            }
//
//
//            ratios.get(i).setEndX((int) endX);
//            ratios.get(i).setEndY((int) endY);
//            Log.d("DynamicLayout", "只有一个直接分配");
//        } else {
//
//            double lastStartX = mOriginWidth - width;
//            double lastStartY = mOriginHeight - height;
//
//            double lastEndX = mOriginWidth - width;
//            double lastEndY = mOriginHeight - height;
//
//
//            for (int j = index; j <= i; j++) {
//                double segValue = ratios.get(j).getRatio() / usedRatio * minSide;
//                ratios.get(j).setWidth(orientation == H ? value : segValue);
//                ratios.get(j).setHeight(orientation == H ? segValue : value);
//
//                ratios.get(j).setStartX((int) lastStartX);
//                ratios.get(j).setStartY((int) lastStartY);
//
//                lastStartX = orientation == H ? mOriginWidth - width : lastStartX + ratios.get(j).getWidth();
//                lastStartY = orientation == H ? lastStartY + ratios.get(j).getHeight() : mOriginHeight - height;
//
//                lastEndX = orientation == H ? mOriginWidth - width + ratios.get(j).getWidth() : lastEndX + ratios.get(j).getWidth();
//                lastEndY = orientation == H ? lastEndY + ratios.get(j).getHeight() : mOriginHeight - height + ratios.get(j).getHeight();
//
//                if (j == i) {
//                    if (isFinalSpread) {
//                        if (mOriginHeight - lastEndY <= 1 && mOriginHeight - lastEndY > 0) {
//                            lastEndY = mOriginHeight;
//                        }
//                        if (mOriginWidth - lastEndX <= 1 && mOriginWidth - lastEndX > 0) {
//                            lastEndX = mOriginWidth;
//                        }
//                    } else {
//                        if (orientation == H) {
//                            if (mOriginHeight - lastEndY <= 1 && mOriginHeight - lastEndY > 0) {
//                                lastEndY = mOriginHeight;
//                            }
//                        } else {
//                            if (mOriginWidth - lastEndX <= 1 && mOriginWidth - lastEndX > 0) {
//                                lastEndX = mOriginWidth;
//                            }
//                        }
//                    }
//
//                }
//
//                ratios.get(j).setEndX((int) lastEndX);
//                ratios.get(j).setEndY((int) lastEndY);
//                Log.d("DynamicLayout", "多个循环分配" + j);
//            }
//        }
//    }
//
//}