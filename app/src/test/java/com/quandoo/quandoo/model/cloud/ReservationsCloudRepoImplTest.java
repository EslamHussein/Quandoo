package com.quandoo.quandoo.model.cloud;

import com.quandoo.quandoo.BaseTest;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.spy;


/**
 * Created by Eslam Hussein on 11/30/17.
 */
public class ReservationsCloudRepoImplTest extends BaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    ReservationsCloudRepo cloudRepo;

    @Before
    public void setUp() throws Exception {

        cloudRepo = spy(new ReservationsCloudRepoImpl());
        initMocks(this);

    }

    @Test
    public void testGetAllCustomer() throws Exception {
        Assert.assertNotNull(cloudRepo.getCustomers());
    }

    @Test
    public void testGetAllTables() throws Exception {
        Assert.assertNotNull(cloudRepo.getTables());

    }

}