package com.wy.ledindicator;

import android.widget.RelativeLayout;


import com.wy.ledindicator.widget.LoopTextView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rl_main)
    RelativeLayout rl_main;

    @BindView(R.id.loopTextView)
    LoopTextView mTextView;

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mTextView.setText("测试");

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView.startScroll();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTextView.stopScroll();
    }
}
