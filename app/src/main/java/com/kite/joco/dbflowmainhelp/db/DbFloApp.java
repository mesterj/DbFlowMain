package com.kite.joco.dbflowmainhelp.db;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Joco on 2015.08.31..
 */
public class DbFloApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}


