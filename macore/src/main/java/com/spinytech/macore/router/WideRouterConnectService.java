package com.spinytech.macore.router;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spinytech.macore.IWideRouterAIDL;
import com.spinytech.macore.MaActionResult;
import com.spinytech.macore.MaApplication;
import com.spinytech.macore.MaApplicationLike;
import com.spinytech.macore.tools.Logger;
import com.tencent.tinker.loader.app.TinkerApplication;


/**
 * Created by wanglei on 2016/11/29.
 */

public final class WideRouterConnectService extends Service {
    private static final String TAG = "WideRouterConnectService";

    @Override
    public void onCreate(){
        super.onCreate();
//        if (!(getApplication() instanceof MaApplication)) {
//            throw new RuntimeException("Please check your AndroidManifest.xml and make sure the application is instance of MaApplication.");
//        }



    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        String domain = intent.getStringExtra("domain");
        if (WideRouter.getInstance(MaApplicationLike.getMaApplicationLike()).mIsStopping) {
            Logger.e(TAG,"Bind error: The wide router is stopping.");
            return null;
        }
        if (domain != null && domain.length() > 0) {
            boolean hasRegistered = WideRouter.getInstance(MaApplicationLike.getMaApplicationLike()).checkLocalRouterHasRegistered(domain);
            if (!hasRegistered) {
                Logger.e(TAG,"Bind error: The local router of process " + domain + " is not bidirectional." +
                        "\nPlease create a Service extend LocalRouterConnectService then register it in AndroidManifest.xml and the initializeAllProcessRouter method of MaApplication." +
                        "\nFor example:" +
                        "\n<service android:name=\"XXXConnectService\" android:process=\"your process name\"/>" +
                        "\nWideRouter.registerLocalRouter(\"your process name\",XXXConnectService.class);");
                return null;
            }
            WideRouter.getInstance(MaApplicationLike.getMaApplicationLike()).connectLocalRouter(domain);
        } else {
            Logger.e(TAG,"Bind error: Intent do not have \"domain\" extra!");
            return null;
        }
        return stub;
    }

    IWideRouterAIDL.Stub stub = new IWideRouterAIDL.Stub() {

        @Override
        public boolean checkResponseAsync(String domain,String routerRequest) throws RemoteException{
            return
                    WideRouter.getInstance(MaApplicationLike.getMaApplicationLike())
                            .answerLocalAsync(domain,routerRequest);
        }

        @Override
        public String route(String domain,String routerRequest){
            try {
                return WideRouter.getInstance(MaApplicationLike.getMaApplicationLike())
                        .route(domain,routerRequest)
                        .mResultString;
            } catch (Exception e) {
                e.printStackTrace();
                return new MaActionResult.Builder()
                        .code(MaActionResult.CODE_ERROR)
                        .msg(e.getMessage())
                        .build()
                        .toString();
            }
        }

        @Override
        public boolean stopRouter(String domain) throws RemoteException{
            return WideRouter.getInstance(MaApplicationLike.getMaApplicationLike())
                    .disconnectLocalRouter(domain);
        }

    };
}
