package com.poison.goodbody.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class HttpUtils
{
    private static String mStrUrl = "http://www.ys137.com/api.php?act=getlist&catid=";
    private static String mStrUrl_ = "&page=1&pagesize=20";
    private static String mStrData;

    public static String getData(int catid)
    {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request mRequest = new Request.Builder().url(mStrUrl + catid + mStrUrl_).build();
        Call mCall = mOkHttpClient.newCall(mRequest);
        mCall.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                mStrData = response.body().string();
            }
        });

        return mStrData;
    }

}
