package com.spinytech.musicdemo;

import android.content.Intent;
import android.util.Log;

import com.spinytech.macore.router.LocalRouterConnectService;

/**
 * Created by wanglei on 2016/12/28.
 */

public class MusicRouterConnectService extends LocalRouterConnectService {
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
