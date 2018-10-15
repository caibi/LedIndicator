package com.wy.ledindicator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

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
    int scrollX = 10;


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
        isStopScroll = false;
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getWidth()!=0&&getWidth()<DensityUtil.getScreenWidth()){
                    setWidth(DensityUtil.getScreenWidth());
                }
            }
        },100);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (direction == 1) {
                    //向左
                    scrollX = 10;
                } else {
                    //向右
                    scrollX = -10;
                }
                scrollBy(scrollX, 0);

                if (direction == 1) {
                    //向左
                    currentLeft = currentLeft - 10;
                } else {
                    //向右
                    currentLeft = currentLeft + 10;
                }
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

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
        LogUtil.e("变化：" + (horiz - oldHoriz) + ",horiz:" + horiz + ",old:" + oldHoriz + ",width:" + getWidth()+",ScreenWidth:"+org.xutils.common.util.DensityUtil.getScreenWidth());
        if ((horiz) >= getWidth() && direction == 1) {
            //向左滑动完成
            LogUtil.e("向左滑动完成");
            scrollBy(-org.xutils.common.util.DensityUtil.getScreenWidth() - getWidth(), 0);
            if (!isLoop) {
                scrollTo(0, 0);
                stopScroll();
                LogUtil.e("结束了");
                return;
            }
        }

        if ((horiz) <= -org.xutils.common.util.DensityUtil.getScreenWidth() && direction == 2) {
            //向右滑动完成
            LogUtil.e("向右滑动完成");
            scrollBy(DensityUtil.getScreenWidth() + getWidth(), 0);
            if (!isLoop) {
                scrollTo(0, 0);
                stopScroll();
                LogUtil.e("结束了");
                return;
            }
        }
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        if (mHandler != null && mRunnable != null) {
            isStopScroll = true;
            mHandler.removeCallbacks(mRunnable);
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
