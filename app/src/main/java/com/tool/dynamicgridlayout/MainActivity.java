package com.tool.dynamicgridlayout;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.tool.dynamicgridlayout.utils.StringUtils;
import com.tool.dynamicgridlayout.widget.mygrid.DynamicGridLayout;
import com.tool.dynamicgridlayout.mygriduse.EntBean;
import com.tool.dynamicgridlayout.mygriduse.GridAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridAdapter gridAdapter = new GridAdapter();
        DynamicGridLayout dynamicGridLayout = findViewById(R.id.dgl_layout);
        dynamicGridLayout.setColumnCount(4);
        dynamicGridLayout.setRowCount(2);
        List<EntBean> entBeans = JSON.parseArray(StringUtils.getString(getApplicationContext(),"griddata"), EntBean.class);
        gridAdapter.setDataList(entBeans);
        dynamicGridLayout.setAdapter(gridAdapter);
    }


}