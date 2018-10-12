package com.wy.ledindicator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.wy.ledindicator.R;


public class LoopTextView extends AppCompatTextView {

    float speed;            //1-100(默认20)
    Handler mHandler;
    int currentLeft;        //当前与左侧距离

    int direction = 1;          //方向(默认向左)
    boolean isLoop = true;      //是否循环(默认true)


    public LoopTextView(Context context) {
        super(context);
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public void init(Context context,AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoopTextView, 0, 0);
        speed = typedArray.getFloat(R.styleable.LoopTextView_speed,20);
    }

    /**
     * @param speed 滚动速度（1-100）
     *              设置滚动速度
     */
    public void setSpeed(int speed){
        this.speed = speed;
    }

    /**
     * @param isLoop    是否循环滚动
     *                  设置是否循环滚动
     */
    public void setLoop(boolean isLoop){
        this.isLoop = isLoop;
    }

    /**
     * @param direction 1.向左 2.向右
     *                  设置滚动方向
     */
    public void setDirection(int direction){
        this.direction = direction;
    }

    /**
     * 开始滚动
     */
    public void startScroll(){
        final ViewGroup viewGroup = (ViewGroup) getParent();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentLeft = getLeft();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(direction==1){
                            //向左
                            if(getRight()<=0){
                                currentLeft = viewGroup.getWidth();
                                if(!isLoop){
                                    mHandler.removeCallbacks(this);
                                    return;
                                }
                            }
                        }else{
                            //向右
                            if(getRight()>=viewGroup.getWidth()+getWidth()){
                                currentLeft = -getWidth();
                                if(!isLoop){
                                    mHandler.removeCallbacks(this);
                                    return;
                                }
                            }
                        }

                        layout(currentLeft,getTop(),currentLeft+getWidth(),getHeight()+getTop());

                        if(direction==1){
                            //向左
                            currentLeft = currentLeft-10;
                        }else{
                            //向右
                            currentLeft = currentLeft+10;
                        }
                        mHandler.postDelayed(this,(int)(101-speed));
                    }
                },(int)(101-speed));
            }
        },500);
    }

    /**
     * 停止滚动
     */
    public void stopScroll(){
        if(mHandler!=null)
        mHandler.removeCallbacksAndMessages(null);
    }
}
