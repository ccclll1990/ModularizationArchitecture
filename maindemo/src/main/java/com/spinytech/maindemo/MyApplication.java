package com.spinytech.maindemo;

import com.spinytech.macore.MaApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by wanglei on 2016/11/29.
 */

public class MyApplication extends MaApplication {


    public MyApplication(){
        super(ShareConstants.TINKER_ENABLE_ALL,"com.spinytech.maindemo.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader",false);
    }
}
