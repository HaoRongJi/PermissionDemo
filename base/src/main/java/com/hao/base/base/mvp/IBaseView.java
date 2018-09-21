package com.hao.base.base.mvp;

public interface IBaseView {

    BasePresenter initPresenter();

    void showProgressBar();//显示加载
    void hideProgress();//隐藏加载

    void fail(String Msg);//请求失败


}
