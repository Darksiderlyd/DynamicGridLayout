package com.tool.dynamicgridlayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tool.dynamicgridlayout.markuse.MarkAdapter;
import com.tool.dynamicgridlayout.markuse.MarkBean;
import com.tool.dynamicgridlayout.mygriduse.EntBean;
import com.tool.dynamicgridlayout.mygriduse.GridAdapter;
import com.tool.dynamicgridlayout.utils.StringUtils;
import com.tool.dynamicgridlayout.widget.mark.DynamicMarkLayout;
import com.tool.dynamicgridlayout.widget.mark.MyItemClickListener;
import com.tool.dynamicgridlayout.widget.multigrid.MultiDynamicGridLayout;
import com.tool.dynamicgridlayout.widget.mygrid.DynamicGridLayout;

import java.util.List;

import static com.tool.dynamicgridlayout.utils.DimenUtils.onlyViewMeasureLayout;

public class MarketActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        final MarkAdapter markAdapter = new MarkAdapter();
        DynamicMarkLayout markLayout = findViewById(R.id.dml_layout);
        ImageView imageView = findViewById(R.id.iv_market);


        final List<MarkBean> markBeans = JSON.parseArray(StringUtils.getString(getApplicationContext(), "markdata"), MarkBean.class);

        onlyViewMeasureLayout(imageView, View.MeasureSpec.AT_MOST, View.MeasureSpec.UNSPECIFIED);
        Log.d("markLayout", imageView.getWidth() + "");
        Log.d("markLayout", imageView.getHeight() + "");
        for (MarkBean markBean : markBeans) {
            Log.d("markLayout", "RealLeft: " + imageView.getWidth() * markBean.getLeftScale());
            Log.d("markLayout", "RealTop: " + imageView.getHeight() * markBean.getTopScale());
            markBean.setLeft((int) (imageView.getWidth() * markBean.getLeftScale()));
            markBean.setTop((int) (imageView.getHeight() * markBean.getTopScale()));
        }
        markAdapter.setDataList(markBeans);
        markLayout.setAdapter(markAdapter);
        markAdapter.setListener(new MyItemClickListener() {
            @Override
            public void onItemReClick(View view, int position) {
                Toast.makeText(MarketActivity.this, "重复：" + markBeans.get(position).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MarketActivity.this, "第一次" + markBeans.get(position).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v = (int) (Math.random() * (markBeans.size() - 1));
                markAdapter.selectItem(v);
            }
        });



        GridAdapter multiGridAdapter = new GridAdapter();
        MultiDynamicGridLayout multiDynamicGridLayout = findViewById(R.id.dgl_layout);
        multiDynamicGridLayout.setColumnCount(4);
        multiDynamicGridLayout.setRowCount(5);
        List<EntBean> entBeans1 = JSON.parseArray(StringUtils.getString(getApplicationContext(),"griddata2"), EntBean.class);
        multiGridAdapter.setDataList(entBeans1);
        multiDynamicGridLayout.setAdapter(multiGridAdapter);


        GridAdapter gridAdapter = new GridAdapter();
        DynamicGridLayout dynamicGridLayout = findViewById(R.id.dgl_layout2);
        dynamicGridLayout.setColumnCount(4);
        dynamicGridLayout.setRowCount(2);
        List<EntBean> entBeans2 = JSON.parseArray(StringUtils.getString(getApplicationContext(),"griddata"), EntBean.class);
        gridAdapter.setDataList(entBeans2);
        dynamicGridLayout.setAdapter(gridAdapter);

    }
}