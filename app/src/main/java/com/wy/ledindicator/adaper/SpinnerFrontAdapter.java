package com.wy.ledindicator.adaper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wy.ledindicator.R;
import com.wy.ledindicator.utils.FontManager;

import java.util.List;

public class SpinnerFrontAdapter extends BaseAdapter {

    Context mContext;
    List<String> mList;

    public SpinnerFrontAdapter(Context mContext){
        this.mContext = mContext;
        mList = FontManager.getFonts();
    }

    @Override
    public int getCount() {
        return mList.size();
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
        ViewHodler viewHolder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_font,null);
            viewHolder = new ViewHodler();
            viewHolder.line = convertView.findViewById(R.id.line);
            viewHolder.text = convertView.findViewById(R.id.text_front);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHodler) convertView.getTag();
        viewHolder.text.setText(FontManager.getFontFromPosition(position));
        Typeface tf = Typeface.createFromAsset(((Activity)mContext).getAssets(),
                "fonts/"+FontManager.getFont(position));
        viewHolder.text.setTypeface(tf);
        viewHolder.line = convertView.findViewById(R.id.line);
        if(position==27){
            viewHolder.line.setVisibility(View.GONE);
        }
        return convertView;
    }

    public int getPosition(String ttfs){
        for (int i=0;i<FontManager.fonts_ttf.size();i++){
            if(ttfs.equals(FontManager.fonts_ttf.get(i))){
                return i;
            }
        }
        return 0;
    }

    public class ViewHodler{
        TextView text;
        View line;
    }
}
