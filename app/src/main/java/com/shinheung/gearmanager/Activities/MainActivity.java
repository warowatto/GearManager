package com.shinheung.gearmanager.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.shinheung.gearmanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stock(View view) {
        Intent intent = new Intent(this, StockListActivity.class);
        startActivity(intent);
    }

    public void calcurator(View view) {
        Intent intent = new Intent(this, CalcuratorEditActivity.class);
        startActivity(intent);
    }
}
