package com.syh.yongheshen.volleydemo.img;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.syh.yongheshen.volleydemo.MyApplation;
import com.syh.yongheshen.volleydemo.R;

/**
 * 作者： yongheshen on 15/8/10.
 * 描述：
 */
public class ImgArc extends Activity
{
    private ImageView img;
    private NetworkImageView networkImageView;

    private static final String IMG_URL1 = "http://f.hiphotos.baidu.com/image/pic/item/63d0f703918fa0ec9d7fc4ae239759ee3c6ddbdd.jpg";
    private static final String IMG_URL2 = "http://b.hiphotos.baidu.com/image/pic/item/3b292df5e0fe992585d8025a36a85edf8cb171e7.jpg";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_layout);
        initViews();
//        getImgWithImageRequest();
        getImgWithImgLoad();
        ImageLoader loader = new ImageLoader(MyApplation.getHttpQueue(),new BitmapCache());
        networkImageView.setDefaultImageResId(R.drawable.defaut_img);
        networkImageView.setErrorImageResId(R.drawable.defaut_img);
        networkImageView.setImageUrl(IMG_URL2,loader);
    }

    private void initViews()
    {
        img = (ImageView) findViewById(R.id.iv_img);
        networkImageView = (NetworkImageView) findViewById(R.id.iv_net);
    }

    private void getImgWithImageRequest()
    {
        ImageRequest request = new ImageRequest(IMG_URL2, new Response.Listener<Bitmap>()
        {

            public void onResponse(Bitmap bitmap)
            {
                img.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError volleyError)
            {
                System.out.println("加载失败");
                img.setImageResource(R.drawable.defaut_img);
            }
        });
        MyApplation.getHttpQueue().add(request);
    }

    private void getImgWithImgLoad()
    {
        ImageLoader loader = new ImageLoader(MyApplation.getHttpQueue(),new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(img,R.drawable.defaut_img,R.drawable.defaut_img);
        loader.get(IMG_URL1,listener);
    }
}
