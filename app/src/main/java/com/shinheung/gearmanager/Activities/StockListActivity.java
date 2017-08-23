package com.shinheung.gearmanager.Activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.common.primitives.Ints;
import com.shinheung.gearmanager.Adapter.StockListAdapter;
import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.MVP.StockListContract;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ActivityStockListBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class StockListActivity extends AppCompatActivity
        implements StockListContract.View, StockListContract.Presenter, SearchView.OnQueryTextListener {

    Realm realm;
    ActivityStockListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_list);
        init();
    }

    private void init() {
        realm = Realm.getDefaultInstance();
        StockListAdapter adapter = new StockListAdapter();
        adapter.setQuery(realm.where(Stock.class).findAllSorted("date", Sort.ASCENDING));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.searchView.setOnQueryTextListener(this);

        binding.setHandler(this);
    }

    @Override
    public void dataLoading(String message) {

    }

    @Override
    public void loadingError(String message) {

    }

    @Override
    public void loadStockList() {

    }

    @Override
    public void changeSort(StockListContract.ORDER sort) {

    }

    @Override
    public void addStock() {
        Intent intent = new Intent(this, StockAddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        StockListAdapter adapter = (StockListAdapter) binding.recyclerView.getAdapter();
        adapter.refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stock_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        List<Integer> options = Ints.asList(
                R.id.menu_sort_company_asc,
                R.id.menu_sort_company_asc,
                R.id.menu_sort_company_asc,
                R.id.menu_sort_company_asc,
                R.id.menu_sort_company_asc
        );

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String q) {
        findData(q);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        System.out.println(newText);
        findData(newText);
        return true;
    }

    public void findData(String q) {
        RealmResults<Stock> query = realm.where(Stock.class)
                .contains("companyName", q, Case.INSENSITIVE)
                .or()
                .contains("note", q, Case.INSENSITIVE)
                .findAll();

        ((StockListAdapter) binding.recyclerView.getAdapter()).setQuery(query);
    }
}
