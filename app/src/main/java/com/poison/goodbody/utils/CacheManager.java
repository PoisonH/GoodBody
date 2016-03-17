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
import java.math.BigDecimal;
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

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     *
     * @param context
     */
    public static void cleanFiles(Context context)
    {
        deleteDir(context.getFilesDir());
    }

    /**
     * 递归删除
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir)
    {
        if (dir != null && dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success)
                {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /***
     * 递归获取文件大小
     *
     * @return
     */
    public static long getFolderSize(File file) throws Exception
    {
        long size = 0;
        try
        {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++)
            {
                // 如果下面还有文件
                if (fileList[i].isDirectory())
                {
                    size = size + getFolderSize(fileList[i]);
                } else
                {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size)
    {
        double kiloByte = size / 1024;
        if (kiloByte < 1)
        {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1)
        {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1)
        {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1)
        {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getCacheSize(Context context) throws Exception
    {
        return getFormatSize(getFolderSize(context.getCacheDir()));
    }
}
