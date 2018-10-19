package com.wy.ledindicator;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;


import com.wy.ledindicator.entity.Params;
import com.wy.ledindicator.utils.FontManager;
import com.wy.ledindicator.utils.SharedPreferencesUtil;
import com.wy.ledindicator.widget.LoopTextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rl_main)
    RelativeLayout rl_main;

    @BindView(R.id.loopTextView)
    LoopTextView mTextView;

    @BindView(R.id.horizontalScrollView)
    HorizontalScrollView horizontalScrollView;      //滚动的View


    @Override
    public int setContentView() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        initView();
        new FontManager();      //初始化字体数据
    }


    private void initView() {
        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPamrasAndRefreshUi();
        mTextView.startScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTextView.stopScroll();
    }

    @OnClick(R.id.loopTextView)
    public void click(View v){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * 从SP中获取配置参数
     */
    public void getPamrasAndRefreshUi(){
        Params params = SharedPreferencesUtil.getParams();
        mTextView.clearData();
        mTextView.setText(params.getText());
        mTextView.setSpeed(params.getSpeed());
        mTextView.setTextColor(params.getTextColor());
        mTextView.setDirection(params.getDirection());
        mTextView.setTextSize(params.getSize());

        if(!TextUtils.isEmpty(params.getFont())){
            Typeface tf = Typeface.createFromAsset(getAssets(),
                    "fonts/"+params.getFont());
            mTextView.setTypeface(tf);
        }

        if(params.getType()==1){
            //颜色背景
            rl_main.setBackgroundColor(params.getBgColor());
        }else{
            rl_main.setBackground(Drawable.createFromPath(params.getPicPath()));
        }
    }

}
