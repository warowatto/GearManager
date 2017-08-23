package com.shinheung.gearmanager.Adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shinheung.gearmanager.Adapter.ViewHolder.ExportViewHolder;
import com.shinheung.gearmanager.Data.Export;
import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ListItemExportBinding;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class ExportListAdapter extends DefaultRealmRecyclerAdapter<Export, ExportViewHolder> {

    Realm realm;

    public interface Handler {
        void wrapDetailPage(int stockId);

        void deleteExport(ExportListAdapter adapter, Export export);
    }

    public ExportListAdapter() {
        this(null);
    }

    public ExportListAdapter(RealmResults<Export> results) {
        super(results);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ExportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemExportBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_export,
                parent,
                false
        );

        return new ExportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ExportViewHolder holder, int position) {

        Export export = getItem(position);
        Stock stock = realm.where(Stock.class).equalTo("id", export.getStockId()).findFirst();

        ListItemExportBinding binding = holder.getBinding();
        binding.setExport(export);
        binding.setStock(stock);
        binding.setAdapter(this);
        binding.setHandler(holder);

        binding.executePendingBindings();
    }
}
