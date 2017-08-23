package com.shinheung.gearmanager.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shinheung.gearmanager.MVP.StockListContract;
import com.shinheung.gearmanager.R;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class StockListActivity extends AppCompatActivity
        implements StockListContract.View, StockListContract.Presenter {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_stock_list);
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

    }
}
