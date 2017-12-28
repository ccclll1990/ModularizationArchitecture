package com.spinytech.picdemo;

import android.util.Log;

import com.spinytech.macore.multiprocess.BaseApplicationLogic;
import com.spinytech.macore.router.LocalRouter;

/**
 * Created by wanglei on 2017/1/4.
 */

public class PicApplicationLogic extends BaseApplicationLogic {

    @Override
    public void onCreate() {
        super.onCreate();
        LocalRouter.getInstance(mApplicationLike).registerProvider("pic",new PicProvider());


    }
}
