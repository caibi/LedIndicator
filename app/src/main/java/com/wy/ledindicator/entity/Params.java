package com.wy.ledindicator.entity;

public class Params {
    private String text;        //文字
    private int direction;      //滚动方向
    private int size;           //文字大小
    private String font;        //字体
    private int textColor;      //文字颜色
    private int speed;          //滚动
    private int type;           //（背景）类型 【1.颜色 2.图片】
    private int bgColor;        //背景颜色
    private String picPath;     //背景图片文件路径

    public Params(String text, int direction, int size, String font, int textColor, int speed, int type, int bgColor, String picPath) {
        this.text = text;
        this.direction = direction;
        this.size = size;
        this.font = font;
        this.textColor = textColor;
        this.speed = speed;
        this.type = type;
        this.bgColor = bgColor;
        this.picPath = picPath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
