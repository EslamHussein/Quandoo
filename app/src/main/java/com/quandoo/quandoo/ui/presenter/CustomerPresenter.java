package com.quandoo.quandoo.ui.presenter;


import com.quandoo.base.presenter.BasePresenter;
import com.quandoo.quandoo.ui.view.CustomerView;


public abstract class CustomerPresenter extends BasePresenter<CustomerView> {
    public abstract void getCustomer();
}
