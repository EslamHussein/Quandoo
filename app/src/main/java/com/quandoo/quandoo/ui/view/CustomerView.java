package com.quandoo.quandoo.ui.view;

import com.quandoo.base.view.MvpView;
import com.quandoo.quandoo.model.cloud.dto.Customer;

import java.util.List;

/**
 * Created by Eslam Hussein on 11/22/17.
 */

public interface CustomerView extends MvpView {


    void showProgressBar();

    void hideProgressBar();

    void showCustomers(List<Customer> customers);

    void showError(String errorMessage);
}
