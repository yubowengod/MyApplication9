package com.example.god.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by GOD on 2016/9/9.
 */
public class Pic_Adapter extends ArrayAdapter<Pic> {
    private int resourceId;

    main main_Pic_Adapter;
    public Pic_Adapter(Context context, int textViewResourceId,
                        List<Pic> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pic fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.picimage = (ImageView) view.findViewById(R.id.item_iv);
            viewHolder.picname = (TextView) view.findViewById(R.id.item_tv);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        String url = main_Pic_Adapter.urls.get(position);
        //在ListView中加载列表图片
        Glide.with(main_Pic_Adapter).load(url).centerCrop()
                .placeholder(R.mipmap.ic_launcher).crossFade()
                .into(viewHolder.picimage);
        viewHolder.picname.setText(url);
//        viewHolder.picimage.setImageBitmap(fruit.getImageId());
//        viewHolder.picname.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView picimage;
        TextView picname;
    }

}
