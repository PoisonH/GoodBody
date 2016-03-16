package com.poison.goodbody.utils;

import android.content.Context;
import android.util.Log;

import com.poison.goodbody.bean.DataList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class CacheManager
{

    // wifi缓存时间为5分钟
    private static long wifi_cache_time = 5 * 60 * 1000;
    // 其他网络环境为1小时
    private static long other_cache_time = 60 * 60 * 1000;

    /**
     * @param context
     * @param lists
     * @return
     */
    public static boolean saveObject(Context context, List<DataList> lists, String filename)
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        String file = getAppStoragePath(context) + "/" + filename + "DataList.bat";
        try
        {
            // fos = context.openFileOutput(getAppStoragePath(context), Context.MODE_PRIVATE);
            fos = new FileOutputStream(file, false);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(lists);
            oos.flush();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            try
            {
                oos.close();
            } catch (Exception e)
            {
            }
            try
            {
                fos.close();
            } catch (Exception e)
            {
            }
        }
    }

    /**
     * 读取对象
     *
     * @return
     * @throws IOException
     */
    public static List<DataList> readObject(Context context, String filename)
    {
        String file = getAppStoragePath(context) + "/" + filename + "DataList.bat";
        if (!isExistDataCache(context, file))
        {
            return null;
        }
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try
        {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            return (List<DataList>) ois.readObject();
        } catch (FileNotFoundException e)
        {
        } catch (Exception e)
        {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException)
            {
                File data = context.getFileStreamPath(file);
                data.delete();
            }
        } finally
        {
            try
            {
                ois.close();
            } catch (Exception e)
            {
            }
            try
            {
                fis.close();
            } catch (Exception e)
            {
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public static boolean isExistDataCache(Context context, String cachefile)
    {
        if (context == null)
        {
            return false;
        }
        boolean exist = false;
        //  File data = context.getFileStreamPath(cachefile);
        File data = new File(cachefile);
        if (data.exists())
        {
            exist = true;
        }
        return exist;
    }

    /**
     * 判断缓存是否已经失效
     */
    public static boolean isCacheDataFailure(Context context, String filename)
    {
        String cachefile = getAppStoragePath(context) + "/" + filename + "DataList.bat";
        //    File data = context.getFileStreamPath(cachefile);
        File data = new File(cachefile);
        if (!data.exists())
        {

            return false;
        }
        long existTime = System.currentTimeMillis() - data.lastModified();
        boolean failure = false;
        if (NetUtils.isWifi(context))
        {
            failure = existTime > wifi_cache_time ? true : false;
        } else
        {
            failure = existTime > other_cache_time ? true : false;
        }
        return failure;
    }

    /**
     * 获取目录
     *
     * @param context
     * @return /data/data/com.xxx.xxx/files
     */
    public static String getAppStoragePath(Context context)
    {
        String path = context.getApplicationContext().getFilesDir().getAbsolutePath();
        Log.i("Cache", path);
        return path;
    }
}
