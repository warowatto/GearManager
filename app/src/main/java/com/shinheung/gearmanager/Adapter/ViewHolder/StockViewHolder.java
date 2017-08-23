package com.shinheung.gearmanager.Adapter.ViewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.shinheung.gearmanager.Activities.StockDetailActivity;
import com.shinheung.gearmanager.Adapter.DefaultRealmRecyclerAdapter;
import com.shinheung.gearmanager.Adapter.StockListAdapter;
import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ListItemStockBinding;

import io.realm.Realm;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class StockViewHolder extends DefaultBindingViewHolder<ListItemStockBinding> implements StockListAdapter.Handler {
    public StockViewHolder(ListItemStockBinding itemView) {
        super(itemView);
    }

    @Override
    public void warpDetailPage(Stock stock) {
        Context context = itemView.getContext();
        Intent intent = new Intent(context, StockDetailActivity.class);
        intent.putExtra("id", stock.getId());

        context.startActivity(intent);
    }

    @Override
    public void deleteItem(DefaultRealmRecyclerAdapter adapter,  Stock stock) {
        Context context = itemView.getContext();
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_title_delete))
                .setMessage(context.getString(R.string.dialog_message_delete, stock.getNote()))
                .setPositiveButton(context.getString(R.string.dialog_button_delete), ((dialogInterface, i) ->
                        Realm.getDefaultInstance().executeTransaction(realm -> {
                            stock.deleteFromRealm();
                            adapter.refresh();
                        })))
                .setNegativeButton(context.getString(R.string.dialog_button_cancel), null)
                .show();
    }
}
