package com.hao.base.base.mvp;

import com.hao.base.base.BaseActivity;
import com.hao.base.base.BaseFragment;

public abstract class BaseMvpFragment<M extends IBaseModel, P extends BasePresenter> extends BaseFragment implements IBaseView {

    public M model;
    public P presenter;

    @Override
    protected void initData() {
        presenter = (P) initPresenter();
        if (presenter != null) {
            model = (M) presenter.getmModel();
            if (model != null) {

                presenter.attachView(model, this);


            }

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){

            presenter.detachView();//解除绑定，回收资源，释放内存，提高性能

        }
    }
}
