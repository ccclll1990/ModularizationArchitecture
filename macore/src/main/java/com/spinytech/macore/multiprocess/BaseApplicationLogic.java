package com.spinytech.macore.multiprocess;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

import com.spinytech.macore.MaApplicationLike;

/**
 * Created by wanglei on 2016/11/25.
 */

public class BaseApplicationLogic {
    protected MaApplicationLike mApplicationLike;
    public BaseApplicationLogic() {
    }

    public void setApplication(@NonNull MaApplicationLike applicationLike) {
        mApplicationLike = applicationLike;
    }

    public void onCreate() {
    }

    public void onTerminate() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
