package com.tool.dynamicgridlayout.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class DimenUtils {
    private static int mScreenWidth = -1;                           //手机屏幕的宽度
    private static int mScreenHeight = -1;                           //手机屏幕的高度
    private static float mScreenDensity = -1;                            //手机屏幕dpi
    private static int mRealWidth = -1;
    private static int mRealHeight = -1;
    private static int sStatusbarHeight = -1;

    /**
     * 获取屏幕的宽度和高度
     *
     * @param context
     * @return
     */
    public static int[] getScreenWidthAndHeight(Context context) {
        //WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int[] screenDimen = new int[2];
        // mScreenWidth = wm.getDefaultDisplay().getWidth();
        //  mScreenHeight = wm.getDefaultDisplay().getHeight();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        mScreenHeight = displayMetrics.heightPixels;
        screenDimen[0] = mScreenWidth;
        screenDimen[1] = mScreenHeight;
        mScreenDensity = displayMetrics.density;

        return screenDimen;
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    // 将px值转换为dip或dp值
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (mScreenWidth == -1) {
            getScreenWidthAndHeight(context);
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (mScreenHeight == -1) {
            getScreenWidthAndHeight(context);
        }
        return mScreenHeight;
    }

    public static void onlyViewMeasureLayout(View view, int widthMode, int heightMode) {
        // 测量
        int widthSpec = View.MeasureSpec.makeMeasureSpec(DimenUtils.getScreenWidth(view.getContext()), widthMode);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(DimenUtils.getScreenHeight(view.getContext()), heightMode);
        view.measure(widthSpec, heightSpec);
        // 布局
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        view.layout(0, 0, measuredWidth, measuredHeight);
    }
}
