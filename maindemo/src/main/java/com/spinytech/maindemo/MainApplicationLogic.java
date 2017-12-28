package com.spinytech.maindemo;

import android.util.Log;

import com.spinytech.macore.multiprocess.BaseApplicationLogic;
import com.spinytech.macore.router.LocalRouter;

/**
 * Created by wanglei on 2016/11/29.
 */

public class MainApplicationLogic extends BaseApplicationLogic {
    @Override
    public void onCreate(){
        super.onCreate();
        LocalRouter.getInstance(mApplicationLike).registerProvider("main",new MainProvider());
    }
}
