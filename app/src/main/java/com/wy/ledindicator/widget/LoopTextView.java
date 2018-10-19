package com.wy.ledindicator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wy.ledindicator.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;


public class LoopTextView extends AppCompatTextView {

    float speed;            //1-100(默认20)
    Handler mHandler;
    int currentLeft;        //当前与左侧距离

    int direction = 1;          //方向(默认向左)
    boolean isLoop = true;      //是否循环(默认true)
    Runnable mRunnable;
    boolean isStopScroll = false;
    String[] mStrings;
    int position = 0;           //播放位置
    String currentStr;

    public LoopTextView(Context context) {
        super(context);
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoopTextView, 0, 0);
        speed = typedArray.getFloat(R.styleable.LoopTextView_speed, 20);
    }

    /**
     * @param speed 滚动速度（1-100）
     *              设置滚动速度
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @param isLoop 是否循环滚动
     *               设置是否循环滚动
     */
    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    /**
     * @param direction 1.向左 2.向右
     *                  设置滚动方向
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        if(speed==0){
            return;
        }
        isStopScroll = false;
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getWidth() != 0 && getWidth() < DensityUtil.getScreenWidth()) {
                    setWidth(DensityUtil.getScreenWidth());
                }
            }
        }, 100);
        mRunnable = new Runnable() {
            @Override
            public void run() {

                currentLeft = getLeft();

                if (direction == 1) {
                    //向左
                    currentLeft = currentLeft - 5;
                    if (currentLeft <= -getWidth()) {
                        LogUtil.e("向左滑动完毕");
                        if (mStrings != null && mStrings.length > 1) {
                            if (++position < mStrings.length) {
                                setText(mStrings[position]);
                            } else {
                                position = 0;
                                setText(mStrings[position]);
                            }
                        }
                        currentLeft = DensityUtil.getScreenWidth();

                    } else {
                        if (!isLoop) {
                            stopScroll();
                            return;
                        }
                    }

                } else {
                    //向右
                    currentLeft = currentLeft + 5;
                    if (currentLeft >= DensityUtil.getScreenWidth()) {
                        LogUtil.e("向右滑动完毕");
                        if (mStrings != null && mStrings.length > 1) {
                            if (++position < mStrings.length) {
                                setText(mStrings[position]);
                            } else {
                                position = 0;
                                setText(mStrings[position]);
                            }
                        }

                        currentLeft = -getTextViewLength(currentStr);

                    } else {
                        if (!isLoop) {
                            stopScroll();
                            return;
                        }
                    }
                }

                layout(currentLeft, getTop(), currentLeft + getTextViewLength(currentStr), getBottom());


                if (isStopScroll) {
                    mHandler.removeCallbacks(this);
                    return;
                }
                mHandler.postDelayed(this, (int) (101 - speed));
            }
        };

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentLeft = getLeft();
                mHandler.postDelayed(mRunnable, (int) (101 - speed));
            }
        }, 500);
    }

    public void setText(String str) {
        if (str != null && str.length() != 0 && str.contains("\n") && str.split("\n").length > 1) {
            position = 0;
            mStrings = str.split("\n");
            super.setText(mStrings[position]);
            currentStr = mStrings[position];
        } else {
            super.setText(str);
            currentStr = str;
        }
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        if (mHandler != null && mRunnable != null) {
            layout(0, getTop(), getTextViewLength(currentStr), getBottom());
            isStopScroll = true;
            mHandler.removeCallbacks(mRunnable);
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * @param text      文字
     * @return      测量文字长度
     */
    public int getTextViewLength(String text) {
        TextPaint paint = getPaint();
        // 得到使用该paint写上text的时候,像素为多少
        int textLength = (int) paint.measureText(text);
        return textLength;
    }

    /**
     * 清除数据
     */
    public void clearData() {
        currentStr = null;
        mStrings = null;
        isStopScroll = true;
    }
}
