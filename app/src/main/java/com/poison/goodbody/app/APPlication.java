package com.poison.goodbody.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by PoisonH on 2016/3/1.
 */
public class APPlication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
