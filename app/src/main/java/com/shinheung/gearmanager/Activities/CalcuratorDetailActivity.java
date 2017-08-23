package com.shinheung.gearmanager.Activities;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableMap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.common.collect.Maps;
import com.shinheung.gearmanager.Data.Gear;
import com.shinheung.gearmanager.MVP.GearCalDetailContract;
import com.shinheung.gearmanager.R;
import com.shinheung.gearmanager.Util.GearCalcurator.Calcurator;
import com.shinheung.gearmanager.Util.GearCalcurator.GearCalcurator;
import com.shinheung.gearmanager.databinding.ActivityCalDetailBinding;

import java.text.DecimalFormat;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by 신흥정밀 on 2017-08-21.
 */

public class CalcuratorDetailActivity extends AppCompatActivity
        implements GearCalDetailContract.View, GearCalDetailContract.Presenter {

    ActivityCalDetailBinding binding;

    Gear gear;
    GearCalcurator calcurator;

    ObservableMap<String, Integer> obParam;
    Map<Integer, Integer> menuOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cal_detail);
        init();
    }

    public void init() {
        menuOptions = Maps.newHashMap();
        menuOptions.put(R.id.menu_format1, 3);
        menuOptions.put(R.id.menu_format2, 4);
        menuOptions.put(R.id.menu_format3, 5);
        menuOptions.put(R.id.menu_format4, 6);
        menuOptions.put(R.id.menu_format5, 7);

        gear = (Gear) getIntent().getSerializableExtra("gear");
        calcurator = new Calcurator(gear);

        obParam = new ObservableArrayMap<>();
        obParam.put("format", 3);
        obParam.put("zm", (int) Math.ceil(calcurator.getZm()));

        binding.setGear(gear);
        binding.setHandler(this);
        binding.setOptions(obParam);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cal_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (menuOptions.containsKey(item.getItemId())) {
            obParam.put("format", menuOptions.get(item.getItemId()));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getGearName() {
        StringBuilder builder = new StringBuilder();

        builder.append(gear.getAddTeeth() > 0 ? "전위 " : "");
        builder.append(gear.isDP() ? "DP " : "M ");
        builder.append(gear.getToolSize());
        builder.append(" X ");
        builder.append(gear.getTeeth() + "T ");
        builder.append(gear.getTorisionAngle() > 0 ? "헨리컬" : "평");
        builder.append("기어");

        return builder.toString();
    }

    @Override
    public String getSM(int zm, int format) {
        double result = calcurator.getSm(zm);
        StringBuilder builder = new StringBuilder("0.");
        Observable.range(0, format).subscribe(i -> builder.append("0"));
        return new DecimalFormat(builder.toString()).format(result);
    }

    @Override
    public String getZM() {
        int result = (int) calcurator.getZm();
        return String.valueOf(result);
    }

    @Override
    public void changeZm(boolean sign) {
        int value = obParam.get("zm") + (sign ? 1 : -1);
        obParam.put("zm", value);
    }
}
