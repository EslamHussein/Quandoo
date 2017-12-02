package com.quandoo.quandoo.business;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.model.cloud.ReservationsCloudRepo;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.ReservationsDBRepo;

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
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by Eslam Hussein on 12/1/17.
 */
public class TableBusinessTest extends BaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private TableBusiness tableBusiness;

    @Mock
    ReservationsDBRepo reservationsDBRepo;
    @Mock
    ReservationsCloudRepo reservationsCloudRepo;

    @Before
    public void setUp() throws Exception {

        tableBusiness = spy(new TableBusiness(reservationsDBRepo, reservationsCloudRepo));
        initMocks(this);

    }

    @Test(expected = AssertionError.class)
    public void getTablesWithException() throws Exception {
        TestObserver<List<Table>> tablesTestObserver = new TestObserver<>();
        tableBusiness.getTables().subscribe(tablesTestObserver);
        tablesTestObserver.assertComplete();
        tablesTestObserver.assertError(new Exception());

    }

    @Test
    public void getTables() throws Exception {
        TestObserver<List<Table>> tablesTestObserver = new TestObserver<>();
        tableBusiness.getTables().subscribe(tablesTestObserver);
        tablesTestObserver.assertComplete();
        tablesTestObserver.assertNoErrors();

    }

    @Test
    public void bookTable() throws Exception {

        Table tableToBook = new Table(1, true);
        assertNotNull(tableToBook);
        assertNotNull(tableBusiness);


        TestObserver<Boolean> bookTableTestObserver = new TestObserver<>();
        tableBusiness.bookTable(tableToBook).subscribe(bookTableTestObserver);
        bookTableTestObserver.assertComplete();
        bookTableTestObserver.assertNoErrors();


    }

    @After
    public void endTestCases() {
        RxAndroidPlugins.reset();

    }

}