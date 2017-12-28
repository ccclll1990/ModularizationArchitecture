package com.spinytech.macore;

import com.tencent.tinker.loader.app.TinkerApplication;

/**
 * Created by wanglei on 2016/11/25.
 */

public abstract class MaApplication extends TinkerApplication {
    protected MaApplication(int tinkerFlags,String delegateClassName,String loaderClassName,boolean tinkerLoadVerifyFlag){
        super(tinkerFlags,delegateClassName,loaderClassName,tinkerLoadVerifyFlag);
    }

}
