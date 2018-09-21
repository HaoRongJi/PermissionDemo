package com.hao.base.base;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {


    public  boolean isFullScreen=false;
    private boolean isStatus=false;
    private boolean isToolBar=false;
    Unbinder unbinder=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayoutId());
        unbinder=ButterKnife.bind(this);
        initView();
        initData();
        setFullScreen();
    }

    protected abstract void initData();

    public abstract boolean getIsFullScreen();

    public void setFullScreen(){


        if (getIsFullScreen()){

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        }

    }


    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定根布局
     * @return
     */
    protected abstract int bindLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){

            unbinder.unbind();
            unbinder=null;

        }
    }

    /**
     * 无参跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz){

        startActivity(new Intent(this,clz));

    }/**
     * 有参跳转
     * @param clz
     */
    public void startActivity(Class<? extends Activity> clz,Bundle bundle){

        Intent intent=new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /**
     * 吐司
     * @param Msg
     */
    public void showToast(String Msg){

        Toast.makeText(this, Msg, Toast.LENGTH_SHORT).show();

    }/**
     * 显示带时间的toast
     * @param Msg
     */
    public void showToast(String Msg,int time){

        Toast.makeText(this, Msg, time).show();

    }


}
