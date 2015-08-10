package com.syh.yongheshen.volleydemo;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * 作者： yongheshen on 15/8/7.
 * 描述：
 */
public class VolleyRequest
{

    public static StringRequest stringRequest;
    public static Context context;
    public static VolleyInterface volleyInterface;

    public static void RequestGet(Context context,String url,String tag,
                                  VolleyInterface volleyInterface)
    {
        MyApplation.getHttpQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url,volleyInterface.loadListener(),
                volleyInterface.loadErroeListner());
        stringRequest.setTag(tag);
        MyApplation.getHttpQueue().add(stringRequest);
        MyApplation.getHttpQueue().start();
    }

    public static void RequestPost(Context context,String url,String tag, final Map<String,String> params
            ,VolleyInterface volleyInterface)
    {
        MyApplation.getHttpQueue().cancelAll(tag);
        stringRequest = new StringRequest(url,volleyInterface.loadListener()
                ,volleyInterface.loadErroeListner()){

            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        stringRequest.setTag(tag);
        MyApplation.getHttpQueue().add(stringRequest);
        MyApplation.getHttpQueue().start();
    }
}
