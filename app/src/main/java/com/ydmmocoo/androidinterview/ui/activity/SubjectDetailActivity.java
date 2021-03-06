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
import com.ydmmocoo.androidinterview.model.AlgorithmSubject;
import com.ydmmocoo.androidinterview.model.AndroidSubject;
import com.ydmmocoo.androidinterview.model.HRSubject;
import com.ydmmocoo.androidinterview.model.JavaSubject;
import com.ydmmocoo.androidinterview.model.NetworkSubject;
import com.ydmmocoo.androidinterview.model.OpenSourceSubject;
import com.ydmmocoo.androidinterview.ui.adapter.RvAlgorithmSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvAndroidSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvHRSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvJavaSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvNetworkSubjectDetailAdapter;
import com.ydmmocoo.androidinterview.ui.adapter.RvOpenSourceSubjectDetailAdapter;

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
    //开源
    private RvOpenSourceSubjectDetailAdapter mOpenSourceAdapter;
    private List<OpenSourceSubject> mOpenSourceList;
    //网络
    private RvNetworkSubjectDetailAdapter mNetworkAdapter;
    private List<NetworkSubject> mNetworkList;
    //算法
    private RvAlgorithmSubjectDetailAdapter mAlgorithmAdapter;
    private List<AlgorithmSubject> mAlgorithmList;
    //HR
    private RvHRSubjectDetailAdapter mHRAdapter;
    private List<HRSubject> mHRList;

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
            mOpenSourceAdapter = new RvOpenSourceSubjectDetailAdapter(R.layout.item_rv_subject_detail, mOpenSourceList);
            mRvContent.setAdapter(mOpenSourceAdapter);
            getOpenSourceData();
        } else if (title.contains("Android网络编程面试题")) {
            mNetworkAdapter = new RvNetworkSubjectDetailAdapter(R.layout.item_rv_subject_detail, mNetworkList);
            mRvContent.setAdapter(mNetworkAdapter);
            getNetworkData();
        } else if (title.contains("数据结构与算法面试题")) {
            mAlgorithmAdapter = new RvAlgorithmSubjectDetailAdapter(R.layout.item_rv_subject_detail, mAlgorithmList);
            mRvContent.setAdapter(mAlgorithmAdapter);
            getAlgorithmData();
        } else if (title.contains("HR面试题")) {
            mHRAdapter = new RvHRSubjectDetailAdapter(R.layout.item_rv_subject_detail, mHRList);
            mRvContent.setAdapter(mHRAdapter);
            getHRData();
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

    private void getOpenSourceData() {
        BmobQuery<OpenSourceSubject> query = new BmobQuery<OpenSourceSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<OpenSourceSubject>() {
            @Override
            public void done(List<OpenSourceSubject> object, BmobException e) {
                if (e == null) {
                    mOpenSourceList = object;
                    mOpenSourceAdapter.setNewData(mOpenSourceList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void getNetworkData() {
        BmobQuery<NetworkSubject> query = new BmobQuery<NetworkSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<NetworkSubject>() {
            @Override
            public void done(List<NetworkSubject> object, BmobException e) {
                if (e == null) {
                    mNetworkList = object;
                    mNetworkAdapter.setNewData(mNetworkList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void getAlgorithmData() {
        BmobQuery<AlgorithmSubject> query = new BmobQuery<AlgorithmSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<AlgorithmSubject>() {
            @Override
            public void done(List<AlgorithmSubject> object, BmobException e) {
                if (e == null) {
                    mAlgorithmList = object;
                    mAlgorithmAdapter.setNewData(mAlgorithmList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void getHRData() {
        BmobQuery<HRSubject> query = new BmobQuery<HRSubject>();
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        query.include("answer");
        //执行查询方法
        query.findObjects(new FindListener<HRSubject>() {
            @Override
            public void done(List<HRSubject> object, BmobException e) {
                if (e == null) {
                    mHRList = object;
                    mHRAdapter.setNewData(mHRList);
                } else {
                    KLog.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
}
