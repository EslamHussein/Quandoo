package com.quandoo.quandoo.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quandoo.base.view.BaseActivity;
import com.quandoo.quandoo.R;
import com.quandoo.quandoo.business.TableBusiness;
import com.quandoo.quandoo.model.cloud.ReservationsCloudRepoImpl;
import com.quandoo.quandoo.model.cloud.dto.Customer;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.quandoo.model.db.ReservationsDBRepoImpl;
import com.quandoo.quandoo.ui.adapter.TableAdapter;
import com.quandoo.quandoo.ui.presenter.TablePresenter;
import com.quandoo.quandoo.ui.presenter.TablePresenterImpl;
import com.quandoo.quandoo.ui.view.TableView;
import com.quandoo.utils.DialogUtils;
import com.quandoo.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends BaseActivity<TablePresenter> implements TableView, TableAdapter.TablesAdapterListener {
    public static final String ARG_CUSTOMER = "customer";

    private RecyclerView tableRecyclerView;
    private List<Table> tableList;
    private TableAdapter mAdapter;

    private LinearLayout loadingView;
    private TextView errorTextView;

    private ProgressDialog progressDialog;

    @Override
    protected TablePresenter createPresenter() {
        return new TablePresenterImpl(new TableBusiness(new ReservationsDBRepoImpl(),
                new ReservationsCloudRepoImpl()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        Customer customer = getIntent().getParcelableExtra(ARG_CUSTOMER);


        tableRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_tables);
        errorTextView = (TextView) findViewById(R.id.text_view_error);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(customer.getCustomerFirstName() + " " + customer.getCustomerLastName());


        loadingView = (LinearLayout) findViewById(R.id.loading_view);

        tableList = new ArrayList<>();

        mAdapter = new TableAdapter(tableList, this);

        tableRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        tableRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tableRecyclerView.setAdapter(mAdapter);
        getPresenter().getTables();

    }

    @Override
    public void onTableSelected(final Table table) {


        if (table.isAvailable()) {
            table.setAvailable(false);
            String dialogMsg = String.format(TextUtils.getString(R.string.are_you_sure_to_book_table_number), String.valueOf(table.getId()));

            Dialog bookTableDialog = DialogUtils.getOkDialog(this,
                    TextUtils.getString(R.string.book_table), dialogMsg, TextUtils.getString(R.string.confirm),
                    TextUtils.getString(R.string.cancel), new Runnable() {
                        @Override
                        public void run() {
                            getPresenter().bookTable(table);
                        }
                    });

            bookTableDialog.show();
        } else {

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    TextUtils.getString(R.string.this_table_already_booked), Snackbar.LENGTH_LONG);
            snackbar.show();
        }


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
    public void showProgressDialog() {

        if (progressDialog == null)
            progressDialog = DialogUtils.getProgressDialog(this,
                    TextUtils.getString(R.string.booking_table), false,
                    false);

        progressDialog.show();

    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();

    }

    @Override
    public void showCustomers(List<Table> tables) {
        tableList.clear();
        tableList.addAll(tables);
        mAdapter.notifyDataSetChanged();
        errorTextView.setVisibility(View.GONE);

    }

    @Override
    public void showError(String errorMessage) {

        if (tableList.isEmpty()) {
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
    public void showBookTableSuccess() {

        Toast.makeText(this, TextUtils.getString(R.string.book_table_success), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void clearView() {
        tableList.clear();
        showError(TextUtils.getString(R.string.no_Tables));
    }

    @Override
    public void showBookTableFailed(String errorMessage) {

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                errorMessage, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
