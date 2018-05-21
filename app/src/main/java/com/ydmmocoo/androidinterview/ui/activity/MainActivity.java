package com.ydmmocoo.androidinterview.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.ydmmocoo.androidinterview.R;
import com.ydmmocoo.androidinterview.base.BaseActivity;
import com.ydmmocoo.androidinterview.ui.fragment.AboutFragment;
import com.ydmmocoo.androidinterview.ui.fragment.AccountFragment;
import com.ydmmocoo.androidinterview.ui.fragment.GankFragment;
import com.ydmmocoo.androidinterview.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabs)
    QMUITabSegment mTabs;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private TabPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getMainView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        //将所有fragment加入到集合当中
        mFragments.add(new HomeFragment());
        mFragments.add(new GankFragment());
        mFragments.add(new AccountFragment());
        mFragments.add(new AboutFragment());
        //初始化Tab
        int normalColor = QMUIResHelper.getAttrColor(mContext, R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(mContext, R.attr.qmui_config_color_blue);
        mTabs.setDefaultNormalColor(normalColor);
        mTabs.setDefaultSelectedColor(selectColor);

        QMUITabSegment.Tab home = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_home),
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_home_selected),
                getResources().getString(R.string.tab_home), false
        );
        QMUITabSegment.Tab activity = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_gank),
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_gank_selected),
                getResources().getString(R.string.tab_gank), false
        );
        QMUITabSegment.Tab account = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_account),
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_account_selected),
                getResources().getString(R.string.tab_account), false
        );
        QMUITabSegment.Tab message = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_about),
                ContextCompat.getDrawable(mContext, R.mipmap.ic_tab_about_selected),
                getResources().getString(R.string.tab_about), false
        );

        mTabs.reset();
        mTabs.addTab(home);
        mTabs.addTab(activity);
        mTabs.addTab(message);
        mTabs.addTab(account);

        //初始化ViewPager
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mAdapter.setFragments(mFragments);
        mVpContent.setAdapter(mAdapter);
        mTabs.setupWithViewPager(mVpContent, false);
        mTabs.setMode(QMUITabSegment.MODE_FIXED);
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList;

        public void setFragments(List<Fragment> fragments) {
            mFragmentList = fragments;
        }

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
