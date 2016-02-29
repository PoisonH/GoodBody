package com.poison.goodbody.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class HttpUtils
{
    private static String url = "http://www.ys137.com/api.php?act=getlist&catid=0&page=1&pagesize=5";
    private static String data;

    public static String getData(Context context)
    {
        final ProgressDialog mDialog = ProgressDialog.show(context, "正在加载", "loading.........");
        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        if (mDialog.isShowing() && mDialog != null)
                        {
                            mDialog.dismiss();
                        }
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
        Toast.makeText(context,"显示"+data,Toast.LENGTH_LONG).show();
        return data;

    }
}
