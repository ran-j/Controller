package br.ucam_campos.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO Auto-generated method stub
        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action)) {
            MainActivity.aumentar();
        } else if (AppConstant.STOP_ACTION.equals(action)) {
            MainActivity.diminuir();
        } else if (AppConstant.STOP_MAYBE.equals(action)) {
            MainActivity.mutar();
        }
    }

}