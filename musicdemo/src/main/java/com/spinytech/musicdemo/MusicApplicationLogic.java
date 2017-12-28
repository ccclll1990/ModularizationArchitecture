package com.spinytech.musicdemo;

import android.util.Log;

import com.spinytech.macore.multiprocess.BaseApplicationLogic;
import com.spinytech.macore.router.LocalRouter;

/**
 * Created by wanglei on 2016/11/30.
 */

public class MusicApplicationLogic extends BaseApplicationLogic {
    @Override
    public void onCreate(){
        super.onCreate();
        LocalRouter.getInstance(mApplicationLike).registerProvider("music",new MusicProvider());


    }
}
