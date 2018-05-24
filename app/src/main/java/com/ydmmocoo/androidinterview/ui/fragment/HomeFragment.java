package com.ydmmocoo.androidinterview.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.socks.library.KLog;
import com.ydmmocoo.androidinterview.R;
import com.ydmmocoo.androidinterview.base.BaseFragment;
import com.ydmmocoo.androidinterview.model.Category;
import com.ydmmocoo.androidinterview.ui.activity.SubjectDetailActivity;
import com.ydmmocoo.androidinterview.ui.adapter.GvHomePageAdapter;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tbl)
    QMUITopBar mTbl;
    @BindView(R.id.gv_classification)
    GridView mGvClassification;

    private GvHomePageAdapter mAdapter;
    private List<Category> mList;

    @Override
    protected int getMainView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        mTbl.setTitle(getResources().getString(R.string.tab_home));
        mAdapter = new GvHomePageAdapter(mContext, mList);
        mGvClassification.setAdapter(mAdapter);
        getData();

        mGvClassification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, SubjectDetailActivity.class);
                intent.putExtra("title", mList.get(i).getName());
                startActivity(intent);
            }
        });
    }

    private void getData() {
        BmobQuery<Category> query = new BmobQuery<Category>();
        //执行查询方法
        query.findObjects(new FindListener<Category>() {
            @Override
            public void done(List<Category> object, BmobException e) {
                if (e == null) {
                    mList = object;
                    mAdapter.setData(mList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {
    }
}
