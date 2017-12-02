package com.quandoo.quandoo.model.cloud;

import com.quandoo.quandoo.model.cloud.dto.Customer;

import java.util.List;

/**
 * Created by Eslam Hussein on 11/22/17.
 */

public interface ReservationsCloudRepo {
    List<Customer> getCustomers() throws Exception;

    List<Boolean> getTables() throws Exception;
}
