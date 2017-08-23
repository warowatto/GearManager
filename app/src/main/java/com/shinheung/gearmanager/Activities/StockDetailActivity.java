package com.shinheung.gearmanager.Activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ActivityStockDetailBinding;

import io.realm.Realm;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class StockDetailActivity extends AppCompatActivity {

    ActivityStockDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_detail);

        init();
    }

    private void init() {
        long id = getIntent().getLongExtra("id", 1);
        Stock stock = Realm.getDefaultInstance().where(Stock.class).equalTo("id", id).findFirst();

        binding.setStock(stock);
    }
}
