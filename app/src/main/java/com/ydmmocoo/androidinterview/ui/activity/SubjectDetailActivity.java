package com.ydmmocoo.androidinterview.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.socks.library.KLog;
import com.ydmmocoo.androidinterview.R;
import com.ydmmocoo.androidinterview.base.BaseActivity;
import com.ydmmocoo.androidinterview.model.AndroidSubject;
import com.ydmmocoo.androidinterview.model.JavaSubject;
import com.ydmmocoo.androidinterview.ui.adapter.RvAndroidSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvJavaSubjectDetailAdapter;

import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SubjectDetailActivity extends BaseActivity {

    @BindView(R.id.tbl)
    QMUITopBar mTbl;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;

    //Java题库
    private RvJavaSubjectDetailAdapter mJavaAdapter;
    private List<JavaSubject> mJavaList;
    //Android题库
    private RvAndroidSubjectDetailAdapter mAndroidAdapter;
    private List<AndroidSubject> mAndroidList;

    private SnapHelper mSnapHelper;

    @Override
    protected int getMainView() {
        return R.layout.activity_subject_detail;
    }

    @Override
    protected void init() {
        String title = getIntent().getStringExtra("title");
        mTbl.setTitle(title);
        //返回
        mTbl.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRvContent.setLayoutManager(manager);
        if (title.contains("Java面试题")) {
            mJavaAdapter = new RvJavaSubjectDetailAdapter(R.layout.item_rv_subject_detail, mJavaList);
            mRvContent.setAdapter(mJavaAdapter);
            getJavaData();
        } else if (title.contains("Android面试题")) {
            mAndroidAdapter = new RvAndroidSubjectDetailAdapter(R.layout.item_rv_subject_detail, mAndroidList);
            mRvContent.setAdapter(mAndroidAdapter);
            getAndroidData();
        } else if (title.contains("Android开源库面试题")) {

        } else if (title.contains("Android网络编程面试题")) {

        } else if (title.contains("数据结构与算法面试题")) {

        } else if (title.contains("HR面试题")) {

        } else {
            mJavaAdapter = new RvJavaSubjectDetailAdapter(R.layout.item_rv_subject_detail, mJavaList);
            mRvContent.setAdapter(mJavaAdapter);
            getJavaData();
        }

        // PagerSnapHelper每次只能滚动一个item;用LinearSnapHelper则可以一次滚动多个，并最终保证定位
        // mSnapHelper = new LinearSnapHelper();
        mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(mRvContent);
    }

    private void getJavaData() {
        BmobQuery<JavaSubject> query = new BmobQuery<JavaSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<JavaSubject>() {
            @Override
            public void done(List<JavaSubject> object, BmobException e) {
                if (e == null) {
                    mJavaList = object;
                    mJavaAdapter.setNewData(mJavaList);
                    /*KLog.e("查询成功：共" + object.size() + "条数据。");
                    for (JavaSubject javaSubject : object) {
                        KLog.e(javaSubject.getQuestion());
                        KLog.e(javaSubject.getAnswer().getTitle());
                        KLog.e(javaSubject.getAnswer().getUrl());
                    }*/
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void getAndroidData() {
        BmobQuery<AndroidSubject> query = new BmobQuery<AndroidSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<AndroidSubject>() {
            @Override
            public void done(List<AndroidSubject> object, BmobException e) {
                if (e == null) {
                    mAndroidList = object;
                    mAndroidAdapter.setNewData(mAndroidList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
}
