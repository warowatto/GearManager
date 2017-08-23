package com.shinheung.gearmanager.Activities;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import com.shinheung.gearmanager.Data.Stock;
import com.shinheung.gearmanager.MVP.StockAddContract;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.databinding.ActivityStockAddBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by 신흥정밀 on 2017-08-23.
 */

public class StockAddActivity extends AppCompatActivity
        implements StockAddContract.View, StockAddContract.Presenter, DatePickerDialog.OnDateSetListener {

    ActivityStockAddBinding binding;

    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_add);

        init();
    }

    private void init() {
        realm = Realm.getDefaultInstance();

        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        binding.txtDate.setText(dateNow);

        binding.setHandler(this);
    }

    @Override
    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        dialog.show();
    }

    @Override
    public void errorBuildObject(Exception e) {
        String message = getString(R.string.error_stock);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void selectDate() {
        showDatePickerDialog();
    }

    @Override
    public void create() {
        try {
            String companyName = binding.editCompany.getText().toString();
            String productName = binding.editProduct.getText().toString();
            int productCount = Integer.valueOf(binding.editCount.getText().toString());
            Date createAt = new SimpleDateFormat("yyyy-MM-dd").parse(binding.txtDate.getText().toString());

            realm.executeTransaction(r -> {
                Number primaryKey = realm.where(Stock.class).max("id");
                int id = primaryKey == null ? 1 : primaryKey.intValue() + 1;

                Stock stock = r.createObject(Stock.class, id);
                stock.setCompanyName(companyName);
                stock.setNote(productName);
                stock.setProductCount(productCount);
                stock.setDate(createAt);
            });

            this.finish();
        } catch (Exception e) {
            errorBuildObject(e);
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String dateString = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
        binding.txtDate.setText(dateString);
    }
}
