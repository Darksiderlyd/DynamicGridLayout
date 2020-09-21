# DynamicGridLayout
GridLayout结合RecyclerView.Adapter实现单页栅格列表的显示

Demo中没有加载第一个Item，需要到Adapter中修改

使用：
1.添加布局
```
<com.tool.dynamicgridlayout.mygrid.DynamicGridLayout
        android:id="@+id/dgl_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:columnCount="6"
        android:orientation="horizontal"
        android:rowCount="6"/>
```
2.实现自己的adapter，设置数据，给DynamicGridLayout设置adapter
```
GridAdapter gridAdapter = new GridAdapter();
DynamicGridLayout dynamicGridLayout = findViewById(R.id.dgl_layout);
List<EntBean> entBeans = JSON.parseArray(getString(), EntBean.class);
gridAdapter.setDataList(entBeans);
dynamicGridLayout.setAdapter(gridAdapter);
```

显示效果:![](https://github.com/Darksiderlyd/DynamicGridLayout/blob/master/screencap/screen.png)

