package com.spinytech.maindemo;


import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.spinytech.macore.MaApplicationLike;
import com.spinytech.macore.router.WideRouter;
import com.spinytech.musicdemo.MusicApplicationLogic;
import com.spinytech.musicdemo.MusicRouterConnectService;
import com.spinytech.picdemo.PicApplicationLogic;
import com.spinytech.picdemo.PicRouterConnectService;
import com.spinytech.webdemo.WebApplicationLogic;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @version V1.0
 * @Des
 * @FileName: com.spinytech.maindemo.MyApplicationLike.java
 * @author: cl1
 * @date: 2017-12-25 10:45
 */
public class MyApplicationLike extends MaApplicationLike {

    private Application application;

    public MyApplicationLike(Application application,int tinkerFlags,
                             boolean tinkerLoadVerifyFlag,long applicationStartElapsedTime,
                             long applicationStartMillisTime,Intent tinkerResultIntent){
        super(application,tinkerFlags,tinkerLoadVerifyFlag,applicationStartElapsedTime,applicationStartMillisTime,tinkerResultIntent);
    }

    public Application getMyApplication(){

        return application;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        this.application = getApplication();


        Beta.canNotifyUserRestart = true;
        // 设置开发者模式
        CrashReport.setIsDevelopmentDevice(getApplication(),true);

        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(getApplication(),"bafac169fd",true);



    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base){
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);



    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks){
        getApplication().registerActivityLifecycleCallbacks(callbacks);

    }

    @Override
    public void initializeAllProcessRouter(){


        WideRouter.registerLocalRouter("com.spinytech.maindemo",MainRouterConnectService.class);
        WideRouter.registerLocalRouter("com.spinytech.maindemo:music",MusicRouterConnectService.class);
        WideRouter.registerLocalRouter("com.spinytech.maindemo:pic",PicRouterConnectService.class);

    }

    @Override
    protected void initializeLogic(){


        registerApplicationLogic("com.spinytech.maindemo",999,MainApplicationLogic.class);
        registerApplicationLogic("com.spinytech.maindemo",998,WebApplicationLogic.class);
        registerApplicationLogic("com.spinytech.maindemo:music",999,MusicApplicationLogic.class);
        registerApplicationLogic("com.spinytech.maindemo:pic",999,PicApplicationLogic.class);

    }

    @Override
    public boolean needMultipleProcess(){


        return true;
    }
}
