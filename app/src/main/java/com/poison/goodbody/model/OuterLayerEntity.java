package com.poison.goodbody.model;

import java.util.List;

/**
 * Created by PoisonH on 2016/2/19.
 */
public class OuterLayerEntity
{
    private boolean status;
    private String message;
    private Data data;

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Data getData()
    {
        return data;
    }

    public void setData(Data data)
    {
        this.data = data;
    }
}
