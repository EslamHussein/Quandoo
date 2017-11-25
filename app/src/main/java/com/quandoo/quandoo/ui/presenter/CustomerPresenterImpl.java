package com.quandoo.quandoo.ui.presenter;

import android.util.Log;

import com.quandoo.quandoo.R;
import com.quandoo.quandoo.business.CustomerBusiness;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.ui.view.CustomerView;
import com.quandoo.utils.TextUtils;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class CustomerPresenterImpl extends CustomerPresenter {
    CompositeDisposable disposables;
    CustomerBusiness business;

    public CustomerPresenterImpl(CustomerBusiness business) {
        this.business = business;
    }

    @Override
    public void onAttach(CustomerView view) {
        super.onAttach(view);
        disposables = new CompositeDisposable();
    }

    @Override
    public void getCustomer() {
        if (!isViewAttached())
            return;
        getView().showProgressBar();

        disposables.add(business.getCustomersDB().subscribeWith(new DisposableObserver<List<Customer>>() {
            @Override
            public void onNext(@NonNull List<Customer> customers) {

                if (!isViewAttached())
                    return;

                if (customers.isEmpty()){
                    getView().showError(TextUtils.getString(R.string.no_customers));
                }else {
                    getView().showCustomers(customers);

                }
                Log.d("TAG", "onNext() returned: " + customers.size());
            }

            @Override
            public void onError(@NonNull Throwable e) {

                if (!isViewAttached())
                    return;
                getView().showError(e.getLocalizedMessage());

            }

            @Override
            public void onComplete() {
                if (!isViewAttached())
                    return;

            }
        }));
        disposables.add(business.getCustomersCloud().subscribeWith(new DisposableObserver<List<Customer>>() {
            @Override
            public void onNext(@NonNull List<Customer> customers) {
                if (!isViewAttached())
                    return;
                if (customers.isEmpty()){
                    getView().showError(TextUtils.getString(R.string.no_customers));
                }else {
                    getView().showCustomers(customers);

                }
                Log.d("TAG", "onNext() returned cloud: " + customers.size());

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (!isViewAttached())
                    return;
                getView().showError(e.getLocalizedMessage());
                getView().hideProgressBar();

            }

            @Override
            public void onComplete() {
                if (!isViewAttached())
                    return;

                getView().hideProgressBar();
            }
        }));


    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposables.dispose();
    }
}
