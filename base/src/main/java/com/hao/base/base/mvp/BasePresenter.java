package com.hao.base.base.mvp;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public abstract class BasePresenter<M ,V> {

    public M mModel;
    public V mView;
    private WeakReference<V> weakReference;

    public abstract M getmModel();

    /**
     * 绑定view
     * @param mModel
     * @param mView
     */
    public void attachView(M mModel,V mView){

        this.mModel=mModel;
        weakReference=new WeakReference<>(mView);
        this.mView=weakReference.get();

    }

    /**
     * 解绑view
     */
    public void detachView(){

        if (weakReference!=null){

            weakReference.clear();
            weakReference=null;

        }

    }

}
