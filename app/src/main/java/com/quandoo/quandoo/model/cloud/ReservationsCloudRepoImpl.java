package com.quandoo.quandoo.model.cloud;


import com.quandoo.base.repo.cloud.BaseCloudRepo;
import com.quandoo.quandoo.model.cloud.dto.Customer;

import java.util.List;

/**
 * Created by Eslam Hussein on 10/27/17.
 */

public class ReservationsCloudRepoImpl extends BaseCloudRepo implements ReservationsCloudRepo {

    @Override
    public List<Customer> getCustomers() throws Exception {
        return execute(create(ReservationsServices.class).getCustomer());
    }

    @Override
    public List<Boolean> getTables() throws Exception {
        return execute(create(ReservationsServices.class).getTableMap());
    }
}
