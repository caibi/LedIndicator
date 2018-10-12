package com.wy.ledindicator.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wy.ledindicator.R;
import com.wy.ledindicator.utils.DensityUtil;

public class TitleView extends LinearLayout implements View.OnClickListener{

    String text;        //标题文字资源
    int resRight = R.drawable.more;       //右图的资源(默认为更多)
    int resBack = R.drawable.back;       //返回键的资源
    int  rightTxt = R.string.save;        //右侧文字

    ImageView rightImg;     //右侧功能按钮
    ImageView backImg;      //左侧返回按钮
    TextView mTextView;     //标题文字
    TextView rightTv;       //右侧文字

    RelativeLayout mRelativeLayout;
    TitleClckListener mTitleClckListener;
    Context context;
    public TitleView(Context context) {
        super(context);
        this.context  = context;
        setOrientation(VERTICAL);       //竖向
        addAllViews();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context  = context;
        setOrientation(VERTICAL);       //竖向
        addAllViews();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context  = context;
        setOrientation(VERTICAL);       //竖向
        addAllViews();
    }

    public void setText(String text){
        this.text = text;
        mTextView.setText(this.text);
    }

    public void setText(int text){
        this.text = getResources().getString(text);
        mTextView.setText(this.text);
    }

    public void setBackImg(int resImg){
        this.resRight = resImg;
        backImg.setImageResource(this.resRight);
    }


    public void setRightImg(int resImg){
        this.resRight = resImg;
        rightImg.setImageResource(this.resRight);
    }

    public void setRightTv(int tv){
        this.rightTxt = tv;
        rightTv.setText(this.rightTxt);
        rightTv.setVisibility(VISIBLE);
    }

    public void setTextGone(){
        mTextView.setVisibility(View.GONE);
        removeView(mTextView);
    }

    public void setRightImgGone(){
        rightImg.setVisibility(View.GONE);
        mRelativeLayout.removeView(rightImg);
    }

    public void setTextColor(int color){
        mTextView.setTextColor(color);
    }

    public void setBackImgGone(){
        backImg.setVisibility(View.GONE);
        mRelativeLayout.removeView(backImg);
    }

    public void setTitleClckListener(TitleClckListener mTitleClckListener){
        this.mTitleClckListener = mTitleClckListener;
    }

    public ImageView getRightView(){
        return rightImg;
    }

    public ImageView getBackView(){
        return backImg;
    }

    public TextView getTextView(){
        return mTextView;
    }


    public void addAllViews(){
        removeAllViews();
        //添加RelativeLayout
        mRelativeLayout = new RelativeLayout(context);
        LayoutParams rlparams = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(44));
        mRelativeLayout.setLayoutParams(rlparams);

        //RelativeLayout添加backImg
        backImg = new ImageView(context);
        backImg.setImageResource(resBack);
        backImg.setPadding(DensityUtil.dip2px(14),DensityUtil.dip2px(14),DensityUtil.dip2px(14),DensityUtil.dip2px(14));
        RelativeLayout.LayoutParams backImgParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        backImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        backImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        backImg.setLayoutParams(backImgParams);
        backImg.setOnClickListener(this);
        mRelativeLayout.addView(backImg);

        //RelativeLayout添加rightImg
        rightImg = new ImageView(context);
        rightImg.setImageResource(resRight);
        rightImg.setPadding(DensityUtil.dip2px(14),DensityUtil.dip2px(14),DensityUtil.dip2px(14),DensityUtil.dip2px(14));
        RelativeLayout.LayoutParams rightImgParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightImg.setLayoutParams(rightImgParams);
        rightImg.setOnClickListener(this);
        mRelativeLayout.addView(rightImg);

        //添加右侧文字
        rightTv = new TextView(context);
        rightTv.setText(text);
        rightTv.setTextSize(14);
        rightTv.setClickable(true);
        rightTv.setTextColor(getResources().getColor(R.color.home_color));
        RelativeLayout.LayoutParams rightTvParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightTvParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightTvParams.addRule(RelativeLayout.CENTER_VERTICAL);
        rightTvParams.setMargins(0, 0,DensityUtil.dip2px(20), 0);
        rightTv.setLayoutParams(rightTvParams);
        rightTv.setOnClickListener(this);
        rightTv.setVisibility(INVISIBLE);
        mRelativeLayout.addView(rightTv);

        //添加TextView
        mTextView = new TextView(context);
        mTextView.setText(text);
        mTextView.setTextSize(24);
        mTextView.setSingleLine();
        mTextView.setEms(8);
        mTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        mTextView.setTextColor(getResources().getColor(R.color.title_text_color));
        LayoutParams tvparams = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(44));
        tvparams.setMargins(DensityUtil.dip2px(35), 0,0, 0);
        mTextView.setLayoutParams(tvparams);


        addView(mRelativeLayout);
        addView(mTextView);
    }



    @Override
    public void onClick(View v) {
            if(v==rightImg||v==rightTv){
                if(mTitleClckListener!=null){
                    mTitleClckListener.onRightImgClick();
                }
            }else if(v==backImg){
                if(mTitleClckListener!=null){
                    mTitleClckListener.onBackImgClick();
                }
            }
        }

    public interface TitleClckListener{
        void onBackImgClick();  //返回键按下
        void onRightImgClick(); //右图按下
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
