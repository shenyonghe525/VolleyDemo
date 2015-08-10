package com.syh.yongheshen.volleydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.syh.yongheshen.volleydemo.img.ImgArc;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class MainActivity extends Activity implements View.OnClickListener{

    private static  final String GET_URL = "http://apis.juhe.cn/mobile/get?phone=13429667914&key=caa455f44c0f3fb85635c908938c6062";
    private static  final String POST_URL = "http://apis.juhe.cn/mobile/get?";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button volleyGet = (Button) findViewById(R.id.btn_volleyget);
        volleyGet.setOnClickListener(this);
        Button volleyPost = (Button) findViewById(R.id.btn_volleypost);
        volleyPost.setOnClickListener(this);
        Button toImg = (Button) findViewById(R.id.btn_to_img);
        toImg.setOnClickListener(this);
    }

    private void getWithStringRequest(String url)
    {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            public void onResponse(String s)
            {
                System.out.println("获得数据:"+s);
            }
        }, new Response.ErrorListener()
        {

            public void onErrorResponse(VolleyError volleyError)
            {
                System.out.println("获取数据失败");
            }
        });
        request.setTag("getPhoneNub");
        MyApplation.getHttpQueue().add(request);
    }

    private void getWithJsonObject(String url)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>()
        {

            public void onResponse(JSONObject jsonObject)
            {
                System.out.println("获得数据:"+jsonObject);
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError volleyError)
            {
                System.out.println("获取数据失败");
            }
        });
        jsonObjectRequest.setTag("getPhoneNub");
        MyApplation.getHttpQueue().add(jsonObjectRequest);
    }

    private void volley_Get(String url)
    {
//        getWithStringRequest(url);
        getWithJsonObject(url);
    }

    private void volleyPostWithStringRequest(String url)
    {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {

            public void onResponse(String s)
            {
                System.out.println("获得数据:"+s);
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError volleyError)
            {
                System.out.println("获取数据失败");
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map = new HashMap<>();
                map.put("phone","13429667914");
                map.put("key","caa455f44c0f3fb85635c908938c6062");
                return map;
            }
        };
        request.setTag("getPhoneNub");
        MyApplation.getHttpQueue().add(request);
    }

    private void volleyPostWithJsonObject(String url)
    {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("phone","13429667914");
        hashMap.put("key","caa455f44c0f3fb85635c908938c6062");
        JSONObject object = new JSONObject(hashMap);
        JsonObjectRequest request = new JsonObjectRequest(url, object, new Response.Listener<JSONObject>()
        {

            public void onResponse(JSONObject jsonObject)
            {
                System.out.println("获得数据:"+jsonObject);
            }
        }, new Response.ErrorListener()
        {

            public void onErrorResponse(VolleyError volleyError)
            {
                System.out.println("获取数据失败");
            }
        });
        request.setTag("getPhoneNub");
        MyApplation.getHttpQueue().add(request);
    }

    private void volley_Post(String url)
    {
//        volleyPostWithStringRequest(url);
        volleyPostWithJsonObject(url);
    }

    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.btn_volleyget:
//                volley_Get(GET_URL);
                VolleyRequest.RequestGet(this, GET_URL, "getPhoneNub", new VolleyInterface(this
                        ,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
                    public void myResponseSucces(String result) {
                        System.out.println("getMsg:"+result);
                    }

                    public void myResponseError(VolleyError error) {
                        System.out.println("数据请求失败:"+error.getMessage());
                    }
                });
                break;
            case R.id.btn_volleypost:
//                volley_Post(POST_URL);
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("phone","13429667914");
                hashMap.put("key","caa455f44c0f3fb85635c908938c6062");
                VolleyRequest.RequestPost(this, POST_URL, "", hashMap, new VolleyInterface(this
                        ,VolleyInterface.mListener,VolleyInterface.mErrorListener) {
                    @Override
                    public void myResponseSucces(String result) {
                        System.out.println("getMsg:"+result);
                    }

                    @Override
                    public void myResponseError(VolleyError error) {
                        System.out.println("数据请求失败:"+error.getMessage());
                    }
                });
                break;
            case R.id.btn_to_img:
                Intent intent = new Intent(MainActivity.this, ImgArc.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    //设置volley与activity的关联
    protected void onStop() {
        super.onStop();
        MyApplation.getHttpQueue().cancelAll("getPhoneNub");
    }
}
