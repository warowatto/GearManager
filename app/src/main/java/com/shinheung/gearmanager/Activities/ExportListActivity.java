package com.shinheung.gearmanager.Activities;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.SearchView;

import com.shinheung.gearmanager.Adapter.ExportListAdapter;
import com.shinheung.gearmanager.Data.Export;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ActivityExportListBinding;

import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class ExportListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ActivityExportListBinding binding;
    Realm realm;
    ObservableBoolean isEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_export_list);

        init();
    }

    private void init() {
        realm = Realm.getDefaultInstance();
        ExportListAdapter adapter = new ExportListAdapter(realm.where(Export.class).findAllSorted("id", Sort.DESCENDING));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        binding.searchView.setOnQueryTextListener(this);

        binding.setIsEmpty(isEmpty);

    }

    private void listRefresh() {
        ExportListAdapter adapter = (ExportListAdapter) binding.recyclerView.getAdapter();
        if (adapter != null) {
            adapter.refresh();
            boolean empty = adapter.getItemCount() == 0;
            isEmpty.set(empty);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listRefresh();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return true;
    }
}
