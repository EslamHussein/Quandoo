package com.quandoo.quandoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quandoo.quandoo.R;
import com.quandoo.quandoo.model.cloud.dto.Table;
import com.quandoo.utils.TextUtils;

import java.util.List;

/**
 * Created by Eslam Hussein on 11/24/17.
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder> {
    private List<Table> tableList;
    private TablesAdapterListener listener;

    public TableAdapter(List<Table> tableList, TablesAdapterListener listener) {
        this.tableList = tableList;
        this.listener = listener;
    }

    @Override
    public TableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_item_view, parent, false);

        return new TableHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TableHolder holder, int position) {

        final Table table = tableList.get(position);

        String tableNumber = String.format(TextUtils.getString(R.string.table_number), String.valueOf(table.getId()));
        holder.tableNumberTextView.setText(tableNumber);
        if (table.isAvailable()) {
            holder.content.setBackgroundColor((int) TextUtils.getColor(R.color.accent));

        } else {
            holder.content.setBackgroundColor((int) TextUtils.getColor(R.color.divider));

        }

    }

    @Override
    public int getItemCount() {
        return (tableList != null) ? tableList.size() : 0;
    }


    public class TableHolder extends RecyclerView.ViewHolder {

        TextView tableNumberTextView;
        RelativeLayout content;

        public TableHolder(View view) {
            super(view);
            tableNumberTextView = (TextView) view.findViewById(R.id.text_view_table_number);
            content = (RelativeLayout) view.findViewById(R.id.content);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onTableSelected(tableList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface TablesAdapterListener {
        void onTableSelected(Table customer);
    }

}
