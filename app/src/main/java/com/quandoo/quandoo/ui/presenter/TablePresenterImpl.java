package com.quandoo.quandoo.ui.presenter;

import com.quandoo.base.dto.RefreshTables;
import com.quandoo.quandoo.R;
import com.quandoo.quandoo.business.TableBusiness;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.ui.view.TableView;
import com.quandoo.utils.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TablePresenterImpl extends TablePresenter {
    CompositeDisposable disposables;
    TableBusiness business;

    public TablePresenterImpl(TableBusiness business) {
        this.business = business;
    }

    @Override
    public void onAttach(TableView view) {
        super.onAttach(view);
        EventBus.getDefault().register(this);

        disposables = new CompositeDisposable();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshTables event) {
        if (!isViewAttached())
            return;
        getView().clearView();
        getTables();
    }


    @Override
    public void getTables() {

        if (!isViewAttached())
            return;
        getView().showProgressBar();

        disposables.add(business.getTables().observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).subscribeWith(new DisposableObserver<List<Table>>() {
            @Override
            public void onNext(@NonNull List<Table> tables) {
                if (!isViewAttached())
                    return;

                if (tables.isEmpty()) {
                    getView().showError(TextUtils.getString(R.string.no_Tables));
                } else {
                    getView().showCustomers(tables);

                }

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
    public void bookTable(Table table) {

        if (!isViewAttached())
            return;
        getView().showProgressDialog();

        disposables.add(business.bookTable(table).subscribeWith(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                if (!isViewAttached())
                    return;
                if (aBoolean)
                    getView().showBookTableSuccess();
                else
                    getView().showBookTableFailed(TextUtils.getString(R.string.book_table_failed));
                getView().hideProgressDialog();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (!isViewAttached())
                    return;
                getView().showBookTableFailed(e.getLocalizedMessage());
                getView().hideProgressDialog();

            }
        }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposables.dispose();
        EventBus.getDefault().unregister(this);

    }
}
