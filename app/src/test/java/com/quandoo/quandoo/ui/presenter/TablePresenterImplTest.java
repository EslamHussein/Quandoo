package com.quandoo.quandoo.ui.presenter;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.business.CustomerBusiness;
import com.quandoo.quandoo.business.TableBusiness;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.ui.view.CustomerView;
import com.quandoo.quandoo.ui.view.TableView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by Eslam Hussein on 12/1/17.
 */
public class TablePresenterImplTest extends BaseTest {
    TableBusiness business;
    TableView tableView;
    TablePresenter tablePresenter;

    List<Table> tables = new ArrayList<Table>();


    @Before
    public void setUp() throws Exception {
        business = Mockito.mock(TableBusiness.class);
        tableView = Mockito.mock(TableView.class);

        MockitoAnnotations.initMocks(this);
        tables.add(new Table(1,false));

        tablePresenter = spy(new TablePresenterImpl(business));
        tablePresenter.onAttach(tableView);


    }

    @Test
    public void getTables() throws Exception {
        tablePresenter.getTables();
        verify(business).getTables();
        verify(tableView).showCustomers(tables);

    }

}