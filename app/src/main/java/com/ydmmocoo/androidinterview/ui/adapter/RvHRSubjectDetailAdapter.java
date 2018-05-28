package com.ydmmocoo.androidinterview.ui.adapter;

import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.socks.library.KLog;
import com.ydmmocoo.androidinterview.R;
import com.ydmmocoo.androidinterview.model.AlgorithmSubject;
import com.ydmmocoo.androidinterview.model.HRSubject;

import java.util.List;

public class RvHRSubjectDetailAdapter extends BaseQuickAdapter<HRSubject, BaseViewHolder> {

    public RvHRSubjectDetailAdapter(int layoutResId, @Nullable List<HRSubject> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HRSubject item) {
        helper.setText(R.id.tv_title, (helper.getLayoutPosition()+1) + "." + item.getQuestion());

        final QMUITipDialog tipDialog = new QMUITipDialog.Builder(mContext)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        final WebView webView = helper.getView(R.id.wv_content);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setBlockNetworkImage(true);
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        settings.setLoadWithOverviewMode(true);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    tipDialog.dismiss();
                    webView.getSettings().setBlockNetworkImage(false);
                } else {
                    // 网页加载中
                    tipDialog.show();
                }
            }
        });
        KLog.e(item.getAnswer().getUrl());
        webView.loadUrl(item.getAnswer().getUrl());
    }
}
