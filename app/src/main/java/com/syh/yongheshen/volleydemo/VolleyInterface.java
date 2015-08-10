package com.syh.yongheshen.volleydemo;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 作者： yongheshen on 15/8/7.
 * 描述：
 */
public abstract  class VolleyInterface
{
    public Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyInterface(Context context,Response.Listener<String> listener
            ,Response.ErrorListener errorListener)
    {
        this.mContext = context;
        this.mErrorListener = errorListener;
        this.mListener = listener;
    }

    public abstract void myResponseSucces(String result);

    public abstract void myResponseError(VolleyError error);

    public Response.Listener<String> loadListener()
    {
        mListener = new Response.Listener<String>()
        {
            public void onResponse(String s)
            {
                myResponseSucces(s);
            }
        };
        return mListener;
    }

    public Response.ErrorListener loadErroeListner()
    {
        mErrorListener = new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError volleyError)
            {
                myResponseError(volleyError);
            }
        };
        return mErrorListener;
    }
}
