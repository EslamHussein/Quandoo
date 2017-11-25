package com.quandoo.quandoo.ui.presenter;


import com.quandoo.base.presenter.BasePresenter;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.ui.view.TableView;


public abstract class TablePresenter extends BasePresenter<TableView> {
    public abstract void getTables();

    public abstract void bookTable(Table table);
}
