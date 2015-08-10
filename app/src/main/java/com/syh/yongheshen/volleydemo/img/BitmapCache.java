package com.syh.yongheshen.volleydemo.img;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 作者： yongheshen on 15/8/10.
 * 描述：
 */
public class BitmapCache implements ImageCache
{

    private LruCache<String,Bitmap> lruCache;
    private int Max = 10*1024*1024;

    public BitmapCache()
    {
        lruCache = new LruCache<String,Bitmap>(Max)
        {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String key)
    {
        return lruCache.get(key);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap)
    {
         lruCache.put(s,bitmap);
    }
}
