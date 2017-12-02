package com.quandoo.quandoo.ui.presenter;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.business.CustomerBusiness;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.ui.activity.MainActivity;
import com.quandoo.quandoo.ui.view.CustomerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Eslam Hussein on 12/1/17.
 */
public class CustomerPresenterImplTest extends BaseTest {

    CustomerBusiness business;
    CustomerView customerView;


    CustomerPresenter customerPresenter;

    List<Customer> customers = new ArrayList<Customer>();

    @Before
    public void setUp() throws Exception {

        business = Mockito.mock(CustomerBusiness.class);
        customerView = Mockito.mock(CustomerView.class);

        customers.add(new Customer(1, "Eslam", "Hussein"));

        customerPresenter = new CustomerPresenterImpl(business);
        customerPresenter.onAttach(customerView);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void viewInjected() {
        assertNotNull(customerView);
    }


    @Test
    public void businessInjected() {
        assertNotNull(business);
    }

    @Test
    public void getCustomer() throws Exception {
        customerPresenter.getCustomer();

        when(business.getCustomersCloud()).thenReturn(Observable.just(customers));
        verify(business).getCustomersCloud();
        verify(customerView).showCustomers(customers);

    }

}