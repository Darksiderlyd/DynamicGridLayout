package com.tool.dynamicgridlayout.markuse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tool.dynamicgridlayout.R;
import com.tool.dynamicgridlayout.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.tool.dynamicgridlayout.utils.DimenUtils.onlyViewMeasureLayout;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class MarkAdapter extends BaseAdapter<MarkBean, MarkViewHolder> {

    List<MarkViewHolder> holders = new ArrayList<>();
    private MarkViewHolder curHolder;

    public MarkAdapter() {
        super();
    }

    /**
     * 设置数据,并设置点击回调接口
     *
     * @param list 数据集合
     */
    public MarkAdapter(@Nullable List<MarkBean> list) {
        super(list);
    }

    @NonNull
    @Override
    public MarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MarkViewHolder(parent, R.layout.item_market_type_flag_top);
        }
        return new MarkViewHolder(parent, R.layout.item_market_type_flag_bottom);
    }

    @Override
    public void onBindViewHolder(final MarkViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holders.add(holder);
        TextView tvName = holder.itemView.findViewById(R.id.tv_name);
        tvName.setText(getItem(position).getText());

        if (position == 0 && holder != null) {
            curHolder = holder;
            selectItem(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curHolder == holder) {
                    if (listener != null) {
                        listener.onItemReClick(holder.itemView, position);
                    }
                    return;
                }
                if (listener != null) {
                    listener.onItemClick(holder.itemView, position);
                }

                curHolder = holder;

                setCurrentItem(holder);
            }
        });
    }

    private void setCurrentItem(MarkViewHolder holder) {
        if (holders != null && holders.size() > 0) {
            for (MarkViewHolder holder1 : holders) {
                if (holder1 == holder) {
                    selectItem(holder1);
                } else {
                    unSelectItem(holder1);
                }
            }
        }
    }

    private void unSelectItem(MarkViewHolder holder1) {
        ((ImageView) holder1.itemView.findViewById(R.id.iv_market)).setImageResource(R.mipmap.ic_market_point);
        holder1.itemView.findViewById(R.id.ll_flag).setPadding(dp2px(holder1.itemView.getContext(), 1.5f), dp2px(holder1.itemView.getContext(), 1.5f), dp2px(holder1.itemView.getContext(), 3), dp2px(holder1.itemView.getContext(), 1.5f));
        holder1.itemView.findViewById(R.id.tv_name).setVisibility(View.GONE);
        holder1.itemView.findViewById(R.id.space).setVisibility(View.VISIBLE);
        holder1.itemView.setScaleX(1f);
        holder1.itemView.setScaleY(1f);
    }

    private void selectItem(MarkViewHolder holder1) {
        ((ImageView) holder1.itemView.findViewById(R.id.iv_market)).setImageResource(R.mipmap.ic_market_point_big);
        holder1.itemView.findViewById(R.id.ll_flag).setPadding(dp2px(holder1.itemView.getContext(), 4), dp2px(holder1.itemView.getContext(), 3), dp2px(holder1.itemView.getContext(), 4), dp2px(holder1.itemView.getContext(), 3));
        holder1.itemView.findViewById(R.id.tv_name).setVisibility(View.VISIBLE);
        holder1.itemView.findViewById(R.id.space).setVisibility(View.GONE);
        onlyViewMeasureLayout(holder1.itemView, View.MeasureSpec.AT_MOST, View.MeasureSpec.UNSPECIFIED);

        holder1.itemView.setPivotX(holder1.itemView.getWidth());
        holder1.itemView.setPivotY(holder1.getData().isFlagTop() ? holder1.itemView.getHeight() : 7.5f);

        holder1.itemView.setScaleX(1.6f);
        holder1.itemView.setScaleY(1.6f);

        holder1.itemView.bringToFront();
    }


    public void selectItem(int pos) {
        if (holders == null || pos >= holders.size()) return;
        setCurrentItem(holders.get(pos));
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
