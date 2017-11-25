package com.quandoo.quandoo.model.cloud;

import com.quandoo.quandoo.model.cloud.dto.Customer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Eslam Hussein on 10/27/17.
 */

public interface ReservationsServices {

    @GET("customer-list.json")
    Call<List<Customer>> getCustomer();
    @GET("table-map.json")
    Call<List<Boolean>> getTableMap();
}
