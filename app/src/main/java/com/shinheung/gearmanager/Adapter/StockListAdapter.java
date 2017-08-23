package com.shinheung.gearmanager.Adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shinheung.gearmanager.Adapter.ViewHolder.StockViewHolder;
import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ListItemStockBinding;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class StockListAdapter extends DefaultRealmRecyclerAdapter<Stock, StockViewHolder> {

    public interface Handler {
        void warpDetailPage(Stock stock);

        void deleteItem(DefaultRealmRecyclerAdapter adapter,  Stock stock);
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemStockBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_stock,
                parent,
                false
        );

        return new StockViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        Stock item = getItem(position);

        Handler handler = holder;

        holder.getBinding().setStock(item);
        holder.getBinding().setAdapter(this);
        holder.getBinding().setHandler(handler);
        holder.getBinding().executePendingBindings();
    }
}
