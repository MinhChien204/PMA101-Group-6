package com.example.less3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.less3.R;

public class ProfileOptionAdapter extends BaseAdapter {

    private Context context;
    private String[] options;
    private int[] icons;

    public ProfileOptionAdapter(Context context, String[] options, int[] icons) {
        this.context = context;
        this.options = options;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return options.length;
    }

    @Override
    public Object getItem(int position) {
        return options[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_profile_option, parent, false);
        }

        ImageView ivOptionIcon = convertView.findViewById(R.id.iv_option_icon);
        TextView tvOptionText = convertView.findViewById(R.id.tv_option_text);

        ivOptionIcon.setImageResource(icons[position]);
        tvOptionText.setText(options[position]);

        return convertView;
    }
}
