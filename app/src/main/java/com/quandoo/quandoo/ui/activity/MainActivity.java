package com.quandoo.quandoo.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quandoo.base.view.BaseActivity;
import com.quandoo.quandoo.R;
import com.quandoo.quandoo.business.CustomerBusiness;
import com.quandoo.quandoo.model.cloud.ReservationsCloudRepoImpl;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.db.ReservationsDBRepoImpl;
import com.quandoo.quandoo.ui.adapter.CustomerAdapter;
import com.quandoo.quandoo.ui.presenter.CustomerPresenter;
import com.quandoo.quandoo.ui.presenter.CustomerPresenterImpl;
import com.quandoo.quandoo.ui.view.CustomerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<CustomerPresenter> implements CustomerView, CustomerAdapter.CustomersAdapterListener {

    private LinearLayout loadingView;
    private TextView errorTextView;

    private RecyclerView customerRecyclerView;
    private List<Customer> customerList;
    private CustomerAdapter mAdapter;
    private SearchView searchView;


    @Override
    protected CustomerPresenter createPresenter() {
        return new CustomerPresenterImpl(new CustomerBusiness(new ReservationsDBRepoImpl(), new ReservationsCloudRepoImpl()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTextView = (TextView) findViewById(R.id.text_view_error);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);


        loadingView = (LinearLayout) findViewById(R.id.loading_view);
        customerRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_customer);

        customerList = new ArrayList<>();
        mAdapter = new CustomerAdapter(customerList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        customerRecyclerView.setLayoutManager(mLayoutManager);
        customerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        customerRecyclerView.setAdapter(mAdapter);
        getPresenter().getCustomer();
    }

    @Override
    public void showProgressBar() {
        loadingView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        loadingView.setVisibility(View.GONE);

    }

    @Override
    public void showCustomers(List<Customer> customers) {


        customerList.clear();
        customerList.addAll(customers);
        errorTextView.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {
        if (customerList.isEmpty()) {
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText(errorMessage);

        } else {
            errorTextView.setVisibility(View.GONE);

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    errorMessage, Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }


    @Override
    public void onCustomerSelected(Customer customer) {

        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(TableActivity.ARG_CUSTOMER, customer);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
