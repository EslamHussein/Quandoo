package com.quandoo.quandoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.quandoo.quandoo.R;
import com.quandoo.quandoo.model.cloud.dto.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eslam Hussein on 11/24/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerHolder> implements Filterable {
    private List<Customer> customerList;
    private List<Customer> customerListFiltered;
    private CustomersAdapterListener listener;

    public CustomerAdapter(List<Customer> customerList, CustomersAdapterListener listener) {
        this.customerList = customerList;
        this.customerListFiltered = customerList;
        this.listener = listener;
    }

    @Override
    public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_item_view, parent, false);

        return new CustomerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerHolder holder, int position) {

        final Customer customer = customerListFiltered.get(position);
        holder.firstNameTextView.setText(customer.getCustomerFirstName());
        holder.lastNameTextView.setText(customer.getCustomerLastName());

    }

    @Override
    public int getItemCount() {
        return (customerListFiltered != null) ? customerListFiltered.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    customerListFiltered = customerList;
                } else {
                    List<Customer> filteredList = new ArrayList<>();
                    for (Customer row : customerList) {
                        if (row.getCustomerFirstName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCustomerFirstName().contains(charSequence) ||
                                row.getCustomerLastName().toLowerCase().contains(charString.toLowerCase())
                                || row.getCustomerLastName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    customerListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = customerListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                customerListFiltered = (ArrayList<Customer>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CustomerHolder extends RecyclerView.ViewHolder {

        TextView firstNameTextView, lastNameTextView;

        public CustomerHolder(View view) {
            super(view);
            firstNameTextView = (TextView) view.findViewById(R.id.text_view_first_name);
            lastNameTextView = (TextView) view.findViewById(R.id.text_view_last_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCustomerSelected(customerListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface CustomersAdapterListener {
        void onCustomerSelected(Customer customer);
    }

}
