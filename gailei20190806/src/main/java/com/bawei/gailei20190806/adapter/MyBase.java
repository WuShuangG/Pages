package com.bawei.gailei20190806.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.gailei20190806.R;
import com.bawei.gailei20190806.bean.ListBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * author: 盖磊
 * data: 2019/8/6 10:10:01
 * function:
 */
public class MyBase extends BaseAdapter {
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 5;


    private List<ListBean.DataInfo> list;
    private Context context;

    public MyBase(List<ListBean.DataInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position %2 == 0 && position != 4){
            return TYPE_ONE;
        }if (position %2 != 0 && position != 4){
            return  TYPE_TWO;
        }else{
            return TYPE_THREE;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderOne holderOne = null;
        ViewHolderTwo holderTwo = null;
        ViewHolderThree holderThree = null;
        int type = getItemViewType(position);
        if (convertView == null){
            switch (type){
                case TYPE_ONE:
                    convertView =View.inflate(context, R.layout.item_one,null);
                    holderOne = new ViewHolderOne();
                    holderOne.iv1 = convertView.findViewById(R.id.iv1);
                    holderOne.tv1 = convertView.findViewById(R.id.tv1);
                    convertView.setTag(holderOne);
                    break;
                case TYPE_TWO:
                    convertView =View.inflate(context, R.layout.item_two,null);
                    holderTwo = new ViewHolderTwo();
                    holderTwo.iv2 = convertView.findViewById(R.id.iv2);
                    holderTwo.tv2 = convertView.findViewById(R.id.tv2);
                    convertView.setTag(holderTwo);
                    break;
                case TYPE_THREE:
                    convertView =View.inflate(context, R.layout.item_three,null);
                    holderThree = new ViewHolderThree();
                    holderThree.iv3 = convertView.findViewById(R.id.iv3);
                    holderThree.ivs = convertView.findViewById(R.id.ivs);
                    convertView.setTag(holderThree);
                    break;
            }
        }else {
            switch (type){
                case TYPE_ONE:
                    holderOne = (ViewHolderOne) convertView.getTag();
                    break;
                case TYPE_TWO:
                    holderTwo = (ViewHolderTwo) convertView.getTag();
                    break;
                case TYPE_THREE:
                    holderThree = (ViewHolderThree) convertView.getTag();
                    break;
            }
        }


        switch (type){
            case TYPE_ONE:
                holderOne.tv1.setText(list.get(position).getContent());
                Glide.with(context).load(list.get(position).getAvatar()).into(holderOne.iv1);
                break;
            case TYPE_TWO:
                holderTwo.tv2.setText(list.get(position).getContent());
                Glide.with(context).load(list.get(position).getAvatar()).into(holderTwo.iv2);
                break;
            case TYPE_THREE:
                Glide.with(context).load(list.get(position).getAvatar()).into(holderThree.iv3);
                Glide.with(context).load(list.get(position).getImages().get(0).getImageurl()).into(holderThree.ivs);
                break;
        }
        return convertView;
    }

    class ViewHolderOne{
        ImageView iv1;
        TextView tv1;
    }
    class ViewHolderTwo{
        ImageView iv2;
        TextView tv2;
    }
    class ViewHolderThree{
        ImageView iv3;
        ImageView ivs;
    }
}
