package com.tool.dynamicgridlayout.base;

/**
 * @package com.mgo.uikitimpl.view.mygrid
 * @user by lvyaodong
 * @date 2020/8/19
 * @email 1126220529@qq.com
 */
public class BaseMarkData {
    private String imgUrl;
    private String text;
    private int left;
    private int top;
    private float leftScale;
    private float topScale;
    private boolean flagTop;

    public boolean isFlagTop() {
        return flagTop;
    }

    public void setFlagTop(boolean flagTop) {
        this.flagTop = flagTop;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public float getLeftScale() {
        return leftScale;
    }

    public void setLeftScale(float leftScale) {
        this.leftScale = leftScale;
    }

    public float getTopScale() {
        return topScale;
    }

    public void setTopScale(float topScale) {
        this.topScale = topScale;
    }
}
