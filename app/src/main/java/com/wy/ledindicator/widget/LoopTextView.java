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

    float speed;            //1-100
    Handler mHandler;
    int currentLeft;

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

    public void setSpeed(int speed){
        this.speed = speed;
    }

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

                        if(getRight()<=0){
                            currentLeft = viewGroup.getWidth();
                        }
                        layout(currentLeft,getTop(),currentLeft+getWidth(),getHeight()+getTop());
                        currentLeft = currentLeft-5;
                        mHandler.postDelayed(this,(int)(101-speed));
                    }
                },(int)(101-speed));
            }
        },500);

    }

    public void stopScroll(){
        if(mHandler!=null)
        mHandler.removeCallbacksAndMessages(null);
    }
}
