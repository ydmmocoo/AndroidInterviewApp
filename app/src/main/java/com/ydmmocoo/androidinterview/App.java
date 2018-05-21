package com.ydmmocoo.androidinterview;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.socks.library.KLog;
import com.ydmmocoo.androidinterview.loader.GlideLoader;
import com.ydmmocoo.androidinterview.loader.LoaderManager;

import cn.bmob.v3.Bmob;

/**
 * 数据地址 ：https://github.com/guoxiaoxing/android-interview
 */
public class App extends Application{

    public static Context mAppContext = null;
    public static String mCacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = getApplicationContext();

        //初始化KLog
        KLog.init(BuildConfig.LOG_DEBUG);
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            mCacheDir = mAppContext.getExternalCacheDir().toString();
        } else {
            mCacheDir = mAppContext.getCacheDir().toString();
        }

        //初始化图片加载
        LoaderManager.setLoader(new GlideLoader());
        LoaderManager.getLoader().init(this);
        //初始化网络加载框架
        initNet();

        //第一：默认初始化
        Bmob.initialize(this, "2ce463adc4a4d4c7a88bae4043254472","bmob");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    /**
     * 判断SDCard是否存在
     *
     * @return
     */
    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    private void initNet(){
    }
}
