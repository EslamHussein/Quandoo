package com.quandoo.quandoo.business;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.model.cloud.ReservationsCloudRepo;
import com.quandoo.quandoo.model.cloud.ReservationsCloudRepoImpl;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.ReservationDataBase;
import com.quandoo.quandoo.model.db.ReservationsDBRepo;
import com.quandoo.quandoo.model.db.ReservationsDBRepoImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by Eslam Hussein on 12/1/17.
 */
public class CustomerBusinessTest extends BaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private CustomerBusiness customerBusiness;

    ReservationsDBRepo reservationsDBRepo;
    ReservationsCloudRepo reservationsCloudRepo;

    @Before
    public void setUp() throws Exception {


        reservationsDBRepo =mock(ReservationsDBRepoImpl.class);
        reservationsCloudRepo =mock(ReservationsCloudRepoImpl.class);

        customerBusiness = spy(new CustomerBusiness(reservationsDBRepo, reservationsCloudRepo));
        initMocks(this);


    }

    @Test
    public void getCustomersDB() throws Exception {
        TestObserver<List<Customer>> customersTestObserver = new TestObserver<>();
        customerBusiness.getCustomersDB().subscribe(customersTestObserver);
        customersTestObserver.assertComplete();
        customersTestObserver.assertNoErrors();

    }

    @Test
    public void getCustomersCloud() throws Exception {

        TestObserver<List<Customer>> customersTestObserver = new TestObserver<>();
        customerBusiness.getCustomersCloud().subscribe(customersTestObserver);
        customersTestObserver.assertNoErrors();

    }


}