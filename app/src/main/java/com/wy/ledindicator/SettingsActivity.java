package com.wy.ledindicator;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.wy.ledindicator.adaper.SpinnerFrontAdapter;
import com.wy.ledindicator.adaper.SpinnerSizeAdapter;
import com.wy.ledindicator.entity.Params;
import com.wy.ledindicator.utils.FontManager;
import com.wy.ledindicator.utils.SharedPreferencesUtil;
import com.wy.ledindicator.utils.ToastUtils;
import com.wy.ledindicator.widget.ColorPickerView;
import com.wy.ledindicator.widget.TitleView;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;


import org.xutils.common.util.FileUtil;
import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsActivity extends BaseActivity  {

    @BindView(R.id.img_preview_bg)
    ImageView img_preview_bg;   //背景预览图

    @BindView(R.id.rg_bg)
    RadioGroup rg_bg;           //背景选择
    @BindView(R.id.rg_or)
    RadioGroup rg_or;           //方向选择

    @BindView(R.id.rb_left)
    RadioButton rb_left;        //向左
    @BindView(R.id.rb_right)
    RadioButton rb_right;       //向右
    @BindView(R.id.rb_color)
    RadioButton rb_color;       //颜色
    @BindView(R.id.rb_pic)
    RadioButton rb_pic;         //图片
    @BindView(R.id.tv_bj)
    TextView tv_bj;             //背景文字
    @BindView(R.id.tv_wzys)
    TextView tv_wzys;           //文字颜色
    @BindView(R.id.colorPick_bg)
    ColorPickerView colorPickBg;        //选择背景颜色
    @BindView(R.id.colorPick_txt)
    ColorPickerView colorPickTxt;       //选择文字颜色
    @BindView(R.id.et_txt)
    EditText et_txt;                //文字输入框
    @BindView(R.id.seekSpeed)
    SeekBar seekSpeed;               //速度滑动条

    @BindView(R.id.spanner_front)
    Spinner spannerFront;       //字体选择
    @BindView(R.id.spanner_size)
    Spinner spannerSize;        //字体大小

    @BindView(R.id.titleView)
    TitleView mTitleView;       //自定义标题




    SpinnerFrontAdapter mFrontAdapter;
    SpinnerSizeAdapter mSizeAdapter;
    String[] sizes;
    int currentTextColor = 0;       //当前文字颜色
    int currentType = 1;            //当前背景类型
    String currentPicPath = "";      //当前图片路径
    int currentTextSize = 0;        //当前选择的文字大小
    String currentFont = "";        //当前的字体
    int currentDirection = 2;        //当前方向

    @Override
    public int setContentView() {
        return R.layout.activity_settings;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        initTitleView();
        initView();
    }

    /**
     * 初始化标题
     */
    private void initTitleView() {
        mTitleView.setTitleClckListener(new TitleView.TitleClckListener() {
            @Override
            public void onBackImgClick() {
                //退出
                finish();
            }

            @Override
            public void onRightImgClick() {
                //保存
                if(TextUtils.isEmpty(et_txt.getText().toString().trim())){
                    ToastUtils.showToast("请输入文字");
                    et_txt.requestFocus();
                    return;
                }
                Params params = new Params(et_txt.getText().toString().trim(),currentDirection,currentTextSize,currentFont,tv_wzys.getCurrentTextColor(),seekSpeed.getProgress(),currentType,tv_bj.getCurrentTextColor(),currentPicPath);
                SharedPreferencesUtil.putParams(params);
                finish();
            }
        });
        mTitleView.setText("设置");
        mTitleView.setRightTv(R.string.save);
        mTitleView.setRightImgGone();
    }

    /**
     * 初始化View
     */
    private void initView() {



        colorPickBg.setOnColorPickerChangeListener(new ColorPickerView.OnColorPickerChangeListener() {
            @Override
            public void onColorChanged(ColorPickerView picker, int color) {
                //背景颜色变化
                tv_bj.setTextColor(color);
            }

            @Override
            public void onStartTrackingTouch(ColorPickerView picker) {

            }

            @Override
            public void onStopTrackingTouch(ColorPickerView picker) {

            }
        });

        colorPickTxt.setOnColorPickerChangeListener(new ColorPickerView.OnColorPickerChangeListener() {
            @Override
            public void onColorChanged(ColorPickerView picker, int color) {
                //文字颜色变化
                tv_wzys.setTextColor(color);
            }

            @Override
            public void onStartTrackingTouch(ColorPickerView picker) {

            }

            @Override
            public void onStopTrackingTouch(ColorPickerView picker) {

            }
        });

        rg_bg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_color:
                        //颜色选择
                        currentType = 1;
                        currentPicPath = "";

                        colorPickBg.setVisibility(View.VISIBLE);
                        img_preview_bg.setVisibility(View.GONE);
                        break;
                    case R.id.rb_pic:
                        //图片选择
                        currentType = 2;
                        currentTextColor = 0;

                        tv_bj.setTextColor(Color.BLACK);
                        break;
                }
            }
        });

        rb_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MPermissions.requestPermissions(SettingsActivity.this,Constants.Permissions.READ_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });

        rg_or.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        //向左选择
                        currentDirection = 1;
                        break;
                    case R.id.rb_right:
                        //向右选择
                        currentDirection = 2;
                        break;
                }
            }
        });

        mFrontAdapter = new SpinnerFrontAdapter(this);
        spannerFront.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtil.e("选择字体：");
                currentFont = FontManager.fonts_ttf.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spannerFront.setAdapter(mFrontAdapter);
        spannerFront.setSelection(0);



        sizes = getResources().getStringArray(R.array.spinnername);
        mSizeAdapter = new SpinnerSizeAdapter(this,sizes);
        spannerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtil.e("选择文字：");
                currentTextSize  = Integer.valueOf(sizes[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spannerSize.setAdapter(mSizeAdapter);
        spannerSize.setSelection(0);


        getPamrasAndRefreshUi();
    }

    @OnClick({R.id.rl_front, R.id.rl_size})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.rl_front:
                //选择字体
                spannerFront.performClick();
                break;
            case R.id.rl_size:
                //选择大小
                spannerSize.performClick();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> photos = data.getExtras().getStringArrayList(PhotoPickerActivity.EXTRA_RESULT);
            if (photos != null && photos.size() == 1) {
                //选择的图片
                Glide.with(this).load(photos.get(0)).into(img_preview_bg);
                currentPicPath = photos.get(0);
                colorPickBg.setVisibility(View.GONE);
                img_preview_bg.setVisibility(View.VISIBLE);
            }
        }
    }





    @PermissionGrant(Constants.Permissions.READ_STORAGE)
    public void requestSuccess(){
        Intent intent = new Intent(SettingsActivity.this, PhotoPickerActivity.class);
        intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_COUNT, 1);
        startActivityForResult(intent, 1);
    }

    @PermissionDenied(Constants.Permissions.READ_STORAGE)
    public void requestFail(){
        ToastUtils.showToast("未获取到读取权限");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 从SP中获取配置参数
     */
    public void getPamrasAndRefreshUi(){
        Params params = SharedPreferencesUtil.getParams();

        et_txt.setText(params.getText());
        et_txt.setSelection(params.getText().length());

        seekSpeed.setProgress(params.getSpeed());

        tv_wzys.setTextColor(params.getTextColor());


        for (int i=0;i<sizes.length;i++){
            if(Integer.valueOf(sizes[i])==params.getSize()){
                //如果当前的大小与设置的大小一致则显示这一条
                spannerSize.setSelection(i);
            }
        }

        spannerFront.setSelection(mFrontAdapter.getPosition(params.getFont()));

        if(params.getType()==1){
            //颜色背景
            rb_color.setChecked(true);
            tv_bj.setTextColor(params.getBgColor());
        }else{
            rb_pic.setChecked(true);
            if(FileUtil.getFileOrDirSize(new File(params.getPicPath()))!=0){
                //文件存在
                Glide.with(this).load(params.getPicPath()).into(img_preview_bg);
                img_preview_bg.setVisibility(View.VISIBLE);
                colorPickBg.setVisibility(View.GONE);
            }
        }
        if(params.getDirection()==1){
            rb_left.setChecked(true);
        }else{
            rb_right.setChecked(true);
        }
    }
}
