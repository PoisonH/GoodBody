package com.poison.goodbody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity
{
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initToolbar();
    }

    private void initView()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
    }


    private void initToolbar()
    {
        mToolbar.setTitle("好身体");
        setSupportActionBar(mToolbar);
        //给左上角加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
