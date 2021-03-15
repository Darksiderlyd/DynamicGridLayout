package com.tool.dynamicgridlayout.widget.split;

/**
 * author : yaodonglv
 * date : 2021/3/3
 * desc :
 */
public class GlobalIndustryData {
    private String counter_id;//行业代码
    private String name;//行业名称
    private String last_done;//行业现价
    private String prev_close;//行业昨收
    private String chg;// 行业涨跌幅
    private String market_cap;// 行业市值
    private String balance;//行业成交额

    private double width;
    private double height;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private double ratio;

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public String getCounter_id() {
        return counter_id;
    }

    public void setCounter_id(String counter_id) {
        this.counter_id = counter_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_done() {
        return last_done;
    }

    public void setLast_done(String last_done) {
        this.last_done = last_done;
    }

    public String getPrev_close() {
        return prev_close;
    }

    public void setPrev_close(String prev_close) {
        this.prev_close = prev_close;
    }

    public String getChg() {
        return chg;
    }

    public void setChg(String chg) {
        this.chg = chg;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
