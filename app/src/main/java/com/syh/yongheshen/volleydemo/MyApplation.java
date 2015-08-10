package com.syh.yongheshen.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 作者： yongheshen on 15/8/6.
 * 描述：
 */
public class MyApplation extends Application
{
    public  static RequestQueue requestQueue;

    public void onCreate()
    {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueue()
    {
        return requestQueue;
    }

}
