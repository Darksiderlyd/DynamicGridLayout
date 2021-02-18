package com.tool.dynamicgridlayout.mygriduse;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tool.dynamicgridlayout.R;
import com.tool.dynamicgridlayout.base.BaseAdapter;

import java.util.List;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/20
 * @email 1126220529@qq.com
 */
public class GridAdapter extends BaseAdapter<EntBean, GridViewHolder> {

    public GridAdapter() {
        super();
    }

    /**
     * 设置数据,并设置点击回调接口
     *
     * @param list 数据集合
     */
    public GridAdapter(@Nullable List<EntBean> list) {
        super(list);
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GridViewHolder(parent, R.layout.item_ent_panel);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        //如果第一个要做凸出来的效果可以不加载
//        if (position == 0) return;
        super.onBindViewHolder(holder, position);
        ImageView imageView = holder.itemView.findViewById(R.id.iv_panel_img);
        TextView textView1 = holder.itemView.findViewById(R.id.tv_panel_text1);
        TextView textView2 = holder.itemView.findViewById(R.id.tv_panel_text2);
        TextView textView = holder.itemView.findViewById(R.id.tv_panel_text);
        textView.setText(getItem(position).getText());
        textView2.setText(String.format("(StartX %d,StartY %d)", getItem(position).getStartX(), getItem(position).getStartY()));
        textView1.setText(String.format("(EndX %d,EndY %d)", getItem(position).getEndX(), getItem(position).getEndY()));
    }
}
