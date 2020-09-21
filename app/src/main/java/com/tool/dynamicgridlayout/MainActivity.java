package com.tool.dynamicgridlayout;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.tool.dynamicgridlayout.mygrid.DynamicGridLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridAdapter gridAdapter = new GridAdapter();
        DynamicGridLayout dynamicGridLayout = findViewById(R.id.dgl_layout);
        dynamicGridLayout.setColumnCount(4);
        dynamicGridLayout.setRowCount(2);
        List<EntBean> entBeans = JSON.parseArray(getString(), EntBean.class);
        gridAdapter.setDataList(entBeans);
        dynamicGridLayout.setAdapter(gridAdapter);
    }


    private String getString() {
        try {
            AssetManager assetManager = getAssets(); //获得assets资源管理器（assets中的文件无法直接访问，可以使用AssetManager访问）
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("griddata"), "UTF-8"); //使用IO流读取json文件内容
            BufferedReader br = new BufferedReader(inputStreamReader);//使用字符高效流
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            inputStreamReader.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}