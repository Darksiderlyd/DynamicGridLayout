# DynamicGridLayout
GridLayout结合RecyclerView.Adapter实现单页栅格列表的显示

使用：
1.添加布局
```
<com.tool.dynamicgridlayout.mygrid.DynamicGridLayout
        android:id="@+id/dgl_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"/>
```
2.将页面行列分为
```
[
  {
    "endX": 2,
    "endY": 1,
    "imgUrl": "",
    "page": "",
    "startX": 0,
    "startY": 0,
    "tabId": "106",
    "text": "看视频"
  },
  {
    "endX": 3,
    "endY": 2,
    "imgUrl": "",
    "page": "",
    "startX": 2,
    "startY": 0,
    "tabId": "109",
    "text": "少儿频道"
  },
  {
    "endX": 4,
    "endY": 1,
    "imgUrl": "",
    "page": "",
    "startX": 3,
    "startY": 0,
    "tabId": "110",
    "text": "新闻资讯"
  },
  {
    "endX": 1,
    "endY": 2,
    "imgUrl": "",
    "page": "",
    "startX": 0,
    "startY": 1,
    "tabId": "107",
    "text": "玩游戏"
  },
  {
    "endX": 2,
    "endY": 2,
    "imgUrl": "",
    "page": "",
    "startX": 1,
    "startY": 1,
    "tabId": "108",
    "text": "听音乐"
  },
  {
    "endX": 4,
    "endY": 2,
    "imgUrl": "",
    "page": "",
    "startX": 3,
    "startY": 1,
    "tabId": "111",
    "text": "随便看看"
  }
]
```
3.实现自己的adapter，设置数据，给DynamicGridLayout设置adapter
```
GridAdapter gridAdapter = new GridAdapter();
DynamicGridLayout dynamicGridLayout = findViewById(R.id.dgl_layout);
dynamicGridLayout.setColumnCount(4);
dynamicGridLayout.setRowCount(2);
List<EntBean> entBeans = JSON.parseArray(getString(), EntBean.class);
gridAdapter.setDataList(entBeans);
dynamicGridLayout.setAdapter(gridAdapter);
```

显示效果:
![显示效果](https://github.com/Darksiderlyd/DynamicGridLayout/blob/master/screencap/screen.png)

[`GridLayout用法理解`](https://blog.csdn.net/wangmx1993328/article/details/82770910)
