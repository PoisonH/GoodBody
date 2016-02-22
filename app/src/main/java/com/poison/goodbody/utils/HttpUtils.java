package com.poison.goodbody.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.poison.goodbody.model.DataListEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class HttpUtils
{
    private static String url = "http://www.ys137.com/api.php?act=getlist&catid=0&page=1&pagesize=5";
    private static String data;

    public static List<DataListEntity> getData(int catid, Context context)
    {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("TAG", response);
                        data = response.toString();
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(stringRequest);
        return parseJson(data);
    }

    private static List<DataListEntity> parseJson(String str)
    {
        List<DataListEntity> mList = new ArrayList<>();
        try
        {

        } catch (Exception e)
        {

        }
        return mList;
    }


}
