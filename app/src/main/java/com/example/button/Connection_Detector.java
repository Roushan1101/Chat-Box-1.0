package com.example.button;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection_Detector {
    Context context;

    public Connection_Detector(Context context) {
        this.context=context;
    }
    public boolean isConnected(){
        ConnectivityManager check=(ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(check!=null){
            NetworkInfo info=check.getActiveNetworkInfo();
            if(info!=null){
                if(info.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
