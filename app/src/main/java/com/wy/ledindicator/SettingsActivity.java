package com.wy.ledindicator;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.foamtrace.photopicker.PhotoPickerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView img;


    @Override
    public int setContentView() {
        return R.layout.activity_settings;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_chooice)
    public void click(View v){
        switch (v.getId()){
            case R.id.rl_chooice:
                //选择一张图片
                Intent intent = new Intent(this, PhotoPickerActivity.class);
                intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_COUNT,1);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ArrayList<String> photos = data.getExtras().getStringArrayList(PhotoPickerActivity.EXTRA_RESULT);
            if(photos!=null&&photos.size()==1){
                //选择的图片
                Glide.with(this).load(photos.get(0)).into(img);
            }
        }
    }
}
