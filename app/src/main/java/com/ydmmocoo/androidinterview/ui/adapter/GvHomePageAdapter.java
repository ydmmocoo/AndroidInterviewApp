package com.ydmmocoo.androidinterview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydmmocoo.androidinterview.R;
import com.ydmmocoo.androidinterview.loader.ILoader;
import com.ydmmocoo.androidinterview.loader.LoaderManager;
import com.ydmmocoo.androidinterview.model.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GvHomePageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Category> mList;

    public GvHomePageAdapter(Context context, List<Category> list) {
        mContext = context;
        mList = list;
    }

    public void setData(List<Category> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_gv_home_page, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTvClassification.setText(mList.get(i).getName());
        LoaderManager.getLoader().loadNet(holder.mIvIcon,mList.get(i).getIcon().getUrl(), ILoader.Options.defaultOptions());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_classification)
        TextView mTvClassification;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
