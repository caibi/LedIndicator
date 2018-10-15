package com.wy.ledindicator.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wy.ledindicator.R;

import org.xutils.common.util.LogUtil;

public class SpinnerSizeAdapter extends BaseAdapter {


    Context mContext;
    String[] sizes;

    public SpinnerSizeAdapter(Context mContext,String[] sizes){
        this.mContext = mContext;
        this.sizes = sizes;
    }

    @Override
    public int getCount() {
        return sizes.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_font,null);
            viewHodler = new ViewHodler();
            viewHodler.text = convertView.findViewById(R.id.text_front);
            convertView.setTag(viewHodler);
        }
        viewHodler = (ViewHodler) convertView.getTag();

        viewHodler.text.setText(sizes[position]);
//        viewHodler.text.setTextSize(Integer.valueOf(sizes[position]));
        return convertView;
    }

    public class ViewHodler{
        TextView text;
    }
}
