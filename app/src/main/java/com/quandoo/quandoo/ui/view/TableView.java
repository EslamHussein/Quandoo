package com.quandoo.quandoo.ui.view;

import com.quandoo.base.view.MvpView;
import com.quandoo.quandoo.model.cloud.dto.Table;

import java.util.List;

/**
 * Created by Eslam Hussein on 11/25/17.
 */

public interface TableView extends MvpView {

    void showProgressBar();

    void hideProgressBar();

    void showProgressDialog();

    void hideProgressDialog();

    void showCustomers(List<Table> tables);

    void showError(String errorMessage);

    void showBookTableSuccess();

    void clearView();

    void showBookTableFailed(String errorMessage);


}
